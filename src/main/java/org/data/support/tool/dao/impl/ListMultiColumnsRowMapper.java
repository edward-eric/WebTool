package org.data.support.tool.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author eric.chen
 *
 */
public class ListMultiColumnsRowMapper implements RowMapper<List<Object>> {
	
	private final List<String> colNames;
	
	public ListMultiColumnsRowMapper(List<String> colNames){
		this.colNames = colNames;
	}

	@Override
	public List<Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<Object> columns = new ArrayList<Object>();
        for(String col : colNames){
        	columns.add(col);
        }
		return columns;
	}

}
