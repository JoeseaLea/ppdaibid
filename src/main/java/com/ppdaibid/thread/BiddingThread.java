package com.ppdaibid.thread;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ppdai.open.core.Result;
import com.ppdaibid.dao.PPDdao;
import com.ppdaibid.dao.impl.PPDdaoImpl;
import com.ppdaibid.info.LoanInfo;
import com.ppdaibid.strategy.StrategyCheck;
import com.ppdaibid.utils.BidUtil;

public class BiddingThread implements Runnable {
	
	private static final Logger logger = Logger.getLogger(BiddingThread.class);
	
	private PPDdao ppDdao = null;
	private LoanInfo loanInfo = null;
	
	public BiddingThread(LoanInfo loanInfo) {
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		PPDdaoImpl ppDdaoImpl = context.getBean("ppddao", PPDdaoImpl.class);
		this.ppDdao = ppDdaoImpl;
		this.loanInfo = loanInfo;
	}

	@Override
	public void run() {
		if (null == this.loanInfo) {
			return;
		}
		if (StrategyCheck.checkStrategy(loanInfo)) {
			Result result = BidUtil.bidding(this.loanInfo.getListingId(), 51, false);
			if (result.isSucess()) {
				JSONObject context = new JSONObject(result.getContext());
				int bidResult;
				try {
					bidResult = context.getInt("Result");
				} catch (Exception e) {
					logger.error("JSON解析异常", e);
					bidResult = -1;
				}
				if (0 == bidResult) {
					loanInfo.setBid(true);
					logger.info("投标成功，投标结果：" + result.getContext());
				} else {
					logger.info("投标未中，投标结果为：" + result.getContext());
				}
			}
		}
		
		ppDdao.addLoanInfo(loanInfo);
	}

}
