package com.ppdaibid.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.ppdai.open.core.OpenApiClient;
import com.ppdai.open.core.PropertyObject;
import com.ppdai.open.core.Result;
import com.ppdai.open.core.RsaCryptoHelper;
import com.ppdai.open.core.ValueTypeEnum;
import com.ppdaibid.AccessInfo;

/**
 * 投标接口
 * @author Joesea Lea
 */
public class BidUtil {
	private static Logger logger = Logger.getLogger(BidUtil.class);
	
	public static DateFormat dfDay = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat dfMillisecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	private static LinkedBlockingQueue<Date> loanListQueue = new LinkedBlockingQueue<Date>();
	private static LinkedBlockingQueue<Date> batchListingInfosQueue = new LinkedBlockingQueue<Date>();
	private static LinkedBlockingQueue<Date> biddingQueue = new LinkedBlockingQueue<Date>();
	
	private static int loanListFrequency = 600;
	private static int batchListingInfosFrequency = 500;
	private static int biddingFrequency = 2500;

	/**
	 * 投标接口
	 * @param listingId 标编号
	 * @param amount 投标金额
	 * @param useCoupon 是否使用优惠券投标 true:使用 false:不使用
	 * @return result 投标结果
	 */
	public static Result bidding(int listingId, int amount, boolean useCoupon) {
		
		if (biddingQueue.size() > biddingFrequency) {
			return overLimitFrequency();
		}
		
		try {
			biddingQueue.put(Calendar.getInstance().getTime());
			//初始化操作
			OpenApiClient.Init(AccessInfo.appId, RsaCryptoHelper.PKCSType.PKCS8, AccessInfo.serverPublicKey, AccessInfo.clientPrivateKey);
			String accessToken = AccessInfo.accessToken;
			//请求url
			String url = "https://openapi.ppdai.com/invest/BidService/Bidding";
			Result result = OpenApiClient.send(url, accessToken,
					new PropertyObject("ListingId", listingId, ValueTypeEnum.Int32),
					new PropertyObject("Amount",amount, ValueTypeEnum.Double),
					new PropertyObject("UseCoupon", String.valueOf(useCoupon), ValueTypeEnum.String));
			return result;
		} catch (Exception e) {
			logger.error("投标异常", e);
		}
		return null;
	}
	
	/**
	 * 获取用户投标记录
	 * @param listingId 按照标的查询输入标的号，否则输入0
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param pageIndex 页码
	 * @param pageSize 每页数
	 * @return result
	 */
	public static Result bidList(int listingId, Date startTime, Date endTime, int pageIndex, int pageSize) {
		//初始化操作
		try {
			OpenApiClient.Init(AccessInfo.appId, RsaCryptoHelper.PKCSType.PKCS8, AccessInfo.serverPublicKey, AccessInfo.clientPrivateKey);
//			String accessToken = AccessInfo.accessToken;
			//请求url
			String url = "https://openapi.ppdai.com/invest/BidService/BidList";
			Result result = null;
			if (-1 == listingId) {
				result = OpenApiClient.send(url, AccessInfo.accessToken,
						new PropertyObject("StartTime", dfDay.format(startTime), ValueTypeEnum.DateTime),
						new PropertyObject("EndTime", dfDay.format(endTime), ValueTypeEnum.DateTime),
						new PropertyObject("PageIndex", pageIndex, ValueTypeEnum.Int32),
						new PropertyObject("PageSize", pageSize, ValueTypeEnum.Int32));
			} else {
				result = OpenApiClient.send(url, AccessInfo.accessToken,
						new PropertyObject("ListingId", listingId, ValueTypeEnum.Int32),
						new PropertyObject("StartTime", dfDay.format(startTime), ValueTypeEnum.DateTime),
						new PropertyObject("EndTime", dfDay.format(endTime), ValueTypeEnum.DateTime),
						new PropertyObject("PageIndex", pageIndex, ValueTypeEnum.Int32),
						new PropertyObject("PageSize", pageSize, ValueTypeEnum.Int32));
			}
			return result;
		} catch (Exception e) {
			logger.error("获取用户投标记录异常", e);
		}
		return null;
	}
	
