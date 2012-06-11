package org.data.support.tool.data.xml.processor;

import java.util.List;

import org.dom4j.Document;

public interface IProcessor {
	
	public void init();
	
	public List process(Document doc);

}
