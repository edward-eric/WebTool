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
package org.data.support.beans.factory.support;

import org.data.support.beans.factory.QueryDefinitionStoreException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * Simple interface for query definition readers.
 * Specifies load methods with resource parameters
 * 
 * <p>Concrete query definition readers can of course add additional load and register methods
 * for query definitions, specific to their query definition format.
 * 
 * <p>Note that a query definition reader does not have to implement this interface. It only
 * serves as suggestion for query definition readers that want to follow standard naming
 * conventions.
 * 
 * 
 * @author chen
 * @created Dec 27, 2013
 * @since 2013
 */
public interface QueryDefinitionReader {
	
	/**
	 * @return {@link QueryDefinitionRegistry} 
	 * the query factory to register the query definitions with.
	 * <p>The factory is exposed through the {@link QueryDefinitionRegistry} interface,
	 * encapsulating the methods that are relevant for query definition handling.
	 */
	QueryDefinitionRegistry getRegistry();
	
	
	/**
	 * @return {@link ResourceLoader}
	 * return the resource loader to use for resource locations.
	 * Can be checked for the {@link ResourcePatternResolver} interface, and cast
	 * accordingly, for loading multiple resources for a given resource pattern.
	 * <p>Null suggests that absolute resource loading is not available for this 
	 * query definition reader.
	 * <p>This is mainly meant to be used for importing further resources
	 * from within a query definition resource, for example via the "import"
	 * tag in XML query definitions. It is recommended, however, to apply such imports relative
	 * to the defining resource; only explicit full resource locations will trigger absolute
	 * resource loading.
	 * <p>There is also a <code>loadQueryDefinitions(String)</code> method available,
	 * for loading query definitions from a resource location (or a location pattern).
	 * This is a convenience to avoid explicit ResourceLoader handling
	 */
	ResourceLoader getResourceLoader();
	
	/**
	 * @return {@link ClassLoader}
	 * Return the class loader to use for query classes.
	 * <p><code>null</code> suggests to not load query classes eagerly but rather
	 * to just register query definitions with class name,
	 * with the corresponding classes to be resolved later (or never).
	 */
	ClassLoader getQueryClassLoader();
	
	/**
	 * @return {@link QueryNameGenerator}
	 * Return the QueryNameGenerator to use for anonymous queries
	 */
	QueryNameGenerator getQueryNameGenerator();
	
	
	
	/**
	 * Load query definitions from the specified resource.
	 * @param resource the resource descriptor
	 * @return the number of query definitions found
	 * @throws QueryDefinitionStoreException in case of loading or parsing errors
	 */
	int loadQueryDefinitions(Resource resource) throws QueryDefinitionStoreException;
	
	/**
	 * Load query definitions from the specified resources.
	 * @param resources the resource descriptors
	 * @return the number of query definitions found
	 * @throws QueryDefinitionStoreException in case of loading or parsing errors
	 */
	int loadQueryDefinitions(Resource... resources) throws QueryDefinitionStoreException;
	
	/**
	 * Load query definition from the specified resource location.
	 * <p>The location can also be a location pattern, provided that the
	 * ResourceLoader of this query definition reader is a ResourcePatternResolver.
	 * 
	 * @param location the resource location, to be loaded with the ResourceLoader
	 * @return the number of query definitions found
	 * @throws QueryDefinitionStoreException in case of loading or parsing errors
	 */
	int loadQueryDefinitions(String location) throws QueryDefinitionStoreException;
	
	
	/**
	 * Load query definition from the specified resource locations.
     *
	 * @param locations the resource locations, to be loaded with the ResourceLoader
	 * @return the number of query definitions found
	 * @throws QueryDefinitionStoreException in case of loading or parsing errors
	 */
	int loadQueryDefinitions(String... locations) throws QueryDefinitionStoreException;

}
