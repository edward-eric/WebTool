package org.data.support.beans.factory.xml;

import org.w3c.dom.Document;

public interface BeanDefinitionDocumentReader {
	
	public void registerBeanDefinitions(Document doc);

}
