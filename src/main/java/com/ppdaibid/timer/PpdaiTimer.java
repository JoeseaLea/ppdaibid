package com.ppdaibid.timer;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.ppdai.open.core.Result;
import com.ppdaibid.AccessInfo;
import com.ppdaibid.utils.AccountUtil;

public class PpdaiTimer {
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
}
