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
public interface JsonDataAccessor{
	
	/**
	 * Convert data to List<Map<String, Object>>
	 * @param sql
	 * @param colNames
	 * @return JSON
	 */
	public List<Map<String, Object>> queryJsonMapResult(String sql, final List<String> colNames);
	
	/**
	 * Convert data to List<Map<String, Object>>
	 * @param sql
	 * @param params
	 * @param colNames
	 * @return JSON
	 */
	public List<Map<String, Object>> queryJsonMapResult(String sql, Object[] params, final List<String> colNames);

}
