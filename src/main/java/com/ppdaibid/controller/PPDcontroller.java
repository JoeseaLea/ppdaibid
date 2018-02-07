package com.ppdaibid.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppdaibid.service.PPDservice;

@Controller
public class PPDcontroller {
	
//	private static final Logger logger = Logger.getLogger(PPDcontroller.class);
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
	
	@RequestMapping(value="getMobileNumber")
	@ResponseBody
	public String getMobileNumber() {
		return ppdservice.getMobileNumber();
	}
	
	@RequestMapping(value="sendSMSAuthCode")
	@ResponseBody
	public String sendSMSAuthCode () {
		return ppdservice.sendSMSAuthCode();
	}
	
	@RequestMapping(value="smsauthcodelogin")
	@ResponseBody
	public String smsauthcodelogin(String smsAuthCode) {
		return ppdservice.smsauthcodelogin(smsAuthCode);
	}
	
	@RequestMapping(value="startAutoBid")
	@ResponseBody
	public String startAutoBid(int auto) {
		this.ppdservice.startAutoBid(auto);
		return "SUCCESS";
	}
	
	@RequestMapping(value="getAccessInfo")
	@ResponseBody
	public String getAccessInfo() {
		return ppdservice.getAccessInfo();
	}
	
	@RequestMapping(value="getBalanceInfo")
	@ResponseBody
	public String getBalanceInfo() {
		return ppdservice.getBalanceInfo();
	}
}
