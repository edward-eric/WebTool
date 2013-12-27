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

import org.data.support.beans.factory.QueryDefinitionStoreException;
import org.w3c.dom.Document;

/**
 * SPI for parsing an XML document that contains query definitions.
 * Used by XmlQueryDefinitionReader for actually parsing a DOM document.
 *
 * <p>Instantiated per document to parse: Implementations can hold
 * state in instance variables during the execution of the
 * <code>registerQueryDefinitions</code> method, for example global
 * settings that are defined for all query definitions in the document.
 * 
 * @author chen
 * @created Dec 27, 2013
 * @since 2013
 */
public interface QueryDefinitionDocumentReader {
	
	/**
	 * Read query definitions from the given DOM document,
	 * and register them with the given query factory.
	 * @param doc the DOM document
	 * @param readerContext the current context of the reader. Includes the resource being parsed
	 * @throws QueryDefinitionStoreException in case of parsing errors
	 */
	public void registerQueryDefinitions(Document doc, XmlReaderContext context) throws QueryDefinitionStoreException;

}
