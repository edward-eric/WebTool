package org.data.support.tool.web.xml.metadata;

import java.util.ArrayList;
import java.util.List;

public class SubView {
	
	private String id;
	private List<Field> fieldList = new ArrayList<Field>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Field> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}
	
	public void addFieldToList(Field field){
		this.fieldList.add(field);
	}
	
	public void removeFieldFromList(Field field){
		this.fieldList.remove(field);
	}
	public void removeFieldWithIndex(int index){
		this.fieldList.remove(index);
	}
	

}
