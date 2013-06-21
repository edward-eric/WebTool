package org.data.support.tool.xml.grid.metadata;

public class Grid {

	private String name;
	private String metaClass;
	
	private GridDataReaderHolder dataReader;
	private GridViewHolder viewHolder;
	
	public Grid(String name, String metaClass)
	{
		this.name = name;
		this.metaClass = metaClass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMetaClass() {
		return metaClass;
	}

	public void setMetaClass(String metaClass) {
		this.metaClass = metaClass;
	}

	public GridDataReaderHolder getDataReader() {
		return dataReader;
	}

	public void setDataReader(GridDataReaderHolder dataReader) {
		this.dataReader = dataReader;
	}

	public GridViewHolder getViewHolder() {
		return viewHolder;
	}

	public void setViewHolder(GridViewHolder viewHolder) {
		this.viewHolder = viewHolder;
	}

}
