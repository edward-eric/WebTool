package org.data.support.tool.common.file.handler.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.data.support.tool.web.xml.metadata.Field;
import org.data.support.tool.web.xml.metadata.SubView;
import org.data.support.tool.web.xml.metadata.View;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * Class is used to parse view definition XML files into view object list
 * 
 * @author eric.chen
 *
 */
public class ViewFileHandlerImpl extends DefaultFileHandlerImpl{
	
	//temp subview-view map list
	private List<Map<String, String>> viewToSubviewIDMapList = new ArrayList<Map<String,String>>();
	//temp subview set, unrepeatable
	private Set<View> viewSet = new HashSet<View>();
	//temp view set, unrepeatable
	private Set<SubView> subviewSet = new HashSet<SubView>();
	//temperate map stores subview-view with key-value map
	private Map<String, String> viewToSubviewMap = new HashMap<String, String>();
	
	protected void processRootElement(Element root){
		processViewRootElement(root);
	}
	
	protected void processViewRootElement(Element root){
		processViewsElement(root.elements("views"));
		processSubViewsElement(root.elements("subviews"));
		resetViewElement();
	}
	
	protected void processViewsElement(List<Element> views){
		ListIterator<Element> iter = views.listIterator();
		while(iter.hasNext()){
			processViewsElement(iter.next());
		}
	}
	
	protected void processViewsElement(Element views){
		processViewElements(views.elements("view"));
	}
	
	protected void processViewElements(List<Element> views){
		ListIterator<Element> iter = views.listIterator();
		while(iter.hasNext()){
			processViewElement(iter.next());
		}
	}
	
	protected void processViewElement(Element view){
		viewToSubviewMap.clear();
		String viewID = view.attributeValue("id");
		View viewInstance = new View();
		viewInstance.setId(viewID);
		viewSet.add(viewInstance);
		processSubviewsInViewElement(view, viewID);
	}
	
	protected void processSubviewsInViewElement(Element view, String viewID){
		ListIterator<Element> iter = view.elements("subview").listIterator();
		while(iter.hasNext()){
			processSubviewInViewElement(iter.next(), viewID);
		}
		viewToSubviewIDMapList.add(viewToSubviewMap);
	}
	
	protected void processSubviewInViewElement(Element subview, String viewID){
		viewToSubviewMap.put(subview.attributeValue("id"), viewID);
	}
	
	protected void processSubViewsElement(List<Element> subviews){
		ListIterator<Element> iter = subviews.listIterator();
		while(iter.hasNext()){
			processSubViewsElement(iter.next());
		}
	}
	
	protected void processSubViewsElement(Element subviews){
		processSubviewElements(subviews.elements("subview"));
	}
	
	protected void processSubviewElements(List<Element> subviews){
		ListIterator<Element> iter = subviews.listIterator();
		while(iter.hasNext()){
			processSubviewElement(iter.next());
		}
	}
	
	protected void processSubviewElement(Element subview) {
		String subviewID = subview.attributeValue("id");
		SubView subviewInstance = new SubView();
		subviewInstance.setId(subviewID);
		List<Element> fields = subview.elements("field");
		ListIterator<Element> iter = fields.listIterator();
		while(iter.hasNext()){
			processFieldElement(iter.next(), subviewInstance);
		}
		subviewSet.add(subviewInstance);
	}
	
	protected void processFieldElement(Element field, SubView instance){
		String name = field.attributeValue("name");
		String title = field.attributeValue("title");
		Field fieldInstance = new Field();
		fieldInstance.setName(name);
		fieldInstance.setTitle(title);
		instance.addFieldToList(fieldInstance);
	}
	
	protected void resetViewElement(){
		ListIterator<Map<String, String>> iter = viewToSubviewIDMapList.listIterator();
		while(iter.hasNext()){
			Map<String, String> iterMap = iter.next();
			Iterator<Entry<String, String>> iterStringPair = iterMap.entrySet().iterator();
			View entryView = null;
			while(iterStringPair.hasNext()){
				Entry<String, String> entry = iterStringPair.next();
				String viewID = entry.getValue();
				String subviewID = entry.getKey();
				if(entryView == null){
					entryView = findViewObject(viewID);
				}
				entryView.addSubviewToList(findSubViewObject(subviewID));
			}
			returnObjectSet.add(entryView);
		}
	}
	
	protected View findViewObject(String viewID){
		Iterator<View> iter = viewSet.iterator();
		View returnedView = null;
		while(iter.hasNext()){
			View view = iter.next();
			if(view.getId().equals(viewID)){
				returnedView = view;
				break;
			}
		}
		return returnedView;
	}
	
	protected SubView findSubViewObject(String subViewID){
		Iterator<SubView> iter = subviewSet.iterator();
		SubView returnedSubView = null;
		while(iter.hasNext()){
			SubView subview = iter.next();
			if(subview.getId().equals(subViewID)){
				returnedSubView = subview;
				break;
			}
		}
		return returnedSubView;
	}
	

}
