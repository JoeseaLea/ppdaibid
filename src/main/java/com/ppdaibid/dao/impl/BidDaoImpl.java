package com.ppdaibid.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.ppdaibid.dao.BaseDao;
import com.ppdaibid.dao.BidDao;
import com.ppdaibid.info.LoanInfo;
import com.ppdaibid.utils.DateFormateUtil;

@Repository
public class BidDaoImpl extends BaseDao implements BidDao {
	
	
	
	@Override
	public void addLoanInfo(LoanInfo loanInfo) {
		if (null == loanInfo) {
			return;
		}
		
		String sql = "INSERT INTO `ppdai`.`loaninfo` "
				+ " ( "
				+ " `listingId`, "
				+ " `title`, "
				+ " `creditCode`, "
				+ " `amount`, "
				+ " `rate`, "
				+ " `months`, "
				+ " `payWay`, "
				+ " `remainFunding`, "
				+ " `preAuditTime`, "
				+ " `fistBidTime`, "
				+ " `lastBidTime`, "
				+ " `lenderCount`, "
				+ " `auditingTime`, "
				+ " `deadLineTimeOrRemindTimeStr`, "
				+ " `currentRate`, "
				+ " `borrowName`, "
				+ " `gender`, "
				+ " `educationDegree`, "
				+ " `graduateSchool`, "
				+ " `studyStyle`, "
				+ " `age`, "
				+ " `successCount`, "
				+ " `wasteCount`, "
				+ " `cancelCount`, "
				+ " `failedCount`, "
				+ " `normalCount`, "
				+ " `overdueLessCount`, "
				+ " `overdueMoreCount`, "
				+ " `owingPrincipal`, "
				+ " `owingAmount`, "
				+ " `amountToReceive`, "
				+ " `firstSuccessBorrowTime`, "
				+ " `registerTime`, "
				+ " `certificateValidate`, "
				+ " `nciicIdentityCheck`, "
				+ " `phoneValidate`, "
				+ " `videoValidate`, "
				+ " `creditValidate`, "
				+ " `educateValidate`, "
				+ " `lastSuccessBorrowTime`, "
				+ " `highestPrincipal`, "
				+ " `highestDebt`, "
				+ " `totalPrincipal`, "
				+ " `insertTime`, "
				+ " `lastupdateTime`, "
				+ " `isBid`) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
				+ " ON DUPLICATE KEY UPDATE "
				+ " `title` = ?, "
				+ " `creditCode` = ?, "
				+ " `amount` = ?, "
				+ " `rate` = ?, "
				+ " `months` = ?, "
				+ " `payWay` = ?, "
				+ " `remainFunding` = ?, "
				+ " `preAuditTime` = ?, "
				+ " `fistBidTime` = ?, "
				+ " `lastBidTime` = ?, "
				+ " `lenderCount` = ?, "
				+ " `auditingTime` = ?, "
				+ " `deadLineTimeOrRemindTimeStr` = ?, "
				+ " `currentRate` = ?, "
				+ " `borrowName` = ?, "
				+ " `gender` = ?, "
				+ " `educationDegree` = ?, "
				+ " `graduateSchool` = ?, "
				+ " `studyStyle` = ?, "
				+ " `age` = ?, "
				+ " `successCount` = ?, "
				+ " `wasteCount` = ?, "
				+ " `cancelCount` = ?, "
				+ " `failedCount` = ?, "
				+ " `normalCount` = ?, "
				+ " `overdueLessCount` = ?, "
				+ " `overdueMoreCount` = ?, "
				+ " `owingPrincipal` = ?, "
				+ " `owingAmount` = ?, "
				+ " `amountToReceive` = ?, "
				+ " `firstSuccessBorrowTime` = ?, "
				+ " `registerTime` = ?, "
				+ " `certificateValidate` = ?, "
				+ " `nciicIdentityCheck` = ?, "
				+ " `phoneValidate` = ?, "
				+ " `videoValidate` = ?, "
				+ " `creditValidate` = ?, "
				+ " `educateValidate` = ?, "
				+ " `lastSuccessBorrowTime` = ?, "
				+ " `highestPrincipal` = ?, "
				+ " `highestDebt` = ?, "
				+ " `totalPrincipal` = ?, "
				+ " `lastupdateTime` = ?";
		
		this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1 ,loanInfo.getListingId());
				ps.setString(2 ,loanInfo.getTitle());
				ps.setString(3 ,loanInfo.getCreditCode());
				ps.setDouble(4 ,loanInfo.getAmount());
				ps.setDouble(5 ,loanInfo.getRate());
				ps.setInt(6 ,loanInfo.getMonths());
				ps.setInt(7 ,loanInfo.getPayWay());
				ps.setDouble(8 ,loanInfo.getRemainFunding());
				ps.setString(9 ,loanInfo.getPreAuditTime());
				ps.setString(10 ,DateFormateUtil.formateDate(loanInfo.getFistBidTime()));
				ps.setString(11 ,DateFormateUtil.formateDate(loanInfo.getLastBidTime()));
				ps.setInt(12 ,loanInfo.getLenderCount());
				ps.setString(13 ,DateFormateUtil.formateDate(loanInfo.getAuditingTime()));
				ps.setString(14 ,loanInfo.getDeadLineTimeOrRemindTimeStr());
				ps.setDouble(15 ,loanInfo.getCurrentRate());
				ps.setString(16 ,loanInfo.getBorrowName());
				ps.setInt(17 ,loanInfo.getGender());
				ps.setString(18 ,loanInfo.getEducationDegree());
				ps.setString(19 ,loanInfo.getGraduateSchool());
				ps.setString(20 ,loanInfo.getStudyStyle());
				ps.setInt(21 ,loanInfo.getAge());
				ps.setInt(22 ,loanInfo.getSuccessCount());
				ps.setInt(23 ,loanInfo.getWasteCount());
				ps.setInt(24 ,loanInfo.getCancelCount());
				ps.setInt(25 ,loanInfo.getFailedCount());
				ps.setInt(26 ,loanInfo.getNormalCount());
				ps.setInt(27 ,loanInfo.getOverdueLessCount());
				ps.setInt(28 ,loanInfo.getOverdueMoreCount());
				ps.setDouble(29 ,loanInfo.getOwingPrincipal());
				ps.setDouble(30 ,loanInfo.getOwingAmount());
				ps.setDouble(31 ,loanInfo.getAmountToReceive());
				ps.setString(32 ,DateFormateUtil.formateDate(loanInfo.getFirstSuccessBorrowTime()));
				ps.setString(33 ,DateFormateUtil.formateDate(loanInfo.getRegisterTime()));
				ps.setInt(34 ,loanInfo.getCertificateValidate());
				ps.setInt(35 ,loanInfo.getNciicIdentityCheck());
				ps.setInt(36 ,loanInfo.getPhoneValidate());
				ps.setInt(37 ,loanInfo.getVideoValidate());
				ps.setInt(38 ,loanInfo.getCreditValidate());
				ps.setInt(39 ,loanInfo.getEducateValidate());
				ps.setString(40 ,DateFormateUtil.formateDate(loanInfo.getLastSuccessBorrowTime()));
				ps.setDouble(41 ,loanInfo.getHighestPrincipal());
				ps.setDouble(42 ,loanInfo.getHighestDebt());
				ps.setDouble(43 ,loanInfo.getTotalPrincipal());
				ps.setString(44 ,DateFormateUtil.formateDate(loanInfo.getInsertTime()));
				ps.setString(45 ,DateFormateUtil.formateDate(loanInfo.getLastupdateTime()));
				ps.setBoolean(46 ,loanInfo.isBid());
				
