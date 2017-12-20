package com.ppdaibid.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ppdaibid.dao.BidDao;
import com.ppdaibid.info.LoanInfo;
import com.ppdaibid.service.PPDservice;

@Service
public class PPDserviceImpl implements PPDservice {

	@Resource
	public BidDao bidDao;
	
	@Override
	public void addLoanInfos(List<LoanInfo> loanInfos) {
		bidDao.addLoanInfos(loanInfos);
	}

	@Override
	public void startAutoBid(String auto) {
		if ("1".equals(auto)) {
//			AutoBidManager.startAutoBid(AccessInfo.mobile, AccessInfo.deviceFP, AccessInfo.ppdaiAccount);
		}
	}
}
