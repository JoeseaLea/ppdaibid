package com.ppdaibid.utils;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.ppdai.open.core.Result;

public class BidUtilTest {

	@Test
	public void testBidding() {
		Result result = BidUtil.bidding(82626252, 1, false);
		System.out.println(result.getContext());
	}

	@Test
	public void testBidList() {
		Date date = Calendar.getInstance().getTime();
		Result result = BidUtil.bidList(-1, date, date, 1, 20);
		System.out.println(result.getContext());
	}

	@Test
	public void testAtchLenderBidList() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoanList() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND, -30);
		Result result = BidUtil.loanList(1, c.getTime());
		System.out.println(String.format("返回结果:%s", result.isSucess() ? result.getContext() : result.getErrorMessage()));
	}

	@Test
	public void testBatchListingInfos() throws Exception {
		List<Integer> listIds = new ArrayList<Integer>();
		listIds.add(54282997);
		System.out.println(BidUtil.batchListingInfos(listIds).getContext());
		
	}

	@Test
	public void testBatchListingBidInfos() {
		fail("Not yet implemented");
	}

	@Test
	public void testBatchListingStatusInfos() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchLenderRepayment() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testVolumeList() {
		Result result = BidUtil.volumeList(1, new Date());
		System.out.println(result.getContext());
	}
}
