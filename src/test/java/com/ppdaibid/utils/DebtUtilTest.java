package com.ppdaibid.utils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.ppdai.open.core.Result;

public class DebtUtilTest {

	@Test
	public void testBuyDebt() {
		fail("Not yet implemented");
	}

	@Test
	public void testBatchDebtInfos() {
		//{"DebtInfos":[{"DebtId":43521447,"Seller":"pdu32161585","StatusId":2,"Lender":"pdu32161585","BidDateTime":"2017-06-16T21:13:49.92","OwingNumber":7,"OwingPrincipal":121.9300,"OwingInterest":9.0900,"Days":-2,"PriceforSaleRate":21.0000,"PriceforSale":124.3100,"PreferenceDegree":0.0000,"ListingId":53351454,"CreditCode":"D","CurrentCreditCode":"C","ListingAmount":2172.0000,"ListingTime":"2017-06-16T21:11:04.42","ListingMonths":12,"ListingRate":22.0,"PastDueNumber":0,"AllowanceRadio":0.2400,"PastDueDay":0}],"Result":1,"ResultMessage":"","ResultCode":null}
		List<Integer> debtIds = new ArrayList<Integer>();
		debtIds.add(44699065);
		debtIds.add(44699063);
		debtIds.add(44699057);
		debtIds.add(44699054);
		Result result = DebtUtil.batchDebtInfos(debtIds);
		System.out.println(result.getContext());
	}

	@Test
	public void testDebtListNew() {
		/*
		 * {"DebtInfos":[
		 * {"DebtdealId":43521447,"OwingNumber":7,"PriceforSaleRate":21.0000,"PriceforSale":124.3100,"ListingId":53351454,"CreditCode":"D"}
		 * ,{"DebtdealId":43521446,"OwingNumber":5,"PriceforSaleRate":23.0000,"PriceforSale":134.2900,"ListingId":44106579,"CreditCode":"D"},{"DebtdealId":43521445,"OwingNumber":8,"PriceforSaleRate":23.0000,"PriceforSale":69.8300,"ListingId":60186505,"CreditCode":"D"},{"DebtdealId":43521444,"OwingNumber":8,"PriceforSaleRate":20.0000,"PriceforSale":139.5400,"ListingId":59896979,"CreditCode":"C"}],"Count":4,"Result":1,"ResultMessage":"","ResultCode":null}
		 */
		Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND, -12);
		Date startDateTime = c.getTime();
		System.out.println(startDateTime);
		Result result = DebtUtil.debtListNew(1, startDateTime, "A,B,C,D,E,F");
		System.out.println(result.getContext());
	}

}
