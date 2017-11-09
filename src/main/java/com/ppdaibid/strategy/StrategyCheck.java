package com.ppdaibid.strategy;

import java.util.Calendar;
import java.util.Date;

import com.ppdaibid.info.LoanInfo;

public class StrategyCheck {
	public static boolean checkStrategy(LoanInfo loanInfo) {
		if (("AAA".equals(loanInfo.getCreditCode()) || "AA".equals(loanInfo.getCreditCode())
				|| "A".equals(loanInfo.getCreditCode()) || "B".equals(loanInfo.getCreditCode())
				|| "C".equals(loanInfo.getCreditCode()) || "D".equals(loanInfo.getCreditCode()))
				&& 1 == loanInfo.getCertificateValidate()
				&& 12 >= loanInfo.getMonths()
				&& 18 <= loanInfo.getRate()
				&& 20000 >= loanInfo.getAmount()
				&& 1 <= loanInfo.getSuccessCount()
				&& 0 >= loanInfo.getWasteCount()
				&& 3 <= loanInfo.getNormalCount()
				&& 1 >= loanInfo.getOverdueLessCount()
				&& 0 >= loanInfo.getOverdueMoreCount()
				&& 8000 > loanInfo.getOwingAmount()
				&& 0.7 >= loanInfo.getOwingAmount() / loanInfo.getTotalPrincipal()
				&& 0.6 >= loanInfo.getOwingAmount() / loanInfo.getHighestDebt()
				&& 1.2 >= (loanInfo.getAmount() + loanInfo.getOwingAmount()) / loanInfo.getHighestDebt()
				&& 2 >= loanInfo.getAmount() / loanInfo.getHighestPrincipal()
				&& 25000 >= loanInfo.getAmount() + loanInfo.getOwingAmount()
				&& 0 < loanInfo.getRemainFunding()) {
			
			Calendar c = Calendar.getInstance();
			c.setTime(loanInfo.getLastSuccessBorrowTime());
			c.add(Calendar.HOUR, 24*93);
			if (c.getTime().getTime() <= new Date().getTime()) {
				return true;
			}
		}
		
		return false;
	}
}
