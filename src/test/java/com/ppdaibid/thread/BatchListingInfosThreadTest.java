package com.ppdaibid.thread;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class BatchListingInfosThreadTest {

	@Test
	public void test() {
		BatchListingInfosThread batchListingInfosThread = new BatchListingInfosThread();
		batchListingInfosThread.init(new ArrayList<>(), new HashMap<>());
		new java.lang.Thread(batchListingInfosThread).start();
	}

}
