package org.data.support.tool.common.file.handler;

import java.util.Map;

public interface DefaultDefinitionHandler {
	
	public void init();
	
	public void refresh();
	
	public void convert();
	
	public void process();
	
	public Map<String, Object> defaultReturn();

}
