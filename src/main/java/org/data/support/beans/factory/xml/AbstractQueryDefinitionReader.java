/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.data.support.beans.factory.xml;

import java.io.IOException;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.data.support.beans.factory.QueryDefinitionStoreException;
import org.data.support.beans.factory.support.DefaultQueryNameGenerator;
import org.data.support.beans.factory.support.QueryDefinitionReader;
import org.data.support.beans.factory.support.QueryDefinitionRegistry;
import org.data.support.beans.factory.support.QueryNameGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.Assert;
/**
 * Abstract base class for query definition readers which implement the 
 * {@link QueryDefinitionReader} interface
 * 
 * <p>Provides common properties like the query factory to work on
 * and the class loader to use for loading query classes.
 * 
 * @author chen
 * @created Dec 27, 2013
 * @since 2013
 */
public abstract class AbstractQueryDefinitionReader implements QueryDefinitionReader {
	
	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	private final QueryDefinitionRegistry registry;
	
	private ResourceLoader resourceLoader;
	
	private ClassLoader classLoader;
	
	private QueryNameGenerator generator = new DefaultQueryNameGenerator();
	
	/**
	 * Create a new AbstractQueryDefinitionReader for the given query factory.
	 * <p>If the passed-in query factory does not only implement the QueryDefinitionRegistry
	 * interface but also the ResourceLoader interface, it will be used as default
	 * ResourceLoader as well. 
	 * <p>If given a plain BeanDefinitionRegistry, the default ResourceLoader will be a
	 * {@link org.springframework.core.io.support.PathMatchingResourcePatternResolver}.
	 * @param registry the QueryFactory to load bean definitions into,
	 * in the form of a QueryDefinitionRegistry
	 */
	protected AbstractQueryDefinitionReader(QueryDefinitionRegistry registry){
		Assert.notNull(registry, "QueryDefinitionRegistry must not be null");
		this.registry = registry;
		
		if(this.registry instanceof ResourceLoader){
			this.resourceLoader = (ResourceLoader) this.registry;
		}else{
			this.resourceLoader = new PathMatchingResourcePatternResolver();
		}
	}

	@Override
	public final QueryDefinitionRegistry getRegistry() {
		return this.registry;
	}
	
	public final QueryDefinitionRegistry getQueryFactory(){
		return this.registry;
	}
	
	/**
	 * Set the ResourceLoader to use for resource locations.
	 * If specifying a ResourcePatternResolver, the query definition reader
	 * will be capable of resolving resource patterns to Resource arrays.
	 * <p>Default is PathMatchingResourcePatternResolver, also capable of
	 * resource pattern resolving through the ResourcePatternResolver interface.
	 * <p>Setting this to <code>null</code> suggests that absolute resource loading
	 * is not available for this query definition reader.
	 */
	public void setResourceLoader(ResourceLoader loader){
		this.resourceLoader = loader;
	}
	
	public ResourceLoader getResourceLoader(){
		return this.resourceLoader;
	}
	
	/**
	 * Set the ClassLoader to use for query classes.
	 * <p>Default is <code>null</code>, which suggests to not load query classes
	 * eagerly but rather to just register query definitions with class names,
	 * with the corresponding Classes to be resolved later (or never).
	 */
	public void setClassLoader(ClassLoader classLoader){
		this.classLoader = classLoader;
	}
	
	public ClassLoader getQueryClassLoader(){
		return this.classLoader;
	}
	
	public QueryNameGenerator getQueryNameGenerator() {
		return generator;
	}
	/**
	 * Set the generator to use for anonymous queries
	 * (without explicit query name specified).
	 * <p>Default is a {@link DefaultQueryNameGenerator}.
	 */
	public void setGenerator(QueryNameGenerator generator) {
		this.generator = generator;
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
	
	/**
	 * Load query definitions from the specified resource location.
	 * <p>The location can also be a location pattern, provided that the
	 * ResourceLoader of this bean definition reader is a ResourcePatternResolver.
	 * @param location the resource location, to be loaded with the ResourceLoader
	 * (or ResourcePatternResolver) of this bean definition reader
	 * @param actualResources a Set to be filled with the actual Resource objects
	 * that have been resolved during the loading process. May be <code>null</code>
	 * to indicate that the caller is not interested in those Resource objects.
	 * @return the number of query definitions found
	 * @throws QueryDefinitionStoreException in case of loading or parsing errors
	 */
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
	
	public int loadQueryDefinitions(String... locations) throws QueryDefinitionStoreException {
		Assert.notNull(locations, "Location array must not be null");
		int counter = 0;
		for (String location : locations) {
			counter += loadQueryDefinitions(location);
		}
		return counter;
		
	}

}
