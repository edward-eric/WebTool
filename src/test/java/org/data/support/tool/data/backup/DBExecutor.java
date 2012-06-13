package org.data.support.tool.data.backup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DBExecutor {
	private JdbcTemplate jdbcTemp;

	public void setJdbcTemplate(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}

	public List<List<Object>> query(String sql, final List<String> colNames) {
		return jdbcTemp.query(sql, new MultiColumnRowMapper(colNames));
	}

	public List<List<Object>> query(String sql, Object[] params,
			final List<String> colNames) {
		return jdbcTemp.query(sql, params, new MultiColumnRowMapper(colNames));
	}

	public <T> List<T> query(String sql, Object[] params) {
		return jdbcTemp.query(sql, params, new SingleColumnRowMapper<T>());
	}

	public <T> List<T> query(String sql) {
		return jdbcTemp.query(sql, new SingleColumnRowMapper<T>());
	}

	private class SingleColumnRowMapper<T> implements RowMapper<T> {
		@SuppressWarnings("unchecked")
		@Override
		public T mapRow(ResultSet rs, int rowNum) throws SQLException {
			return (T) rs.getObject(1);
		}
	}

	private class MultiColumnRowMapper implements RowMapper<List<Object>> {
		private final List<String> colNames;

		private MultiColumnRowMapper(List<String> colNames) {
			this.colNames = colNames;
		}

		@Override
		public List<Object> mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			List<Object> row = new ArrayList<Object>();
			for (String col : colNames) {
				row.add(rs.getObject(col));
			}
			return row;
		}
	}
}
