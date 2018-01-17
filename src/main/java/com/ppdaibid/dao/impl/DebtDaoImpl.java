package com.ppdaibid.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
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
		
		/*String sql = "INSERT INTO `ppdai`.`debtinfo` "
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
				+ " `isBid` = ?";*/
		
		String sql = "INSERT INTO `ppdai`.`debtinfo` "
				+ " ( "
				+ " `debtdealId`, "
				+ " `owingNumber`, "
				+ " `priceforSale`, "
				+ " `listingId`, "
				+ " `creditCode`, "
				+ " `amount`, "
				+ " `months`, "
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
				+ " `fistBidTime`, "
				+ " `lastBidTime`, "
				+ " `lenderCount`, "
				+ " `auditingTime`, "
				+ " `remainFunding`, "
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
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
				+ " ON DUPLICATE KEY UPDATE "
				+ " `debtdealId` = ?, "
				+ " `owingNumber` = ?, "
				+ " `priceforSale` = ?, "
				+ " `listingId` = ?, "
				+ " `creditCode` = ?, "
				+ " `amount` = ?, "
				+ " `months` = ?, "
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
				+ " `fistBidTime` = ?, "
				+ " `lastBidTime` = ?, "
				+ " `lenderCount` = ?, "
				+ " `auditingTime` = ?, "
				+ " `remainFunding` = ?, "
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
				+ " `insertTime` = ?, "
				+ " `lastupdateTime` = ?, "
				+ " `isBid` = ? ";
		
		this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				/*ps.setInt(1, debtInfo.getDebtdealId());
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
				ps.setBoolean(51, debtInfo.isBid());*/
				
				ps.setInt(1, debtInfo.getDebtdealId());
				ps.setInt(2, debtInfo.getOwingNumber());
				ps.setDouble(3, debtInfo.getPriceforSale());
				ps.setInt(4, debtInfo.getListingId());
				ps.setString(5, debtInfo.getCreditCode());
				ps.setDouble(6, debtInfo.getAmount());
				ps.setInt(7, debtInfo.getMonths());
				ps.setInt(8, debtInfo.getDebtId());
				ps.setString(9, debtInfo.getSeller());
				ps.setInt(10, debtInfo.getStatusId());
				ps.setString(11, debtInfo.getLender());
				ps.setString(12, DateFormateUtil.formateDate(debtInfo.getBidDateTime()));
				ps.setDouble(13, debtInfo.getOwingPrincipal());
				ps.setDouble(14, debtInfo.getOwingInterest());
				ps.setInt(15, debtInfo.getDays());
				ps.setDouble(16, debtInfo.getPriceforSaleRate());
				ps.setDouble(17, debtInfo.getPreferenceDegree());
				ps.setDouble(18, debtInfo.getListingAmount());
				ps.setInt(19, debtInfo.getListingMonths());
				ps.setString(20, DateFormateUtil.formateDate(debtInfo.getListingTime()));
				ps.setDouble(21, debtInfo.getListingRate());
				ps.setInt(22, debtInfo.getPastDueNumber());
				ps.setString(23, debtInfo.getCurrentCreditCode());
				ps.setDouble(24, debtInfo.getAllowanceRadio());
				ps.setInt(25, debtInfo.getPastDueDay());
				ps.setString(26, DateFormateUtil.formateDate(debtInfo.getFistBidTime()));
				ps.setString(27, DateFormateUtil.formateDate(debtInfo.getLastBidTime()));
				ps.setInt(28, debtInfo.getLenderCount());
				ps.setString(29, DateFormateUtil.formateDate(debtInfo.getAuditingTime()));
				ps.setDouble(30, debtInfo.getRemainFunding());
				ps.setString(31, debtInfo.getDeadLineTimeOrRemindTimeStr());
				ps.setDouble(32, debtInfo.getCurrentRate());
				ps.setString(33, debtInfo.getBorrowName());
				ps.setInt(34, debtInfo.getGender());
				ps.setString(35, debtInfo.getEducationDegree());
				ps.setString(36, debtInfo.getGraduateSchool());
				ps.setString(37, debtInfo.getStudyStyle());
				ps.setInt(38, debtInfo.getAge());
				ps.setInt(39, debtInfo.getSuccessCount());
				ps.setInt(40, debtInfo.getWasteCount());
				ps.setInt(41, debtInfo.getCancelCount());
				ps.setInt(42, debtInfo.getFailedCount());
				ps.setInt(43, debtInfo.getNormalCount());
				ps.setInt(44, debtInfo.getOverdueLessCount());
				ps.setInt(45, debtInfo.getOverdueMoreCount());
				ps.setDouble(46, debtInfo.getOwingAmount());
				ps.setDouble(47, debtInfo.getAmountToReceive());
				ps.setString(48, DateFormateUtil.formateDate(debtInfo.getFirstSuccessBorrowTime()));
				ps.setString(49, DateFormateUtil.formateDate(debtInfo.getRegisterTime()));
				ps.setInt(50, debtInfo.getCertificateValidate());
				ps.setInt(51, debtInfo.getNciicIdentityCheck());
				ps.setInt(52, debtInfo.getPhoneValidate());
				ps.setInt(53, debtInfo.getVideoValidate());
				ps.setInt(54, debtInfo.getCreditValidate());
				ps.setInt(55, debtInfo.getEducateValidate());
				ps.setString(56, DateFormateUtil.formateDate(debtInfo.getLastSuccessBorrowTime()));
				ps.setDouble(57, debtInfo.getHighestPrincipal());
				ps.setDouble(58, debtInfo.getHighestDebt());
				ps.setDouble(59, debtInfo.getTotalPrincipal());
				ps.setString(60, DateFormateUtil.formateDate(debtInfo.getInsertTime()));
				ps.setString(61, DateFormateUtil.formateDate(debtInfo.getLastupdateTime()));
				ps.setBoolean(62, debtInfo.isBid());
				
				ps.setInt(63, debtInfo.getDebtdealId());
				ps.setInt(64, debtInfo.getOwingNumber());
				ps.setDouble(65, debtInfo.getPriceforSale());
				ps.setInt(66, debtInfo.getListingId());
				ps.setString(67, debtInfo.getCreditCode());
				ps.setDouble(68, debtInfo.getAmount());
				ps.setInt(69, debtInfo.getMonths());
				ps.setInt(70, debtInfo.getDebtId());
				ps.setString(71, debtInfo.getSeller());
				ps.setInt(72, debtInfo.getStatusId());
				ps.setString(73, debtInfo.getLender());
				ps.setString(74, DateFormateUtil.formateDate(debtInfo.getBidDateTime()));
				ps.setDouble(75, debtInfo.getOwingPrincipal());
				ps.setDouble(76, debtInfo.getOwingInterest());
				ps.setInt(77, debtInfo.getDays());
				ps.setDouble(78, debtInfo.getPriceforSaleRate());
				ps.setDouble(79, debtInfo.getPreferenceDegree());
				ps.setDouble(80, debtInfo.getListingAmount());
				ps.setInt(81, debtInfo.getListingMonths());
				ps.setString(82, DateFormateUtil.formateDate(debtInfo.getListingTime()));
				ps.setDouble(83, debtInfo.getListingRate());
				ps.setInt(84, debtInfo.getPastDueNumber());
				ps.setString(85, debtInfo.getCurrentCreditCode());
				ps.setDouble(86, debtInfo.getAllowanceRadio());
				ps.setInt(87, debtInfo.getPastDueDay());
				ps.setString(88, DateFormateUtil.formateDate(debtInfo.getFistBidTime()));
				ps.setString(89, DateFormateUtil.formateDate(debtInfo.getLastBidTime()));
				ps.setInt(90, debtInfo.getLenderCount());
				ps.setString(91, DateFormateUtil.formateDate(debtInfo.getAuditingTime()));
				ps.setDouble(92, debtInfo.getRemainFunding());
				ps.setString(93, debtInfo.getDeadLineTimeOrRemindTimeStr());
				ps.setDouble(94, debtInfo.getCurrentRate());
				ps.setString(95, debtInfo.getBorrowName());
				ps.setInt(96, debtInfo.getGender());
				ps.setString(97, debtInfo.getEducationDegree());
				ps.setString(98, debtInfo.getGraduateSchool());
				ps.setString(99, debtInfo.getStudyStyle());
				ps.setInt(100, debtInfo.getAge());
				ps.setInt(101, debtInfo.getSuccessCount());
				ps.setInt(102, debtInfo.getWasteCount());
				ps.setInt(103, debtInfo.getCancelCount());
				ps.setInt(104, debtInfo.getFailedCount());
				ps.setInt(105, debtInfo.getNormalCount());
				ps.setInt(106, debtInfo.getOverdueLessCount());
				ps.setInt(107, debtInfo.getOverdueMoreCount());
				ps.setDouble(108, debtInfo.getOwingAmount());
				ps.setDouble(109, debtInfo.getAmountToReceive());
				ps.setString(110, DateFormateUtil.formateDate(debtInfo.getFirstSuccessBorrowTime()));
				ps.setString(111, DateFormateUtil.formateDate(debtInfo.getRegisterTime()));
				ps.setInt(112, debtInfo.getCertificateValidate());
				ps.setInt(113, debtInfo.getNciicIdentityCheck());
				ps.setInt(114, debtInfo.getPhoneValidate());
				ps.setInt(115, debtInfo.getVideoValidate());
				ps.setInt(116, debtInfo.getCreditValidate());
				ps.setInt(117, debtInfo.getEducateValidate());
				ps.setString(118, DateFormateUtil.formateDate(debtInfo.getLastSuccessBorrowTime()));
				ps.setDouble(119, debtInfo.getHighestPrincipal());
				ps.setDouble(120, debtInfo.getHighestDebt());
				ps.setDouble(121, debtInfo.getTotalPrincipal());
				ps.setString(122, DateFormateUtil.formateDate(debtInfo.getInsertTime()));
				ps.setString(123, DateFormateUtil.formateDate(debtInfo.getLastupdateTime()));
				ps.setBoolean(124, debtInfo.isBid());
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

	@Override
	public List<DebtInfo> getDebtInfos() {
		RowMapper<DebtInfo> rowMapper=new BeanPropertyRowMapper<>(DebtInfo.class);
		String sql = " SELECT                             "
				+ "   id,                              "
				+ "   debtdealId,                      "
				+ "   owingNumber,                     "
				+ "   priceforSale,                    "
				+ "   listingId,                       "
				+ "   creditCode,                      "
				+ "   amount,                          "
				+ "   months,                          "
				+ "   debtId,                          "
				+ "   seller,                          "
				+ "   statusId,                        "
				+ "   lender,                          "
				+ "   bidDateTime,                     "
				+ "   owingPrincipal,                  "
				+ "   owingInterest,                   "
				+ "   days,                            "
				+ "   priceforSaleRate,                "
				+ "   preferenceDegree,                "
				+ "   listingAmount,                   "
				+ "   listingMonths,                   "
				+ "   listingTime,                     "
				+ "   listingRate,                     "
				+ "   pastDueNumber,                   "
				+ "   currentCreditCode,               "
				+ "   allowanceRadio,                  "
				+ "   pastDueDay,                      "
				+ "   fistBidTime,                     "
				+ "   lastBidTime,                     "
				+ "   lenderCount,                     "
				+ "   auditingTime,                    "
				+ "   remainFunding,                   "
				+ "   deadLineTimeOrRemindTimeStr,     "
				+ "   currentRate,                     "
				+ "   borrowName,                      "
				+ "   gender,                          "
				+ "   educationDegree,                 "
				+ "   graduateSchool,                  "
				+ "   studyStyle,                      "
				+ "   age,                             "
				+ "   successCount,                    "
				+ "   wasteCount,                      "
				+ "   cancelCount,                     "
				+ "   failedCount,                     "
				+ "   normalCount,                     "
				+ "   overdueLessCount,                "
				+ "   overdueMoreCount,                "
				+ "   owingAmount,                     "
				+ "   amountToReceive,                 "
				+ "   firstSuccessBorrowTime,          "
				+ "   registerTime,                    "
				+ "   certificateValidate,             "
				+ "   nciicIdentityCheck,              "
				+ "   phoneValidate,                   "
				+ "   videoValidate,                   "
				+ "   creditValidate,                  "
				+ "   educateValidate,                 "
				+ "   lastSuccessBorrowTime,           "
				+ "   highestPrincipal,                "
				+ "   highestDebt,                     "
				+ "   totalPrincipal,                  "
				+ "   insertTime,                      "
				+ "   lastupdateTime,                  "
				+ "   isBid                            "
				+ " FROM ppdai.debtinfo WHERE isBid = 1";
		List<DebtInfo> debtInfos = this.jdbcTemplate.query(sql, rowMapper);
		return debtInfos;
	}
	
}
