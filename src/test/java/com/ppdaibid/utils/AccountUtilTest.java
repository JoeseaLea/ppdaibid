package com.ppdaibid.utils;

import java.util.UUID;

import org.junit.Test;

public class AccountUtilTest {

	@Test
	public void testGetAccessToken() {
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}

	@Test
	public void testSendsmsauthcode() {
		AccountUtil.sendsmsauthcode();
	}

	@Test
	public void testSmsauthcodelogin() {
		System.out.println(AccountUtil.smsauthcodelogin("412299")
				.getContext());
		//{"ResultCode":0,"ResultMessage":"OK","OpenID":"fbb020313ab74092820c5b8a0a1ea3cd","AccessToken":"c4617a9a-92f5-470c-b56e-ae8d15baa314","RefreshToken":"f07044c4-848f-4871-9447-2c15ed5dae94","ExpiresIn":604800}
	}

}
