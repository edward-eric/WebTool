package org.data.support.tool.common.data.reverse.bo;

import java.util.HashMap;
import java.util.Map;

public class Schema {
	
	private String name;
	
	private Map<String, Table> tables = new HashMap<String, Table>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Table> getTables() {
		return tables;
	}

	public void setTables(Map<String, Table> tables) {
		this.tables = tables;
	}
	
	public void addTable(Table table){
		this.tables.put(table.getName(), table);
	}
	
	public void removeTable(String tableName){
		this.tables.remove(tableName);
	}

}
