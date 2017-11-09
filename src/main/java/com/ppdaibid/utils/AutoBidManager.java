package com.ppdaibid.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ppdaibid.dao.PPDdao;
import com.ppdaibid.dao.impl.PPDdaoImpl;
import com.ppdaibid.thread.LoanListThread;

public class AutoBidManager {
	private static final Logger logger = Logger.getLogger(AutoBidManager.class);
	
	public static ExecutorService executorService = null;
	public static long sleepTime = 110;
	public static PPDdao ppDdao = null;
	
	public static void startLoanInfos() {
		executorService = Executors.newCachedThreadPool();
		try {
			sleepTime = Integer.parseInt(PropertiesUtil.getProperty("intervalTime", "110"));
		} catch (Exception e) {
			logger.error("LoanList请求间隔时间配置错误", e);
			logger.info("不能正确解析LoanList请求间隔时间，sleepTime设置为默认时间（110毫秒）");
			sleepTime = 110;
		}
		
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		PPDdaoImpl ppDdaoImpl = context.getBean("ppddao", PPDdaoImpl.class);
		ppDdao = ppDdaoImpl;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Thread loanListThread = new Thread(new LoanListThread(1));
				while (true) {
					executorService.execute(loanListThread);
					sleep(sleepTime);
				}
			}
		}).start();
	};

	private static void sleep(long milliseconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (InterruptedException e) { }
	}
}
