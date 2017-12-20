package com.ppdaibid.strategy;

import com.ppdaibid.info.DebtInfo;

public class DebtStrategyCheck {
	public static boolean checkStrategy(DebtInfo debtInfo) {
		
		if (0.2 <= debtInfo.getPriceforSaleRate()
				&& 150 <= debtInfo.getOwingPrincipal() + debtInfo.getOwingInterest()
				&& 5 <= debtInfo.getDays()
				&& 0 >= debtInfo.getPastDueNumber()
				&& (debtInfo.getCreditCode().equals("AA") || debtInfo.getCreditCode().equals("A") || debtInfo.getCreditCode().equals("B") || debtInfo.getCreditCode().equals("C"))
				&& debtInfo.getCreditCode().toUpperCase().charAt(0) >= debtInfo.getCurrentCreditCode().toUpperCase().charAt(0)) {
			return true;
		}
		return false;
	}
}
