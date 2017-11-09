package com.ppdaibid.thread;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ppdai.open.core.Result;
import com.ppdaibid.dao.PPDdao;
import com.ppdaibid.dao.impl.PPDdaoImpl;
import com.ppdaibid.info.LoanInfo;
import com.ppdaibid.utils.BidUtil;

public class LoanListThread implements Runnable {

	public static int maxPageIndex = 0;
	private static Logger logger = Logger.getLogger(LoanListThread.class);

	private int pageIndex = 1;

	private PPDdao ppDdao = null;

	public LoanListThread(int pageIndex) {
		this.pageIndex = pageIndex;
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		PPDdaoImpl ppDdaoImpl = context.getBean("ppddao", PPDdaoImpl.class);
		ppDdao = ppDdaoImpl;
	}

	@Override
	public void run() {

		List<Integer> listIds;
		Map<Integer, LoanInfo> loanInfosMap;

		listIds = new ArrayList<Integer>();
		loanInfosMap = new HashMap<Integer, LoanInfo>();

		Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND, -12);

		List<Integer> ignoreIds = ppDdao.getCanBeIgnoreIds();

		Result result = BidUtil.loanList(pageIndex, c.getTime());

		if (!result.isSucess()) {
			logger.error("获取loanList结果异常：" + result.getContext());
			return;
		}

		logger.debug("loanList结果为：" + result.getContext());

		JSONObject context = new JSONObject(result.getContext());
		JSONArray jsonLoanInfos = context.getJSONArray("LoanInfos");

		int length = jsonLoanInfos.length();

		if (0 >= length) {
			maxPageIndex = this.pageIndex - 1;
			return;
		}
		if (maxPageIndex < this.pageIndex) {
			maxPageIndex = this.pageIndex;
		}

		for (int i = 0; i < length; i++) {
			JSONObject loanInfoJson = (JSONObject) jsonLoanInfos.get(i);
			int listingId = loanInfoJson.getInt("ListingId");
			LoanInfo loanInfo = new LoanInfo();
			loanInfo.setLoanList(loanInfoJson);

			listIds.add(listingId);
			loanInfosMap.put(listingId, loanInfo);
		}

		listIds.removeAll(ignoreIds);

		length = listIds.size();
		
		List<Integer> listIdsParam = new ArrayList<Integer>();
		Map<Integer, LoanInfo> loanInfosMapParam = new HashMap<Integer, LoanInfo>();
		int listingId;
		//TODO 一次性最多查询数量从配置文件中读取
		int batchSize = 10;
		for(int i = 0; i < length; i ++) {
			listingId = listIds.get(i);
			listIdsParam.add(listingId);
			loanInfosMapParam.put(listingId, loanInfosMap.get(listingId));
			
			if ((batchSize <= listIdsParam.size() || i >= length - 1) && 0 < listIdsParam.size()) {
				Runnable batchListingInfosThread = new BatchListingInfosThread(listIdsParam, loanInfosMapParam);
				new Thread(batchListingInfosThread).start();
				listIdsParam = new ArrayList<Integer>();
				loanInfosMapParam = new HashMap<Integer, LoanInfo>();
			}
		}
	}

}
