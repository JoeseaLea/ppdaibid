package com.ppdaibid.thread;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ppdai.open.core.Result;
import com.ppdaibid.AutoBidManager;
import com.ppdaibid.DebtManager;
import com.ppdaibid.info.DebtInfo;
import com.ppdaibid.utils.BidUtil;
import com.ppdaibid.utils.DebtUtil;

public class BatchDebtInfosThread extends Thread {
	
	private boolean isAlive = false;
	
	private static final Logger logger = Logger.getLogger(BatchDebtInfosThread.class);
	
	private List<Integer> debtIds = null;
	private Map<Integer, DebtInfo> debtInfosMap = null;
	
	public void init(List<Integer> debtIds, Map<Integer, DebtInfo> debtInfosMap){
		this.isAlive = true;
		
		this.debtIds = debtIds;
		this.debtInfosMap = debtInfosMap;
	}
	
	@Override
	public void run() {
		Result result = null;
		List<Integer> listingIds = new ArrayList<Integer>();
		
		if (null == debtIds || 0 >= debtIds.size() || null == debtInfosMap || 0 >= debtInfosMap.size()) {
			this.isAlive = false;
			return;
		}
		
		Date batchTime = Calendar.getInstance().getTime();
		
		result = DebtUtil.batchDebtInfos(debtIds);
		
		String context = result.getContext();
		if (context.contains("您的操作太频繁")) {
			logger.error("batchDebtInfos请求太频繁，请求结果为：" + context);
			DebtManager.debtListNeedWait = true;
			
			this.isAlive = false;
			return;
		}
		
		if (!result.isSucess()) {
			logger.error("获取batchDebtInfos结果异常：" + context);
			
			this.isAlive = false;
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
			
			this.isAlive = false;
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
			
//			debtInfo.setInsertTime(batchTime);
//			debtInfo.setLastupdateTime(batchTime);
			listingIds.add(debtInfo.getListingId());
			debtInfosMap.put(debtId, debtInfo);
		}
		
		result = BidUtil.batchListingInfos(listingIds);
		context = result.getContext();
		if (context.contains("您的操作太频繁")) {
			logger.error("LoanInfo请求太频繁，请求结果为：" + context);
			DebtManager.debtListNeedWait = true;
			AutoBidManager.loanListNeedWait = true;
			
			this.isAlive = false;
			return;
		}
		
		if (!result.isSucess()) {
			logger.error("获取BatchListingBidInfos结果异常：" + result.getContext());
			
			this.isAlive = false;
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
			
			this.isAlive = false;
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
			
			for (BuyDebtThread b : DebtManager.buyDebtThreads) {
				if (!b.getStatus()) {
					buyDebtThread = b;
					break;
				}
			}
			
			if (null == buyDebtThread) {
				buyDebtThread = new BuyDebtThread();
			}
			buyDebtThread.init(debtInfo);
			DebtManager.executorService.execute(buyDebtThread);
		}
			
		this.debtIds = null;
		this.debtInfosMap = null;
		
		this.isAlive = false;
	}
	
	public boolean getStatus() {
		return isAlive;
	}
}
