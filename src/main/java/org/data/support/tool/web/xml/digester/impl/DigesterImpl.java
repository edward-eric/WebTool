package org.data.support.tool.web.xml.digester.impl;

import java.util.Collection;
import java.util.Iterator;

import org.data.support.tool.web.xml.digester.Digester;
import org.dom4j.Document;
import org.dom4j.Element;

public class DigesterImpl implements Digester {
	
	private String elementType;
	
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	@Override
	public Collection<?> digestToRequiredObjectCollection(Collection<Document> docs) {
		
		Iterator<Document> iter = docs.iterator();
		
		while(iter.hasNext()){
			returnRequiredObjectCollectionFromOneDocRoot(iter.next());
		}
		
		return null;
	}
	
	protected Collection<?> returnRequiredObjectCollectionFromOneDocRoot(Document doc){
		Element root = doc.getRootElement();
		root.elementIterator("");
		return null;
	}
	
	protected Collection<?> returnRequiredObjectCollectionFromOneElement(Element e){
		return null;
	}
	
	
	

}
