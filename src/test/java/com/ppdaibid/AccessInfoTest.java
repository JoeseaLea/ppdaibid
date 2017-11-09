package com.ppdaibid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

public class AccessInfoTest {

	@Test
	public void test() {
		System.out.println(AccessInfo.code);
		System.out.println(AccessInfo.appId);
		System.out.println(AccessInfo.clientPrivateKey);
		System.out.println(AccessInfo.deviceFP);
		System.out.println(AccessInfo.mobile);
		System.out.println(AccessInfo.serverPublicKey);
		DateFormat df =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(df.format(Calendar.getInstance().getTime()));
		
	}

}
