package org.data.support.tool.xml.grid.metadata;

import java.util.ArrayList;
import java.util.List;

public class GridView {
	
	private String name;
	private List<GridViewColumn> columns;
	
	public GridView(String name){
		this.name = name;
		columns = new ArrayList<GridViewColumn>();
	}

	public String getName() {
		return name;
	}

	public List<GridViewColumn> getColumns() {
		return columns;
	}

	public void addColumnToThisView(GridViewColumn column)
	{
		columns.add(column);
	}

}
