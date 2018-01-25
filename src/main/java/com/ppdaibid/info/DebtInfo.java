package com.ppdaibid.info;

import java.util.Date;

import org.json.JSONObject;

import com.ppdaibid.utils.DateFormateUtil;
import com.ppdaibid.utils.JsonUtil;

public class DebtInfo {
	/**
	 * 债权ID
	 */
	private int debtdealId;
	/**
	 * 剩余期数
	 */
	private int owingNumber;
	/**
	 * 转让价
	 */
	private double priceforSale;
	/**
	 * 列表ID
	 */
	private int listingId;
	/**
	 * 列表等级
	 */
	private String creditCode;
	/**
	 * 借款金额
	 */
	private double amount;
	/**
	 * 期限
	 */
	private int months;
	/**
	 * 债转编号
	 */
	private int debtId;
	/**
	 * 转出人
	 */
	private String seller;
	/**
	 * 1出售中2已完成 0其它无效状态
	 */
	private int statusId;
	/**
	 * 投标人
	 */
	private String lender;
	/**
	 * 投标时间
	 */
	private Date bidDateTime;
	/**
	 * 待还本金
	 */
	private double owingPrincipal;
	/**
	 * 待收利息
	 */
	private double owingInterest;
	/**
	 * 距离下次还款的天数
	 */
	private int days;
	/**
	 * 转让价利率
	 */
	private double priceforSaleRate;
	/**
	 * 优惠度
	 */
	private double preferenceDegree;
	/**
	 * 发标金额
	 */
	private double listingAmount;
	/**
	 * 标期限
	 */
	private int listingMonths;
	/**
	 * 发标时间
	 */
	private Date listingTime;
	/**
	 * 标的利率
	 */
	private double listingRate;
	/**
	 * 曾逾期期数
	 */
	private int pastDueNumber;
	/**
	 * 当前评级
	 */
	private String currentCreditCode;
	/**
	 * 折让比例（%号之前的数值）
	 */
	private double allowanceRadio;
	/**
	 * 曾最大逾期天数
	 */
	private int pastDueDay;
	
	/**
	 * 首次投资时间
	 */
	private Date fistBidTime;
	/**
	 * 末笔投资时间
	 */
	private Date lastBidTime;
	/**
	 * 投标人数
	 */
	private int lenderCount;
	/**
	 * 成交日期
	 */
	private Date auditingTime;
	/**
	 * 可投标金额
	 */
	private double remainFunding;
	
	/**
	 * 截止时间
	 */
	private String deadLineTimeOrRemindTimeStr;
	/**
	 * 利率
	 */
	private double currentRate;
	/**
	 * 借款人的用户名
	 */
	private String borrowName;
	/**
	 * 性别 1 男 2 女 0 未知
	 */
	private int gender;
	/**
	 * 学历
	 */
	private String educationDegree;
	/**
	 * 毕业院校
	 */
	private String graduateSchool;
	/**
	 * 学习形式
	 */
	private String studyStyle;
	/**
	 * 年龄
	 */
	private int age;
	/**
	 * 成功借款次数
	 */
	private int successCount;
	/**
	 * 流标次数
	 */
	private int wasteCount;
	/**
	 * 撤标次数
	 */
	private int cancelCount;
	/**
	 * 失败次数
	 */
	private int failedCount;
	/**
	 * 正常还清次数
	 */
	private int normalCount;
	/**
	 * 逾期(1-15)还清次数
	 */
	private int overdueLessCount;
	/**
	 * 逾期(15天以上)还清次数
	 */
	private int overdueMoreCount;
	/**
	 * 待还金额
	 */
	private double owingAmount;
	/**
	 * 待收金额
	 */
	private double amountToReceive;
	/**
	 * 第一次成功借款时间
	 */
	private Date firstSuccessBorrowTime;
	/**
	 * 注册时间
	 */
	private Date registerTime;
	/**
	 * 学历认证 0 未认证 1已认证
	 */
	private int certificateValidate;
	/**
	 * 户籍认证 0 未认证 1已认证
	 */
	private int nciicIdentityCheck;
	/**
	 * 手机认证 0 未认证 1已认证
	 */
	private int phoneValidate;
	/**
	 * 视屏认证 0 未认证 1已认证
	 */
	private int videoValidate;
	/**
	 * 征信认证 0 未认证 1已认证
	 */
	private int creditValidate;
	/**
	 * 学籍认证 0 未认证 1已认证
	 */
	private int educateValidate;
	/**
	 * 最后一次成功借款时间
	 */
	private Date lastSuccessBorrowTime;
	/**
	 * 单笔最高借款金额
	 */
	private double highestPrincipal;
	/**
	 * 历史最高负债
	 */
	private double highestDebt;
	/**
	 * 累计借款金额
	 */
	private double totalPrincipal;
	/**
	 * 数据爬取时间
	 */
	private Date insertTime;
	/**
	 * 数据最后更新时间
	 */
	private Date lastupdateTime;
	/**
	 * 是否已投标（0:未投标， 1:已投标）
	 */
	private boolean isBid;
	
