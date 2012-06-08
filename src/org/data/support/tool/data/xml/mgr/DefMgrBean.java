package org.data.support.tool.data.xml.mgr;

import org.data.support.tool.data.xml.processor.IProcessor;

public class DefMgrBean implements DefMgr{
	
	/**
	 * Definition files' names where you could define your SQL composition
	 */
	private String[] defnames;
	
	private IProcessor processor;
	
	public void processInput(){
		processor.process();
	}
	
	

}
