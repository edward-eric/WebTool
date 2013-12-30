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
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * EntityResolver implementation for the queries DTD,
 * to load the DTD from classpath.
 *
 * <p>Fetches "*.dtd" from the class path resource
 * 
 * @author chen
 * @created Dec 30, 2013
 * @since 2013
 */
public class QueryDTDResolver implements EntityResolver {
	
	private static final String DTD_EXTENSION = ".dtd";
	
	private static final String[] DTD_NAMES = {"query-definitions"};
	
	private static final Log logger = LogFactory.getLog(QueryDTDResolver.class);

	@Override
	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException {
		if (logger.isTraceEnabled()) {
			logger.trace("Trying to resolve XML entity with public ID [" + publicId +
					"] and system ID [" + systemId + "]");
		}
		if (systemId != null && systemId.endsWith(DTD_EXTENSION)) {
			int lastPathSeparator = systemId.lastIndexOf("/");
			for (String DTD_NAME : DTD_NAMES) {
				int dtdNameStart = systemId.indexOf(DTD_NAME);
				if (dtdNameStart > lastPathSeparator) {
					String dtdFile = systemId.substring(dtdNameStart);
					if (logger.isTraceEnabled()) {
						logger.trace("Trying to locate [" + dtdFile + "] in Spring jar");
					}
					try {
						Resource resource = new ClassPathResource(dtdFile, getClass());
						InputSource source = new InputSource(resource.getInputStream());
						source.setPublicId(publicId);
						source.setSystemId(systemId);
						if (logger.isDebugEnabled()) {
							logger.debug("Found queries DTD [" + systemId + "] in classpath: " + dtdFile);
						}
						return source;
					}
					catch (IOException ex) {
						if (logger.isDebugEnabled()) {
							logger.debug("Could not resolve queries DTD [" + systemId + "]: not found in class path", ex);
						}
					}

				}
			}
		}

		// Use the default behavior -> download from website or wherever.
		return null;
	}
	
	
	@Override
	public String toString() {
		return "EntityResolver for DTDs " + Arrays.toString(DTD_NAMES);
	}

}
