package org.data.support.tool.xml.grid.util;

import java.io.File;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;
import org.data.support.tool.common.ErrorHandler;
import org.data.support.tool.common.GridRunTimeException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLParser {
	
	static Logger logger = Logger.getLogger(XMLParser.class);
	
	private static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
	private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	
	private DocumentBuilder builder;
	private Validator validator;
	
	public XMLParser(String xsdFile){
		try {
			initParser(xsdFile);
		} catch (Exception e) {
			throw new GridRunTimeException("failed creating XML parser for schema " + xsdFile, e);
		} 
	}
	
	public XMLParser(){ this(null);}
	
	protected void initParser(String xsdFile) throws ParserConfigurationException, SAXException{
		if(xsdFile != null){
			builder  =  initBuilder();
			validator  =  initValidator(xsdFile);
		}
	}
	
	private DocumentBuilder initBuilder() throws ParserConfigurationException
	{
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		
		builderFactory.setValidating(true);
		builderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		builder.setErrorHandler(new ErrorHandler());
		
		return builder;
	}
	
	private Validator initValidator(String xsdFile) throws SAXException
	{
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Source schemaFile = new StreamSource(XMLParser.class.getResourceAsStream(xsdFile));
		Schema schema = factory.newSchema(schemaFile);
		return schema.newValidator();
	}
	
	public Document loadDefinitionXML(InputStream is)
	{
		Document document = null;
		try{
			document = builder.parse(is);
			if(validator != null){
				validator.validate(new DOMSource(document));
			}
		}catch(Exception e){
			throw new GridRunTimeException("Error loading definition xml for inputStream", e);
		}
		
		return document;
	}
	
	public Document loadDefinitionXMl(String filename)
	{
		Document document = null;
		File file = new File(filename);
		
		if(!file.exists()){
			throw new IllegalArgumentException("File " + filename + " does not exist.");
		}
		
		try{
			document = builder.parse(file);
			if(validator !=  null){
				validator.validate(new DOMSource(document));
			}
		}catch (SAXParseException e) {
            logger.error("SAXParseException: Validation failed on file:" + file.getName() + " Reason:" + e.getMessage());
            throw new GridRunTimeException("SAXParseException: Validation failed on file:" + file.getName() + " Reason:" + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Error loading definition xml for file:" + filename);
            throw new GridRunTimeException("Error loading definition xml for file:" + filename, e);
        }
		
		return document;
	}

}
