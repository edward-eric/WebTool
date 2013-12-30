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
package org.data.support.beans.factory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * XML-specific XmlQueryDefinitionStoreException subclass that wraps a
 * {@link org.xml.sax.SAXException}, typically a {@link org.xml.sax.SAXParseException}
 * which contains information about the error location.
 * 
 * 
 * @author chen
 * @created Dec 30, 2013
 * @since 2013
 */
public class XmlQueryDefinitionStoreException extends QueryDefinitionStoreException {

	/**
	 * Create a new XmlQueryDefinitionStoreException.
	 * @param resourceDescription description of the resource that the bean definition came from
	 * @param msg the detail message (used as exception message as-is)
	 * @param cause the SAXException (typically a SAXParseException) root cause
	 */
	public XmlQueryDefinitionStoreException(String resourceDescription, String msg, SAXException cause) {
		super(resourceDescription, msg, cause);
	}
	
	/**
	 * Return the line number in the XML resource that failed.
	 * @return the line number if available (in case of a SAXParseException); -1 else
	 */
	public int getLineNumber() {
		Throwable cause = getCause();
		if (cause instanceof SAXParseException) {
			return ((SAXParseException) cause).getLineNumber();
		}
		return -1;
	}

}
