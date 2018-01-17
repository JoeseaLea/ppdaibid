package com.ppdaibid.strategy;

import java.util.Calendar;

import com.ppdaibid.info.DebtInfo;

public class DebtStrategyCheck {
	
	/* 20180106 学历 22 ABCD 无逾期 */
	public static boolean checkStrategy1648(DebtInfo debtInfo) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -2);
		
		if (22 <= debtInfo.getPriceforSaleRate()  //转让利率大于等于22%
				&& 200 >= debtInfo.getPriceforSale()  //转让价小于等于200元
				&& 6 <= debtInfo.getDays()  //距下次还款天数大于等于6天
				&& 0 >= debtInfo.getPastDueNumber()   //曾逾期次数小于等于0次
				&& (debtInfo.getCreditCode().equals("A") || debtInfo.getCreditCode().equals("B") || debtInfo.getCreditCode().equals("C") || debtInfo.getCreditCode().equals("D"))  //魔镜等级为A、B、C、D
				&& CreditCode2Int(debtInfo.getCreditCode().toUpperCase()) <= CreditCode2Int(debtInfo.getCurrentCreditCode().toUpperCase())  //当前评级升级或者不变
				&& debtInfo.getCertificateValidate() == 1 //学历认证
				&& debtInfo.getSuccessCount() >= 1 //成功借款次数
				&& debtInfo.getFailedCount() <=0 //借款失败次数
				&& debtInfo.getWasteCount() <=0 //借款流标次数
				&& debtInfo.getNormalCount() >= 5 //正常还清次数次数
				&& debtInfo.getOverdueLessCount() <=0 //逾期还清次数(0-15天)
				&& debtInfo.getOverdueMoreCount() <=0 //逾期还清次数(＞15天)
				&& debtInfo.getOwingAmount() <=18000 //当前待还金额
				&& debtInfo.getLastSuccessBorrowTime().getTime() < c.getTime().getTime() //最后一次成功借款到现在≥2 个月
				&& debtInfo.getOwingAmount()/debtInfo.getHighestDebt() <= 0.9//待还金额/最大负债
				) {
			return true;
		}
		
		return false;
	}
	
	/* 20180106 学历 20 ABC 无逾期 */
	public static boolean checkStrategy1795(DebtInfo debtInfo) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -2);
		
		if (20 <= debtInfo.getPriceforSaleRate()  //转让利率大于等于20%
				&& 200 >= debtInfo.getPriceforSale()  //转让价小于等于200元
				&& 6 <= debtInfo.getDays()  //距下次还款天数大于等于6天
				&& 0 >= debtInfo.getPastDueNumber()   //曾逾期次数小于等于0次
				&& (debtInfo.getCreditCode().equals("AA") || debtInfo.getCreditCode().equals("A") || debtInfo.getCreditCode().equals("B") || debtInfo.getCreditCode().equals("C"))  //魔镜等级为AA、A、B、C
				&& CreditCode2Int(debtInfo.getCreditCode().toUpperCase()) <= CreditCode2Int(debtInfo.getCurrentCreditCode().toUpperCase())  //当前评级升级或者不变
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
	
	/* 20180106 学历 18 AB 0次逾期 */
	public static boolean checkStrategy2156(DebtInfo debtInfo) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -2);
		
		if (18 <= debtInfo.getPriceforSaleRate()  //转让利率大于等于18%
				&& 1 <= debtInfo.getOwingNumber() && debtInfo.getOwingNumber() <= 12  //剩下期数：1 - 12 个月
				&& 200 >= debtInfo.getPriceforSale()  //转让价小于等于200元
				&& 6 <= debtInfo.getDays()  //距下次还款天数大于等于5天
				&& 0 >= debtInfo.getPastDueNumber()   //曾逾期次数小于等于1次
				&& (debtInfo.getCreditCode().equals("A") || debtInfo.getCreditCode().equals("B"))  //魔镜等级为A、B
				&& CreditCode2Int(debtInfo.getCreditCode().toUpperCase()) <= CreditCode2Int(debtInfo.getCurrentCreditCode().toUpperCase())  //当前评级升级或者不变
				&& debtInfo.getMonths() <= 12 && debtInfo.getMonths() >= 1 //期限1到12个月
				&& debtInfo.getCertificateValidate() == 1 //学历认证
				&& debtInfo.getWasteCount() <=0 //借款流标次数
				&& debtInfo.getOverdueLessCount() <=0 //逾期还清次数(0-15天)
				&& debtInfo.getOverdueMoreCount() <=0 //逾期还清次数(＞15天)
				&& debtInfo.getOwingAmount() <=20000 //当前待还金额
				&& debtInfo.getLastSuccessBorrowTime().getTime() < c.getTime().getTime() //最后一次成功借款到现在≥2 个月
				&& debtInfo.getOwingAmount()/debtInfo.getHighestDebt() <= 1.0 //待还金额/最大负债
				) {
			return true;
		}
		
		return false;
	}
	
		/* 20180106 学历 >0.19 AB 1次逾期 */
	public static boolean checkStrategy3645(DebtInfo debtInfo) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -2);
		
		if (19 <= debtInfo.getPriceforSaleRate()  //转让利率大于等于19%
				&& 1 <= debtInfo.getOwingNumber() && debtInfo.getOwingNumber() <= 12  //剩下期数：1 - 12 个月
				&& 150 >= debtInfo.getPriceforSale()  //转让价小于等于200元
				&& 6 <= debtInfo.getDays()  //距下次还款天数大于等于5天
				&& 1 >= debtInfo.getPastDueNumber()   //曾逾期次数小于等于1次
				&& (debtInfo.getCreditCode().equals("A") || debtInfo.getCreditCode().equals("B"))  //魔镜等级为A、B
				&& CreditCode2Int(debtInfo.getCreditCode().toUpperCase()) <= CreditCode2Int(debtInfo.getCurrentCreditCode().toUpperCase())  //当前评级升级或者不变
				&& debtInfo.getMonths() <= 12 && debtInfo.getMonths() >= 1 //期限1到12个月
				&& debtInfo.getCertificateValidate() == 1 //学历认证
				&& debtInfo.getWasteCount() <=0 //借款流标次数
				&& debtInfo.getNormalCount() >=5 //正常还清次数次数
				&& debtInfo.getOverdueLessCount() <=1 //逾期还清次数(0-15天)
				&& debtInfo.getOverdueMoreCount() <=0 //逾期还清次数(＞15天)
				&& debtInfo.getOwingAmount() <=20000 //当前待还金额
				&& debtInfo.getLastSuccessBorrowTime().getTime() < c.getTime().getTime() //最后一次成功借款到现在≥2 个月
				&& debtInfo.getOwingAmount()/debtInfo.getHighestDebt() <= 1 //待还金额/最大负债
				) {
			return true;
		}
		
		return false;
	}
	
		/* 20180106 学历 21 ABC 逾期1 */
	public static boolean checkStrategy3646(DebtInfo debtInfo) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -2);
		
		if (21 <= debtInfo.getPriceforSaleRate()  //转让利率大于等于21%
				&& 100 >= debtInfo.getPriceforSale()  //转让价小于等于200元
				&& 6 <= debtInfo.getDays()  //距下次还款天数大于等于6天
				&& 1 >= debtInfo.getPastDueNumber()   //曾逾期次数小于等于1次
				&& (debtInfo.getCreditCode().equals("AA") || debtInfo.getCreditCode().equals("A") || debtInfo.getCreditCode().equals("B") || debtInfo.getCreditCode().equals("C"))  //魔镜等级为AA、A、B、C
				&& CreditCode2Int(debtInfo.getCreditCode().toUpperCase()) <= CreditCode2Int(debtInfo.getCurrentCreditCode().toUpperCase())  //当前评级升级或者不变
				&& debtInfo.getMonths() <= 12 && debtInfo.getMonths() >= 1 //期限1到12个月
				&& debtInfo.getCertificateValidate() == 1 //学历认证
				&& debtInfo.getSuccessCount() >= 1 //成功借款次数
				&& debtInfo.getFailedCount() <=0 //借款失败次数
				&& debtInfo.getWasteCount() <=0 //借款流标次数
				&& debtInfo.getNormalCount() >=5 //正常还清次数次数
				&& debtInfo.getOverdueLessCount() <=1 //逾期还清次数(0-15天)
				&& debtInfo.getOverdueMoreCount() <=0 //逾期还清次数(＞15天)
				&& debtInfo.getOwingAmount() <=18000 //当前待还金额
				&& debtInfo.getLastSuccessBorrowTime().getTime() < c.getTime().getTime() //最后一次成功借款到现在≥2 个月
				&& debtInfo.getOwingAmount()/debtInfo.getHighestDebt() <= 0.9//待还金额/最大负债
				) {
			return true;
		}
		
		return false;
	}
	
	
	public static boolean checkStrategy1777(DebtInfo debtInfo) {
		if (13 <= debtInfo.getPriceforSaleRate()  //转让利率大于等于13%
				&& debtInfo.getCreditCode().equals("AA")  //魔镜等级：AA
				) {
			return true;
		}
		
		return false;
	}
	
	/* 20180106 无学历 22 ABCD 无逾期 */
	/*public static boolean checkStrategy1133(DebtInfo debtInfo) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -3);
		
		if (22 <= debtInfo.getPriceforSaleRate()  //转让利率大于等于22%
				&& 100 >= debtInfo.getPriceforSale()  //转让价小于等于100元
				&& 6 <= debtInfo.getDays()  //距下次还款天数大于等于5天
				&& 0 >= debtInfo.getPastDueNumber()   //曾逾期次数小于等于0次
				&& (debtInfo.getCreditCode().equals("C") || debtInfo.getCreditCode().equals("D") || debtInfo.getCreditCode().equals("B"))  //魔镜等级为C、D、B
				&& (debtInfo.getCurrentCreditCode().equals("A") || debtInfo.getCreditCode().equals("B") || debtInfo.getCreditCode().equals("C") || debtInfo.getCreditCode().equals("D"))  //魔镜等级为C、D、B
				&& CreditCode2Int(debtInfo.getCreditCode().toUpperCase()) <= CreditCode2Int(debtInfo.getCurrentCreditCode().toUpperCase())  //当前评级升级或者不变
				&& debtInfo.getMonths() <= 12 && debtInfo.getMonths() >= 1 //期限1到12个月
				&& debtInfo.getSuccessCount() >= 1 //成功借款次数
				&& debtInfo.getFailedCount() <=0 //借款失败次数
				&& debtInfo.getWasteCount() <=0 //借款流标次数
				&& debtInfo.getNormalCount() >=7 //正常还清次数次数
				&& debtInfo.getOverdueLessCount() <=0 //逾期还清次数(0-15天)
				&& debtInfo.getOverdueMoreCount() <=0 //逾期还清次数(＞15天)
				&& debtInfo.getOwingAmount() <=8000 //当前待还金额
				&& debtInfo.getLastSuccessBorrowTime().getTime() < c.getTime().getTime() //最后一次成功借款到现在≥3 个月
				&& debtInfo.getTotalPrincipal() >= 500 //累计借款金额
				&& debtInfo.getOwingAmount()/debtInfo.getHighestDebt() <= 0.4//待还金额/最大负债
				) {
			return true;
		}
		
		return false;
	}*/
	
	/* 20180106 无学历 20 ABC 无逾期 */
	/*public static boolean checkStrategy1978(DebtInfo debtInfo) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -3);
		
		if (20 <= debtInfo.getPriceforSaleRate()  //转让利率大于等于20%
				&& 100 >= debtInfo.getPriceforSale()  //转让价小于等于100元
				&& 6 <= debtInfo.getDays()  //距下次还款天数大于等于5天
				&& 0 >= debtInfo.getPastDueNumber()   //曾逾期次数小于等于0次
				&& (debtInfo.getCreditCode().equals("AA") || debtInfo.getCreditCode().equals("A") || debtInfo.getCreditCode().equals("B") || debtInfo.getCreditCode().equals("C"))  //魔镜等级为AA、A、B、C
				&& (debtInfo.getCurrentCreditCode().equals("A") || debtInfo.getCurrentCreditCode().equals("B") || debtInfo.getCurrentCreditCode().equals("C"))  //魔镜等级为A、B、C
				&& CreditCode2Int(debtInfo.getCreditCode().toUpperCase()) <= CreditCode2Int(debtInfo.getCurrentCreditCode().toUpperCase())  //当前评级升级或者不变
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
	}*/
	
	public static int CreditCode2Int(String creditCode) {
		if (creditCode.equals("AAAA")) {
			return 12;
		} else if (creditCode.equals("AAA")) {
			return 11;
		} else if (creditCode.equals("AA")) {
			return 10;
		} else if (creditCode.equals("A")) {
			return 9;
		} else if (creditCode.equals("B")) {
			return 8;
		} else if (creditCode.equals("C")) {
			return 7;
		} else if (creditCode.equals("D")) {
			return 6;
		} else if (creditCode.equals("E")) {
			return 5;
		} else if (creditCode.equals("F")) {
			return 4;
		} else if (creditCode.equals("G")) {
			return 3;
		} else if (creditCode.equals("H")) {
			return 2;
		} else if (creditCode.equals("I")) {
			return 1;
		}
		return 0;
	}
}
