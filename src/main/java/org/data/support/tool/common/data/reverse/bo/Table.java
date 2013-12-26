package org.data.support.tool.common.data.reverse.bo;

import java.util.HashMap;
import java.util.Map;

public class Table {
	
	private String name;
	
	private Map<String, Column> columns = new HashMap<String, Column>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Column> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, Column> columns) {
		this.columns = columns;
	}
	
	public void addColumn(Column column){
		this.columns.put(column.getName(), column);
	}
	
	public void removeColumn(String columnName){
		this.columns.remove(columnName);
	}

}