	/**
	 * （跟投）用户最近投资标的信息（批量）
	 * @param lenderNames 投资用户名列表，个数1至5 （lenderNames长度为1到5）
	 * @param topIndex 记录条数，1至20条
	 * @return result
	 */
	public static Result batchLenderBidList(List<String> lenderNames, int topIndex) {
		try {
			//初始化操作
			OpenApiClient.Init(AccessInfo.appId, RsaCryptoHelper.PKCSType.PKCS8, AccessInfo.serverPublicKey, AccessInfo.clientPrivateKey);
			//请求url
			String url = "https://openapi.ppdai.com/invest/BidService/BatchLenderBidList";
			
			if (null == AccessInfo.accessToken) {
				throw new InterruptedException("accessToken is null");
			}
			
			Result result = OpenApiClient.send(url, AccessInfo.accessToken,
					new PropertyObject("LenderNames", lenderNames, ValueTypeEnum.Other),
					new PropertyObject("TopIndex", topIndex, ValueTypeEnum.Int32));
			return result;
		} catch (Exception e) {
			logger.error("跟投）用户最近投资标的信息（批量）异常", e);
		}
		return null;
	}
	
	/**
	 * 获取散标可投标列表
	 * @param pageIndex 页码
	 * @param startDateTime 如果有则查询该时间之后的列表
	 * @return result
	 */
	public static Result loanList(int pageIndex, Date startDateTime) {
		Result result = null;
		if (loanListQueue.size() > loanListFrequency) {
			return overLimitFrequency();
		}

		try {
			loanListQueue.put(Calendar.getInstance().getTime());
			// 初始化操作
			OpenApiClient.Init(AccessInfo.appId, RsaCryptoHelper.PKCSType.PKCS8, AccessInfo.serverPublicKey, AccessInfo.clientPrivateKey);
			// 请求url
			String url = "https://openapi.ppdai.com/invest/LLoanInfoService/LoanList";
			result = OpenApiClient.send(url, new PropertyObject("PageIndex", pageIndex, ValueTypeEnum.Int32),
					new PropertyObject("StartDateTime", dfMillisecond.format(startDateTime), ValueTypeEnum.DateTime));
			return result;
		} catch (Exception e) {
			logger.error("获取散标可投标列表异常", e);
		}
		return null;
	}

	/**
	 * 批量获取散标详情
	 * @param listIds 列表IDs
	 * @return result
	 */
	public static Result batchListingInfos(List<Integer> listIds) {
		
		if (batchListingInfosQueue.size() >= batchListingInfosFrequency) {
			return overLimitFrequency();
		}
		
		try {
			batchListingInfosQueue.put(Calendar.getInstance().getTime());
			//初始化操作
			OpenApiClient.Init(AccessInfo.appId, RsaCryptoHelper.PKCSType.PKCS8, AccessInfo.serverPublicKey, AccessInfo.clientPrivateKey);
			//请求url
			String url = "https://openapi.ppdai.com/invest/LLoanInfoService/BatchListingInfos";
			Result result = OpenApiClient.send(url,
					new PropertyObject("ListingIds", listIds, ValueTypeEnum.Other));
			return result;
		} catch (Exception e) {
			logger.error("批量获取散标详情异常", e);
		}
		return null;
	}
	
	/**
	 * 批量获取散标投资记录
	 * @param listIds 列表IDs
	 * @return result
	 */
	public static Result batchListingBidInfos(List<Integer> listIds) {
		try {
			//初始化操作
			OpenApiClient.Init(AccessInfo.appId, RsaCryptoHelper.PKCSType.PKCS8, AccessInfo.serverPublicKey, AccessInfo.clientPrivateKey);
			//请求url
			String url = "https://openapi.ppdai.com/invest/LLoanInfoService/BatchListingBidInfos";
			Result result = OpenApiClient.send(url,
					new PropertyObject("ListingIds", listIds, ValueTypeEnum.Other));
			return result;
		} catch (Exception e) {
			logger.error("批量获取散标投资记录异常", e);
		}
		return null;
	}
	
