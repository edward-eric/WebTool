package org.data.support.tool.common;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ErrorHandler implements org.xml.sax.ErrorHandler{
	
	
	private static final Logger logger = Logger.getLogger(ErrorHandler.class);
	
	public ErrorHandler(){}

	@Override
	public void warning(SAXParseException exception) throws SAXException {
		printError(exception);		
	}

	@Override
	public void error(SAXParseException exception) throws SAXException {
		printError(exception);		
	}

	@Override
	public void fatalError(SAXParseException exception) throws SAXException {
		printError(exception);
	}
	
	private void printError(SAXParseException ex){
		logger.warn(ex);
		logger.warn("Warning while parsing XML file \n" + ex.getMessage());
		logger.warn("Public ID: " + ex.getPublicId() + " SystemID:  " + ex.getSystemId());
		logger.warn("Line: " + ex.getLineNumber() + "  Column : " + ex.getColumnNumber());
	}

}
