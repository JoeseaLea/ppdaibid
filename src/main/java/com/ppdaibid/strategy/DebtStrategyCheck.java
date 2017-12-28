package com.ppdaibid.strategy;

import java.util.Calendar;

import com.ppdaibid.info.DebtInfo;

public class DebtStrategyCheck {
	public static boolean checkStrategy1796(DebtInfo debtInfo) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -2);
		
		if (0.2 <= debtInfo.getPriceforSaleRate()  //转让利率大于等于20%
				&& 200 <= debtInfo.getOwingPrincipal() + debtInfo.getOwingInterest()  //剩下本息小于等于150元
				&& 5 <= debtInfo.getDays()  //距下次还款天数大于等于5天
				&& 0 >= debtInfo.getPastDueNumber()   //曾逾期次数小于等于0次
				&& (debtInfo.getCreditCode().equals("AA") || debtInfo.getCreditCode().equals("A") || debtInfo.getCreditCode().equals("B") || debtInfo.getCreditCode().equals("C"))  //魔镜等级为AA、A、B、C
				&& debtInfo.getCreditCode().toUpperCase().charAt(0) >= debtInfo.getCurrentCreditCode().toUpperCase().charAt(0)  //当前评级升级或者不变
				&& debtInfo.getMonths() <= 12 && debtInfo.getMonths() >= 1 //期限1到12个月
				&& debtInfo.getCertificateValidate() == 1 //学历认证
				&& debtInfo.getSuccessCount() >= 1 //成功借款次数
				&& debtInfo.getFailedCount() <=0 //借款失败次数
				&& debtInfo.getWasteCount() <=0 //借款流标次数
				&& debtInfo.getNormalCount() >=3 //正常还清次数次数
				&& debtInfo.getOverdueLessCount() <=0 //逾期还清次数(0-15天)
				&& debtInfo.getOverdueMoreCount() <=0 //逾期还清次数(＞15天)
				&& debtInfo.getOwingAmount() <=18000 //当前待还金额
				&& debtInfo.getLastSuccessBorrowTime().getTime() < c.getTime().getTime() //最后一次成功借款到现在≥2 个月
				&& debtInfo.getOwingAmount()/debtInfo.getHighestDebt() <=1.0 //最后一次成功借款到现在
				) {
			return true;
		}
		return false;
	}
	
	public static boolean checkStrategy1133(DebtInfo debtInfo) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -3);
		
		if (0.22 <= debtInfo.getPriceforSaleRate()  //转让利率大于等于20%
				&& 100 >= debtInfo.getOwingPrincipal() + debtInfo.getOwingInterest()  //剩下本息小于等于150元
				&& 5 <= debtInfo.getDays()  //距下次还款天数大于等于5天
				&& 0 >= debtInfo.getPastDueNumber()   //曾逾期次数小于等于0次
				&& (debtInfo.getCreditCode().equals("C") || debtInfo.getCreditCode().equals("D") || debtInfo.getCreditCode().equals("B"))  //魔镜等级为C、D、B
				&& (debtInfo.getCurrentCreditCode().equals("A") || debtInfo.getCreditCode().equals("B") || debtInfo.getCreditCode().equals("C") || debtInfo.getCreditCode().equals("D"))  //魔镜等级为C、D、B
				&& debtInfo.getCreditCode().toUpperCase().charAt(0) >= debtInfo.getCurrentCreditCode().toUpperCase().charAt(0)  //当前评级升级或者不变
				&& debtInfo.getMonths() <= 12 && debtInfo.getMonths() >= 1 //期限1到12个月
				&& debtInfo.getSuccessCount() >= 1 //成功借款次数
				&& debtInfo.getFailedCount() <=0 //借款失败次数
				&& debtInfo.getWasteCount() <=0 //借款流标次数
				&& debtInfo.getNormalCount() >=7 //正常还清次数次数
				&& debtInfo.getOverdueLessCount() <=0 //逾期还清次数(0-15天)
				&& debtInfo.getOverdueMoreCount() <=0 //逾期还清次数(＞15天)
				&& debtInfo.getOwingAmount() <=8000 //当前待还金额
				&& debtInfo.getLastSuccessBorrowTime().getTime() < c.getTime().getTime() //最后一次成功借款到现在≥3 个月
				&& debtInfo.getTotalPrincipal() >= 500 //当前待还金额
				&& debtInfo.getOwingAmount()/debtInfo.getHighestDebt() >= 0.1//待还金额/最大负债
				&& debtInfo.getOwingAmount()/debtInfo.getHighestDebt() <= 0.4//待还金额/最大负债
				) {
			return true;
		}
		return false;
	}
	
	public static boolean checkStrategy1648(DebtInfo debtInfo) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -2);
		
		if (0.22 <= debtInfo.getPriceforSaleRate()  //转让利率大于等于20%
				&& 200 >= debtInfo.getOwingPrincipal() + debtInfo.getOwingInterest()  //剩下本息小于等于200元
				&& 5 <= debtInfo.getDays()  //距下次还款天数大于等于5天
				&& 0 >= debtInfo.getPastDueNumber()   //曾逾期次数小于等于0次
				&& (debtInfo.getCreditCode().equals("A") || debtInfo.getCreditCode().equals("B") || debtInfo.getCreditCode().equals("C") || debtInfo.getCreditCode().equals("D"))  //魔镜等级为A、B、C、D
				&& debtInfo.getCreditCode().toUpperCase().charAt(0) >= debtInfo.getCurrentCreditCode().toUpperCase().charAt(0)  //当前评级升级或者不变
				&& debtInfo.getCertificateValidate() == 1 //学历认证
				&& debtInfo.getSuccessCount() >= 1 //成功借款次数
				&& debtInfo.getFailedCount() <=0 //借款失败次数
				&& debtInfo.getWasteCount() <=0 //借款流标次数
				&& debtInfo.getNormalCount() >=3 //正常还清次数次数
				&& debtInfo.getOverdueLessCount() <=0 //逾期还清次数(0-15天)
				&& debtInfo.getOverdueMoreCount() <=0 //逾期还清次数(＞15天)
				&& debtInfo.getOwingAmount() <=15000 //当前待还金额
				&& debtInfo.getLastSuccessBorrowTime().getTime() < c.getTime().getTime() //最后一次成功借款到现在≥2 个月
				&& debtInfo.getOwingAmount()/debtInfo.getHighestDebt() <= 1.0//待还金额/最大负债
				) {
			return true;
		}
		return false;
	}
	
	public static boolean checkStrategy1795(DebtInfo debtInfo) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -2);
		
		if (0.20 <= debtInfo.getPriceforSaleRate()  //转让利率大于等于20%
				&& 200 >= debtInfo.getOwingPrincipal() + debtInfo.getOwingInterest()  //剩下本息小于等于200元
				&& 5 <= debtInfo.getDays()  //距下次还款天数大于等于5天
				&& 0 >= debtInfo.getPastDueNumber()   //曾逾期次数小于等于0次
				&& (debtInfo.getCreditCode().equals("AA") || debtInfo.getCreditCode().equals("A") || debtInfo.getCreditCode().equals("B") || debtInfo.getCreditCode().equals("C"))  //魔镜等级为AA、A、B、C
				&& debtInfo.getCreditCode().toUpperCase().charAt(0) >= debtInfo.getCurrentCreditCode().toUpperCase().charAt(0)  //当前评级升级或者不变
				&& debtInfo.getMonths() <= 12 && debtInfo.getMonths() >= 1 //期限1到12个月
				&& debtInfo.getCertificateValidate() == 1 //学历认证
				&& debtInfo.getSuccessCount() >= 1 //成功借款次数
				&& debtInfo.getFailedCount() <=0 //借款失败次数
				&& debtInfo.getWasteCount() <=0 //借款流标次数
				&& debtInfo.getNormalCount() >=3 //正常还清次数次数
				&& debtInfo.getOverdueLessCount() <=0 //逾期还清次数(0-15天)
				&& debtInfo.getOverdueMoreCount() <=0 //逾期还清次数(＞15天)
				&& debtInfo.getOwingAmount() <=18000 //当前待还金额
				&& debtInfo.getLastSuccessBorrowTime().getTime() < c.getTime().getTime() //最后一次成功借款到现在≥2 个月
				&& debtInfo.getOwingAmount()/debtInfo.getHighestDebt() <= 1.0//待还金额/最大负债
				) {
			return true;
		}
		return false;
	}
	
	public static boolean checkStrategy1777(DebtInfo debtInfo) {
		if (0.13 <= debtInfo.getPriceforSaleRate()  //转让利率大于等于13%
				&& debtInfo.getCreditCode().equals("AA")  //魔镜等级：AA
				) {
			return true;
		}
		return false;
	}
	
	
	public static boolean checkStrategy1978(DebtInfo debtInfo) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -3);
		
		if (0.20 <= debtInfo.getPriceforSaleRate()  //转让利率大于等于20%
				&& 100 >= debtInfo.getOwingPrincipal() + debtInfo.getOwingInterest()  //剩下本息小于等于200元
				&& 5 <= debtInfo.getDays()  //距下次还款天数大于等于5天
				&& 0 >= debtInfo.getPastDueNumber()   //曾逾期次数小于等于0次
				&& (debtInfo.getCreditCode().equals("AA") || debtInfo.getCreditCode().equals("A") || debtInfo.getCreditCode().equals("B") || debtInfo.getCreditCode().equals("C"))  //魔镜等级为AA、A、B、C
				&& (debtInfo.getCurrentCreditCode().equals("A") || debtInfo.getCurrentCreditCode().equals("B") || debtInfo.getCurrentCreditCode().equals("C"))  //魔镜等级为A、B、C
				&& debtInfo.getCreditCode().toUpperCase().charAt(0) >= debtInfo.getCurrentCreditCode().toUpperCase().charAt(0)  //当前评级升级或者不变
				&& debtInfo.getMonths() <= 12 && debtInfo.getMonths() >= 1 //期限1到12个月
				&& debtInfo.getSuccessCount() >= 1 //成功借款次数
				&& debtInfo.getFailedCount() <=0 //借款失败次数
				&& debtInfo.getWasteCount() <=0 //借款流标次数
				&& debtInfo.getNormalCount() >=7 //正常还清次数次数
				&& debtInfo.getOverdueLessCount() <=0 //逾期还清次数(0-15天)
				&& debtInfo.getOverdueMoreCount() <=0 //逾期还清次数(＞15天)
				&& debtInfo.getOwingAmount() <=8000 //当前待还金额
				&& debtInfo.getLastSuccessBorrowTime().getTime() < c.getTime().getTime() //最后一次成功借款到现在≥3 个月
				&& debtInfo.getOwingAmount()/debtInfo.getHighestDebt() <= 0.4//待还金额/最大负债
				) {
			return true;
		}
		return false;
	}
	
	public static boolean checkStrategy2156(DebtInfo debtInfo) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -2);
		
		if (0.18 <= debtInfo.getPriceforSaleRate()  //转让利率大于等于20%
				&& 1 <= debtInfo.getOwingNumber() && debtInfo.getOwingNumber() <= 12  //剩下期数：1 - 12 个月
				&& 200 >= debtInfo.getOwingPrincipal() + debtInfo.getOwingInterest()  //剩下本息小于等于200元
				&& 5 <= debtInfo.getDays()  //距下次还款天数大于等于5天
				&& 0 >= debtInfo.getPastDueNumber()   //曾逾期次数小于等于0次
				&& (debtInfo.getCreditCode().equals("A") || debtInfo.getCreditCode().equals("B"))  //魔镜等级为A、B
				&& debtInfo.getCreditCode().toUpperCase().charAt(0) >= debtInfo.getCurrentCreditCode().toUpperCase().charAt(0)  //当前评级升级或者不变
				&& debtInfo.getMonths() <= 12 && debtInfo.getMonths() >= 1 //期限1到12个月
				&& debtInfo.getCertificateValidate() == 1 //学历认证
				&& debtInfo.getWasteCount() <=0 //借款流标次数
				&& debtInfo.getOverdueLessCount() <=0 //逾期还清次数(0-15天)
				&& debtInfo.getOverdueMoreCount() <=0 //逾期还清次数(＞15天)
				&& debtInfo.getOwingAmount() <=18000 //当前待还金额
				&& debtInfo.getLastSuccessBorrowTime().getTime() < c.getTime().getTime() //最后一次成功借款到现在≥2 个月
				&& debtInfo.getOwingAmount()/debtInfo.getHighestDebt() <= 1.0 //待还金额/最大负债
				) {
			return true;
		}
		return false;
	}
}
