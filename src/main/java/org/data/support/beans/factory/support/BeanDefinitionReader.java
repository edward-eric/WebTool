package org.data.support.beans.factory.support;

import org.data.support.beans.factory.BeanDefinitionStoreException;
import org.springframework.core.io.Resource;

public interface BeanDefinitionReader {
	
	BeanDefinitionRegistry getRegistry();
	
	int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException;
	
	int loadBeanDefinitions(Resource... resources) throws BeanDefinitionStoreException;
	
	int loadBeanDefinitions(String location) throws BeanDefinitionStoreException;
	
	int loadBeanDefinitions(String... locations) throws BeanDefinitionStoreException;

}