	public int getDebtdealId() {
		return debtdealId;
	}
	public void setDebtdealId(int debtdealId) {
		this.debtdealId = debtdealId;
	}
	public int getOwingNumber() {
		return owingNumber;
	}
	public void setOwingNumber(int owingNumber) {
		this.owingNumber = owingNumber;
	}
	public double getPriceforSale() {
		return priceforSale;
	}
	public void setPriceforSale(double priceforSale) {
		this.priceforSale = priceforSale;
	}
	public int getListingId() {
		return listingId;
	}
	public void setListingId(int listingId) {
		this.listingId = listingId;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public int getDebtId() {
		return debtId;
	}
	public void setDebtId(int debtId) {
		this.debtId = debtId;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getLender() {
		return lender;
	}
	public void setLender(String lender) {
		this.lender = lender;
	}
	public Date getBidDateTime() {
		return bidDateTime;
	}
	public void setBidDateTime(Date bidDateTime) {
		this.bidDateTime = bidDateTime;
	}
	public double getOwingPrincipal() {
		return owingPrincipal;
	}
	public void setOwingPrincipal(double owingPrincipal) {
		this.owingPrincipal = owingPrincipal;
	}
	public double getOwingInterest() {
		return owingInterest;
	}
	public void setOwingInterest(double owingInterest) {
		this.owingInterest = owingInterest;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public double getPriceforSaleRate() {
		return priceforSaleRate;
	}
	public void setPriceforSaleRate(double priceforSaleRate) {
		this.priceforSaleRate = priceforSaleRate;
	}
	public double getPreferenceDegree() {
		return preferenceDegree;
	}
	public void setPreferenceDegree(double preferenceDegree) {
		this.preferenceDegree = preferenceDegree;
	}
	public double getListingAmount() {
		return listingAmount;
	}
	public void setListingAmount(double listingAmount) {
		this.listingAmount = listingAmount;
	}
	public int getListingMonths() {
		return listingMonths;
	}
	public void setListingMonths(int listingMonths) {
		this.listingMonths = listingMonths;
	}
	public Date getListingTime() {
		return listingTime;
	}
	public void setListingTime(Date listingTime) {
		this.listingTime = listingTime;
	}
	public double getListingRate() {
		return listingRate;
	}
	public void setListingRate(double listingRate) {
		this.listingRate = listingRate;
	}
	public int getPastDueNumber() {
		return pastDueNumber;
	}
	public void setPastDueNumber(int pastDueNumber) {
		this.pastDueNumber = pastDueNumber;
	}
	public String getCurrentCreditCode() {
		return currentCreditCode;
	}
	public void setCurrentCreditCode(String currentCreditCode) {
		this.currentCreditCode = currentCreditCode;
	}
	public double getAllowanceRadio() {
		return allowanceRadio;
	}
	public void setAllowanceRadio(double allowanceRadio) {
		this.allowanceRadio = allowanceRadio;
	}
	public int getPastDueDay() {
		return pastDueDay;
	}
	public void setPastDueDay(int pastDueDay) {
		this.pastDueDay = pastDueDay;
	}
	public Date getFistBidTime() {
		return fistBidTime;
	}
	public void setFistBidTime(Date fistBidTime) {
		this.fistBidTime = fistBidTime;
	}
	public Date getLastBidTime() {
		return lastBidTime;
	}
	public void setLastBidTime(Date lastBidTime) {
		this.lastBidTime = lastBidTime;
	}
	public int getLenderCount() {
		return lenderCount;
	}
	public void setLenderCount(int lenderCount) {
		this.lenderCount = lenderCount;
	}
	public Date getAuditingTime() {
		return auditingTime;
	}
	public void setAuditingTime(Date auditingTime) {
		this.auditingTime = auditingTime;
	}
	public String getDeadLineTimeOrRemindTimeStr() {
		return deadLineTimeOrRemindTimeStr;
	}
	public void setDeadLineTimeOrRemindTimeStr(String deadLineTimeOrRemindTimeStr) {
		this.deadLineTimeOrRemindTimeStr = deadLineTimeOrRemindTimeStr;
	}
	public double getCurrentRate() {
		return currentRate;
	}
	public void setCurrentRate(double currentRate) {
		this.currentRate = currentRate;
	}
	public String getBorrowName() {
		return borrowName;
	}
	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getEducationDegree() {
		return educationDegree;
	}
	public void setEducationDegree(String educationDegree) {
		this.educationDegree = educationDegree;
	}
	public String getGraduateSchool() {
		return graduateSchool;
	}
	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}
	public String getStudyStyle() {
		return studyStyle;
	}
	public void setStudyStyle(String studyStyle) {
		this.studyStyle = studyStyle;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}
	public int getWasteCount() {
		return wasteCount;
	}
	public void setWasteCount(int wasteCount) {
		this.wasteCount = wasteCount;
	}
	public int getCancelCount() {
		return cancelCount;
	}
	public void setCancelCount(int cancelCount) {
		this.cancelCount = cancelCount;
	}
	public int getFailedCount() {
		return failedCount;
	}
	public void setFailedCount(int failedCount) {
		this.failedCount = failedCount;
	}
	public int getNormalCount() {
		return normalCount;
	}
	public void setNormalCount(int normalCount) {
		this.normalCount = normalCount;
	}
	public int getOverdueLessCount() {
		return overdueLessCount;
	}
	public void setOverdueLessCount(int overdueLessCount) {
		this.overdueLessCount = overdueLessCount;
	}
	public int getOverdueMoreCount() {
		return overdueMoreCount;
	}
	public void setOverdueMoreCount(int overdueMoreCount) {
		this.overdueMoreCount = overdueMoreCount;
	}
	public double getOwingAmount() {
		return owingAmount;
	}
	public void setOwingAmount(double owingAmount) {
		this.owingAmount = owingAmount;
	}
	public double getAmountToReceive() {
		return amountToReceive;
	}
	public void setAmountToReceive(double amountToReceive) {
		this.amountToReceive = amountToReceive;
	}
	public Date getFirstSuccessBorrowTime() {
		return firstSuccessBorrowTime;
	}
	public void setFirstSuccessBorrowTime(Date firstSuccessBorrowTime) {
		this.firstSuccessBorrowTime = firstSuccessBorrowTime;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public int getCertificateValidate() {
		return certificateValidate;
	}
	public void setCertificateValidate(int certificateValidate) {
		this.certificateValidate = certificateValidate;
	}
	public int getNciicIdentityCheck() {
		return nciicIdentityCheck;
	}
	public void setNciicIdentityCheck(int nciicIdentityCheck) {
		this.nciicIdentityCheck = nciicIdentityCheck;
	}
	public int getPhoneValidate() {
		return phoneValidate;
	}
	public void setPhoneValidate(int phoneValidate) {
		this.phoneValidate = phoneValidate;
	}
	public int getVideoValidate() {
		return videoValidate;
	}
	public void setVideoValidate(int videoValidate) {
		this.videoValidate = videoValidate;
	}
	public int getCreditValidate() {
		return creditValidate;
	}
	public void setCreditValidate(int creditValidate) {
		this.creditValidate = creditValidate;
	}
	public int getEducateValidate() {
		return educateValidate;
	}
	public void setEducateValidate(int educateValidate) {
		this.educateValidate = educateValidate;
	}
	public Date getLastSuccessBorrowTime() {
		return lastSuccessBorrowTime;
	}
	public void setLastSuccessBorrowTime(Date lastSuccessBorrowTime) {
		this.lastSuccessBorrowTime = lastSuccessBorrowTime;
	}
	public double getHighestPrincipal() {
		return highestPrincipal;
	}
	public void setHighestPrincipal(double highestPrincipal) {
		this.highestPrincipal = highestPrincipal;
	}
	public double getHighestDebt() {
		return highestDebt;
	}
	public void setHighestDebt(double highestDebt) {
		this.highestDebt = highestDebt;
	}
	public double getTotalPrincipal() {
		return totalPrincipal;
	}
	public void setTotalPrincipal(double totalPrincipal) {
		this.totalPrincipal = totalPrincipal;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
	}
	public double getRemainFunding() {
		return remainFunding;
	}
	public void setRemainFunding(double remainFunding) {
		this.remainFunding = remainFunding;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Date getLastupdateTime() {
		return lastupdateTime;
	}
	public void setLastupdateTime(Date lastupdateTime) {
		this.lastupdateTime = lastupdateTime;
	}
	public boolean isBid() {
		return isBid;
	}
	public void setBid(boolean isBid) {
		this.isBid = isBid;
	}
	
	public void setDebtList(JSONObject jsonDebtInfo) {
		debtdealId = jsonDebtInfo.getInt("DebtdealId");
		owingNumber = JsonUtil.getInt(jsonDebtInfo, "OwingNumber", 0);
		priceforSaleRate = JsonUtil.getDouble(jsonDebtInfo, "PriceforSaleRate", 0);
		priceforSale = JsonUtil.getDouble(jsonDebtInfo, "PriceforSale", 0);
		listingId = JsonUtil.getInt(jsonDebtInfo, "ListingId", 0);
		creditCode = JsonUtil.getString(jsonDebtInfo, "CreditCode", null);
	}
	public void setDebtInfo(JSONObject jsonDebtInfo) {
		debtId = jsonDebtInfo.getInt("DebtId");
		seller = JsonUtil.getString(jsonDebtInfo, "Seller", null);
		statusId = JsonUtil.getInt(jsonDebtInfo, "StatusId", 0);
		lender = JsonUtil.getString(jsonDebtInfo, "Lender", null);
		lender = JsonUtil.getString(jsonDebtInfo, "Lender", null);
		bidDateTime = DateFormateUtil.formateDate(JsonUtil.getString(jsonDebtInfo, "BidDateTime", null));
//		owingNumber = JsonUtil.getInt(jsonDebtInfo, "OwingNumber", 0);
		owingPrincipal = JsonUtil.getDouble(jsonDebtInfo, "OwingPrincipal", 0);
		owingInterest = JsonUtil.getDouble(jsonDebtInfo, "OwingInterest", 0);
		days = JsonUtil.getInt(jsonDebtInfo, "Days", 0);
//		priceforSaleRate = JsonUtil.getDouble(jsonDebtInfo, "PriceforSaleRate", 0);
//		priceforSale = JsonUtil.getDouble(jsonDebtInfo, "PriceforSale", 0);
		preferenceDegree = JsonUtil.getDouble(jsonDebtInfo, "PreferenceDegree", 0);
//		listingId = JsonUtil.getInt(jsonDebtInfo, "ListingId", 0);
//		creditCode = JsonUtil.getString(jsonDebtInfo, "CreditCode", null);
		listingAmount = JsonUtil.getDouble(jsonDebtInfo, "ListingAmount", 0);
		listingMonths = JsonUtil.getInt(jsonDebtInfo, "ListingMonths", 0);
		listingTime = DateFormateUtil.formateDate(JsonUtil.getString(jsonDebtInfo, "ListingTime", null));
		listingRate = JsonUtil.getDouble(jsonDebtInfo, "ListingRate", 0);
		pastDueNumber = JsonUtil.getInt(jsonDebtInfo, "PastDueNumber", 0);
		currentCreditCode = JsonUtil.getString(jsonDebtInfo, "CurrentCreditCode", null);
		allowanceRadio = JsonUtil.getDouble(jsonDebtInfo, "AllowanceRadio", 0);
		pastDueDay = JsonUtil.getInt(jsonDebtInfo, "PastDueDay", 0);
	}
	
public void setListingInfo(JSONObject listingInfos) {
		
		fistBidTime = DateFormateUtil.formateDate(JsonUtil.getString(listingInfos, "FistBidTime", null));
		lastBidTime = DateFormateUtil.formateDate(JsonUtil.getString(listingInfos, "LastBidTime", null));
		lenderCount = JsonUtil.getInt(listingInfos, "LenderCount", 0);
		auditingTime = DateFormateUtil.formateDate(JsonUtil.getString(listingInfos, "AuditingTime", null));
		remainFunding = JsonUtil.getDouble(listingInfos, "RemainFunding", 0.0);
		deadLineTimeOrRemindTimeStr = JsonUtil.getString(listingInfos, "DeadLineTimeOrRemindTimeStr", null);
		creditCode = JsonUtil.getString(listingInfos, "CreditCode", null);
//		listingId = JsonUtil.getInt(listingInfos, "ListingId");
		amount = JsonUtil.getDouble(listingInfos, "Amount", 0.0);
		months = JsonUtil.getInt(listingInfos, "Months", 0);
		currentRate = JsonUtil.getDouble(listingInfos, "CurrentRate", 0.0);
		borrowName = JsonUtil.getString(listingInfos, "BorrowName", null);
		gender = JsonUtil.getInt(listingInfos, "Gender", 0);
		educationDegree = JsonUtil.getString(listingInfos, "EducationDegree", null);
		graduateSchool = JsonUtil.getString(listingInfos, "GraduateSchool", null);
		studyStyle = JsonUtil.getString(listingInfos, "StudyStyle", null);
		age = JsonUtil.getInt(listingInfos, "Age", 0);
		successCount = JsonUtil.getInt(listingInfos, "SuccessCount", 0);
		wasteCount = JsonUtil.getInt(listingInfos, "WasteCount", 0);
		cancelCount = JsonUtil.getInt(listingInfos, "CancelCount", 0);
		failedCount = JsonUtil.getInt(listingInfos, "FailedCount", 0);
		normalCount = JsonUtil.getInt(listingInfos, "NormalCount", 0);
		overdueLessCount = JsonUtil.getInt(listingInfos, "OverdueLessCount", 0);
		overdueMoreCount = JsonUtil.getInt(listingInfos, "OverdueMoreCount", 0);
		owingPrincipal = JsonUtil.getDouble(listingInfos, "OwingPrincipal", 0.0);
		owingAmount = JsonUtil.getDouble(listingInfos, "OwingAmount", 0.0);
		amountToReceive = JsonUtil.getDouble(listingInfos, "AmountToReceive", 0.0);
		firstSuccessBorrowTime = DateFormateUtil.formateDate(JsonUtil.getString(listingInfos, "FirstSuccessBorrowTime", null));
		registerTime = DateFormateUtil.formateDate(JsonUtil.getString(listingInfos, "RegisterTime", null));
		certificateValidate = JsonUtil.getInt(listingInfos, "CertificateValidate", 0);
		nciicIdentityCheck = JsonUtil.getInt(listingInfos, "NciicIdentityCheck", 0);
		phoneValidate = JsonUtil.getInt(listingInfos, "PhoneValidate", 0);
		videoValidate = JsonUtil.getInt(listingInfos, "VideoValidate", 0);
		creditValidate = JsonUtil.getInt(listingInfos, "CreditValidate", 0);
		educateValidate = JsonUtil.getInt(listingInfos, "EducateValidate", 0);
		lastSuccessBorrowTime = DateFormateUtil.formateDate(JsonUtil.getString(listingInfos, "LastSuccessBorrowTime", null));
		highestPrincipal = JsonUtil.getDouble(listingInfos, "HighestPrincipal", 0.0);
		highestDebt = JsonUtil.getDouble(listingInfos, "HighestDebt", 0.0);
		totalPrincipal = JsonUtil.getDouble(listingInfos, "TotalPrincipal", 0.0);
	}
}
