package org.data.support.beans.factory.xml;

import org.data.support.beans.factory.support.QueryDefinitionRegistry;
import org.springframework.beans.factory.parsing.ProblemReporter;
import org.springframework.beans.factory.parsing.ReaderContext;
import org.springframework.beans.factory.parsing.ReaderEventListener;
import org.springframework.beans.factory.parsing.SourceExtractor;
import org.springframework.beans.factory.xml.NamespaceHandlerResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class XMLReaderContext extends ReaderContext{
	
	
	private final XmlQueryDefinitionReader reader;
	
	private final NamespaceHandlerResolver resolver;

	public XMLReaderContext(Resource resource, ProblemReporter problemReporter,
			ReaderEventListener eventListener, SourceExtractor sourceExtractor,
			XmlQueryDefinitionReader reader,
			NamespaceHandlerResolver resolver) {
		super(resource, problemReporter, eventListener, sourceExtractor);
		this.reader = reader;
		this.resolver = resolver;
	}
	
	public final XmlQueryDefinitionReader getReader() {
		return this.reader;
	}
	
	public final QueryDefinitionRegistry getRegistry() {
		return this.reader.getRegistry();
	}
	
	public final ResourceLoader getResourceLoader() {
		return this.reader.getResourceLoader();
	}
	
	public final ClassLoader getQueryClassLoader(){
		return null;
	}
	

}
