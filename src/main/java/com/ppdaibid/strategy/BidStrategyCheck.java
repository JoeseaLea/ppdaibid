package com.ppdaibid.strategy;

import java.util.Calendar;
import java.util.Date;

import com.ppdaibid.info.LoanInfo;

public class BidStrategyCheck {
	
	/*投标金额99 学历93天 创建日期：20171030
	历史修改记录
	1--20171201 loanInfo.getAmount() / loanInfo.getHighestPrincipal() 本次/最高单次 由2修改到3*/
	public static boolean checkStrategy99(LoanInfo loanInfo) {
		if (("AAA".equals(loanInfo.getCreditCode()) || "AA".equals(loanInfo.getCreditCode())
				|| "A".equals(loanInfo.getCreditCode()) || "B".equals(loanInfo.getCreditCode())
				|| "C".equals(loanInfo.getCreditCode()) || "D".equals(loanInfo.getCreditCode()))
				&& 1 == loanInfo.getCertificateValidate() //学历认证
				&& null != loanInfo.getEducationDegree() && 1 == loanInfo.getEducateValidate()
				&& 12 >= loanInfo.getMonths()
				&& 18 <= loanInfo.getRate()
				&& 20000 >= loanInfo.getAmount()
				&& 1 <= loanInfo.getSuccessCount()
				&& 0 >= loanInfo.getWasteCount()
				&& 5 <= loanInfo.getNormalCount()
				&& 1 >= loanInfo.getOverdueLessCount()
				&& 0 >= loanInfo.getOverdueMoreCount()
				&& 8000 > loanInfo.getOwingAmount()
				&& 0.7 >= loanInfo.getOwingAmount() / loanInfo.getTotalPrincipal()/*待还金额/累计借款金额小于0.7*/
				&& 0.6 >= loanInfo.getOwingAmount() / loanInfo.getHighestDebt()/*本次借+待还/最高债小于0.6*/
				&& 1.2 >= (loanInfo.getAmount() + loanInfo.getOwingAmount()) / loanInfo.getHighestDebt()/*本次借+待还/最高债小于1.2*/
				&& 3 >= loanInfo.getAmount() / loanInfo.getHighestPrincipal()/*本次/最高单次小于3*/
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

	/*投标金额93 学历31天 创建日期：20171030
	  历史修改记录
	  1--20171201 loanInfo.getAmount() / loanInfo.getHighestPrincipal() 本次/最高单次 由1修改到2*/
	public static boolean checkStrategy93(LoanInfo loanInfo) {
		if (("AAA".equals(loanInfo.getCreditCode()) || "AA".equals(loanInfo.getCreditCode())
				|| "A".equals(loanInfo.getCreditCode()) || "B".equals(loanInfo.getCreditCode())
				|| "C".equals(loanInfo.getCreditCode()) || "D".equals(loanInfo.getCreditCode()))/*等级*/
				&& 1 == loanInfo.getCertificateValidate() /*学历认证1*/
				&& null != loanInfo.getEducationDegree() && 1 == loanInfo.getEducateValidate()/*判断学校是否空*/
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
				&& 2 >= loanInfo.getAmount() / loanInfo.getHighestPrincipal()/*本次/最高单次小于1*/
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
	
	/*投标金额90 学历0天 创建日期：20171201*/
	public static boolean checkStrategy90(LoanInfo loanInfo) {
		if (("AAA".equals(loanInfo.getCreditCode()) || "AA".equals(loanInfo.getCreditCode())
				|| "A".equals(loanInfo.getCreditCode()) || "B".equals(loanInfo.getCreditCode())
				|| "C".equals(loanInfo.getCreditCode()) || "D".equals(loanInfo.getCreditCode()))/*等级*/
				&& 1 == loanInfo.getCertificateValidate() /*学历认证1*/
				&& null != loanInfo.getEducationDegree() && 1 == loanInfo.getEducateValidate()/*判断学校是否空*/
				&& 12 >= loanInfo.getMonths() /*借款周期小于12个月*/
				&& 18 <= loanInfo.getRate() /*借款利率大于20*/
				&& 16000 >= loanInfo.getAmount()/*借款金额小于16000*/
				&& 1 <= loanInfo.getSuccessCount()/*成功借款次数大于1*/
				&& 0 >= loanInfo.getWasteCount()/*流标次数0*/
				&& 5 <= loanInfo.getNormalCount()/*正常还清次数大于5*/
				&& 0 >= loanInfo.getOverdueLessCount()/*15天内逾期0*/
				&& 0 >= loanInfo.getOverdueMoreCount()/*15天外逾期0*/
				&& 10000 > loanInfo.getOwingAmount()/*剩余金额小于8000*/
				&& 0.9 >= loanInfo.getOwingAmount() / loanInfo.getTotalPrincipal()/*待还金额/累计借款金额小于0.9*/
				&& 0.7 >= loanInfo.getOwingAmount() / loanInfo.getHighestDebt()/*待还金额/历史最高债小于0,7*/
				&& 1.2 >= (loanInfo.getAmount() + loanInfo.getOwingAmount()) / loanInfo.getHighestDebt()/*本次借+待还/最高债小于1.2*/
				&& 3 >= loanInfo.getAmount() / loanInfo.getHighestPrincipal()/*本次/最高单次小于3*/
				&& 18000 >= loanInfo.getAmount() + loanInfo.getOwingAmount()/*借后负债小于25000*/
				&& 0 < loanInfo.getRemainFunding()) {/*可投金额大于0*/
			
			return true;
		}
		
		return false;
	}
	
	/*投标金额52 无学历93天 创建日期：20171201*/
	public static boolean checkStrategy52(LoanInfo loanInfo) {
		if (("AAA".equals(loanInfo.getCreditCode()) || "AA".equals(loanInfo.getCreditCode())
				|| "A".equals(loanInfo.getCreditCode()) || "B".equals(loanInfo.getCreditCode())
				|| "C".equals(loanInfo.getCreditCode()) || "D".equals(loanInfo.getCreditCode()))/*等级*/
				&& 0 == loanInfo.getCertificateValidate() /*学历认证0*/
				/*&& null != loanInfo.getEducationDegree() && 1 == loanInfo.getEducateValidate()*//*无学历策略，不用判断学校判断学校是否空*/
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
				&& 0.5 >= loanInfo.getOwingAmount() / loanInfo.getHighestDebt()/*待还金额/历史最高债小于0.5*/
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
	
	/*投标金额51 无学历70天 创建日期：20171201*/
	public static boolean checkStrategy51(LoanInfo loanInfo) {
		if (("AAA".equals(loanInfo.getCreditCode()) || "AA".equals(loanInfo.getCreditCode())
				|| "A".equals(loanInfo.getCreditCode()) || "B".equals(loanInfo.getCreditCode())
				|| "C".equals(loanInfo.getCreditCode()) || "D".equals(loanInfo.getCreditCode()))/*等级*/
				&& 0 == loanInfo.getCertificateValidate() /*学历认证0*/
				/*&& null != loanInfo.getEducationDegree() && 1 == loanInfo.getEducateValidate()*//*无学历策略，不用判断学校判断学校是否空*/
				&& 12 >= loanInfo.getMonths() /*借款周期小于12个月*/
				&& 18 <= loanInfo.getRate() /*借款利率大于18*/
				&& 9000 >= loanInfo.getAmount()/*借款金额小于6000*/
				&& 0 <= loanInfo.getSuccessCount()/*成功借款次数大于1*/
				&& 0 >= loanInfo.getWasteCount()/*流标次数0*/
				&& 8 <= loanInfo.getNormalCount()/*正常还清次数大于8*/
				&& 0 >= loanInfo.getOverdueLessCount()/*15天内逾期0*/
				&& 0 >= loanInfo.getOverdueMoreCount()/*15天外逾期0*/
				&& 6000 > loanInfo.getOwingAmount()/*剩余金额小于6000*/
				&& 0.42 >= loanInfo.getOwingAmount() / loanInfo.getTotalPrincipal()/*待还金额/累计借款金额小于0.42*/
				&& 0.4 >= loanInfo.getOwingAmount() / loanInfo.getHighestDebt()/*待还金额/历史最高债小于0.4*/
				&& 1.1 >= (loanInfo.getAmount() + loanInfo.getOwingAmount()) / loanInfo.getHighestDebt()/*借后负债/最高债小于0.8*/
				&& 1.5 >= loanInfo.getAmount() / loanInfo.getHighestPrincipal()/*本次/最高单次小于1*/
				&& 10000 >= loanInfo.getAmount() + loanInfo.getOwingAmount()/*借后负债小于10000*/
				&& 0 < loanInfo.getRemainFunding()) {/*可投金额大于0*/
			
			Calendar c = Calendar.getInstance();
			c.setTime(loanInfo.getLastSuccessBorrowTime());
			c.add(Calendar.HOUR, 24*70);/*距最近借款时间大于93天*/
			if (c.getTime().getTime() <= new Date().getTime()) {
				return true;
			}
		}
		
		return false;
	}

	/*投标金额98 学历B 22 0天 创建日期：20180106*/
	public static boolean checkStrategy98(LoanInfo loanInfo) {
		if (("AAA".equals(loanInfo.getCreditCode()) || "AA".equals(loanInfo.getCreditCode())
				|| "A".equals(loanInfo.getCreditCode()) || "B".equals(loanInfo.getCreditCode()))/*等级*/
				&& 1 == loanInfo.getCertificateValidate() /*学历认证1*/
				&& null != loanInfo.getEducationDegree() && 1 == loanInfo.getEducateValidate()/*判断学校是否空*/
				&& 12 >= loanInfo.getMonths() /*借款周期小于12个月*/
				&& 22 <= loanInfo.getRate() /*借款利率大于22*/
				&& 40000 >= loanInfo.getAmount()/*借款金额小于40000*/
				&& 1 <= loanInfo.getSuccessCount()/*成功借款次数大于1*/
				&& 0 >= loanInfo.getWasteCount()/*流标次数0*/
				&& 3 <= loanInfo.getNormalCount()/*正常还清次数大于3*/
				&& 1 >= loanInfo.getOverdueLessCount()/*15天内逾期0*/
				&& 0 >= loanInfo.getOverdueMoreCount()/*15天外逾期0*/
				&& 16000 > loanInfo.getOwingAmount()/*剩余金额小于12000*/
				/*&& 0.9 >= loanInfo.getOwingAmount() / loanInfo.getTotalPrincipal()/*待还金额/累计借款金额小于0.9*/
				/*&& 0.7 >= loanInfo.getOwingAmount() / loanInfo.getHighestDebt()/*待还金额/历史最高债小于0,7*/
				&& 1 >= (loanInfo.getAmount() + loanInfo.getOwingAmount()) / loanInfo.getHighestDebt()/*本次借+待还/最高债小于1*/
				/*&& 3 >= loanInfo.getAmount() / loanInfo.getHighestPrincipal()本次/最高单次小于3*/
				&& 40000 >= loanInfo.getAmount() + loanInfo.getOwingAmount()/*借后负债小于40000*/
				&& 0 < loanInfo.getRemainFunding()) {/*可投金额大于0*/
			
			return true;
		}
		
		return false;
	}
	
	/*投标金额78 学历C 28 0天 创建日期：20180402*/
	public static boolean checkStrategy78(LoanInfo loanInfo) {
		if (("AAA".equals(loanInfo.getCreditCode()) || "AA".equals(loanInfo.getCreditCode())
				|| "A".equals(loanInfo.getCreditCode()) || "B".equals(loanInfo.getCreditCode())
				|| "C".equals(loanInfo.getCreditCode()) || "D".equals(loanInfo.getCreditCode()))/*等级*/
				&& 1 == loanInfo.getCertificateValidate() /*学历认证1*/
				&& null != loanInfo.getEducationDegree() && 1 == loanInfo.getEducateValidate()/*无学历策略，不用判断学校判断学校是否空*/
				&& 12 >= loanInfo.getMonths() /*借款周期小于12个月*/
				&& 26 <= loanInfo.getRate() /*借款利率大于26*/
				&& 30000 >= loanInfo.getAmount()/*借款金额小于30000*/
				&& 1 <= loanInfo.getSuccessCount()/*成功借款次数大于1*/
				&& 0 >= loanInfo.getWasteCount()/*流标次数0*/
				&& 5 <= loanInfo.getNormalCount()/*正常还清次数大于8*/
				&& 0 >= loanInfo.getOverdueLessCount()/*15天内逾期0*/
				&& 0 >= loanInfo.getOverdueMoreCount()/*15天外逾期0*/
				&& 15000 > loanInfo.getOwingAmount()/*剩余金额小于15000*/
				/*&& 0.42 >= loanInfo.getOwingAmount() / loanInfo.getTotalPrincipal()/*待还金额/累计借款金额小于0.42*/
				&& 0.8 >= loanInfo.getOwingAmount() / loanInfo.getHighestDebt()/*待还金额/历史最高债小于0.8*/
				/*&& 1.1 >= (loanInfo.getAmount() + loanInfo.getOwingAmount()) / loanInfo.getHighestDebt()/*借后负债/最高债小于0.8*/
				/*&& 1.5 >= loanInfo.getAmount() / loanInfo.getHighestPrincipal()/*本次/最高单次小于1*/
				&& 30000 >= loanInfo.getAmount() + loanInfo.getOwingAmount()/*借后负债小于30000*/
				&& 0 < loanInfo.getRemainFunding()) {/*可投金额大于0*/
			
			Calendar c = Calendar.getInstance();
			c.setTime(loanInfo.getLastSuccessBorrowTime());
			c.add(Calendar.HOUR, 24*60);/*距最近借款时间大于60天*/
			if (c.getTime().getTime() <= new Date().getTime()) {
				return true;
			}
		}
		
		return false;
	}
}