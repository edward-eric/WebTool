package org.data.support.beans.factory.support;

import org.data.support.beans.factory.QueryDefinitionStoreException;
import org.springframework.core.io.Resource;

public interface QueryDefinitionReader {
	
	QueryDefinitionRegistry getRegistry();
	
	int loadQueryDefinitions(Resource resource) throws QueryDefinitionStoreException;
	
	int loadQueryDefinitions(Resource... resources) throws QueryDefinitionStoreException;
	
	int loadQueryDefinitions(String location) throws QueryDefinitionStoreException;
	
	int loadQueryDefinitions(String... locations) throws QueryDefinitionStoreException;

}
