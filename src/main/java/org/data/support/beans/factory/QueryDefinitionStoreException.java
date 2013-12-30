package org.data.support.beans.factory;

import org.springframework.beans.FatalBeanException;

public class QueryDefinitionStoreException extends FatalBeanException{
	
	private String resourceDescription;
	
	public QueryDefinitionStoreException(String message){
		super(message);
	}
	
public QueryDefinitionStoreException(String message, Throwable cause){
	super(message, cause);
		
	}

public QueryDefinitionStoreException(String resourceDescription, String msg, Throwable cause) {
	super(msg, cause);
	this.resourceDescription = resourceDescription;
}

}
