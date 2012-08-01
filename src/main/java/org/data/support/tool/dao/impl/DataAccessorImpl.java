package org.data.support.tool.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Parent Data accessory implementation
 * @author eric.chen
 *
 */
public abstract class DataAccessorImpl{
	
	private static String tempSql = "";
	private static String finalSql = "";
	private static String sizeSql = "";
	
	private static int startIndex = 0;
	private static int pageSize = 20;
	
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
	
	protected String getFinalSql(){
		return finalSql;
	}
	
	protected String getSizeSql(){
		return sizeSql;
	}
	
	protected void setPagingSize(int startIndex, int pageSize){
		this.startIndex = startIndex;
		this.pageSize = pageSize;
	}
	
	private void wrapToRowNumberSql(String sql){
		StringBuffer pagingSql = new StringBuffer();
		pagingSql.append("SELECT TEMPX.*, ROWNUMBER() OVER() ROWNO FROM (");
		pagingSql.append(sql);
		pagingSql.append(") TEMPX");
		tempSql = pagingSql.toString();
	}
	
	protected void wrapToPaingSql(String sql){
		wrapToRowNumberSql(sql);
		StringBuffer pagingSql = new StringBuffer();
		pagingSql.append("SELECT TEMPY.* FROM (");
		pagingSql.append(tempSql);
		pagingSql.append(") TEMPY WHERE ROWNO BETWEEN ");
		pagingSql.append(startIndex);
		pagingSql.append(" and ");
		pagingSql.append(startIndex + pageSize);
		finalSql = pagingSql.toString();
	}
	
	protected void wrapToSizeSql(String sql){
		StringBuffer pagingSql = new StringBuffer();
		pagingSql.append("SELECT count(*) FROM (");
		pagingSql.append(sql);
		pagingSql.append(")");
		sizeSql = pagingSql.toString();
	}

}
