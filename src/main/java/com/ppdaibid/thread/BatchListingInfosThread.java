package com.ppdaibid.thread;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ppdai.open.core.Result;
import com.ppdaibid.info.LoanInfo;
import com.ppdaibid.utils.BidUtil;

public class BatchListingInfosThread implements Runnable {
	
	private static Logger logger = Logger.getLogger(BatchListingInfosThread.class);
	
	private static LinkedBlockingQueue<Date> blockingQueue = new LinkedBlockingQueue<Date>();

	private List<Integer> listIds;
	private Map<Integer, LoanInfo>loanInfosMap;
	
//	private PPDdao ppDdao = null;
	
	public BatchListingInfosThread(List<Integer> listIds, Map<Integer, LoanInfo>loanInfosMap) {
		this.listIds = listIds;
		this.loanInfosMap = loanInfosMap;
//		this.ppDdao = ppDdao;
	}
	
	@Override
	public void run() {
		Result result = null;
		List<LoanInfo> loanInfos = new ArrayList<LoanInfo>();
		
		Date batchTime = null;
		
		if (null == listIds || 0 >= listIds.size()) {
			return;
		}
		//TODO 一次最多能查询的个数
		int maxBatchLength = 10;
		int listIdsLength = listIds.size();
		
		for (int i = 0; i < listIdsLength; i = i + maxBatchLength) {
			if (blockingQueue.size() > 500) {
				return;
			}
			
			if (i + maxBatchLength >= listIdsLength) {
				result = BidUtil.batchListingInfos(listIds.subList(i, listIdsLength));
			} else {
				result = BidUtil.batchListingInfos(listIds.subList(i, i + listIdsLength));
			}
			try {
				blockingQueue.put(Calendar.getInstance().getTime());
			} catch (InterruptedException e1) { }
			
			if (!result.isSucess()) {
				continue;
			}
			logger.debug("batchListingBidInfos结果为：" + result.getContext());
			
			batchTime = Calendar.getInstance().getTime();
			
			JSONObject context = new JSONObject(result.getContext());
			JSONArray jsonLoanInfos = null;
			try {
				jsonLoanInfos = context.getJSONArray("LoanInfos");
			} catch (Exception e) {
				logger.debug(context.toString());
				logger.error("batchListingBidInfos结果JSON解析错误：", e);
				continue;
			}
			
			if (0 == jsonLoanInfos.length()) {
				continue;
			}
			
			for (int j = 0; j < jsonLoanInfos.length(); j++) {
				JSONObject loanInfoJson = (JSONObject)jsonLoanInfos.get(j);
				int listingId = loanInfoJson.getInt("ListingId");
				LoanInfo loanInfo = loanInfosMap.get(listingId);
				loanInfo.setListingInfo(loanInfoJson);
				
				loanInfo.setInsertTime(batchTime);
				loanInfo.setLastupdateTime(batchTime);
				loanInfo.setBid(false);
				
//				loanInfos.add(loanInfo);
				new Thread(new BiddingThread(loanInfo)).start();
			}
			
//			ppDdao.addLoanInfos(loanInfos);
			loanInfos.clear();
		}
		
		this.listIds.clear();
		this.loanInfosMap.clear();
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
		checkValidReqTime();
	}
}
