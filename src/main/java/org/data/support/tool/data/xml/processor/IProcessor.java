package org.data.support.tool.data.xml.processor;

import java.util.List;

import org.dom4j.Document;

public interface IProcessor {
	
	public List process(List<Document> docs);

}
