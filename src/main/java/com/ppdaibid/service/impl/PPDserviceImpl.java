package com.ppdaibid.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.ppdai.open.core.Result;
import com.ppdaibid.AccessInfo;
import com.ppdaibid.dao.BidDao;
import com.ppdaibid.info.LoanInfo;
import com.ppdaibid.service.PPDservice;
import com.ppdaibid.utils.AccountUtil;
import com.ppdaibid.utils.BalanceUtil;
import com.ppdaibid.utils.JsonUtil;
import com.ppdaibid.utils.PropertiesUtil;

@Service
public class PPDserviceImpl implements PPDservice {
	
	private static final Logger logger = Logger.getLogger(PPDserviceImpl.class);
	
	@Resource
	public BidDao bidDao;
	
	@Override
	public String getMobileNumber() {
		String mobile = PropertiesUtil.getProperty("mobile", "18688395156");
		mobile = mobile.substring(0, 3) + "***" + mobile.substring(7);
		return mobile;
	}

	@Override
	public void addLoanInfos(List<LoanInfo> loanInfos) {
		bidDao.addLoanInfos(loanInfos);
	}

	@Override
	public void startAutoBid(int auto) {
		if (1 == auto) {
//			AutoBidManager.startAutoBid(AccessInfo.mobile, AccessInfo.deviceFP, AccessInfo.ppdaiAccount);
		}
	}

	@Override
	public String sendSMSAuthCode() {
		
		AccessInfo.deviceFP = UUID.randomUUID().toString().replaceAll("-", "");
		
		Result result = AccountUtil.sendsmsauthcode();
		return result.getContext();
	}

	@Override
	public String getAccessInfo() {
		String accessInfo = "ppdaiAccount：" + AccessInfo.ppdaiAccount
				+ "<br>code:" + AccessInfo.code
				+ "<br>appId:" + AccessInfo.appId
				+ "<br>clientPrivateKey:" + AccessInfo.clientPrivateKey
				+ "<br>serverPublicKey:" + AccessInfo.serverPublicKey
				+ "<br>mobile:" + AccessInfo.mobile
				+ "<br>deviceFP:" + AccessInfo.deviceFP
				+ "<br>openID:" + AccessInfo.openID
				+ "<br>accessToken:" + AccessInfo.accessToken
				+ "<br>refreshToken:" + AccessInfo.refreshToken
				+ "<br>expiresIn:" + AccessInfo.expiresIn;
		return accessInfo;
	}

	@Override
	public String getBalanceInfo() {
		Result result = BalanceUtil.queryBalance();
		if (null != result && result.isSucess()) {
			return result.getContext();
		}
		return "查询用户资金余额错误";
	}

	@Override
	public String smsauthcodelogin(String smsAuthCode) {
		Result result = AccountUtil.smsauthcodelogin(smsAuthCode);
		
		logger.debug("deviceFP:" + AccessInfo.deviceFP);
		logger.debug("短信登录结果:" + result.getContext());
		
		if (null != result && result.isSucess()) {
			
			JSONObject accessInfo = new JSONObject(result.getContext());
			AccessInfo.openID = JsonUtil.getString(accessInfo, "OpenID", null);
			AccessInfo.accessToken = JsonUtil.getString(accessInfo, "AccessToken", null);
			AccessInfo.refreshToken = JsonUtil.getString(accessInfo, "RefreshToken", null);
			AccessInfo.expiresIn = JsonUtil.getInt(accessInfo, "ExpiresIn", 0);
			
			return "SUCCESS";
		}
		
		return "ERROR";
	}
}
