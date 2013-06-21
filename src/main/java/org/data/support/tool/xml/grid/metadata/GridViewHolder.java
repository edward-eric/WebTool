package org.data.support.tool.xml.grid.metadata;

import java.util.HashMap;
import java.util.Map;

public class GridViewHolder {
	
	private Map<String, GridView> views;
	
	public GridViewHolder() {
		views = new HashMap<String, GridView>();
	}

	public Map<String, GridView> getViews() {
		return views;
	}
	
	public void addViewToThisHolder(GridView view)
	{
		views.put(view.getName(), view);
	}

}
