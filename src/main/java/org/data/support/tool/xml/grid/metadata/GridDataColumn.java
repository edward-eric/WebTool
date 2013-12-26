package org.data.support.tool.xml.grid.metadata;

public class GridDataColumn {
	
	private final String name;
	private final String type;
	
	public GridDataColumn(String name, String type){
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

}
