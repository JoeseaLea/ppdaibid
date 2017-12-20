package com.ppdaibid.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.ppdaibid.dao.BaseDao;
import com.ppdaibid.dao.DebtDao;
import com.ppdaibid.info.DebtInfo;
import com.ppdaibid.utils.DateFormateUtil;

public class DebtDaoImpl extends BaseDao implements DebtDao {

	@Override
	public void addDebtInfo(DebtInfo debtInfo) {
		if (null == debtInfo) {
			return ;
		}
		
		String sql = "INSERT INTO `ppdai`.`debtinfo` "
				+ " ( "
				+ " `debtdealId`, "
				+ " `owingNumber`, "
				+ " `priceforSale`, "
				+ " `listingId`, "
				+ " `creditCode`, "
				+ " `debtId`, "
				+ " `seller`, "
				+ " `statusId`, "
				+ " `lender`, "
				+ " `bidDateTime`, "
				+ " `owingPrincipal`, "
				+ " `owingInterest`, "
				+ " `days`, "
				+ " `priceforSaleRate`, "
				+ " `preferenceDegree`, "
				+ " `listingAmount`, "
				+ " `listingMonths`, "
				+ " `listingTime`, "
				+ " `listingRate`, "
				+ " `pastDueNumber`, "
				+ " `currentCreditCode`, "
				+ " `allowanceRadio`, "
				+ " `pastDueDay`, "
				+ " `insertTime`, "
				+ " `lastupdateTime`,"
				+ " `isBid`) "
				+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
				+ " ON DUPLICATE KEY UPDATE "
				+ " `owingNumber` = ?, "
				+ " `priceforSale` = ?, "
				+ " `listingId` = ?, "
				+ " `creditCode` = ?, "
				+ " `debtId` = ?, "
				+ " `seller` = ?, "
				+ " `statusId` = ?, "
				+ " `lender` = ?, "
				+ " `bidDateTime` = ?, "
				+ " `owingPrincipal` = ?, "
				+ " `owingInterest` = ?, "
				+ " `days` = ?, "
				+ " `priceforSaleRate` = ?, "
				+ " `preferenceDegree` = ?, "
				+ " `listingAmount` = ?, "
				+ " `listingMonths` = ?, "
				+ " `listingTime` = ?, "
				+ " `listingRate` = ?, "
				+ " `pastDueNumber` = ?, "
				+ " `currentCreditCode` = ?, "
				+ " `allowanceRadio` = ?, "
				+ " `pastDueDay` = ?, "
				+ " `insertTime` = ?, "
				+ " `lastupdateTime` = ?,"
				+ " `isBid` = ?";
		
		this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, debtInfo.getDebtdealId());
				ps.setInt(2, debtInfo.getOwingNumber());
				ps.setDouble(3, debtInfo.getPriceforSale());
				ps.setInt(4, debtInfo.getListingId());
				ps.setString(5, debtInfo.getCreditCode());
				ps.setInt(6, debtInfo.getDebtId());
				ps.setString(7, debtInfo.getSeller());
				ps.setInt(8, debtInfo.getStatusId());
				ps.setString(9, debtInfo.getLender());
				ps.setString(10, DateFormateUtil.formateDate(debtInfo.getBidDateTime()));
				ps.setDouble(11, debtInfo.getOwingPrincipal());
				ps.setDouble(12, debtInfo.getOwingInterest());
				ps.setInt(13, debtInfo.getDays());
				ps.setDouble(14, debtInfo.getPriceforSaleRate());
				ps.setDouble(15, debtInfo.getPreferenceDegree());
				ps.setDouble(16, debtInfo.getListingAmount());
				ps.setInt(17, debtInfo.getListingMonths());
				ps.setString(18, DateFormateUtil.formateDate(debtInfo.getListingTime()));
				ps.setDouble(19, debtInfo.getListingRate());
				ps.setInt(20, debtInfo.getPastDueNumber());
				ps.setString(21, debtInfo.getCurrentCreditCode());
				ps.setDouble(22, debtInfo.getAllowanceRadio());
				ps.setInt(23, debtInfo.getPastDueDay());
				ps.setString(24, DateFormateUtil.formateDate(debtInfo.getInsertTime()));
				ps.setString(25, DateFormateUtil.formateDate(debtInfo.getLastupdateTime()));
				ps.setBoolean(26, debtInfo.isBid());
				
				ps.setInt(27, debtInfo.getOwingNumber());
				ps.setDouble(28, debtInfo.getPriceforSale());
				ps.setInt(29, debtInfo.getListingId());
				ps.setString(30, debtInfo.getCreditCode());
				ps.setInt(31, debtInfo.getDebtId());
				ps.setString(32, debtInfo.getSeller());
				ps.setInt(33, debtInfo.getStatusId());
				ps.setString(34, debtInfo.getLender());
				ps.setString(35, DateFormateUtil.formateDate(debtInfo.getBidDateTime()));
				ps.setDouble(36, debtInfo.getOwingPrincipal());
				ps.setDouble(37, debtInfo.getOwingInterest());
				ps.setInt(38, debtInfo.getDays());
				ps.setDouble(39, debtInfo.getPriceforSaleRate());
				ps.setDouble(40, debtInfo.getPreferenceDegree());
				ps.setDouble(41, debtInfo.getListingAmount());
				ps.setInt(42, debtInfo.getListingMonths());
				ps.setString(43, DateFormateUtil.formateDate(debtInfo.getListingTime()));
				ps.setDouble(44, debtInfo.getListingRate());
				ps.setInt(45, debtInfo.getPastDueNumber());
				ps.setString(46, debtInfo.getCurrentCreditCode());
				ps.setDouble(47, debtInfo.getAllowanceRadio());
				ps.setInt(48, debtInfo.getPastDueDay());
				ps.setString(49, DateFormateUtil.formateDate(debtInfo.getInsertTime()));
				ps.setString(50, DateFormateUtil.formateDate(debtInfo.getLastupdateTime()));
				ps.setBoolean(51, debtInfo.isBid());
			}
			
			@Override
			public int getBatchSize() {
				return 1;
			}
		});
	}

	@Override
	public List<Integer> getCanBeIgnoreIds() {
		List<Integer> listingIds = new ArrayList<Integer>();

		String sql = "SELECT t.debtdealId FROM ppdai.debtinfo t "
				+ "WHERE t.`lastupdateTime` > DATE_ADD(NOW(), INTERVAL -1 MINUTE) ORDER BY t.`lastupdateTime` DESC LIMIT 3000";
		SqlRowSet rowSet = this.jdbcTemplate.queryForRowSet(sql);
		while (rowSet.next()) {
			listingIds.add(rowSet.getInt(1));
		}

		return listingIds;
	}
	
}
