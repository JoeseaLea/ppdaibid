package com.ppdaibid.thread;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ppdai.open.core.Result;
import com.ppdaibid.DebtManager;
import com.ppdaibid.dao.DebtDao;
import com.ppdaibid.dao.impl.DebtDaoImpl;
import com.ppdaibid.info.DebtInfo;
import com.ppdaibid.strategy.DebtStrategyCheck;
import com.ppdaibid.utils.DebtUtil;

public class BuyDebtThread extends Thread {
	
	private static final Logger logger = Logger.getLogger(BuyDebtThread.class);
	
	private DebtInfo debtInfo = null;
	private DebtDao debtDao = null;
	
	public BuyDebtThread() {
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		DebtDaoImpl debtDaoImpl = context.getBean("debtDao", DebtDaoImpl.class);
		this.debtDao = debtDaoImpl;
	}
	
	public void init(DebtInfo debtInfo) {
		this.debtInfo = debtInfo;
	}
	
	@Override
	public void run() {
		if (null == debtInfo) {
			return;
		}
		Result result = null;
		if (DebtStrategyCheck.checkStrategy1796(debtInfo)) {
			result = DebtUtil.buyDebt(debtInfo.getDebtdealId());
		}
		
		if (null == result) {
			return;
		}
		
		String context = result.getContext();
		if (context.contains("您的操作太频繁")) {
			logger.error("Bidding请求太频繁，请求结果为：" + context);
			DebtManager.debtListNeedWait = true;
			return;
		}
		
		if (result.isSucess()) {
			JSONObject jsoncontext = new JSONObject(result.getContext());
			int buyResult;
			try {
				buyResult = jsoncontext.getInt("Result");
			} catch (Exception e) {
				logger.error("JSON解析异常", e);
				buyResult = -1;
			}
			if (0 == buyResult) {
				debtInfo.setBid(true);
				logger.info("购买债权成功，购买结果：" + result.getContext());
			} else {
				logger.info("购买债权失败，购买结果为：" + result.getContext());
			}
		}
		
		debtDao.addDebtInfo(debtInfo);
		
		debtInfo = null;
	}
}
