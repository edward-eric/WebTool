package org.data.support.tool.data.xml.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Query implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3850276420980855430L;
	
	
	private String name;
	private String description;
	private String baseTable;
	private String alias;
	
	private List<Column> columns = new ArrayList<Column>();
	private List<Join> joins = new ArrayList<Join>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBaseTable() {
		return baseTable;
	}
	public void setBaseTable(String baseTable) {
		this.baseTable = baseTable;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	
	public List<Column> getColumns() {
		return columns;
	}
	
	public Column getColumnAtIndex(int index){
		return this.columns.get(index);
	}
	
	public void addColumn(Column column){
		this.columns.add(column);
	}
	
	public void removeColumn(Column column){
		this.columns.remove(column);
	}

	public List<Join> getJoins() {
		return joins;
	}
	
	public Join getJoinAtIndex(int index){
		return this.joins.get(index);
	}
	
	public void addJoin(Join join){
		this.joins.add(join);
	}
	
	public void removeJoin(Join join){
		this.joins.remove(join);
	}
	
	public Query(String name, String description, String baseTable, String alias) {
		this.name = name;
		this.description = description;
		this.baseTable = baseTable;
		this.alias = alias;
	}
	
	
	
	

}
