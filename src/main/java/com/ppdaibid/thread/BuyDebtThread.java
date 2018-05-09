package com.ppdaibid.thread;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ppdai.open.core.Result;
import com.ppdaibid.AccessInfo;
import com.ppdaibid.DebtManager;
import com.ppdaibid.dao.DebtDao;
import com.ppdaibid.dao.impl.DebtDaoImpl;
import com.ppdaibid.info.DebtInfo;
import com.ppdaibid.strategy.DebtStrategyCheck;
import com.ppdaibid.utils.DebtUtil;

public class BuyDebtThread extends Thread {

	private static final Logger logger = Logger.getLogger(BuyDebtThread.class);

	private boolean isAlive = false;

	private DebtInfo debtInfo = null;
	private DebtDao debtDao = null;

	public void init(DebtInfo debtInfo) {
		isAlive = true;
		this.debtInfo = debtInfo;
	}

	public BuyDebtThread() {
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		DebtDaoImpl debtDaoImpl = context.getBean("debtDao", DebtDaoImpl.class);
		this.debtDao = debtDaoImpl;
	}

	@Override
	public void run() {
		try {
			if (null == debtInfo) {
				return;
			}
			Result result = null;

			// 符合策略购买
			if (DebtStrategyCheck.checkStrategy1648(debtInfo) || DebtStrategyCheck.checkStrategy1795(debtInfo)
					|| DebtStrategyCheck.checkStrategy2156(debtInfo) || DebtStrategyCheck.checkStrategy3645(debtInfo)
					|| DebtStrategyCheck.checkStrategy3646(debtInfo) || DebtStrategyCheck.checkStrategy1777(debtInfo)
			// || DebtStrategyCheck.checkStrategy1133(debtInfo)
			// || DebtStrategyCheck.checkStrategy1978(debtInfo)
			) {
				result = DebtUtil.buyDebt(debtInfo.getDebtdealId());
				// result = new Result();
				// result.setSucess(true);
				// result.setContext("{\"Result\": 0,\"ResultMessage\": \"null\",\"debtDealId\":
				// \"" + debtInfo.getDebtdealId() + "\"}");
				// result.setErrorMessage("just for test");
			}

			if (null == result) {
				logger.debug("债权(" + debtInfo.getDebtdealId() + ")不符合所有策略");
				debtDao.addDebtInfo(debtInfo);

				return;
			}

			String context = result.getContext();
			if (context.contains("您的操作太频繁")) {
				logger.error("购买债权(" + debtInfo.getDebtdealId() + ")时，Bidding请求太频繁，请求结果为：" + context);
				DebtManager.debtListNeedWait = true;
				debtInfo.setBid(false);
				debtDao.addDebtInfo(debtInfo);

				return;
			}

			if (context.contains("令牌") && (context.contains("失败") || context.contains("不存在"))) {
				AccessInfo.tokenIsValid = false;

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
					logger.info(debtInfo.getDebtdealId() + "购买债权成功，购买结果(ListingId:" + debtInfo.getListingId() + ")："
							+ result.getContext());
				} else {
					debtInfo.setBid(false);
					logger.info(debtInfo.getDebtdealId() + "购买债权失败，购买结果为(ListingId:" + debtInfo.getListingId() + ")："
							+ result.getContext());
				}
			} else {
				debtInfo.setBid(false);
			}

			debtDao.addDebtInfo(debtInfo);

			debtInfo = null;
		} finally {
			isAlive = false;
		}
	}

	public boolean getStatus() {
		return isAlive;
	}
}
