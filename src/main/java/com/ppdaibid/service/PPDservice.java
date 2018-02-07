package com.ppdaibid.service;

import java.util.List;

import com.ppdaibid.info.LoanInfo;

public interface PPDservice {
	/**
	 * 添加投标信息
	 * @param loanInfos 投标信息
	 */
	public void addLoanInfos(List<LoanInfo> loanInfos);

	/**
	 * 开启自动投标功能
	 * @param auto 1：开启  0：关闭
	 */
	public void startAutoBid(int auto);

	/**
	 * 获取个人开发者注册手机号码
	 * @return 个人开发者注册手机号码
	 */
	public String getMobileNumber();

	/**
	 * 发送登录动态密码
	 * @return 发送登录动态密码结果
	 */
	public String sendSMSAuthCode();

	/**
	 * 获取用户授权信息
	 * @return 用户授权信息
	 */
	public String getAccessInfo();

	/**
	 * 获取用户资金余额
	 * @return 用户资金余额
	 */
	public String getBalanceInfo();

	/**
	 * 手机动态密码登录
	 * @param smsAuthCode 手机动态密码
	 * @return 登录结果
	 */
	public String smsauthcodelogin(String smsAuthCode);
}
