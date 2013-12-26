package org.data.support.beans.factory.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DefaultBeanDefinitionDocumentReader implements BeanDefinitionDocumentReader {
	
	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public void registerBeanDefinitions(Document doc) {
		log.debug("Loading bean definitions");
		Element root = doc.getDocumentElement();
		
		BeanDefinitionParserDelegate delegate = new BeanDefinitionParserDelegate();
		
		preProcess(root);
		process(root, delegate);
		postProcess(root);
	}
	
	
	protected void preProcess(Element e) {}
	
	protected void postProcess(Element e) {}
	
	protected void process(Element e, BeanDefinitionParserDelegate delegate){
		
	}

}
