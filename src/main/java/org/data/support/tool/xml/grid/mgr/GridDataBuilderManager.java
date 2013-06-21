package org.data.support.tool.xml.grid.mgr;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.data.support.tool.xml.grid.metadata.Grid;

public class GridDataBuilderManager {
	
	private static Logger LOG = Logger.getLogger(GridDataBuilderManager.class);

	private Map<String, Grid> _metadataCache = new HashMap<String, Grid>();
	private String baseDir;
	
	public GridDataBuilderManager(){}
	
	public GridDataBuilderManager(String baseDir){
		this.baseDir = baseDir;
		LOG.info("GridMetadataManager started with base directory: " + baseDir);
	}
	
	public synchronized Grid getMetadata(String uri){
		LOG.debug("Invoke meta data with uri: " + uri);
		
		if(!_metadataCache.containsKey(uri)){
			Grid metadata = loadGridMetadata(uri);
			_metadataCache.put(uri, metadata);
		}
		
		return _metadataCache.get(uri);
	}
	
	protected Grid loadGridMetadata(String uri){
		return null;
	}
	
	
	
	

}
