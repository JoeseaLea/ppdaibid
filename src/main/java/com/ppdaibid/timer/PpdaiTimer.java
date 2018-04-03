package com.ppdaibid.timer;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.ppdai.open.core.Result;
import com.ppdaibid.AccessInfo;
import com.ppdaibid.utils.AccountUtil;
import com.ppdaibid.utils.BalanceUtil;

public class PpdaiTimer {
	private final static Logger logger = Logger.getLogger(PpdaiTimer.class);
	/**
	 * 发送短信提示登录
	 */
	public void sendSMSAuthCode() {
		AccessInfo.deviceFP = UUID.randomUUID().toString().replaceAll("-", "");
		Result result = AccountUtil.sendsmsauthcode();
		for (int i = 0; i <10; i ++) {
			if (result.isSucess()) {
				break;
			}
			
			try {
				TimeUnit.SECONDS.sleep(30);
			} catch (InterruptedException e) { }
			
			result = AccountUtil.sendsmsauthcode();
		}
	}
	
	public void checkTokenIsValid() {
		Result result = null;
		while (true) {
			result = BalanceUtil.queryBalance();
			if (result.isSucess()) {
				break;
			}
		}
		
		String context = result.getContext();
		if (context.contains("令牌") && (context.contains("失败") || context.contains("不存在"))) {
			logger.info(context);
			sendSMSAuthCode();
			return;
		}
		
		AccessInfo.tokenIsValid = true;
	}
}
