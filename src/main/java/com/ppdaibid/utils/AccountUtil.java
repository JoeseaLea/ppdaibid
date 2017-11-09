package com.ppdaibid.utils;

import org.apache.log4j.Logger;

import com.ppdai.open.core.*;
import com.ppdaibid.AccessInfo;

public class AccountUtil {
	private static final Logger logger = Logger.getLogger(AccountUtil.class);
	
	public static String getAccessToken() {
		try {
			// 仅初始化一次即可
			OpenApiClient.Init(AccessInfo.appId, RsaCryptoHelper.PKCSType.PKCS8, AccessInfo.serverPublicKey, AccessInfo.clientPrivateKey);
			// 上一步中获取的code
			// 授权信息
			AuthInfo authInfo = OpenApiClient.authorize(AccessInfo.code);
			// 获取accessToken
			String accessToke = authInfo.getAccessToken();
			return accessToke;
		} catch (Exception e) {
			logger.error("获取AccessToken异常", e);
		}
		return null;
	}

	public static Result sendsmsauthcode() {
		try {
			String url = "http://gw.open.ppdai.com/auth/authservice/sendsmsauthcode";
		    //初始化（执行一次即可）
		    OpenApiClient.Init(AccessInfo.appId, RsaCryptoHelper.PKCSType.PKCS8, AccessInfo.serverPublicKey, AccessInfo.clientPrivateKey);
		    Result result = OpenApiClient.send(url
		            , new PropertyObject("Mobile", AccessInfo.mobile, ValueTypeEnum.String)
		            , new PropertyObject("DeviceFP", AccessInfo.deviceFP, ValueTypeEnum.String));
		    return result;
		} catch (Exception e) {
			logger.error("发送登录动态密码异常", e);
		}
		return null;
	}
	
	public static Result smsauthcodelogin(String smsAuthCode) {
		 try {
			 //初始化操作
			OpenApiClient.Init(AccessInfo.appId, RsaCryptoHelper.PKCSType.PKCS8, AccessInfo.serverPublicKey, AccessInfo.clientPrivateKey);
			//请求url
			String url = "http://gw.open.ppdai.com/open/oauthservice/smsauthcodelogin";
			Result result = OpenApiClient.send(url
					, new PropertyObject("Mobile", AccessInfo.mobile, ValueTypeEnum.String)
					, new PropertyObject("DeviceFP", AccessInfo.deviceFP, ValueTypeEnum.String)
					, new PropertyObject("SMSAuthCode", smsAuthCode, ValueTypeEnum.String));
			return result;
		} catch (Exception e) {
			logger.error("手机动态密码登录错误", e);
		}
		return null;
	}
}
