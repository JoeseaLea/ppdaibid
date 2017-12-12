package com.ppdaibid.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppdai.open.core.Result;
import com.ppdaibid.AccessInfo;
import com.ppdaibid.service.PPDservice;
import com.ppdaibid.utils.AccountUtil;
import com.ppdaibid.utils.JsonUtil;

@Controller
public class PPDcontroller {
	
	private static final Logger logger = Logger.getLogger(PPDcontroller.class);
	@Resource
	private PPDservice ppdservice;
	
	@RequestMapping(value="test")
	@ResponseBody
	public String test(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ppdservice.addLoanInfos(null);
		System.out.println("hello world");
		resp.sendRedirect("index.html");
		return "index.jsp";
	}
	
	@RequestMapping(value="sendSMSAuthCode")
	@ResponseBody
	public String sendSMSAuthCode () {
//		String result = "{\"ResultCode\": 0, \"ResultMessage\": \"动态登录密码发送成功\"}";
//		return result;
		Result result = AccountUtil.sendsmsauthcode();
		return result.getContext();
	}
	
	@RequestMapping(value="smsauthcodelogin")
	@ResponseBody
	public String smsauthcodelogin(String smsAuthCode) {
		Result result = AccountUtil.smsauthcodelogin(smsAuthCode);
		
		logger.debug("deviceFP:" + AccessInfo.deviceFP);
		logger.debug("短信登录结果:" + result.getContext());
		
		if (result.isSucess()) {
			
			JSONObject accessInfo = new JSONObject(result.getContext());
			AccessInfo.openID = JsonUtil.getString(accessInfo, "OpenID", null);
			//TODO 
//			AccessInfo.accessToken = JsonUtil.getString(accessInfo, "AccessToken", null);
			AccessInfo.refreshToken = JsonUtil.getString(accessInfo, "RefreshToken", null);
			AccessInfo.expiresIn = JsonUtil.getInt(accessInfo, "ExpiresIn", 0);
			
			return "SUCCESS";
		}
		
		return "ERROR";
	}
	
	@RequestMapping(value="startAutoBid")
	@ResponseBody
	public String startAutoBid(String auto) {
		this.ppdservice.startAutoBid(auto);
		return "SUCCESS";
	}
}
