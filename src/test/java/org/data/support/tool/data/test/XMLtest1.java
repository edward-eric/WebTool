package org.data.support.tool.data.test;

import java.util.ArrayList;
import java.util.List;

import org.data.support.tool.common.SpringUtil;
import org.data.support.tool.data.IDBAccessor;
import org.data.support.tool.data.backup.Spring;
import org.data.support.tool.data.xml.mgr.DefMgr;

public class XMLtest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*DefMgr mgr = SpringUtil.getBean(DefMgr.class);


		dbtest(mgr.returnQueryList().get(0));*/
		
		
		System.out.println("/");
	}

	private static void dbtest(String sql) {
		IDBAccessor executor = SpringUtil.getBean(IDBAccessor.class);
        List cols = new ArrayList();
        cols.add("id");
        cols.add("name");
        cols.add("runid");
		List<List<Object>> schemas = executor
				.getResult(sql, cols);

		System.out.println(schemas);
	}

}
