package com.ppdaibid.info;

import java.util.Date;

import org.json.JSONObject;

import com.ppdaibid.utils.DateFormateUtil;
import com.ppdaibid.utils.JsonUtil;

public class LoanInfo {
	/**
	 * 列表ID
	 */
	private int listingId;
	/**
	 * 标的标题
	 */
	private String title;
	/**
	 * 标的级别
	 */
	private String creditCode;
	/**
	 * 借款金额
	 */
	private double amount;
	/**
	 * 利率
	 */
	private double rate;
	/**
	 * 期限
	 */
	private int months;
	/**
	 * 还款方式(0:等额本息(按月还款) 1:一次性还本付息)
	 */
	private int payWay;
	/**
	 * 可投标金额
	 */
	private double remainFunding;
	/**
	 * 预审时间
	 */
	private String preAuditTime;

	//详情
	
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
	 * 剩余待还本金
	 */
	private double owingPrincipal;
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
	public int getListingId() {
		return listingId;
	}
	public void setListingId(int listingId) {
		this.listingId = listingId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
	}
	public int getPayWay() {
		return payWay;
	}
	public void setPayWay(int payWay) {
		this.payWay = payWay;
	}
	public String getPreAuditTime() {
		return preAuditTime;
	}
	public void setPreAuditTime(String preAuditTime) {
		this.preAuditTime = preAuditTime;
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
	public double getRemainFunding() {
		return remainFunding;
	}
	public void setRemainFunding(double remainFunding) {
		this.remainFunding = remainFunding;
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
	public double getOwingPrincipal() {
		return owingPrincipal;
	}
	public void setOwingPrincipal(double owingPrincipal) {
		this.owingPrincipal = owingPrincipal;
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
	public void setLoanList(JSONObject loanList) {
		listingId = loanList.getInt("ListingId");
		title = JsonUtil.getString(loanList, "Title", null);
		creditCode = JsonUtil.getString(loanList, "CreditCode", null);
		amount = JsonUtil.getDouble(loanList, "Amount", 0.0);
		rate = JsonUtil.getDouble(loanList, "Rate", 0.0);
		months =  JsonUtil.getInt(loanList, "Months", 0);
		payWay = JsonUtil.getInt(loanList, "PayWay", 0);
		remainFunding = JsonUtil.getDouble(loanList, "RemainFunding", 0.0);
		preAuditTime = JsonUtil.getString(loanList, "PreAuditTime", null);
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("listingId:" + listingId + ", ");
		sb.append("title:" + title + ", ");
		sb.append("creditCode:" + creditCode + ", ");
		sb.append("amount:" + amount + ", ");
		sb.append("rate:" + rate + ", ");
		sb.append("months:" + months + ", ");
		sb.append("payWay:" + payWay + ", ");
		sb.append("remainFunding:" + remainFunding + ", ");
		sb.append("preAuditTime:" + preAuditTime + ", ");
		sb.append("fistBidTime:" + fistBidTime + ", ");
		sb.append("lastBidTime:" + lastBidTime + ", ");
		sb.append("lenderCount:" + lenderCount + ", ");
		sb.append("auditingTime:" + auditingTime + ", ");
		sb.append("deadLineTimeOrRemindTimeStr:" + deadLineTimeOrRemindTimeStr + ", ");
		sb.append("currentRate:" + currentRate + ", ");
		sb.append("borrowName:" + borrowName + ", ");
		sb.append("gender:" + gender + ", ");
		sb.append("educationDegree:" + educationDegree + ", ");
		sb.append("graduateSchool:" + graduateSchool + ", ");
		sb.append("studyStyle:" + studyStyle + ", ");
		sb.append("age:" + age + ", ");
		sb.append("successCount:" + successCount + ", ");
		sb.append("wasteCount:" + wasteCount + ", ");
		sb.append("cancelCount:" + cancelCount + ", ");
		sb.append("failedCount:" + failedCount + ", ");
		sb.append("normalCount:" + normalCount + ", ");
		sb.append("overdueLessCount:" + overdueLessCount + ", ");
		sb.append("overdueMoreCount:" + overdueMoreCount + ", ");
		sb.append("owingPrincipal:" + owingPrincipal + ", ");
		sb.append("owingAmount:" + owingAmount + ", ");
		sb.append("amountToReceive:" + amountToReceive + ", ");
		sb.append("firstSuccessBorrowTime:" + firstSuccessBorrowTime + ", ");
		sb.append("registerTime:" + registerTime + ", ");
		sb.append("certificateValidate:" + certificateValidate + ", ");
		sb.append("nciicIdentityCheck:" + nciicIdentityCheck + ", ");
		sb.append("phoneValidate:" + phoneValidate + ", ");
		sb.append("videoValidate:" + videoValidate + ", ");
		sb.append("creditValidate:" + creditValidate + ", ");
		sb.append("educateValidate:" + educateValidate + ", ");
		sb.append("lastSuccessBorrowTime:" + lastSuccessBorrowTime + ", ");
		sb.append("highestPrincipal:" + highestPrincipal + ", ");
		sb.append("highestDebt:" + highestDebt + ", ");
		sb.append("totalPrincipal:" + totalPrincipal);
		return sb.toString();
	}
}
