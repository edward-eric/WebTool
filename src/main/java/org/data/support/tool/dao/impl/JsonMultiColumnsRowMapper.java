package org.data.support.tool.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
/**
 * JSON row mapper
 * @author eric.chen
 *
 */
public class JsonMultiColumnsRowMapper implements RowMapper<Map<String, Object>> {
	
	private final List<String> colNames;
	
	public JsonMultiColumnsRowMapper(List<String> colNames){
		this.colNames = colNames;
	}

	@Override
	public Map<String, Object> mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		Map<String, Object> columns = new HashMap<String, Object>();
		for(String col : colNames){
			columns.put(col, rs.getObject(col));
		}
		return columns;
	}
}
