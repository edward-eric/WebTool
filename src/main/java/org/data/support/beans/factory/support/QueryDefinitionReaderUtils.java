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
import org.data.support.beans.factory.QueryFactoryUtils;
import org.data.support.beans.factory.config.QueryDefinition;
import org.springframework.util.StringUtils;

/**
 * Utility methods that are useful for query definition reader implementations.
 * Mainly intended for internal use.
 * 
 * 
 * @author chen
 * @created Dec 27, 2013
 * @since 2013
 */
public class QueryDefinitionReaderUtils {
	
	/**
	 * Separator for generated query names. If a class name or parent name is not
	 * unique, "#1", "#2" etc will be appended, until the name becomes unique.
	 */
	public static final String GENERATED_QUERY_NAME_SEPERATOR = QueryFactoryUtils.GENERATED_QUERY_NAME_SEPARATOR;
	
	
	/**
	 * Generate a query name for the given query definition, unique within the
	 * given query factory.
	 * @param definition the query definition to generate a query name for
	 * @param registry the query factory that the definition is going to be
	 * registered with (to check for existing query names)
	 * @return the generated query name
	 * @throws QueryDefinitionStoreException if no unique name can be generated
	 * for the given query definition
	 */
	public static String generateQueryName(QueryDefinition definition, QueryDefinitionRegistry registry) throws QueryDefinitionStoreException {
		String generatedQueryName = definition.getQueryClassName();
		if (!StringUtils.hasText(generatedQueryName)) {
			throw new QueryDefinitionStoreException("Unnamed query definition specified - can't generate bean name");
		}

		int counter = -1;

		String id = generatedQueryName;

		while (counter == -1 || registry.containsQueryDefinition(id)) {
			counter++;
			id = generatedQueryName + GENERATED_QUERY_NAME_SEPERATOR + counter;
		}
		return id;
	}

}
