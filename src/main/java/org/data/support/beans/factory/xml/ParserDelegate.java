package org.data.support.beans.factory.xml;

import org.w3c.dom.Element;

public interface ParserDelegate {
	
	public void preProcess(Element e);
	public void postProcess(Element e);
	public void process(Element e);

}
