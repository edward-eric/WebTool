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
	private Map<String, List<SubView>> viewToSubviewMap;
	
	private List<Element> viewsElementList;
	private List<Element> subviewsElementList;

	@Override
	public void process() {
		viewMap = new HashMap<String, Object>();
		subviewMap = new HashMap<String, Object>();
		viewToSubviewMap = new HashMap<String, List<SubView>>();
		
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
			List<SubView> listForSubviews = viewToSubviewMap.get(entry.getKey());
			ListIterator<SubView> iter2 = listForSubviews.listIterator();
			while(iter2.hasNext()){
				SubView ref = iter2.next();
				SubView sub = (SubView) subviewMap.get(ref.getId());
				if(ref.isLeading() && viewInstance.getLeadingSubView() == null){
					viewInstance.setLeadingSubView(ref.getId());
				}
				sub.setLeading(ref.isLeading());
				sub.setNextSubView(ref.getNextSubView());
				viewInstance.addSubViewToMap(sub);
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
		List<SubView> subs = new ArrayList<SubView>();
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
	
	private SubView processSubRefElement(Element subref) {
		String id = subref.attributeValue("id");
		String next = subref.attributeValue("next");
		String isleading = subref.attributeValue("isleading");
		SubView sub = new SubView();
		sub.setId(id);
		sub.setLeading(Boolean.valueOf(isleading));
		sub.setNextSubView(next);
		return sub;
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
