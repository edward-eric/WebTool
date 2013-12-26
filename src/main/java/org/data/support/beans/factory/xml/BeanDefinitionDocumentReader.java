package org.data.support.beans.factory.xml;

import org.data.support.beans.factory.QueryDefinitionStoreException;
import org.w3c.dom.Document;

public interface BeanDefinitionDocumentReader {
	
	public void registerBeanDefinitions(Document doc) throws QueryDefinitionStoreException;

}