				ps.setString(47 ,loanInfo.getTitle());
				ps.setString(48 ,loanInfo.getCreditCode());
				ps.setDouble(49 ,loanInfo.getAmount());
				ps.setDouble(50 ,loanInfo.getRate());
				ps.setInt(51 ,loanInfo.getMonths());
				ps.setInt(52 ,loanInfo.getPayWay());
				ps.setDouble(53 ,loanInfo.getRemainFunding());
				ps.setString(54 ,loanInfo.getPreAuditTime());
				ps.setString(55 ,DateFormateUtil.formateDate(loanInfo.getFistBidTime()));
				ps.setString(56 ,DateFormateUtil.formateDate(loanInfo.getLastBidTime()));
				ps.setInt(57 ,loanInfo.getLenderCount());
				ps.setString(58 ,DateFormateUtil.formateDate(loanInfo.getAuditingTime()));
				ps.setString(59 ,loanInfo.getDeadLineTimeOrRemindTimeStr());
				ps.setDouble(60 ,loanInfo.getCurrentRate());
				ps.setString(61 ,loanInfo.getBorrowName());
				ps.setInt(62 ,loanInfo.getGender());
				ps.setString(63 ,loanInfo.getEducationDegree());
				ps.setString(64 ,loanInfo.getGraduateSchool());
				ps.setString(65 ,loanInfo.getStudyStyle());
				ps.setInt(66 ,loanInfo.getAge());
				ps.setInt(67 ,loanInfo.getSuccessCount());
				ps.setInt(68 ,loanInfo.getWasteCount());
				ps.setInt(69 ,loanInfo.getCancelCount());
				ps.setInt(70 ,loanInfo.getFailedCount());
				ps.setInt(71 ,loanInfo.getNormalCount());
				ps.setInt(72 ,loanInfo.getOverdueLessCount());
				ps.setInt(73 ,loanInfo.getOverdueMoreCount());
				ps.setDouble(74 ,loanInfo.getOwingPrincipal());
				ps.setDouble(75 ,loanInfo.getOwingAmount());
				ps.setDouble(76 ,loanInfo.getAmountToReceive());
				ps.setString(77 ,DateFormateUtil.formateDate(loanInfo.getFirstSuccessBorrowTime()));
				ps.setString(78 ,DateFormateUtil.formateDate(loanInfo.getRegisterTime()));
				ps.setInt(79 ,loanInfo.getCertificateValidate());
				ps.setInt(80 ,loanInfo.getNciicIdentityCheck());
				ps.setInt(81 ,loanInfo.getPhoneValidate());
				ps.setInt(82 ,loanInfo.getVideoValidate());
				ps.setInt(83 ,loanInfo.getCreditValidate());
				ps.setInt(84 ,loanInfo.getEducateValidate());
				ps.setString(85 ,DateFormateUtil.formateDate(loanInfo.getLastSuccessBorrowTime()));
				ps.setDouble(86 ,loanInfo.getHighestPrincipal());
				ps.setDouble(87 ,loanInfo.getHighestDebt());
				ps.setDouble(88 ,loanInfo.getTotalPrincipal());
				ps.setString(89 ,DateFormateUtil.formateDate(loanInfo.getLastupdateTime()));
				
			}
			
			@Override
			public int getBatchSize() {
				return 1;
			}
		});
	}

	@Override
	public void addLoanInfos(List<LoanInfo> loanInfos) {
		
		if (null == loanInfos || 0 == loanInfos.size()) {
			return ;
		}
		
		final List<LoanInfo> tempLoanInfos = loanInfos;
		
		String sql = "INSERT INTO `ppdai`.`loaninfo` "
				+ " ( "
				+ " `listingId`, "
				+ " `title`, "
				+ " `creditCode`, "
				+ " `amount`, "
				+ " `rate`, "
				+ " `months`, "
				+ " `payWay`, "
				+ " `remainFunding`, "
				+ " `preAuditTime`, "
				+ " `fistBidTime`, "
				+ " `lastBidTime`, "
				+ " `lenderCount`, "
				+ " `auditingTime`, "
				+ " `deadLineTimeOrRemindTimeStr`, "
				+ " `currentRate`, "
				+ " `borrowName`, "
				+ " `gender`, "
				+ " `educationDegree`, "
				+ " `graduateSchool`, "
				+ " `studyStyle`, "
				+ " `age`, "
				+ " `successCount`, "
				+ " `wasteCount`, "
				+ " `cancelCount`, "
				+ " `failedCount`, "
				+ " `normalCount`, "
				+ " `overdueLessCount`, "
				+ " `overdueMoreCount`, "
				+ " `owingPrincipal`, "
				+ " `owingAmount`, "
				+ " `amountToReceive`, "
				+ " `firstSuccessBorrowTime`, "
				+ " `registerTime`, "
				+ " `certificateValidate`, "
				+ " `nciicIdentityCheck`, "
				+ " `phoneValidate`, "
				+ " `videoValidate`, "
				+ " `creditValidate`, "
				+ " `educateValidate`, "
				+ " `lastSuccessBorrowTime`, "
				+ " `highestPrincipal`, "
				+ " `highestDebt`, "
				+ " `totalPrincipal`, "
				+ " `insertTime`, "
				+ " `lastupdateTime`, "
				+ " `isBid`) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
				+ " ON DUPLICATE KEY UPDATE "
				+ " `title` = ?, "
				+ " `creditCode` = ?, "
				+ " `amount` = ?, "
				+ " `rate` = ?, "
				+ " `months` = ?, "
				+ " `payWay` = ?, "
				+ " `remainFunding` = ?, "
				+ " `preAuditTime` = ?, "
				+ " `fistBidTime` = ?, "
				+ " `lastBidTime` = ?, "
				+ " `lenderCount` = ?, "
				+ " `auditingTime` = ?, "
				+ " `deadLineTimeOrRemindTimeStr` = ?, "
				+ " `currentRate` = ?, "
				+ " `borrowName` = ?, "
				+ " `gender` = ?, "
				+ " `educationDegree` = ?, "
				+ " `graduateSchool` = ?, "
				+ " `studyStyle` = ?, "
				+ " `age` = ?, "
				+ " `successCount` = ?, "
				+ " `wasteCount` = ?, "
				+ " `cancelCount` = ?, "
				+ " `failedCount` = ?, "
				+ " `normalCount` = ?, "
				+ " `overdueLessCount` = ?, "
				+ " `overdueMoreCount` = ?, "
				+ " `owingPrincipal` = ?, "
				+ " `owingAmount` = ?, "
				+ " `amountToReceive` = ?, "
				+ " `firstSuccessBorrowTime` = ?, "
				+ " `registerTime` = ?, "
				+ " `certificateValidate` = ?, "
				+ " `nciicIdentityCheck` = ?, "
				+ " `phoneValidate` = ?, "
				+ " `videoValidate` = ?, "
				+ " `creditValidate` = ?, "
				+ " `educateValidate` = ?, "
				+ " `lastSuccessBorrowTime` = ?, "
				+ " `highestPrincipal` = ?, "
				+ " `highestDebt` = ?, "
				+ " `totalPrincipal` = ?, "
				+ " `lastupdateTime` = ?";
		
		this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				LoanInfo loanInfo = tempLoanInfos.get(i);
				
				ps.setInt(1 ,loanInfo.getListingId());
				ps.setString(2 ,loanInfo.getTitle());
				ps.setString(3 ,loanInfo.getCreditCode());
				ps.setDouble(4 ,loanInfo.getAmount());
				ps.setDouble(5 ,loanInfo.getRate());
				ps.setInt(6 ,loanInfo.getMonths());
				ps.setInt(7 ,loanInfo.getPayWay());
				ps.setDouble(8 ,loanInfo.getRemainFunding());
				ps.setString(9 ,loanInfo.getPreAuditTime());
				ps.setString(10 ,DateFormateUtil.formateDate(loanInfo.getFistBidTime()));
				ps.setString(11 ,DateFormateUtil.formateDate(loanInfo.getLastBidTime()));
				ps.setInt(12 ,loanInfo.getLenderCount());
				ps.setString(13 ,DateFormateUtil.formateDate(loanInfo.getAuditingTime()));
				ps.setString(14 ,loanInfo.getDeadLineTimeOrRemindTimeStr());
				ps.setDouble(15 ,loanInfo.getCurrentRate());
				ps.setString(16 ,loanInfo.getBorrowName());
				ps.setInt(17 ,loanInfo.getGender());
				ps.setString(18 ,loanInfo.getEducationDegree());
				ps.setString(19 ,loanInfo.getGraduateSchool());
				ps.setString(20 ,loanInfo.getStudyStyle());
				ps.setInt(21 ,loanInfo.getAge());
				ps.setInt(22 ,loanInfo.getSuccessCount());
				ps.setInt(23 ,loanInfo.getWasteCount());
				ps.setInt(24 ,loanInfo.getCancelCount());
				ps.setInt(25 ,loanInfo.getFailedCount());
				ps.setInt(26 ,loanInfo.getNormalCount());
				ps.setInt(27 ,loanInfo.getOverdueLessCount());
				ps.setInt(28 ,loanInfo.getOverdueMoreCount());
				ps.setDouble(29 ,loanInfo.getOwingPrincipal());
				ps.setDouble(30 ,loanInfo.getOwingAmount());
				ps.setDouble(31 ,loanInfo.getAmountToReceive());
				ps.setString(32 ,DateFormateUtil.formateDate(loanInfo.getFirstSuccessBorrowTime()));
				ps.setString(33 ,DateFormateUtil.formateDate(loanInfo.getRegisterTime()));
				ps.setInt(34 ,loanInfo.getCertificateValidate());
				ps.setInt(35 ,loanInfo.getNciicIdentityCheck());
				ps.setInt(36 ,loanInfo.getPhoneValidate());
				ps.setInt(37 ,loanInfo.getVideoValidate());
				ps.setInt(38 ,loanInfo.getCreditValidate());
				ps.setInt(39 ,loanInfo.getEducateValidate());
				ps.setString(40 ,DateFormateUtil.formateDate(loanInfo.getLastSuccessBorrowTime()));
				ps.setDouble(41 ,loanInfo.getHighestPrincipal());
				ps.setDouble(42 ,loanInfo.getHighestDebt());
				ps.setDouble(43 ,loanInfo.getTotalPrincipal());
				ps.setString(44 ,DateFormateUtil.formateDate(loanInfo.getInsertTime()));
				ps.setString(45 ,DateFormateUtil.formateDate(loanInfo.getLastupdateTime()));
				ps.setBoolean(46 ,loanInfo.isBid());
				
				ps.setString(47 ,loanInfo.getTitle());
				ps.setString(48 ,loanInfo.getCreditCode());
				ps.setDouble(49 ,loanInfo.getAmount());
				ps.setDouble(50 ,loanInfo.getRate());
				ps.setInt(51 ,loanInfo.getMonths());
				ps.setInt(52 ,loanInfo.getPayWay());
				ps.setDouble(53 ,loanInfo.getRemainFunding());
				ps.setString(54 ,loanInfo.getPreAuditTime());
				ps.setString(55 ,DateFormateUtil.formateDate(loanInfo.getFistBidTime()));
				ps.setString(56 ,DateFormateUtil.formateDate(loanInfo.getLastBidTime()));
				ps.setInt(57 ,loanInfo.getLenderCount());
				ps.setString(58 ,DateFormateUtil.formateDate(loanInfo.getAuditingTime()));
				ps.setString(59 ,loanInfo.getDeadLineTimeOrRemindTimeStr());
				ps.setDouble(60 ,loanInfo.getCurrentRate());
				ps.setString(61 ,loanInfo.getBorrowName());
				ps.setInt(62 ,loanInfo.getGender());
				ps.setString(63 ,loanInfo.getEducationDegree());
				ps.setString(64 ,loanInfo.getGraduateSchool());
				ps.setString(65 ,loanInfo.getStudyStyle());
				ps.setInt(66 ,loanInfo.getAge());
				ps.setInt(67 ,loanInfo.getSuccessCount());
				ps.setInt(68 ,loanInfo.getWasteCount());
				ps.setInt(69 ,loanInfo.getCancelCount());
				ps.setInt(70 ,loanInfo.getFailedCount());
				ps.setInt(71 ,loanInfo.getNormalCount());
				ps.setInt(72 ,loanInfo.getOverdueLessCount());
				ps.setInt(73 ,loanInfo.getOverdueMoreCount());
				ps.setDouble(74 ,loanInfo.getOwingPrincipal());
				ps.setDouble(75 ,loanInfo.getOwingAmount());
				ps.setDouble(76 ,loanInfo.getAmountToReceive());
				ps.setString(77 ,DateFormateUtil.formateDate(loanInfo.getFirstSuccessBorrowTime()));
				ps.setString(78 ,DateFormateUtil.formateDate(loanInfo.getRegisterTime()));
				ps.setInt(79 ,loanInfo.getCertificateValidate());
				ps.setInt(80 ,loanInfo.getNciicIdentityCheck());
				ps.setInt(81 ,loanInfo.getPhoneValidate());
				ps.setInt(82 ,loanInfo.getVideoValidate());
				ps.setInt(83 ,loanInfo.getCreditValidate());
				ps.setInt(84 ,loanInfo.getEducateValidate());
				ps.setString(85 ,DateFormateUtil.formateDate(loanInfo.getLastSuccessBorrowTime()));
				ps.setDouble(86 ,loanInfo.getHighestPrincipal());
				ps.setDouble(87 ,loanInfo.getHighestDebt());
				ps.setDouble(88 ,loanInfo.getTotalPrincipal());
				ps.setString(89 ,DateFormateUtil.formateDate(loanInfo.getLastupdateTime()));
			}
			
			@Override
			public int getBatchSize() {
				return tempLoanInfos.size();
			}
		});
	}

	@Override
	public List<Integer> getIdsbyStrategy(String strategy) {
		List<Integer> listingIds = new ArrayList<Integer>();
		
		String sql = "SELECT t.listingId FROM ppdai.loanInfo t "
				+ "WHERE t.isBid = false AND t.`lastupdateTime` > DATE_ADD(NOW(), INTERVAL -3 MINUTE) AND " + strategy;
		SqlRowSet rowSet = this.jdbcTemplate.queryForRowSet(sql);
		while (rowSet.next()){
			listingIds.add(rowSet.getInt(1));
		}
		
		return listingIds;
	}
	
	@Override
	public List<Integer> getCanBeIgnoreIds() {
		List<Integer> listingIds = new ArrayList<Integer>();
		
		String sql = "SELECT t.listingId FROM ppdai.loanInfo t "
				+ "WHERE t.`lastupdateTime` > DATE_ADD(NOW(), INTERVAL -1 MINUTE) ORDER BY t.`lastupdateTime` DESC LIMIT 3000";
		SqlRowSet rowSet = this.jdbcTemplate.queryForRowSet(sql);
		while (rowSet.next()){
			listingIds.add(rowSet.getInt(1));
		}
		
		return listingIds;
	}

	@Override
	public void updateLoanInfo2isBid(Integer listingId) {
		String sql = "UPDATE `ppdai`.`loaninfo` SET `isBid` = TRUE WHERE `listingId` = " + listingId;
		this.jdbcTemplate.batchUpdate(sql);
	}
	
	

}
