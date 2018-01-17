package com.ppdaibid.dao;

import java.util.List;

import com.ppdaibid.info.DebtInfo;

public interface DebtDao {
	public void addDebtInfo(DebtInfo debtInfo);

	public List<Integer> getCanBeIgnoreIds();
	
	public List<DebtInfo> getDebtInfos();
}
