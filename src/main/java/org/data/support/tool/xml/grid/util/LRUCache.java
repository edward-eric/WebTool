package org.data.support.tool.xml.grid.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V> extends LinkedHashMap<K, V> {
	
	public static final int DEFAULT_CAPACITY = 100;
	public static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	private int capacity;
	
	public LRUCache(){
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	public LRUCache(int maxsize){
		this(maxsize, DEFAULT_LOAD_FACTOR);
	}

	public LRUCache(int maxsize, float load) {
		super(maxsize, load, true);
		capacity = maxsize;
	}
	
	protected boolean removeEldestEntry(Map.Entry entry){
		return size() > capacity;
	}

}
