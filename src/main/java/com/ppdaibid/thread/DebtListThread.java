package com.ppdaibid.thread;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.ppdaibid.DebtManager;
import com.ppdaibid.dao.DebtDao;
import com.ppdaibid.dao.impl.DebtDaoImpl;
import com.ppdaibid.info.DebtInfo;
import com.ppdaibid.utils.DebtUtil;
import com.ppdaibid.utils.PropertiesUtil;

public class DebtListThread extends Thread {
	private static final Logger logger = Logger.getLogger(DebtListThread.class);
	
	private DebtDao debtDao = null;
	
	private static Map<Integer, Date> ignoreIdsMap = new ConcurrentHashMap<Integer, Date>();
	
	//页码
	private static int pageIndex = 1;
	//一次batchListingInfos请求最大允许的ListingIds数量
	private static int batchDebtInfosSize = 10;
	private static int pageListFirstSize = 50;
	private static int validTime = -12;
	
	public DebtListThread() {
		this(1);
	}

	public DebtListThread(int pageIndex_) {
		pageIndex = pageIndex_;
		
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		DebtDaoImpl debtDaoImpl = context.getBean("debtDao", DebtDaoImpl.class);
		this.debtDao = debtDaoImpl;
		
		try {
			validTime = 0 - Integer.parseInt(PropertiesUtil.getProperty("validTime", "12"));
		} catch (Exception e) {
			logger.error("The second time that's used to get valid data of LoanList request(second) configurate error", e);
			validTime = -12;
		}
		
		try {
			//从配置文件中读取一次batchDebtInfos请求最大允许的DebtIds数量，如果未配置或者配置错误，则使用默认值
			batchDebtInfosSize = Integer.parseInt(PropertiesUtil.getProperty("batchDebtInfosSize", "10"));
		} catch (Exception e) {
			logger.error("batchDebtInfosSize configurate error", e);
			batchDebtInfosSize = 10;
		}
		
		try {
			//每页前pageListFirstSize条数据作为有效数据
			pageListFirstSize = Integer.parseInt(PropertiesUtil.getProperty("pageListFirstSize", "50"));
		} catch (Exception e) {
			logger.error("ListingIds parameter's size of one batchListingInfos request configurate error", e);
			pageListFirstSize = 50;
		}
	}

	@Override
	public void run() {
		List<Integer> debtIds = new ArrayList<Integer>();
		Map<Integer, DebtInfo> debtInfosMap = new ConcurrentHashMap<Integer, DebtInfo>();
		
		List<Integer> ignoreIds = debtDao.getCanBeIgnoreIds();
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND, validTime);
		
		Result result = DebtUtil.debtListNew(pageIndex, c.getTime(), "A,B,C,D,E,F,G");
		
		String context = result.getContext();
		if (context.contains("您的操作太频繁")) {
			logger.error("DebtList请求太频繁，请求结果为：" + context);
			DebtManager.debtListNeedWait = true;
			return;
		}
		
		if (null == result || !result.isSucess()) {
			logger.error("获取debtList结果异常：" + result.getContext());
			return;
		}
		
		logger.debug("debtList结果为：" + context);
		
		JSONObject jsoncontext = new JSONObject(context);
		JSONArray jsonDebtInfos = null;
		try {
			jsonDebtInfos = jsoncontext.getJSONArray("DebtInfos");
		} catch (Exception e) {
			logger.error("LoanList结果JSON解析错误：", e);
			logger.error("JSON解析错误报文为：" + context);
			return;
		}
		
		int length = jsonDebtInfos.length();
		
		for (int i = 0; i < length  && i < pageListFirstSize; i++) {
			JSONObject jsonDebtInfo = (JSONObject) jsonDebtInfos.get(i);
			int debtdealId = jsonDebtInfo.getInt("DebtdealId");
			DebtInfo debtInfo = new DebtInfo();
			debtInfo.setDebtList(jsonDebtInfo);

			debtIds.add(debtdealId);
			debtInfosMap.put(debtdealId, debtInfo);
		}
		
		debtIds.removeAll(ignoreIds);
		debtIds.removeAll(ignoreIdsMap.keySet());
		
		final List<Integer> fdebtIds = debtIds;
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Date date = Calendar.getInstance().getTime();
				for (Integer debtId : fdebtIds) {
					ignoreIdsMap.put(debtId, date);
				}

			}
		});
		DebtManager.executorService.execute(thread);
		
		length = debtIds.size();
		
		List<Integer> debtIdsParam = new ArrayList<Integer>();
		Map<Integer, DebtInfo> debtInfosMapParam = new ConcurrentHashMap<Integer, DebtInfo>();
		int debtId;
		
		for(int i = 0; i < length; i ++) {
			debtId = debtIds.get(i);
			debtIdsParam.add(debtId);
			debtInfosMapParam.put(debtId, debtInfosMap.get(debtId));
			
			if ((batchDebtInfosSize <= debtIdsParam.size() || i >= length - 1) && 0 < debtIdsParam.size()) {
				BatchDebtInfosThread batchDebtInfosThread = null;
				
				for (BatchDebtInfosThread b : DebtManager.batchDebtInfosThreads) {
					if (!b.getStatus()) {
						batchDebtInfosThread = b;
						break;
					}
				}
				
				// 没有空闲线程，返回等待空闲线程
				if (null == batchDebtInfosThread) {
					return;
				}
				
				batchDebtInfosThread.init(debtIdsParam, debtInfosMapParam);
				
				DebtManager.executorService.execute(batchDebtInfosThread);
				debtIdsParam = new ArrayList<Integer>();
				debtInfosMapParam = new ConcurrentHashMap<Integer, DebtInfo>();
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
					calendar.add(Calendar.SECOND, -30);
					long currentTime = calendar.getTime().getTime();
					for (Integer key : keys) {
						if (ignoreIdsMap.get(key).getTime() < currentTime) {
							ignoreIdsMap.remove(key);
						}
					}
				}
			}
		});
		
		DebtManager.executorService.execute(thread);
	}
	
	static {
		removeInvalidIgnoreIds();
	}
}
