package org.data.support.tool.data.xml.processor.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.data.support.tool.data.xml.metadata.Column;
import org.data.support.tool.data.xml.metadata.Constraint;
import org.data.support.tool.data.xml.metadata.Join;
import org.data.support.tool.data.xml.metadata.Query;
import org.dom4j.Document;
import org.dom4j.Element;

public class QueryProcessor extends XMLProcessor {
	
	public List<Query> process(List<Document> docs){
		List<Query> queries = new ArrayList<Query>();
		ListIterator<Document> iter = docs.listIterator();
		while(iter.hasNext()){
			queries.addAll(process(iter.next()));
		}
		return queries;
	}
	
	public List<Query> process(Document doc) {
		return getQueryList(doc.getRootElement());
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
	
	private Query getQuery(Element e){
		Query query = new Query(
				getAttributeValue(e, "name"),
				getAttributeValue(e, "description"),
				getAttributeValue(e, "basetable"),
				getAttributeValue(e, "alias"));
        Iterator<Element> columnIter = e.elementIterator("column");
        Iterator<Element> joinIter = e.elementIterator("join");
		while(columnIter.hasNext()){
			query.addColumn(getColumn(columnIter.next()));
		}
		while(joinIter.hasNext()){
			query.addJoin(getJoin(joinIter.next()));
		}
		return query;
	}
	
	private List<Query> getQueryList(Element e){
		List<Query> queryList = new ArrayList<Query>();
		Iterator<Element> iter = e.elementIterator("query");
		while(iter.hasNext()){
			queryList.add(getQuery(iter.next()));
		}
		return queryList;
	}

}
