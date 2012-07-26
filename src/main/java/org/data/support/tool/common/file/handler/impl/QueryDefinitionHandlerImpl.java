package org.data.support.tool.common.file.handler.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.data.support.tool.common.file.handler.QueryProducer;
import org.data.support.tool.data.xml.metadata.Column;
import org.data.support.tool.data.xml.metadata.Constraint;
import org.data.support.tool.data.xml.metadata.Join;
import org.data.support.tool.data.xml.metadata.Query;
import org.dom4j.Document;
import org.dom4j.Element;

public class QueryDefinitionHandlerImpl extends DefaultDefinitionHandlerImpl {
	
	private static final String QUERY_STAMP = "query";
	
	private List<Element> queryElementList;
	
	private QueryProducer producer;
	
	public QueryProducer getProducer() {
		return producer;
	}

	public void setProducer(QueryProducer producer) {
		this.producer = producer;
	}

	@Override
	public void process() {
		queryElementList = new ArrayList<Element>();
		
		ListIterator<Document> iteratorForDocs = docs.listIterator();
		while(iteratorForDocs.hasNext()){
			processDoc(iteratorForDocs.next());
		}
		
		processQueries(queryElementList);
	}
	
	private void processDoc(Document doc){
		processRoot(doc.getRootElement());
	}
	
	private void processRoot(Element root){
		queryElementList.addAll(root.elements(QUERY_STAMP));
	}
	
	private void processQueries(List<Element> queryList){
		ListIterator<Element> iter = queryList.listIterator();
		while(iter.hasNext()){
			processQuery(iter.next());
		}
	}
	
	private void processQuery(Element query){
		Query queryInstance = new Query(
				getAttributeValue(query, "name"),
				getAttributeValue(query, "description"),
				getAttributeValue(query, "basetable"),
				getAttributeValue(query, "alias"));
        Iterator<Element> columnIter = query.elementIterator("column");
        Iterator<Element> joinIter = query.elementIterator("join");
		while(columnIter.hasNext()){
			queryInstance.addColumn(getColumn(columnIter.next()));
		}
		while(joinIter.hasNext()){
			queryInstance.addJoin(getJoin(joinIter.next()));
		}
		defaultMap.put(queryInstance.getName(), producer.outputSQL(queryInstance));
	}
	
	
	private String getAttributeValue(Element element, String key, String defaultValue){
		return element.attributeValue(key, defaultValue);
	}
	
	private String getAttributeValue(Element element, String key){
		return element.attributeValue(key);
	}
	
	private Column getColumn(Element e){
		return  new Column(
				getAttributeValue(e, "name"), 
				getAttributeValue(e, "columnname"),
				Boolean.valueOf(getAttributeValue(e, "orderby")),
				Boolean.valueOf(getAttributeValue(e, "trend")),
				getAttributeValue(e, "from"));
	}
	
	private Constraint getConstraint(Element e){
		return  new Constraint(
				getAttributeValue(e, "src"),
				getAttributeValue(e, "dest"));
	}
	
	private Join getJoin(Element e){
		Join join =  new Join(
				getAttributeValue(e, "name"),
				getAttributeValue(e, "table"),
				getAttributeValue(e, "alias"),
				Boolean.valueOf(getAttributeValue(e, "outer")));
		Iterator<Element> iter = e.elementIterator("constraint");
		while(iter.hasNext()){
			join.addConstraint(getConstraint(iter.next()));
		}
		return join;
	}

}
