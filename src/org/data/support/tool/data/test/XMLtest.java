package org.data.support.tool.data.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.data.support.tool.data.xml.mgr.DefMgr;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class XMLtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"resource/appctx.xml");

		DefMgr mgr = (DefMgr) ctx.getBean("defMgr");

		mgr.processInput();
		
		dbtest(ctx);

	}

	private static void dbtest(ApplicationContext ctx) {
		JdbcTemplate jdbcTemp = (JdbcTemplate) ctx.getBean("jdbcTemplate");

		List<String> schemas = jdbcTemp.query(
				"select schemaname from syscat.schemata",
				new RowMapper<String>() {
					public String mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return rs.getString(1).trim();
					}
				});
		
		System.out.println(schemas);
	}

}
