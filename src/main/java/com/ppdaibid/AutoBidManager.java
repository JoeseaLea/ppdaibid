package com.ppdaibid;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.ppdaibid.thread.BatchListingInfosThread;
import com.ppdaibid.thread.BiddingThread;
import com.ppdaibid.thread.LoanListThread;
import com.ppdaibid.utils.PropertiesUtil;

public class AutoBidManager {
	private static final Logger logger = Logger.getLogger(AutoBidManager.class);
	
	private static int batchListingInfosThreadLength = 50;
	private static int biddingThreadsLength = 200;
	
	public static final ExecutorService executorService = Executors.newCachedThreadPool();
	public static final LoanListThread loanListThread = new LoanListThread();
	public static final BatchListingInfosThread[] batchListingInfosThreads = new BatchListingInfosThread[batchListingInfosThreadLength];
	public static final BiddingThread[] biddingThreads = new BiddingThread[biddingThreadsLength];
	
	// LoanList请求间隔时间
	public static long loanListIntervalTime = 110;
	
	public static boolean loanListNeedWait = false;
	
	/**
	 * 开始投标
	 */
	public static void startLoanInfos() {
		for (int i = 0; i < batchListingInfosThreadLength; i ++) {
			batchListingInfosThreads[i] =  new BatchListingInfosThread();
		}
		for (int i = 0; i < biddingThreadsLength; i ++) {
			biddingThreads[i] = new BiddingThread();
		}
		
		try {
			//从配置文件中读取LoanList请求间隔时间，如果未配置或者配置错误，则使用默认值
			loanListIntervalTime = Integer.parseInt(PropertiesUtil.getProperty("loanListIntervalTime", "110"));
			if (loanListIntervalTime < 110) {
				loanListIntervalTime = 110;
			}
		} catch (Exception e) {
			logger.error("LoanList request interval time configurate error", e);
			loanListIntervalTime = 110;
		}
		
		/*
		 * 开启投标线程
		 * 每隔隔一个固定时间新建一个线程向服务器发送请求
		 */
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
				
				logger.info("Start to bid...");
				while (true) {
					if (!AccessInfo.tokenIsValid) {
						sleep(loanListIntervalTime);
						continue;
					}
					
					try {
						if (loanListNeedWait) {
							sleep(waitTime*60*1000);
							loanListNeedWait = false;
						}
						
						executorService.execute(loanListThread);
						sleep(loanListIntervalTime);
					} catch (Exception e) {
						logger.error("", e);
					}
				}
			}
		});
		
		executorService.execute(thread);
	};

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