	/**
	 * 批量查询散标投资状态
	 * @param listIds 列表IDs
	 * @return result
	 */
	public static Result batchListingStatusInfos(List<Integer> listIds) {
		try {
			//初始化操作
			OpenApiClient.Init(AccessInfo.appId, RsaCryptoHelper.PKCSType.PKCS8, AccessInfo.serverPublicKey, AccessInfo.clientPrivateKey);
			//请求url
			String url = "https://openapi.ppdai.com/invest/LLoanInfoService/BatchListingStatusInfos";
			Result result = OpenApiClient.send(url,
					new PropertyObject("ListingIds", listIds, ValueTypeEnum.Other));
			return result;
		} catch (Exception e) {
			logger.error("批量查询散标投资状态异常", e);
		}
		return null;
	}
	
	/**
	 * 获取用户投资列表的还款情况
	 * @param listingId 散标列表编号
	 * @param orderId 还款期数,参数为空时，获取当前列表所有期数；参数不为空时，获取传参的期数
	 * @return
	 */
	public static Result fetchLenderRepayment(int listingId, String orderId) {
		try {
			//初始化操作
			OpenApiClient.Init(AccessInfo.appId, RsaCryptoHelper.PKCSType.PKCS8, AccessInfo.serverPublicKey, AccessInfo.clientPrivateKey);
			//请求url
			String url = "https://openapi.ppdai.com/invest/RepaymentService/FetchLenderRepayment";
			Result result = OpenApiClient.send(url, AccessInfo.accessToken,
					new PropertyObject("ListingId", listingId, ValueTypeEnum.Int32),
					new PropertyObject("OrderId", orderId, ValueTypeEnum.String));
			return result;
		} catch (Exception e) {
			logger.error("获取用户投资列表的还款情况异常", e);
		}

		return null;
	}
	
	/**
	 * 投资扣费对账明细
	 * @param page 页码，从1开始
	 * @param date 日期
	 * @return
	 */
	public static Result volumeList(int page, Date date) {
		try {
			//初始化操作
			OpenApiClient.Init(AccessInfo.appId, RsaCryptoHelper.PKCSType.PKCS8, AccessInfo.serverPublicKey, AccessInfo.clientPrivateKey);
			 //请求url
			String url = "https://openapi.ppdai.com/charge/volume/list";
			PropertyObject propertyObjects[] = {new PropertyObject("page", page, ValueTypeEnum.Int32),
					new PropertyObject("date",dfDay.format(date), ValueTypeEnum.DateTime)};
			Result result = OpenApiClient.send(url, propertyObjects);
			return result;
		} catch (Exception e) {
			logger.error("获取投资扣费对账明细", e);
		}
		return null;
	}
	
	public static Result overLimitFrequency(){
		Result result = new Result();
		result = new Result();
		result.setSucess(false);
		result.setContext("{msg:\"一分钟内访问次数超过限次频率，请稍后再试\"}");
		result.setErrorMessage("一分钟内访问次数超过限次频率，请稍后再试");
		return result;
	}
	
	private static void checkValidReqTime() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					Calendar c = Calendar.getInstance();
					c.add(Calendar.SECOND, -60);
					Date nowTime = c.getTime();
					Date loanListfirstTime = loanListQueue.peek();
					Date batchListingInfosfirstTime = batchListingInfosQueue.peek();
					Date biddingfirstTime = biddingQueue.peek();
					
					if (null != loanListfirstTime) {
						if (nowTime.getTime() > loanListfirstTime.getTime()) {
							try {
								loanListQueue.take();
							} catch (InterruptedException e) { }
						}
					}
					if (null != batchListingInfosfirstTime) {
						if (nowTime.getTime() > batchListingInfosfirstTime.getTime()) {
							try {
								batchListingInfosQueue.take();
							} catch (InterruptedException e) { }
						}
					}
					if (null != biddingfirstTime) {
						if (nowTime.getTime() > biddingfirstTime.getTime()) {
							try {
								biddingQueue.take();
							} catch (InterruptedException e) { }
						}
					}
					
					try {
						TimeUnit.MILLISECONDS.sleep(1);
					} catch (InterruptedException e) { }
				}
			}
		}).start();
	}
	
	static {
		checkValidReqTime();
	}
}
