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
import org.data.support.beans.factory.NoSuchQueryDefinitionException;
import org.data.support.beans.factory.config.QueryDefinition;

import org.springframework.core.AliasRegistry;

/**
 * Interface for registry that hold query definitions. Typically implemented by
 * QueryFactories that internally work with the AbstractQueryDefinition hierarchy.
 * 
 * 
 * @author chen
 * @created Dec 27, 2013
 * @since 2013
 */
public interface QueryDefinitionRegistry extends AliasRegistry{
	
	/**
	 * Register a new query definition with this registry.
	 * @param name the name of the query instance to register.
	 * @param bean definition of the query instance to register.
	 * @throws QueryDefinitionStoreException
	 */
	void registerQueryDefinition(String name, QueryDefinition bean) throws QueryDefinitionStoreException;
	
	/**
	 * Remove the queryDefinition for the given name
	 * @param name name the name of the query instance to register.
	 * @throws QueryDefinitionStoreException
	 */
	void removeQueryDefinition(String name) throws QueryDefinitionStoreException;
	
	/**
	 * Return the QueryDefinition for the given query name.
	 * @param name name of the query to find a definition for
	 * @return the QueryDefinition for the given name (never <code>null</code>)
	 * @throws NoSuchQueryDefinitionException if there is no such query definition
	 */
	QueryDefinition getQueryDefinition(String name) throws NoSuchQueryDefinitionException;
	
	/**
	 * Check if this registry contains a query definition with the given name.
	 * @param name the name of the query to look for
	 * @return if this registry contains a query definition with the given name
	 */
	boolean containsQueryDefinition(String name);
	
	/**
	 * Return the names of all queries defined in this registry.
	 * @return the names of all queries defined in this registry,
	 * or an empty array if none defined
	 */
	String[] getQueryDefinitionNames();
	
	/**
	 * Return the number of queries defined in the registry.
	 * @return the number of queries defined in the registry
	 */
	int getQueryDefinitionCount();
	
	/**
	 * Determine whether the given query name is already in use within this registry,
	 * @param name the name to check
	 * @return whether the given query name is already in use
	 */
	boolean isQueryNameInUse(String name);
	
}
