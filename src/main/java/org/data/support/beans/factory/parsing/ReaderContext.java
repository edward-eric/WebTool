package org.data.support.beans.factory.parsing;

import org.springframework.core.io.Resource;

public class ReaderContext {
	
	private final Resource resource;
	
	public ReaderContext(Resource resource){
		this.resource = resource;
	}
	
	public final Resource getResource(){
		return this.resource;
	}

}
