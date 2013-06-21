package org.data.support.tool.xml.grid.metadata;

public class GridDataColumn {
	
	private final String columnName;
	private final String metaType;
	private final String dataType;
	
	public GridDataColumn(String name, String meta, String type)
	{
		columnName = name;
		metaType = meta;
		dataType = type;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getMetaType() {
		return metaType;
	}

	public String getDataType() {
		return dataType;
	}

}
