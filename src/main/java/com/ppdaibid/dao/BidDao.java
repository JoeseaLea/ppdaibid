package com.ppdaibid.dao;

import java.util.List;

import com.ppdaibid.info.LoanInfo;

public interface BidDao {

	public void addLoanInfo(LoanInfo loanInfo);
	
	public void addLoanInfos(List<LoanInfo> loanInfos);

	public List<Integer> getIdsbyStrategy(String strategy);

	public void updateLoanInfo2isBid(Integer listingId);

	public List<Integer> getCanBeIgnoreIds();

}
