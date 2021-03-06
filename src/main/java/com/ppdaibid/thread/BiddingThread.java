package com.ppdaibid.thread;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ppdai.open.core.Result;
import com.ppdaibid.AccessInfo;
import com.ppdaibid.AutoBidManager;
import com.ppdaibid.dao.BidDao;
import com.ppdaibid.dao.impl.BidDaoImpl;
import com.ppdaibid.info.LoanInfo;
import com.ppdaibid.strategy.BidStrategyCheck;
import com.ppdaibid.utils.BidUtil;

public class BiddingThread implements Runnable {
	
	private static final Logger logger = Logger.getLogger(BiddingThread.class);
	
	private boolean isAlive = false;
	
	private BidDao bidDao = null;
	private LoanInfo loanInfo = null;
	
	public BiddingThread() {
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		BidDaoImpl bidDaoImpl = context.getBean("bidDao", BidDaoImpl.class);
		this.bidDao = bidDaoImpl;
	}
	
	public void init(LoanInfo loanInfo) {
		this.isAlive = true;
		this.loanInfo = loanInfo;
	}

	@Override
	public void run() {
		try {
		if (null == this.loanInfo) {
			
			return;
		}
		
		Result result = null;
		if (BidStrategyCheck.checkStrategy99(loanInfo)) {
			result = BidUtil.bidding(this.loanInfo.getListingId(), 99, false);
		} else if (BidStrategyCheck.checkStrategy93(loanInfo)) {
			result = BidUtil.bidding(this.loanInfo.getListingId(), 93, false);
		} else if (BidStrategyCheck.checkStrategy90(loanInfo)) {
			result = BidUtil.bidding(this.loanInfo.getListingId(), 90, false);
		} else if (BidStrategyCheck.checkStrategy52(loanInfo)) {
			result = BidUtil.bidding(this.loanInfo.getListingId(), 52, false);
		} else if (BidStrategyCheck.checkStrategy51(loanInfo)) {
			result = BidUtil.bidding(this.loanInfo.getListingId(), 51, false);
		} else if (BidStrategyCheck.checkStrategy78(loanInfo)) {
			result = BidUtil.bidding(this.loanInfo.getListingId(), 78, false);
		}
		
		if (null == result) {
			loanInfo.setBid(false);
			bidDao.addLoanInfo(loanInfo);
			
			return;
		}
		
		String context = result.getContext();
		if (context.contains("您的操作太频繁")) {
			logger.error("Bidding请求太频繁，请求结果为：" + context);
			AutoBidManager.loanListNeedWait = true;
			
			loanInfo.setBid(false);
			bidDao.addLoanInfo(loanInfo);
			
			return;
		}
		
		if (context.contains("令牌") && (context.contains("失败") || context.contains("不存在"))) {
			AccessInfo.tokenIsValid = false;
			
			return;
		}
		
		if (result.isSucess()) {
			JSONObject jsoncontext = new JSONObject(result.getContext());
			int bidResult;
			try {
				bidResult = jsoncontext.getInt("Result");
			} catch (Exception e) {
				logger.error("JSON解析异常", e);
				bidResult = -1;
			}
			if (0 == bidResult) {
				loanInfo.setBid(true);
				logger.info("投标成功，投标结果：" + result.getContext());
			} else {
				loanInfo.setBid(false);
				logger.info("投标未中，投标结果为：" + result.getContext());
			}
		}
		
		bidDao.addLoanInfo(loanInfo);
		} finally {
			isAlive = false;
		}
	}
	
	public boolean getStatus() {
		return isAlive;
	}
}
