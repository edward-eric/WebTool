package org.data.support.tool.xml.grid.mgr;

import org.apache.log4j.Logger;
import org.data.support.tool.common.SpringUtil;
import org.data.support.tool.xml.grid.metadata.Grid;
import org.data.support.tool.xml.grid.metadata.GridDataReader;
import org.data.support.tool.xml.grid.util.LRUCache;

public class GridDataReaderManager {
	
	private static Logger logger = Logger.getLogger(GridDataReaderManager.class);
	
	private LRUCache<String, Grid> gridCache = new LRUCache<String, Grid>();
	
	public void addGridToCache(String gridName, Grid grid){
		gridCache.put(gridName, grid);
	}
	
	public Grid getGridFromCache(String gridName){
		return gridCache.get(gridName);
	}
	
	public LRUCache<String, Grid> getGridCache() {
		return gridCache;
	}

	protected GridDataReader getGridDataReader(Grid grid){
		return (GridDataReader)SpringUtil.getBean(grid.getDataReader().getClass());
	}

}
