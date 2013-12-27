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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.data.support.beans.factory.QueryDefinitionStoreException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * Default implementation of the {@link QueryDefinitionDocumentReader} interface.
 * Reads query definitions according to the "queries" DTD and XSD format.
 *
 * <p>The structure, elements and attribute names of the required XML document
 * are hard-coded in this class. (Of course a transform could be run if necessary
 * to produce this format). <code>&lt;queries&gt;</code> doesn't need to be the root
 * element of the XML document: This class will parse all query definition elements
 * in the XML file, not regarding the actual root element.
 * 
 * @author chen
 * @created Dec 27, 2013
 * @since 2013
 */
public class DefaultQueryDefinitionDocumentReader implements QueryDefinitionDocumentReader {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	
	/**
	 * Allow the XML to be extensible by processing any custom element types first,
	 * before we start to process the bean definitions. This method is a natural
	 * extension point for any other custom pre-processing of the XML.
	 * <p>The default implementation is empty. Subclasses can override this method to
	 * convert custom elements into standard query definitions, for example.
	 * Implementors have access to the parser's query definition reader and the
	 * underlying XML resource, through the corresponding accessors.
	 */
	protected void preProcess(Element e) {}
	
	
	/**
	 * Allow the XML to be extensible by processing any custom element types last,
	 * after we finished processing the query definitions. This method is a natural
	 * extension point for any other custom post-processing of the XML.
	 * <p>The default implementation is empty. Subclasses can override this method to
	 * convert custom elements into standard query definitions, for example.
	 * Implementors have access to the parser's bean definition reader and the
	 * underlying XML resource, through the corresponding accessors.
	 */
	protected void postProcess(Element e) {}
	
	protected void process(Element e, QueryDefinitionParserDelegate delegate){
		
		
		
	}
	
	private XmlReaderContext readerContext;
	
	
	/**
	 * Invoke the {@link org.springframework.beans.factory.parsing.SourceExtractor} to pull the
	 * source metadata from the supplied {@link Element}.
	 */
	protected Object extractSource(Element ele) {
		return this.readerContext.extractSource(ele);
	}
	
	/**
	 * Return the descriptor for the XML resource that this parser works on.
	 */
	protected final XmlReaderContext getReaderContext(){
		return this.readerContext;
	}
	
	
	protected QueryDefinitionParserDelegate createHelper(XmlReaderContext readerContext, Element root) {
		QueryDefinitionParserDelegate delegate = new QueryDefinitionParserDelegate(readerContext);
		delegate.initDefaults(root);
		return delegate;
	}


	/**
	 * Parses query definitions according to the DTD.
	 * <p>Opens a DOM Document; then initializes the default settings
	 * specified at <code>&lt;queries&gt;</code> level; then parses
	 * the contained query definitions.
	 */
	public void registerQueryDefinitions(Document doc, XmlReaderContext context)
			throws QueryDefinitionStoreException {
		log.debug("Loading query definitions");
		Element root = doc.getDocumentElement();
		
		QueryDefinitionParserDelegate delegate = createHelper(readerContext, root);
		
		preProcess(root);
		process(root, delegate);
		postProcess(root);
		
	}

}
