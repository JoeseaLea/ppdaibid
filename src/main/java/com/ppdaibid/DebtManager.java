package com.ppdaibid;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.ppdaibid.thread.BatchDebtInfosThread;
import com.ppdaibid.thread.BuyDebtThread;
import com.ppdaibid.thread.DebtListThread;
import com.ppdaibid.utils.PropertiesUtil;

public class DebtManager {
	private static final Logger logger = Logger.getLogger(DebtManager.class);
	
	private static int batchDebtInfosThreadsLength = 20;
	private static int buyDebtThreadsLength = 100;
	public static final ExecutorService executorService = Executors.newCachedThreadPool();
	public static final DebtListThread debtListThread = new DebtListThread();
	public static final BatchDebtInfosThread[] batchDebtInfosThreads = new BatchDebtInfosThread[batchDebtInfosThreadsLength];
	public static final BuyDebtThread[] buyDebtThreads = new BuyDebtThread[buyDebtThreadsLength];
	
	// DebtList请求间隔时间
	public static long debtListIntervalTime = 1210;
	public static boolean debtListNeedWait = false;
	
	/**
	 * 开始购买债务
	 */
	public static void startDebtTask() {
		
		for (int i = 0; i < batchDebtInfosThreadsLength; i ++) {
			batchDebtInfosThreads[i] =  new BatchDebtInfosThread();
		}
		for (int i = 0; i < buyDebtThreadsLength; i ++) {
			buyDebtThreads[i] = new BuyDebtThread();
		}
		
		try {
			//从配置文件中读取LoanList请求间隔时间，如果未配置或者配置错误，则使用默认值
			debtListIntervalTime = Integer.parseInt(PropertiesUtil.getProperty("debtListIntervalTime", "1210"));
			if (debtListIntervalTime < 1210) {
				debtListIntervalTime = 1210;
			}
		} catch (Exception e) {
			logger.error("LoanList request interval time configurate error", e);
			debtListIntervalTime = 1210;
		}
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				int waitTime = 15;
				try {
					waitTime = Integer.parseInt(PropertiesUtil.getProperty("waitTime", "15"));
					if (waitTime < 15) {
						waitTime = 15;
					}
				} catch (Exception e) {
					logger.error("", e);
					waitTime = 15;
				}
				
				logger.info("Start to buy debt...");
				
				while (true) {
					if (!AccessInfo.tokenIsValid) {
						sleep(debtListIntervalTime);
						continue;
					}
					
					try {
						if (debtListNeedWait) {
							sleep(waitTime*60*1000);
							debtListNeedWait = false;
						}
						
						executorService.execute(DebtManager.debtListThread);
						sleep(debtListIntervalTime);
					} catch (Exception e) {
						logger.error("", e);
					}
				}
			}
		});
		
		executorService.execute(thread);
	}
	
	/**
	 * 暂停指定时间
	 * @param milliseconds
	 */
	private static void sleep(long milliseconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (InterruptedException e) { }
	}
}
