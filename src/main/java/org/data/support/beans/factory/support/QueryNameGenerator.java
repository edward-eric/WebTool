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
import org.data.support.beans.factory.config.QueryDefinition;

/**
 * Strategy interface for generating query names for query definitions.
 * 
 * 
 * @author chen
 * @created Dec 27, 2013
 * @since 2013
 */
public interface QueryNameGenerator {
	
	/**
	 * Generate a query name for given query definition.
	 * @param definition the query definition to generate a name for
	 * @param registry the query definition registry that the given definition is supposed 
	 * to be registered with
	 * @return the generated query name
	 * @throws QueryDefinitionStoreException 
	 */
	String generateQueryName(QueryDefinition definition, QueryDefinitionRegistry registry) throws QueryDefinitionStoreException;

}
