package com.ppdaibid.dao.impl;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ppdaibid.dao.DebtDao;
import com.ppdaibid.info.DebtInfo;
import com.ppdaibid.strategy.DebtStrategyCheck;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class DebtDaoImplTest {
	@Autowired
	DebtDao debtDao;
	
	@Test
	public void testAddDebtInfo() {
		DebtInfo d = null;
		while(true) {
			d = new DebtInfo();
			if(d.isBid()) {
				break;
			}
			d.setBid(true);
		}
	}

	@Test
	public void testGetCanBeIgnoreIds() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDebtInfos() {
		int i = 0;
		List<DebtInfo> debtInfos = debtDao.getDebtInfos();
//		ObjectMapper objectMapper = new ObjectMapper();
		for (DebtInfo debtInfo : debtInfos) {
			if (DebtStrategyCheck.checkStrategy1648(debtInfo)
					|| DebtStrategyCheck.checkStrategy1795(debtInfo)
					|| DebtStrategyCheck.checkStrategy2156(debtInfo)
					|| DebtStrategyCheck.checkStrategy3645(debtInfo)
					|| DebtStrategyCheck.checkStrategy3646(debtInfo)
					|| DebtStrategyCheck.checkStrategy1777(debtInfo)
					/*|| DebtStrategyCheck.checkStrategy1133(debtInfo)
					|| DebtStrategyCheck.checkStrategy1978(debtInfo)*/) {
				i ++;
			}
		}
		System.out.println(i);
	}

}
