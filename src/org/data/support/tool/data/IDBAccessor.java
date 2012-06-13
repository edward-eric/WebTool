package org.data.support.tool.data;

import java.util.List;

public interface IDBAccessor {
	
	public List<List<Object>> getResult(String sql, final List<String> colNames);
	
	public List<List<Object>> getResult(String sql, Object[] params, final List<String> colNames);

}
