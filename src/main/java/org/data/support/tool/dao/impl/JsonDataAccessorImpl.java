package org.data.support.tool.dao.impl;

import java.util.List;
import java.util.Map;

import org.data.support.tool.dao.JsonDataAccessor;
/**
 * JSON data accessory method
 * @author eric.chen
 *
 */
public class JsonDataAccessorImpl extends DataAccessorImpl implements JsonDataAccessor {
	
	@Override
	public List<Map<String, Object>> queryJsonMapResult(String sql,
			List<String> colNames) {
		return getJdbcTemplate().query(sql, new JsonMultiColumnsRowMapper(colNames));
	}

	@Override
	public List<Map<String, Object>> queryJsonMapResult(String sql,
			Object[] params, List<String> colNames) {
		return getJdbcTemplate().query(sql, params, new JsonMultiColumnsRowMapper(colNames));
	}

}
