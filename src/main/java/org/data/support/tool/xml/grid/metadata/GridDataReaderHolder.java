package org.data.support.tool.xml.grid.metadata;

import java.util.HashMap;
import java.util.Map;

public class GridDataReaderHolder {
	
	
	private String id;
	private String queryClass;
	private GridDataQuery dataQuery;
	private Map<String, GridDataColumn> columns;
	
	public GridDataReaderHolder(String id, String queryClass){
		this.id = id;
		this.queryClass = queryClass;
		this.columns = new HashMap<String, GridDataColumn>();
	}

	public String getId() {
		return id;
	}

	public String getQueryClass() {
		return queryClass;
	}

	public GridDataQuery getDataQuery() {
		return dataQuery;
	}

	public void setDataQuery(GridDataQuery dataQuery) {
		this.dataQuery = dataQuery;
	}

	public Map<String, GridDataColumn> getColumns() {
		return columns;
	}

	public void addColumn(GridDataColumn column){
		this.columns.put(column.getName(), column);
	}
	
	public void removeColumn(GridDataColumn column){
		this.columns.remove(column.getName());
	}
	
	

}
