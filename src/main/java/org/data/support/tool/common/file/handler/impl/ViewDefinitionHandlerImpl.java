package org.data.support.tool.common.file.handler.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.data.support.tool.web.xml.metadata.Field;
import org.data.support.tool.web.xml.metadata.SubView;
import org.data.support.tool.web.xml.metadata.View;
import org.dom4j.Document;
import org.dom4j.Element;

public class ViewDefinitionHandlerImpl extends DefaultDefinitionHandlerImpl {
	
	private static final String VIEWS_STAMP = "views";
	private static final String SUBVIEWS_STAMP = "subviews";
	private static final String VIEW_STAMP = "view";
	private static final String SUBVIEW_STAMP = "subview";
	private static final String SUBREF_STAMP = "subref";
	private static final String FIELD_STAMP = "field";
	
	private Map<String, Object> viewMap;
	private Map<String, Object> subviewMap;
	private Map<String, List<String>> viewToSubviewMap;
	
	private List<Element> viewsElementList;
	private List<Element> subviewsElementList;

	@Override
	public void process() {
		viewMap = new HashMap<String, Object>();
		subviewMap = new HashMap<String, Object>();
		viewToSubviewMap = new HashMap<String, List<String>>();
		
		viewsElementList = new ArrayList<Element>();
		subviewsElementList = new ArrayList<Element>();
		
		ListIterator<Document> iteratorForDocs = docs.listIterator();
		while(iteratorForDocs.hasNext()){
			processDoc(iteratorForDocs.next());
		}
		
		processViews(viewsElementList);
		processSubViews(subviewsElementList);
		
		Iterator<Entry<String, Object>> iter = viewMap.entrySet().iterator();
		
		while(iter.hasNext()){
			Entry<String, Object> entry = iter.next();
			View viewInstance = (View) entry.getValue();
			List<String> listForSubviews = viewToSubviewMap.get(entry.getKey());
			ListIterator<String> iter2 = listForSubviews.listIterator();
			while(iter2.hasNext()){
				viewInstance.addSubViewToMap((SubView) subviewMap.get(iter2.next()));
			}
			defaultMap.put(entry.getKey(), viewInstance);
		}
	}
	
	private void processDoc(Document doc){
		processRoot(doc.getRootElement());
	}
	
	private void processRoot(Element root){
		viewsElementList.addAll(root.elements(VIEWS_STAMP));
		subviewsElementList.addAll(root.elements(SUBVIEWS_STAMP));
	}
	
	private void processViews(List<Element> views){
		ListIterator<Element> iter = views.listIterator();
		while(iter.hasNext()){
			processViewsElement(iter.next());
		}
	}
	
	private void processSubViews(List<Element> subviews){
		ListIterator<Element> iter = subviews.listIterator();
		while(iter.hasNext()){
			processSubViewsElement(iter.next());
		}
	}
	
	private void processViewsElement(Element views){
		ListIterator<Element> iter = views.elements(VIEW_STAMP).listIterator();
		while(iter.hasNext()){
			processViewElement(iter.next());
		}
	}
	
	private void processSubViewsElement(Element subviews){
		ListIterator<Element> iter = subviews.elements(SUBVIEW_STAMP).listIterator();
		while(iter.hasNext()){
			processSubViewElement(iter.next());
		}
	}
	
	private void processViewElement(Element view){
		String viewID = view.attributeValue("id");
		String viewTitle = view.attributeValue("title");
		View viewInstance = new View();
		viewInstance.setId(viewID);
		viewInstance.setTitle(viewTitle);
		viewMap.put(viewID, viewInstance);
		List<String> subs = new ArrayList<String>();
		ListIterator<Element> iter = view.elements(SUBREF_STAMP).listIterator();
		while(iter.hasNext()){
			subs.add(processSubRefElement(iter.next()));
		}
		viewToSubviewMap.put(viewID, subs);
	}
	
	private void processSubViewElement(Element subview){
		String subviewID = subview.attributeValue("id");
		String subviewTitle = subview.attributeValue("title");
		SubView subviewInstance = new SubView();
		subviewInstance.setId(subviewID);
		subviewInstance.setTitle(subviewTitle);
		
		ListIterator<Element> iter = subview.elements(FIELD_STAMP).listIterator();
		while(iter.hasNext()){
			subviewInstance.addFieldToList((processFieldElement(iter.next())));
		}
		
		subviewMap.put(subviewID, subviewInstance);
	}
	
	private String processSubRefElement(Element subref) {
		return subref.attributeValue("id");
	}
	
	private Field processFieldElement(Element field){
		String name = field.attributeValue("name");
		String title = field.attributeValue("title");
		String width = field.attributeValue("width");
		Field fieldInstance = new Field();
		fieldInstance.setName(name);
		fieldInstance.setTitle(title);
		fieldInstance.setWidth(width);
		return fieldInstance;
	}

}
