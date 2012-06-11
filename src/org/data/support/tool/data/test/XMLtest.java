package org.data.support.tool.data.test;

import org.data.support.tool.data.xml.mgr.DefMgr;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;



public class XMLtest {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ApplicationContext ctx = new FileSystemXmlApplicationContext("resource/appctx.xml");
		
		DefMgr mgr = (DefMgr) ctx.getBean("defMgr");
		
		mgr.processInput();
		

	}

}
