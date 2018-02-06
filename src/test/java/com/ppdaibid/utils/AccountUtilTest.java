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
		System.out.println(AccountUtil.smsauthcodelogin("826438")
				.getContext());
		//{"ResultCode":0,"ResultMessage":"OK","OpenID":"fbb020313ab74092820c5b8a0a1ea3cd","AccessToken":"6e83bdf3-11ba-4420-a6ff-87fbf3c87dc0","RefreshToken":"b5c070a5-8dff-44ec-b35f-5e0a2d9a7f5d","ExpiresIn":604800}

	}

}
