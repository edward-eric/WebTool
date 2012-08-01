package org.data.support.tool.dao;

import java.util.List;
import java.util.Map;
/**
 * 
 * Common JSON Data accessory interface
 * 
 * @author eric.chen
 *
 */
public interface JsonDataAccessor extends DataAccessor{
	
	public List<Map<String, Object>> queryJsonMapResult(String sql,
			final List<String> colNames);

	public List<Map<String, Object>> queryJsonMapResult(String sql,
			Object[] params, final List<String> colNames);

	public List<Map<String, Object>> queryJsonMapResult(String sql,
			final List<String> colNames, int startIndex, int pageSize);

	public List<Map<String, Object>> queryJsonMapResult(String sql,
			Object[] params, final List<String> colNames, int startIndex,
			int pageSize);
}
