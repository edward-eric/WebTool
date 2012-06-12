package org.data.support.tool.data.test;

import java.util.List;

import org.data.support.tool.common.Spring;
import org.data.support.tool.data.DBExecutor;
import org.data.support.tool.data.xml.mgr.DefMgr;

public class XMLtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DefMgr mgr = Spring.getBean(DefMgr.class);

		mgr.processInput();

		dbtest();
	}

	private static void dbtest() {
		DBExecutor executor = Spring.getBean(DBExecutor.class);

		List<String> schemas = executor
				.query("select schemaname from syscat.schemata");

		System.out.println(schemas);
	}

}
