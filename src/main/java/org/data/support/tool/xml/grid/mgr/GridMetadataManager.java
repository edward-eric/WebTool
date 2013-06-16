package org.data.support.tool.xml.grid.mgr;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.data.support.tool.xml.grid.metadata.GridMetadata;

public class GridMetadataManager {
	
	private static Logger LOG = Logger.getLogger(GridMetadataManager.class);

	private Map<String, GridMetadata> _metadataCache = new HashMap<String, GridMetadata>();
	private String baseDir;
	
	public GridMetadataManager(){}
	
	public GridMetadataManager(String baseDir){
		this.baseDir = baseDir;
		LOG.info("GridMetadataManager started with base directory: " + baseDir);
	}
	
	public synchronized GridMetadata getMetadata(String uri){
		LOG.debug("Invoke meta data with uri: " + uri);
		
		if(!_metadataCache.containsKey(uri)){
			GridMetadata metadata = loadGridMetadata(uri);
			_metadataCache.put(uri, metadata);
		}
		
		return _metadataCache.get(uri);
	}
	
	protected GridMetadata loadGridMetadata(String uri){
		return null;
	}
	
	
	
	

}
