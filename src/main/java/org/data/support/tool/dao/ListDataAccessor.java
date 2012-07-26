package org.data.support.tool.dao;

import java.util.List;
/**
 * Common Data accessory interface
 * 
 * @author eric.chen
 *
 */
public interface ListDataAccessor {
	
	
	/**
	 * 
	 * @param sql
	 * @param colNames
	 * @return
	 */
    public List<List<Object>> queryJsonMapResult(String sql, final List<String> colNames);
	
    /**
     * 
     * @param sql
     * @param params
     * @param colNames
     * @return
     */
	public List<List<Object>> queryJsonMapResult(String sql, Object[] params, final List<String> colNames);

}
