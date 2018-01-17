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
		System.out.println(AccountUtil.smsauthcodelogin("787115")
				.getContext());
		//{"ResultCode":0,"ResultMessage":"OK","OpenID":"fbb020313ab74092820c5b8a0a1ea3cd","AccessToken":"33e05d09-e94f-4dce-bd6d-c997e0e56b1c","RefreshToken":"0a7b1445-75eb-44ca-9eef-89c7e1b1fbb0","ExpiresIn":604800}
	}

}
