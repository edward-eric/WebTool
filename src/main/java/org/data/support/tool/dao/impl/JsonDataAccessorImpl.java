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
		wrapToPaingSql(sql);
		return getJdbcTemplate().query(getFinalSql(),
				new JsonMultiColumnsRowMapper(colNames));
	}

	@Override
	public List<Map<String, Object>> queryJsonMapResult(String sql,
			Object[] params, List<String> colNames) {
		wrapToPaingSql(sql);
		return getJdbcTemplate().query(getFinalSql(), params,
				new JsonMultiColumnsRowMapper(colNames));
	}

	@Override
	public List<Map<String, Object>> queryJsonMapResult(String sql,
			List<String> colNames, int startIndex, int pageSize) {
		setPagingSize(startIndex, pageSize);
		wrapToPaingSql(sql);
		return getJdbcTemplate().query(getFinalSql(),
				new JsonMultiColumnsRowMapper(colNames));
	}

	@Override
	public List<Map<String, Object>> queryJsonMapResult(String sql,
			Object[] params, List<String> colNames, int startIndex, int pageSize) {
		setPagingSize(startIndex, pageSize);
		wrapToPaingSql(sql);
		return getJdbcTemplate().query(getFinalSql(), params,
				new JsonMultiColumnsRowMapper(colNames));
	}

	@Override
	public int getTotalRecords(String sql) {
		wrapToSizeSql(sql);
		return getJdbcTemplate().queryForInt(getSizeSql());
	}

}
