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
 * 债转接口
 * @author Joesea Lea
 */
public class DebtUtil {
	private static Logger logger = Logger.getLogger(DebtUtil.class);
	
	public static DateFormat dfMillisecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static LinkedBlockingQueue<Date> debtListNewQueue = new LinkedBlockingQueue<Date>();
	private static LinkedBlockingQueue<Date> batchDebtInfosQueue = new LinkedBlockingQueue<Date>();
	
	private static int debtListNewFrequency = 50;
	private static int batchDebtInfosFrequency = 400;
	
	/**
	 * 购买债权
	 * @param debtDealId 债转编号
	 * @return result
	 */
	public static Result buyDebt(int debtDealId) {
		try {
			//初始化操作
			OpenApiClient.Init(AccessInfo.appId, RsaCryptoHelper.PKCSType.PKCS8, AccessInfo.serverPublicKey, AccessInfo.clientPrivateKey);
			//请求url
			String url = "http://gw.open.ppdai.com/invest/BidService/BuyDebt";
			Result result = OpenApiClient.send(url, AccessInfo.accessToken,
					new PropertyObject("debtDealId", debtDealId, ValueTypeEnum.Int32));
			return result;
		} catch (Exception e) {
			logger.error("购买债权异常", e);
		}
		return null;
	}
	
	/**
	 * 批量获取债转详情
	 * @param debtIds 债转列表IDs
	 * @return result
	 */
	public static Result batchDebtInfos(List<Integer> debtIds) {
		
		if (batchDebtInfosQueue.size() >= batchDebtInfosFrequency) {
			return overLimitFrequency();
		}
		
		try {
			batchDebtInfosQueue.put(Calendar.getInstance().getTime());
			//初始化操作
			OpenApiClient.Init(AccessInfo.appId, RsaCryptoHelper.PKCSType.PKCS8, AccessInfo.serverPublicKey, AccessInfo.clientPrivateKey);
			//请求url
			String url = "http://gw.open.ppdai.com/invest/LLoanInfoService/BatchDebtInfos";
			Result result = OpenApiClient.send(url,
					new PropertyObject("DebtIds", debtIds, ValueTypeEnum.Other));
			return result;
		} catch (Exception e) {
			logger.error("批量获取债转详情异常", e);
		}
		return null;
	}
	
	/**
	 * 获取债权可购买列表
	 * @param pageIndex 页码(每页50条)
	 * @param startDateTime 时间
	 * @param levels 等级,多个用,（英文）隔开
	 * @return result
	 */
	public static Result debtListNew(int pageIndex, Date startDateTime, String levels) {
		
		if (debtListNewQueue.size() > debtListNewFrequency) {
			return overLimitFrequency();
		}
		
		try {
			debtListNewQueue.put(Calendar.getInstance().getTime());
			//初始化操作
			OpenApiClient.Init(AccessInfo.appId, RsaCryptoHelper.PKCSType.PKCS8, AccessInfo.serverPublicKey, AccessInfo.clientPrivateKey);
			//请求url
			String url = "http://gw.open.ppdai.com/invest/LLoanInfoService/DebtListNew";
			Result result = OpenApiClient.send(url,
			        new PropertyObject("PageIndex", pageIndex, ValueTypeEnum.Int32),
			        new PropertyObject("StartDateTime", dfMillisecond.format(startDateTime), ValueTypeEnum.DateTime),
			        new PropertyObject("Levels", levels, ValueTypeEnum.String));
			return result;
		} catch (Exception e) {
			logger.error("获取债权可购买列表异常", e);
		}
		return null;
	}
	
	private static Result overLimitFrequency() {
		Result result = new Result();
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
					Date debtListNewfirstTime = debtListNewQueue.peek();
					Date batchDebtInfosfirstTime = batchDebtInfosQueue.peek();
					
					if (null != debtListNewfirstTime) {
						if (nowTime.getTime() > debtListNewfirstTime.getTime()) {
							try {
								debtListNewQueue.take();
							} catch (InterruptedException e) { }
						}
					}
					if (null != batchDebtInfosfirstTime) {
						if (nowTime.getTime() > batchDebtInfosfirstTime.getTime()) {
							try {
								batchDebtInfosQueue.take();
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
