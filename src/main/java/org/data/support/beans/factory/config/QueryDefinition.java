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
package org.data.support.beans.factory.config;

import org.springframework.beans.BeanMetadataElement;
import org.springframework.core.AttributeAccessor;
/**
 * A QueryDefinition describes a query instance, which has property values,
 * constructor argument values, and further information supplied by
 * concrete implementations.
 * 
 * @author chen
 * @created Dec 27, 2013
 * @since 2013
 */
public interface QueryDefinition extends AttributeAccessor, BeanMetadataElement{
	
	/**
	 * Return a description of the resource that this query definition
	 * came from (for the purpose of showing context in case of errors).
	 */
	String getResourceDescription();
	
	/**
	 * Return a human-readable description of this query definition.
	 */
	String getDescription();
	
	/**
	 * Override the query class name of this query definition.
	 * <p>The class name can be modified during bean factory post-processing,
	 * typically replacing the original class name with a parsed variant of it.
	 */
	void setQueryClassName(String queryClassName);

	/**
	 * Return the current query class name of this query definition.
	 * <p>Note that this does not have to be the actual class name used at runtime, in
	 * case of a child definition overriding/inheriting the class name from its parent.
	 * Hence, do <i>not</i> consider this to be the definitive bean type at runtime but
	 * rather only use it for parsing purposes at the individual query definition level.
	 */
	String getQueryClassName();
	

}
