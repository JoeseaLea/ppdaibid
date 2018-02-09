package com.ppdaibid.utils;

import org.junit.Test;

import com.ppdai.open.core.Result;

public class BalanceUtilTest {

	@Test
	public void testQueryBalance() {
		Result result = BalanceUtil.queryBalance();
		System.out.println(result.getContext());
	}
}
