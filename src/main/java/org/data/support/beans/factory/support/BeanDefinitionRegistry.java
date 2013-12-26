package org.data.support.beans.factory.support;

import org.data.support.beans.factory.BeanDefinitionStoreException;
import org.data.support.beans.factory.NoSuchBeanDefinitionException;
import org.data.support.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
	
	void registerBeanDefinition(String name, BeanDefinition bean)
	throws BeanDefinitionStoreException;
	
	
	void removeBeanDefinition(String name)
	throws BeanDefinitionStoreException;
	
	BeanDefinition getBeanDefinition(String name)
	throws NoSuchBeanDefinitionException;
	
	boolean containsBeanDefinition(String name);
	
	String[] getBeanDefinitionNames();
	
	int getBeanDefinitionCount();
	
}
