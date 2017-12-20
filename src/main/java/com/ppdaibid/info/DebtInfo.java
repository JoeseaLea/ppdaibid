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
		owingNumber = JsonUtil.getInt(jsonDebtInfo, "OwingNumber", 0);
		owingPrincipal = JsonUtil.getDouble(jsonDebtInfo, "OwingPrincipal", 0);
		owingInterest = JsonUtil.getDouble(jsonDebtInfo, "OwingInterest", 0);
		days = JsonUtil.getInt(jsonDebtInfo, "Days", 0);
		priceforSaleRate = JsonUtil.getDouble(jsonDebtInfo, "PriceforSaleRate", 0);
		priceforSale = JsonUtil.getDouble(jsonDebtInfo, "PriceforSale", 0);
		preferenceDegree = JsonUtil.getDouble(jsonDebtInfo, "PreferenceDegree", 0);
		listingId = JsonUtil.getInt(jsonDebtInfo, "ListingId", 0);
		creditCode = JsonUtil.getString(jsonDebtInfo, "CreditCode", null);
		listingAmount = JsonUtil.getDouble(jsonDebtInfo, "ListingAmount", 0);
		listingMonths = JsonUtil.getInt(jsonDebtInfo, "ListingMonths", 0);
		listingTime = DateFormateUtil.formateDate(JsonUtil.getString(jsonDebtInfo, "ListingTime", null));
		listingRate = JsonUtil.getDouble(jsonDebtInfo, "ListingRate", 0);
		pastDueNumber = JsonUtil.getInt(jsonDebtInfo, "PastDueNumber", 0);
		currentCreditCode = JsonUtil.getString(jsonDebtInfo, "CurrentCreditCode", null);
		allowanceRadio = JsonUtil.getDouble(jsonDebtInfo, "AllowanceRadio", 0);
		pastDueDay = JsonUtil.getInt(jsonDebtInfo, "PastDueDay", 0);
	}
}
