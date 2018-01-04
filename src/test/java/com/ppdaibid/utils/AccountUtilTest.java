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
		System.out.println(AccountUtil.smsauthcodelogin("689732")
				.getContext());
		//{"ResultCode":0,"ResultMessage":"OK","OpenID":"fbb020313ab74092820c5b8a0a1ea3cd","AccessToken":"60e72521-eb6f-4c92-b7bf-3192e2f5e4be","RefreshToken":"5ec03732-b487-4c7c-be0b-6f7dae351ab7","ExpiresIn":604800}
	}

}
