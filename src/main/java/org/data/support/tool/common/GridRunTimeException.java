package org.data.support.tool.common;

import org.apache.log4j.Logger;

public class GridRunTimeException extends RuntimeException {
	
	Logger logger = Logger.getLogger(GridRunTimeException.class);
	
	public GridRunTimeException(String str, Throwable th)
	{
		super(str, th);
		logger.error(str);
	}
	
	public GridRunTimeException(String str){
		super(str);
		logger.error(str);
	}

}
