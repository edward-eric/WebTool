package org.data.support.tool.data.xml.processor.impl;

import java.io.File;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.data.support.tool.data.xml.processor.FProcessor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class FileProcessor implements FProcessor {
	
	private final static String _LOCATION_PREFIX = "resource/";
	
	private String[] fileNames;

	public String[] getFileNames() {
		return fileNames;
	}
	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	@Override
	public List getDocuments() throws MalformedURLException, DocumentException {
		SAXReader reader = new SAXReader(true);
		List documents = new ArrayList();
		for(int i = 0; i < fileNames.length; i++){
			File file = new File(_LOCATION_PREFIX + fileNames[i]);
			if(file.exists()){
				Document doc = reader.read(file);
				documents.add(doc);
			}
		}
		return documents;
	}

}
