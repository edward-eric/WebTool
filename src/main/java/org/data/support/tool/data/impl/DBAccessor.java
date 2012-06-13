package org.data.support.tool.data.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.data.support.tool.data.IDBAccessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * Util class which convert DB results to objects
 * @author eric.chen
 *
 */
public class DBAccessor implements IDBAccessor{

	/**
	 * support template class
	 */
	private JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	/**
	 * Fetch result object with columns
	 * @param sql
	 * @param colNames
	 * @return
	 */
	public List<List<Object>> getResult(String sql, final List<String> colNames){
		return template.query(sql, new MultiColumnRowMapper(colNames));
	}
	
	/**
	 * Fetch result objects with columns and params
	 * @param sql
	 * @param params
	 * @param colNames
	 * @return
	 */
	public List<List<Object>> getResult(String sql, Object[] params, final List<String> colNames){
		return template.query(sql, params, new MultiColumnRowMapper(colNames));
	}
	
	/**
	 * Row mapper implements object transfer with required columns
	 * @author eric.chen
	 *
	 */
	private class MultiColumnRowMapper implements RowMapper<List<Object>>{
		
		private final List<String> colNames;

		private MultiColumnRowMapper(List<String> colNames) {
			this.colNames = colNames;
		}

		@Override
		public List<Object> mapRow(ResultSet rs, int rowno)
				throws SQLException {
			List<Object> rows = new ArrayList<Object>();
			rows.add(rowno);
			for (String col : colNames) {
				rows.add(rs.getObject(col));
			}
			return rows;
		}
		
	}
	
	
}
