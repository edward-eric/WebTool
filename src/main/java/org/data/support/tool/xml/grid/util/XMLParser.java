package org.data.support.tool.xml.grid.util;

import java.io.File;
import java.io.IOException;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLParser {
	
	static Logger logger = Logger.getLogger(XMLParser.class);
	
	private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	
	private DocumentBuilder builder;
	private Validator validator;
	
	public XMLParser(String xsdFile){
		try {
			initParser(xsdFile);
		} catch (Exception e) {
			throw new GridRunTimeException("Failed creating XML parser for schema " + xsdFile, e);
		} 
	}
	
	protected void initParser(String xsdFile) throws ParserConfigurationException, SAXException, IOException{
		builder  =  initBuilder();
		if(xsdFile != null){
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
	
	private Validator initValidator(String name) throws SAXException, IOException
	{
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Source schemaFile = new StreamSource(new ClassPathResource("/meta-conf/" + name).getInputStream());
		Schema schema = factory.newSchema(schemaFile);
		return schema.newValidator();
	}
	
	public Document loadFileToDocument(String name) throws IOException
	{
		Document document = null;
		Resource resource = new ClassPathResource("/meta-conf/price/" + name);
		File file = resource.getFile();
		
		if(!file.exists()){
			throw new IllegalArgumentException("File " + name + " does not exist.");
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
            logger.error("Error loading definition xml for file:" + name);
            throw new GridRunTimeException("Error loading definition xml for file:" + name, e);
        }
		
		return document;
	}
}
