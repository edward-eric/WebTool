package org.data.support.tool.data.xml.metadata;

import java.io.Serializable;

public class Join implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6717133075642295187L;
	
	private String name;
	private String table;
	private String alias;
	private boolean outer;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public boolean isOuter() {
		return outer;
	}
	public void setOuter(boolean outer) {
		this.outer = outer;
	}
	
	

}
