package org.data.support.tool.web.xml.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Subview
 * @author eric.chen
 *
 */
public class SubView implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3675161186791435977L;
	private String id;
	private String title;
	private List<Field> fieldList = new ArrayList<Field>();
	
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
	public List<Field> getFieldList() {
		return fieldList;
	}
	public void addFieldToList(Field field){
		this.fieldList.add(field);
	}
	
}
