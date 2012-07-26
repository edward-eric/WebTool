package org.data.support.tool.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Parent Data accessory implementation
 * @author eric.chen
 *
 */
public abstract class DataAccessorImpl {
	
	/**
	 * Set JDBCTemplate
	 */
	protected JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}
