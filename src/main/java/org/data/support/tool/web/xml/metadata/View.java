package org.data.support.tool.web.xml.metadata;

import java.util.ArrayList;
import java.util.List;

public class View {
	
	private String id;
	private List<SubView> subviewList = new ArrayList<SubView>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<SubView> getSubviewList() {
		return subviewList;
	}
	public void setSubviewList(List<SubView> subviewList) {
		this.subviewList = subviewList;
	}
	
	public void addSubviewToList(SubView subview){
		this.subviewList.add(subview);
	}
	
	public void removeSubViewFromList(SubView subview){
		this.subviewList.remove(subview);
	}
	
	public void removeSubViewWithIndex(int index){
		this.subviewList.remove(index);
	}
}
