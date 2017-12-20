package com.ppdaibid;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.ppdai.open.core.Result;
import com.ppdaibid.utils.DebtUtil;

public class DebtThreadTest {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		/*ThreadT[] threads = new ThreadT[50];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new ThreadT();
		}
		
		while (true) {
			for (int i = 0; i < threads.length; i++) {
				if (!threads[i].isAlive()) {
					executorService.execute(threads[i]);
					break;
				}
			}
			TimeUnit.MILLISECONDS.sleep(60*1000/30);
		}*/
		while (true) {
			executorService.execute(new ThreadT());
			TimeUnit.MILLISECONDS.sleep(60*1000/30);
		}
	}
	
	static class ThreadT extends Thread {
		@Override
		public void run() {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.SECOND, -30);
			Result result = DebtUtil.debtListNew(1, c.getTime(), "A,B,C,D,E,F");
			System.out.println(result.getContext());
		}
	}
}
