package org.data.support.tool.web.xml.digester;

import java.util.Collection;

import org.dom4j.Document;

public interface Digester {
	
	public Collection<?> digestToRequiredObjectCollection(Collection<Document> docs);

}
