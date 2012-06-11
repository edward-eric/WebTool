package org.data.support.tool.data.test;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.data.support.tool.data.xml.metadata.Column;
import org.data.support.tool.data.xml.metadata.Constraint;
import org.data.support.tool.data.xml.metadata.Join;
import org.data.support.tool.data.xml.metadata.Query;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class XMLtest {
	
	
	public String getAttributeValue(Element element, String key, String defaultValue){
		return element.attributeValue(key, defaultValue);
	}
	
	public String getAttributeValue(Element element, String key){
		return element.attributeValue(key);
	}
	
	public Column getColumn(Element e){
		return  new Column(
				getAttributeValue(e, "name"), 
				getAttributeValue(e, "columnname"),
				Boolean.valueOf(getAttributeValue(e, "orderby")),
				Boolean.valueOf(getAttributeValue(e, "trend")),
				getAttributeValue(e, "from"));
	}
	
	public Join getJoin(Element e){
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
	
	public Constraint getConstraint(Element e){
		return  new Constraint(
				getAttributeValue(e, "src"),
				getAttributeValue(e, "dest"));
	}
	
	public Query getQuery(Element e){
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
	
	public List<Query> getQueryList(Element e){
		List<Query> queryList = new ArrayList<Query>();
		Iterator<Element> iter = e.elementIterator("Query");
		while(iter.hasNext()){
			queryList.add(getQuery(iter.next()));
		}
		return queryList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SAXReader reader = new SAXReader();
		
		File file = new File("resource/components.xml");
		
		Document document = null;
		try {
			document = reader.read(file);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		XMLtest tt = new XMLtest();
		List list = tt.getQueryList(document.getRootElement());
		
		
		System.out.println(list);
		

	}

}
