package org.data.support.beans.factory.support;

import org.data.support.beans.factory.QueryDefinitionStoreException;
import org.data.support.beans.factory.NoSuchQueryDefinitionException;
import org.data.support.beans.factory.config.QueryDefinition;

public interface QueryDefinitionRegistry {
	
	void registerQueryDefinition(String name, QueryDefinition bean)
	throws QueryDefinitionStoreException;
	
	
	void removeQueryDefinition(String name)
	throws QueryDefinitionStoreException;
	
	QueryDefinition getQueryDefinition(String name)
	throws NoSuchQueryDefinitionException;
	
	boolean containsQueryDefinition(String name);
	
	String[] getQueryDefinitionNames();
	
	int getQueryDefinitionCount();
	
	boolean isQueryNameInUse();
	
}
