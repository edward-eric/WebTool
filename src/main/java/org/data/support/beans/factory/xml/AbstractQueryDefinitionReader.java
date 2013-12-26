package org.data.support.beans.factory.xml;

import java.io.IOException;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.data.support.beans.factory.QueryDefinitionStoreException;
import org.data.support.beans.factory.support.QueryDefinitionReader;
import org.data.support.beans.factory.support.QueryDefinitionRegistry;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.Assert;

public abstract class AbstractQueryDefinitionReader implements QueryDefinitionReader {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private final QueryDefinitionRegistry registry;
	
	private ResourceLoader resourceLoader;
	
	protected AbstractQueryDefinitionReader(QueryDefinitionRegistry registry){
		Assert.notNull(registry, "QueryDefinitionRegistry must not be null");
		this.registry = registry;
	}

	@Override
	public final QueryDefinitionRegistry getRegistry() {
		return this.registry;
	}
	
	public final QueryDefinitionRegistry getQueryFactory(){
		return this.registry;
	}
	
	public void setResourceLoader(ResourceLoader loader){
		this.resourceLoader = loader;
	}
	
	public ResourceLoader getResourceLoader(){
		return this.resourceLoader;
	}

	public int loadQueryDefinitions(Resource... resources) throws QueryDefinitionStoreException{
		
		Assert.notNull(resources, "Resource array must not be null");
		int counter = 0;
		
		for (Resource resource : resources) {
			counter += loadQueryDefinitions(resource);
		}
		return counter;
		
	}
	
	public int loadQueryDefinitions(String location) throws QueryDefinitionStoreException {
		return loadQueryDefinitions(location, null);
	}
	
	public int loadQueryDefinitions(String location, Set<Resource> actualResources) throws QueryDefinitionStoreException{
		
		ResourceLoader resourceLoader = getResourceLoader();
		
		if(resourceLoader == null){
			throw new QueryDefinitionStoreException("Cannot import query definitions from location [" + location
					 + "]: no ResourceLoader available.");
		}
		
		if(resourceLoader instanceof ResourcePatternResolver){
			try{
				Resource[] resources = ((ResourcePatternResolver) resourceLoader).getResources(location);
				int loadCount = loadQueryDefinitions(resources);
				if (actualResources != null){
					for(Resource resource: resources){
						actualResources.add(resource);
					}
				}
				if(logger.isDebugEnabled()){
					logger.debug("Loaded " + loadCount + " query definitions from location pattern [" + location + "]");
				}
				return loadCount;
			} catch (IOException ex) {
				throw new QueryDefinitionStoreException(
						"Could not resolve bean definition resource pattern [" + location + "]", ex);
			}
		}else{
			Resource resource = resourceLoader.getResource(location);
			int loadCount = loadQueryDefinitions(resource);
			if (actualResources != null) {
				actualResources.add(resource);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Loaded " + loadCount + " query definitions from location [" + location + "]");
			}
			return loadCount;
		}
	}
	
	public int loadBeanDefinitions(String... locations) throws QueryDefinitionStoreException {
		Assert.notNull(locations, "Location array must not be null");
		int counter = 0;
		for (String location : locations) {
			counter += loadBeanDefinitions(location);
		}
		return counter;
		
	}

}
