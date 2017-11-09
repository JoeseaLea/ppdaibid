package com.ppdaibid.service;

import java.util.List;

import com.ppdaibid.info.LoanInfo;

public interface PPDservice {
	public void addLoanInfos(List<LoanInfo> loanInfos);

	public void startAutoBid(String auto);
}
