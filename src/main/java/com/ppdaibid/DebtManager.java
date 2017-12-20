package com.ppdaibid;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.ppdaibid.thread.DebtListThread;
import com.ppdaibid.utils.PropertiesUtil;

public class DebtManager {
	private static final Logger logger = Logger.getLogger(DebtManager.class);
	private static final ExecutorService executorService = Executors.newCachedThreadPool();
	
	// LoanList请求间隔时间
	public static long debtListIntervalTime = 1210;
	public static boolean debtListNeedWait = false;
	
	/**
	 * 开始购买债务
	 */
	public static void startDebtTask() {
		
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
				DebtListThread debtListThread = new DebtListThread();
				
				while (true) {
					if (debtListNeedWait) {
						sleep(waitTime*60*1000);
						debtListNeedWait = false;
					}
					
					executorService.execute(debtListThread);
					sleep(debtListIntervalTime);
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
