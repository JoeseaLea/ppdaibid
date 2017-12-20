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
		System.out.println(AccountUtil.smsauthcodelogin("213973")
				.getContext());
		//{"ResultCode":0,"ResultMessage":"OK","OpenID":"fbb020313ab74092820c5b8a0a1ea3cd","AccessToken":"42422f36-fd36-4548-8bf8-84afe34c3948","RefreshToken":"0fdec2fb-bb5b-4a95-bfeb-93ba64653fa8","ExpiresIn":604800}
	}

}
