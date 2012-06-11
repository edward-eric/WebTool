package org.data.support.tool.data.xml.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Join implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6717133075642295187L;
	
	private String name;
	private String table;
	private String alias;
	private boolean outer;
	
	private List<Constraint> constraints = new ArrayList<Constraint>();
	
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
	
	public List<Constraint> getConstraints() {
		return constraints;
	}
	
	public void addConstraint(Constraint constraint){
		constraints.add(constraint);
	}
	
	public void removeConstraint(Constraint constraint){
		constraints.remove(constraint);
	}
	
	public void removeCOnstraint(int index){
		constraints.remove(index);
	}
	
	public Join(String name, String table, String alias, boolean outer) {
		this.name = name;
		this.table = table;
		this.alias = alias;
		this.outer = outer;
	}	

}
