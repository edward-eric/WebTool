package org.data.support.tool.dao;

import java.util.List;
/**
 * 
 * @author eric.chen
 *
 */
public interface CommonDataAccessor {
	/**
	 * 
	 * @param <T>
	 * @param sql
	 * @param colNames
	 * @return
	 */
	public <T extends Object> List<T> queryCommonDataResult(String sql, final List<String> colNames);
	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @param colNames
	 * @return
	 */
	public <T extends Object> List<T> queryCommonDataResult(String sql, Object[] params, final List<String> colNames);

}
