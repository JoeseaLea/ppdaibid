package com.ppdaibid.thread;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ppdai.open.core.Result;
import com.ppdaibid.AutoBidManager;
import com.ppdaibid.dao.BidDao;
import com.ppdaibid.dao.impl.BidDaoImpl;
import com.ppdaibid.info.LoanInfo;
import com.ppdaibid.utils.BidUtil;
import com.ppdaibid.utils.PropertiesUtil;

/**
 * 向拍拍贷服务器发送获取LoanList请求线程，默认请求第一也数据
 * @author joesealea
 */
public class LoanListThread implements Runnable {
	
	private static final Logger logger = Logger.getLogger(LoanListThread.class);
	
	private static Map<Integer, Date> ignoreIdsMap = new ConcurrentHashMap<Integer, Date>();

	//页码
	private static int pageIndex = 1;
	//一次batchListingInfos请求最大允许的ListingIds数量
	private static int batchListingInfosSize = 10;
	private static int pageListFirstSize = 50;
	private static int validTime = -12;
	
	private BidDao bidDao = null;
	
	public LoanListThread() {
		this(1);
	}

	public LoanListThread(int pageIndex_) {
		pageIndex = pageIndex_;
		
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		BidDaoImpl bidDaoImpl = context.getBean("bidDao", BidDaoImpl.class);
		bidDao = bidDaoImpl;
		
		try {
			validTime = 0 - Integer.parseInt(PropertiesUtil.getProperty("validTime", "12"));
		} catch (Exception e) {
			logger.error("The second time that's used to get valid data of LoanList request(second) configurate error", e);
			validTime = -12;
		}
		
		try {
			//从配置文件中读取一次batchListingInfos请求最大允许的ListingIds数量，如果未配置或者配置错误，则使用默认值
			batchListingInfosSize = Integer.parseInt(PropertiesUtil.getProperty("batchListingInfosSize", "10"));
		} catch (Exception e) {
			logger.error("ListingIds parameter's size of one batchListingInfos request configurate error", e);
			batchListingInfosSize = 10;
		}
		
		try {
			//每页前pageListFirstSize条数据作为有效数据
			pageListFirstSize = Integer.parseInt(PropertiesUtil.getProperty("pageListFirstSize", "50"));
		} catch (Exception e) {
			logger.error("pageListFirstSize configurate error", e);
			pageListFirstSize = 10;
		}
	}

	@Override
	public void run() {

		List<Integer> listIds;
		Map<Integer, LoanInfo> loanInfosMap;

		listIds = new ArrayList<Integer>();
		loanInfosMap = new ConcurrentHashMap<Integer, LoanInfo>();

		Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND, validTime);

		List<Integer> ignoreIds = bidDao.getCanBeIgnoreIds();

		Result result = BidUtil.loanList(pageIndex, c.getTime());

		String context = result.getContext();
		if (context.contains("您的操作太频繁")) {
			logger.error("LoanInfo请求太频繁，请求结果为：" + context);
			AutoBidManager.loanListNeedWait = true;
			return;
		}
		
		if (!result.isSucess()) {
			logger.error("获取loanList结果异常：" + result.getContext());
			return;
		}

		logger.debug("loanList结果为：" + context);

		JSONObject jsoncontext = new JSONObject(context);
		JSONArray jsonLoanInfos = null;
		try {
			jsonLoanInfos = jsoncontext.getJSONArray("LoanInfos");
		} catch (Exception e) {
			logger.error("LoanList结果JSON解析错误：", e);
			logger.error("JSON解析错误报文为：" + context);
			return;
		}
		
		int length = jsonLoanInfos.length();

		for (int i = 0; i < length  && i < pageListFirstSize; i++) {
			JSONObject jsonloanInfo = (JSONObject) jsonLoanInfos.get(i);
			int listingId = jsonloanInfo.getInt("ListingId");
			LoanInfo loanInfo = new LoanInfo();
			loanInfo.setLoanList(jsonloanInfo);

			listIds.add(listingId);
			loanInfosMap.put(listingId, loanInfo);
		}

		listIds.removeAll(ignoreIds);
		listIds.removeAll(ignoreIdsMap.keySet());
		
		final List<Integer> fListIds = listIds;
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Date date = Calendar.getInstance().getTime();
				for (Integer listingId : fListIds) {
					ignoreIdsMap.put(listingId, date);
				}

			}
		});
		AutoBidManager.executorService.execute(thread);
		
		length = listIds.size();
		
		List<Integer> listIdsParam = new ArrayList<Integer>();
		Map<Integer, LoanInfo> loanInfosMapParam = new HashMap<Integer, LoanInfo>();
		int listingId;
		
		for(int i = 0; i < length; i ++) {
			listingId = listIds.get(i);
			listIdsParam.add(listingId);
			loanInfosMapParam.put(listingId, loanInfosMap.get(listingId));
			
			if ((batchListingInfosSize <= listIdsParam.size() || i >= length - 1) && 0 < listIdsParam.size()) {
				BatchListingInfosThread batchListingInfosThread = null;
				
				for (BatchListingInfosThread b : AutoBidManager.batchListingInfosThreads) {
					if (!b.getStatus()) {
						batchListingInfosThread = b;
						break;
					}
				}
				
				if (null == batchListingInfosThread) {
					return;
				}
				
				batchListingInfosThread.init(listIdsParam, loanInfosMapParam);
				AutoBidManager.executorService.execute(batchListingInfosThread);
				
				listIdsParam = new ArrayList<Integer>();
				loanInfosMapParam = new HashMap<Integer, LoanInfo>();
			}
		}
	}

	private static void removeInvalidIgnoreIds() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) { }
					
					if (null == ignoreIdsMap || 0 >= ignoreIdsMap.size()) {
						continue;
					}
					
					Set<Integer> keys = ignoreIdsMap.keySet();
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.SECOND, -10);
					long currentTime = calendar.getTime().getTime();
					for (Integer key : keys) {
						if (ignoreIdsMap.get(key).getTime() < currentTime) {
							ignoreIdsMap.remove(key);
						}
					}
				}
			}
		});
		
		AutoBidManager.executorService.execute(thread);
	}
	
	static {
		removeInvalidIgnoreIds();
	}
}
