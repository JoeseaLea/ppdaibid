package com.ppdaibid.thread;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ppdai.open.core.Result;
import com.ppdaibid.AutoBidManager;
import com.ppdaibid.DebtManager;
import com.ppdaibid.info.DebtInfo;
import com.ppdaibid.utils.BidUtil;
import com.ppdaibid.utils.DebtUtil;
import com.ppdaibid.utils.PropertiesUtil;

public class BatchDebtInfosThread extends Thread {
	
	private static final Logger logger = Logger.getLogger(BatchDebtInfosThread.class);
	private static final ExecutorService executorService = Executors.newCachedThreadPool();
	
	private static LinkedBlockingQueue<Date> blockingQueue = new LinkedBlockingQueue<Date>();
	private static int threadLength = 50;
	private static BuyDebtThread[] buyDebtThreads = new BuyDebtThread[threadLength];

	private List<Integer> debtIds = null;
	private Map<Integer, DebtInfo> debtInfosMap = null;
	
	//The count of batchListingInfos request can be request in one minute
	private static int debtInfosCount = 400;
	
	public BatchDebtInfosThread(){
		for (int i = 0; i < threadLength; i++) {
			buyDebtThreads[i] = new BuyDebtThread();
		}
	}
	
	public BatchDebtInfosThread(List<Integer> debtIds, Map<Integer, DebtInfo> debtInfosMap) {
		this.debtIds = debtIds;
		this.debtInfosMap = debtInfosMap;
	}
	
	public void init(List<Integer> debtIds, Map<Integer, DebtInfo> debtInfosMap){
		this.debtIds = debtIds;
		this.debtInfosMap = debtInfosMap;
	}
	
	@Override
	public void run() {
		Result result = null;
		List<Integer> listingIds = new ArrayList<Integer>();
		
		if (null == debtIds || 0 >= debtIds.size() || blockingQueue.size() > debtInfosCount || null == debtInfosMap || 0 >= debtInfosMap.size()) {
			return;
		}
		
		Date batchTime = Calendar.getInstance().getTime();
		try {
			blockingQueue.put(batchTime);
		} catch (InterruptedException e) { }
		
		result = DebtUtil.batchDebtInfos(debtIds);
		
		String context = result.getContext();
		if (context.contains("您的操作太频繁")) {
			logger.error("batchDebtInfos请求太频繁，请求结果为：" + context);
			DebtManager.debtListNeedWait = true;
			return;
		}
		
		if (!result.isSucess()) {
			logger.error("获取batchDebtInfos结果异常：" + context);
			return;
		}
		logger.debug("batchDebtInfos结果为：" + context);
			
		JSONObject jsoncontext = new JSONObject(context);
		JSONArray jsonDebtInfos = null;
		try {
			jsonDebtInfos = jsoncontext.getJSONArray("DebtInfos");
		} catch (Exception e) {
			logger.error("batchDebtInfos结果JSON解析错误：", e);
			logger.error("JSON解析错误报文为：" + context);
			return;
		}
		
		for (int i = 0; i < jsonDebtInfos.length(); i++) {
			JSONObject jsonDebtInfo = (JSONObject)jsonDebtInfos.get(i);
			int debtId = jsonDebtInfo.getInt("DebtId");
			DebtInfo debtInfo = debtInfosMap.get(debtId);
			if (null == debtInfo) {
				logger.error("查询结果错误");
				logger.info(debtId);
				logger.info(debtInfosMap.keySet());
				continue;
			}
			debtInfo.setDebtInfo(jsonDebtInfo);
			
			debtInfo.setInsertTime(batchTime);
			debtInfo.setLastupdateTime(batchTime);
			listingIds.add(debtInfo.getListingId());
			debtInfosMap.put(debtId, debtInfo);
		}
		
		result = BidUtil.batchListingInfos(listingIds);
		context = result.getContext();
		if (context.contains("您的操作太频繁")) {
			logger.error("LoanInfo请求太频繁，请求结果为：" + context);
			AutoBidManager.loanListNeedWait = true;
			return;
		}
		
		if (!result.isSucess()) {
			logger.error("获取BatchListingBidInfos结果异常：" + result.getContext());
			return;
		}
		
		logger.debug("batchListingBidInfos结果为：" + result.getContext());
		
		jsoncontext = new JSONObject(context);
		JSONArray jsonLoanInfos = null;
		try {
			jsonLoanInfos = jsoncontext.getJSONArray("LoanInfos");
		} catch (Exception e) {
			logger.error("batchListingBidInfos结果JSON解析错误：", e);
			logger.error("JSON解析错误报文为：" + context);
			return;
		}
		
		for (int i = 0; i < jsonLoanInfos.length(); i++) {
			JSONObject jsonloanInfo = (JSONObject)jsonLoanInfos.get(i);
			int listingId = jsonloanInfo.getInt("ListingId");
			
			DebtInfo debtInfo = null;
			for (Integer key : debtInfosMap.keySet()) {
				if (debtInfosMap.get(key).getListingId() == listingId) {
					debtInfo = debtInfosMap.get(key);
					break;
				}
			}
			if (null == debtInfo) {
				continue;
			}
			
			debtInfo.setListingInfo(jsonloanInfo);
			
			debtInfo.setInsertTime(batchTime);
			debtInfo.setLastupdateTime(batchTime);
			
			BuyDebtThread buyDebtThread = null;
			for (int j = 0; j < threadLength; j++) {
				if (!buyDebtThreads[j].isAlive()) {
					buyDebtThread = buyDebtThreads[j];
					break;
				}
			}
			if (null != buyDebtThread) {
				buyDebtThread.init(debtInfo);
				executorService.execute(buyDebtThread);
			}
		}
			
		this.debtIds = null;
		this.debtInfosMap = null;
	}

	private static void checkValidReqTime() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					Calendar c = Calendar.getInstance();
					c.add(Calendar.SECOND, -60);
					Date nowTime = c.getTime();
					Date firstTime = blockingQueue.peek();
					
					if (null == firstTime) {
						continue;
					}
					
					if (nowTime.getTime() > firstTime.getTime()) {
						try {
							blockingQueue.take();
							TimeUnit.MILLISECONDS.sleep(1);
						} catch (InterruptedException e) { }
					}
				}				
			}
		}).start();
	}
	
	static {
		try {
			debtInfosCount = Integer.parseInt(PropertiesUtil.getProperty("loanInfosCount", "500"));
		} catch (Exception e) {
			logger.error("The count of batchListingInfos request can be request in one minute configurate error", e);
			debtInfosCount = 500;
		}
		checkValidReqTime();
	}
}
