package com.ppdaibid.utils;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.ppdai.open.core.Result;

public class BalanceUtilTest {

	Logger logger = Logger.getLogger(BalanceUtilTest.class);
	@Test
	public void testQueryBalance() {
		// {"Code":"GTW-BRQ-INVALIDTOKEN","Message":"令牌校验失败:令牌不存在！"}
		Result result = BalanceUtil.queryBalance();
		System.out.println(result.isSucess());
		System.out.println(result.getContext());
	}
}
