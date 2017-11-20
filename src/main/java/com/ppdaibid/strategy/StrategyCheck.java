package com.ppdaibid.strategy;

import java.util.Calendar;
import java.util.Date;

import com.ppdaibid.info.LoanInfo;

public class StrategyCheck {
	/*投标金额55*/
	public static boolean checkStrategy(LoanInfo loanInfo) {
		if (("AAA".equals(loanInfo.getCreditCode()) || "AA".equals(loanInfo.getCreditCode())
				|| "A".equals(loanInfo.getCreditCode()) || "B".equals(loanInfo.getCreditCode())
				|| "C".equals(loanInfo.getCreditCode()) || "D".equals(loanInfo.getCreditCode()))
				&& 1 == loanInfo.getCertificateValidate()
				&& null != loanInfo.getEducationDegree() && !"".equals(loanInfo.getEducateValidate())
				&& 12 >= loanInfo.getMonths()
				&& 18 <= loanInfo.getRate()
				&& 20000 >= loanInfo.getAmount()
				&& 1 <= loanInfo.getSuccessCount()
				&& 0 >= loanInfo.getWasteCount()
				&& 5 <= loanInfo.getNormalCount()
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
	/*投标金额53*/
	public static boolean checkStrategy1(LoanInfo loanInfo) {
		if (("AAA".equals(loanInfo.getCreditCode()) || "AA".equals(loanInfo.getCreditCode())
				|| "A".equals(loanInfo.getCreditCode()) || "B".equals(loanInfo.getCreditCode())
				|| "C".equals(loanInfo.getCreditCode()) || "D".equals(loanInfo.getCreditCode()))/*等级*/
				&& 1 == loanInfo.getCertificateValidate() /*学历认证1*/
				&& null != loanInfo.getEducationDegree() && !"".equals(loanInfo.getEducateValidate())/*学历不为空且毕业学校不为空*/
				&& 12 >= loanInfo.getMonths() /*借款周期小于12个月*/
				&& 18 <= loanInfo.getRate() /*借款利率大于18*/
				&& 16000 >= loanInfo.getAmount()/*借款金额小于16000*/
				&& 1 <= loanInfo.getSuccessCount()/*成功借款次数大于1*/
				&& 0 >= loanInfo.getWasteCount()/*流标次数0*/
				&& 5 <= loanInfo.getNormalCount()/*正常还清次数大于5*/
				&& 0 >= loanInfo.getOverdueLessCount()/*15天内逾期0*/
				&& 0 >= loanInfo.getOverdueMoreCount()/*15天外逾期0*/
				&& 8000 > loanInfo.getOwingAmount()/*剩余金额小于8000*/
				&& 0.9 >= loanInfo.getOwingAmount() / loanInfo.getTotalPrincipal()/*待还金额/累计借款金额小于0.9*/
				&& 0.8 >= loanInfo.getOwingAmount() / loanInfo.getHighestDebt()/*待还金额/历史最高债小于0.8*/
				&& 0.9 >= (loanInfo.getAmount() + loanInfo.getOwingAmount()) / loanInfo.getHighestDebt()/*本次借+待还/最高债小于0.9*/
				&& 1 >= loanInfo.getAmount() / loanInfo.getHighestPrincipal()/*本次/最高单次小于1*/
				&& 20000 >= loanInfo.getAmount() + loanInfo.getOwingAmount()/*借后负债小于20000*/
				&& 0 < loanInfo.getRemainFunding()) {/*可投金额大于0*/
			
			Calendar c = Calendar.getInstance();
			c.setTime(loanInfo.getLastSuccessBorrowTime());
			c.add(Calendar.HOUR, 24*31);/*距最近借款时间大于31天*/
			if (c.getTime().getTime() <= new Date().getTime()) {
				return true;
			}
		}
		
		return false;
	}
    /*投标金额52*/
	public static boolean checkStrategy2(LoanInfo loanInfo) {
		if (("AAA".equals(loanInfo.getCreditCode()) || "AA".equals(loanInfo.getCreditCode())
				|| "A".equals(loanInfo.getCreditCode()) || "B".equals(loanInfo.getCreditCode())
				|| "C".equals(loanInfo.getCreditCode()) || "D".equals(loanInfo.getCreditCode()))/*等级*/
				&& 0 == loanInfo.getCertificateValidate() /*学历认证0*/
				&& null != loanInfo.getEducationDegree() && !"".equals(loanInfo.getEducateValidate())/*学历不为空且毕业学校不为空*/
				&& 12 >= loanInfo.getMonths() /*借款周期小于12个月*/
				&& 18 <= loanInfo.getRate() /*借款利率大于18*/
				&& 6000 >= loanInfo.getAmount()/*借款金额小于6000*/
				&& 0 <= loanInfo.getSuccessCount()/*成功借款次数大于1*/
				&& 0 >= loanInfo.getWasteCount()/*流标次数0*/
				&& 8 <= loanInfo.getNormalCount()/*正常还清次数大于8*/
				&& 0 >= loanInfo.getOverdueLessCount()/*15天内逾期0*/
				&& 0 >= loanInfo.getOverdueMoreCount()/*15天外逾期0*/
				&& 6000 > loanInfo.getOwingAmount()/*剩余金额小于6000*/
				&& 0.8 >= loanInfo.getOwingAmount() / loanInfo.getTotalPrincipal()/*待还金额/累计借款金额小于0.8*/
				&& 0.6 >= loanInfo.getOwingAmount() / loanInfo.getHighestDebt()/*待还金额/历史最高债小于0.6*/
				&& 0.8 >= (loanInfo.getAmount() + loanInfo.getOwingAmount()) / loanInfo.getHighestDebt()/*借后负债/最高债小于0.8*/
				&& 1 >= loanInfo.getAmount() / loanInfo.getHighestPrincipal()/*本次/最高单次小于1*/
				&& 9000 >= loanInfo.getAmount() + loanInfo.getOwingAmount()/*借后负债小于9000*/
				&& 0 < loanInfo.getRemainFunding()) {/*可投金额大于0*/
			
			Calendar c = Calendar.getInstance();
			c.setTime(loanInfo.getLastSuccessBorrowTime());
			c.add(Calendar.HOUR, 24*93);/*距最近借款时间大于93天*/
			if (c.getTime().getTime() <= new Date().getTime()) {
				return true;
			}
		}
		
		return false;
	}
}