package com.ppdaibid.thread;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ppdai.open.core.Result;
import com.ppdaibid.AutoBidManager;
import com.ppdaibid.info.LoanInfo;
import com.ppdaibid.utils.BidUtil;

public class BatchListingInfosThread implements Runnable {

	private static final Logger logger = Logger.getLogger(BatchListingInfosThread.class);

	private boolean isAlive = false;

	private List<Integer> listIds;
	private Map<Integer, LoanInfo> loanInfosMap;

	public void init(List<Integer> listIds, Map<Integer, LoanInfo> loanInfosMap) {
		this.isAlive = true;

		this.listIds = listIds;
		this.loanInfosMap = loanInfosMap;
	}

	@Override
	public void run() {
		try {
			Result result = null;

			if (null == listIds || 0 >= listIds.size() || null == loanInfosMap || 0 >= loanInfosMap.size()) {
				return;
			}

			result = BidUtil.batchListingInfos(listIds);
			Date batchTime = Calendar.getInstance().getTime();

			String context = result.getContext();
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

			JSONObject jsoncontext = new JSONObject(context);
			JSONArray jsonLoanInfos = null;
			try {
				jsonLoanInfos = jsoncontext.getJSONArray("LoanInfos");
			} catch (Exception e) {
				logger.error("batchListingBidInfos结果JSON解析错误：", e);
				logger.error("JSON解析错误报文为：" + context);

				return;
			}

			for (int i = 0; i < jsonLoanInfos.length(); i++) {
				JSONObject jsonloanInfo = (JSONObject) jsonLoanInfos.get(i);
				int listingId = jsonloanInfo.getInt("ListingId");
				LoanInfo loanInfo = loanInfosMap.get(listingId);
				loanInfo.setListingInfo(jsonloanInfo);

				loanInfo.setInsertTime(batchTime);
				loanInfo.setLastupdateTime(batchTime);

				BiddingThread biddingThread = null;

				for (BiddingThread b : AutoBidManager.biddingThreads) {
					if (!b.getStatus()) {
						biddingThread = b;
						break;
					}
				}

				if (null == biddingThread) {
					return;
				}

				biddingThread.init(loanInfo);
				AutoBidManager.executorService.execute(biddingThread);
			}

			this.listIds.clear();
			this.loanInfosMap.clear();

		} finally {
			isAlive = false;
		}
	}

	public boolean getStatus() {
		return isAlive;
	}

	/*
	 * private static void checkValidReqTime() { new Thread(new Runnable() {
	 * 
	 * @Override public void run() { while(true) { Calendar c =
	 * Calendar.getInstance(); c.add(Calendar.SECOND, -60); Date nowTime =
	 * c.getTime(); Date firstTime = blockingQueue.peek();
	 * 
	 * if (null == firstTime) { continue; }
	 * 
	 * if (nowTime.getTime() > firstTime.getTime()) { try { blockingQueue.take();
	 * TimeUnit.MILLISECONDS.sleep(1); } catch (InterruptedException e) { } } } }
	 * }).start(); }
	 */

	/*
	 * static { try { loanInfosCount =
	 * Integer.parseInt(PropertiesUtil.getProperty("loanInfosCount", "500")); }
	 * catch (Exception e) { logger.
	 * error("The count of batchListingInfos request can be request in one minute configurate error"
	 * , e); loanInfosCount = 500; } checkValidReqTime(); }
	 */
}
