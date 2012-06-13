package org.data.support.tool.data.xml.processor;

import java.net.MalformedURLException;
import java.util.List;

import org.dom4j.DocumentException;

public interface FProcessor {
	
	public List getDocuments() throws MalformedURLException, DocumentException;

}
