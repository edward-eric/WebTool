package org.data.support.tool.data.test;

import java.util.List;

import org.data.support.tool.common.Spring;
import org.data.support.tool.data.DBExecutor;
import org.data.support.tool.data.xml.mgr.DefMgr;

public class XMLtest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DefMgr mgr = Spring.getBean(DefMgr.class);


		dbtest(mgr.returnQueryList().get(0));
	}

	private static void dbtest(String sql) {
		DBExecutor executor = Spring.getBean(DBExecutor.class);

		List<String> schemas = executor
				.query(sql);

		System.out.println(schemas);
	}

}
