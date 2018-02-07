package com.ppdaibid.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.ppdai.open.core.*;
import com.ppdaibid.AccessInfo;

public class BalanceUtil {
	
	private static final Logger logger = Logger.getLogger(BalanceUtil.class);
	
	public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public static Result queryBalance() {
		//初始化操作
		try {
			OpenApiClient.Init(AccessInfo.appId, RsaCryptoHelper.PKCSType.PKCS8, AccessInfo.serverPublicKey, AccessInfo.clientPrivateKey);
			//请求url
			String url = "http://gw.open.ppdai.com/balance/balanceService/QueryBalance";
			
			if (null == AccessInfo.accessToken) {
				throw new InterruptedException("accessToken is null");
			}
			
			Result result = OpenApiClient.send(url, AccessInfo.accessToken);
			return result;
		} catch (Exception e) {
			logger.error("获取用户资金余额异常", e);
		}
		 return null;
	}
}
