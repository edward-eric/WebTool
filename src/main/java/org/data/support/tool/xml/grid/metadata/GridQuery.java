package org.data.support.tool.xml.grid.metadata;

import java.util.ArrayList;
import java.util.List;

public class GridQuery {
	
	private List<String> queries;
	
	public GridQuery()
	{
		queries = new ArrayList<String>(8);
	}

	public List<String> getQueries() {
		return queries;
	}
	
	public void addQuery(String queryName)
	{
		queries.add(queryName);
	}

}
