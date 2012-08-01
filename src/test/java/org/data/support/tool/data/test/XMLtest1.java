package org.data.support.tool.data.test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.data.support.tool.common.SpringUtil;
import org.data.support.tool.common.file.handler.DefaultDefinitionHandler;
import org.data.support.tool.dao.JsonDataAccessor;
import org.data.support.tool.data.IDBAccessor;
import org.data.support.tool.data.xml.mgr.DefMgr;

public class XMLtest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		/DefMgr mgr = SpringUtil.getBean(DefMgr.class);
		
		DefaultDefinitionHandler handler = SpringUtil.getBean("queryDefinitionHandler",DefaultDefinitionHandler.class);
		
		System.out.println(handler.defaultReturn());


		dbtest(handler.defaultReturn().get("Scenario").toString());
		
		
	}

	private static void dbtest(String sql) {
		
		JsonDataAccessor accessor = SpringUtil.getBean(JsonDataAccessor.class);
		List cols2 = new ArrayList();
		cols2.add("id");
        cols2.add("name");
        cols2.add("description");
		List<Map<String, Object>> schemas2 = accessor.queryJsonMapResult(sql, cols2, 5, 7);
		ListIterator iter = schemas2.listIterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
		System.out.println(accessor.getTotalRecords(sql));
		
	}

}
