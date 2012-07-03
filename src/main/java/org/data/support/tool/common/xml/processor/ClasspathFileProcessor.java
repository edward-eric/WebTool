package org.data.support.tool.common.xml.processor;

import java.io.IOException;
import java.util.Collection;

import org.dom4j.Document;
import org.dom4j.DocumentException;
/**
 * Interface to get document list
 * @author eric.chen
 *
 */
public interface ClasspathFileProcessor {
	
	public Collection<Document> getDocumentsCollection() throws IOException, DocumentException;

}
