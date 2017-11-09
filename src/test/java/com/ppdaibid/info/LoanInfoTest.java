package com.ppdaibid.info;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class LoanInfoTest {

	@Test
	public void testSetLoanList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetListingInfo() {
		String json = "{\"LoanInfos\":[{\"CreditCode\":\"C\",\"EducationDegree\":null,\"TotalPrincipal\":18481,\"BorrowName\":\"pdu2582510858\",\"OwingPrincipal\":10277.68,\"NormalCount\":14,\"OverdueMoreCount\":0,\"DeadLineTimeOrRemindTimeStr\":\"14天23时50分\",\"Gender\":2,\"SuccessCount\":3,\"HighestPrincipal\":9681,\"LenderCount\":11,\"OverdueLessCount\":5,\"VideoValidate\":0,\"LastSuccessBorrowTime\":\"2017-09-18T12:46:05\",\"AmountToReceive\":0,\"AuditingTime\":\"2000-01-01T00:00:00\",\"OwingAmount\":10975.38,\"FistBidTime\":\"2017-10-31T22:35:17.723\",\"ListingId\":80942483,\"CreditValidate\":0,\"GraduateSchool\":null,\"Age\":35,\"LastBidTime\":\"2017-10-31T22:36:11.117\",\"StudyStyle\":null,\"Months\":9,\"Amount\":4000,\"CurrentRate\":20,\"NciicIdentityCheck\":0,\"WasteCount\":0,\"CertificateValidate\":0,\"RegisterTime\":\"2016-06-15T10:02:37\",\"FirstSuccessBorrowTime\":\"2016-06-15T13:27:26.773\",\"EducateValidate\":0,\"FailedCount\":0,\"CancelCount\":0,\"RemainFunding\":1780,\"PhoneValidate\":1,\"HighestDebt\":12571.5}],\"ResultMessage\":\"\",\"ResultCode\":null,\"Result\":1}";
		
		JSONObject context = new JSONObject(json);
		JSONArray jsonLoanInfos = context.getJSONArray("LoanInfos");
		
		for (int j = 0; j < jsonLoanInfos.length(); j++) {
			JSONObject loanInfoJson = (JSONObject)jsonLoanInfos.get(j);
			LoanInfo loanInfo = new LoanInfo();
			loanInfo.setListingInfo(loanInfoJson);
		}
	}

}
