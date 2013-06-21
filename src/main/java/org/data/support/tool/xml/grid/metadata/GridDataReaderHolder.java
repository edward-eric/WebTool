package org.data.support.tool.xml.grid.metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridDataReaderHolder {
	
	private String className;
	private Map<String, String> params;
	private String id;
	private List<GridDataColumn> columns;
	
	public GridDataReaderHolder(String id, String className){
		this.id = id;
		this.className = className;
		this.params = new HashMap<String, String>();
		this.columns = new ArrayList<GridDataColumn>();
	}
	
	public String getClassName(){
		return className;
	}
	
	public Map<String, String> getParams(){
		return params;
	}
	
	public void addParam(String key, String value)
	{
		params.put(key, value);
	}
	
	public String getId(){
		return id;
	}
	
	public void addDataColumn(GridDataColumn column)
	{
		columns.add(column);
	}

	public List<GridDataColumn> getColumns() {
		return columns;
	}

}
