package org.data.support.tool.web.xml.metadata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * View
 * @author eric.chen
 *
 */
public class View implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6702472430612171264L;
	private String id;
	private String title;
	private String leadingSubView;
	private Map<String, SubView> subviewMap = new HashMap<String, SubView>();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLeadingSubView() {
		return leadingSubView;
	}
	public void setLeadingSubView(String leadingSubView) {
		this.leadingSubView = leadingSubView;
	}
	public Map<String, SubView> getSubviewMap() {
		return subviewMap;
	}
	public void addSubViewToMap(SubView sub){
		if(sub != null)
			subviewMap.put(sub.getId(), sub);
	}
	public void removeSubViewFromMap(String subviewID){
		subviewMap.remove(subviewID);
	}

}
