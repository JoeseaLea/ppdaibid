package com.ppdaibid;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ppdaibid.dao.PPDdao;
import com.ppdaibid.dao.impl.PPDdaoImpl;
import com.ppdaibid.thread.LoanListThread;
import com.ppdaibid.utils.PropertiesUtil;

public class AutoBidManager {
	private static final Logger logger = Logger.getLogger(AutoBidManager.class);
	
	public static final ExecutorService executorService = Executors.newCachedThreadPool();
	// LoanList请求间隔时间
	public static long intervalTime = 110;
	public static PPDdao ppDdao = null;
	
	public static boolean needWait = false;
	
	public static void startLoanInfos() {
		try {
			//从配置文件中读取LoanList请求间隔时间，如果未配置或者配置错误，则使用默认值
			intervalTime = Integer.parseInt(PropertiesUtil.getProperty("intervalTime", "110"));
		} catch (Exception e) {
			logger.error("LoanList request interval time configurate error", e);
			intervalTime = 110;
		}
		
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		PPDdaoImpl ppDdaoImpl = context.getBean("ppddao", PPDdaoImpl.class);
		ppDdao = ppDdaoImpl;
		
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
				} catch (Exception e) {
					logger.error("", e);
					waitTime = 15;
				}
				
				Thread loanListThread = new Thread(new LoanListThread());
				while (true) {
					if (needWait) {
						sleep(waitTime*60*1000);
						needWait = false;
					}
					
					executorService.execute(loanListThread);
					sleep(intervalTime);
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
