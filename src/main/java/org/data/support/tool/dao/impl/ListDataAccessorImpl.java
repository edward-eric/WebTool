package org.data.support.tool.dao.impl;

import java.util.List;

import org.data.support.tool.dao.ListDataAccessor;
/**
 * 
 * @author eric.chen
 *
 */
public class ListDataAccessorImpl extends DataAccessorImpl implements
		ListDataAccessor {

	@Override
	public List<List<Object>> queryJsonMapResult(String sql, List<String> colNames) {
		return getJdbcTemplate().query(sql, new ListMultiColumnsRowMapper(colNames));
	}

	@Override
	public List<List<Object>> queryJsonMapResult(String sql, Object[] params,
			List<String> colNames) {
		return getJdbcTemplate().query(sql, params, new ListMultiColumnsRowMapper(colNames));
	}

}
