package org.data.support.tool.dao.impl;

import java.util.List;

import org.data.support.tool.dao.CommonDataAccessor;
import org.springframework.jdbc.core.RowMapper;

public class CommonDataAccessorImpl extends DataAccessorImpl implements CommonDataAccessor {

	@Override
	public <T> List<T> queryCommonDataResult(String sql, List<String> colNames) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> queryCommonDataResult(String sql, Object[] params,
			List<String> colNames) {
		return null;
	}

}
