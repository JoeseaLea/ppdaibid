package com.ppdaibid.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


public abstract class BaseDao {

	protected JdbcTemplate jdbcTemplate;
	
	/**
	 * 设置数据源
	 * @param dataSource 数据源
	 */
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * 提交事物
	 */
	public void commit() {
		try {
			this.jdbcTemplate.getDataSource().getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
