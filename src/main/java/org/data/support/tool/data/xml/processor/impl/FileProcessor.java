package org.data.support.tool.data.xml.processor.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.data.support.tool.data.xml.processor.FProcessor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class FileProcessor implements FProcessor {
	
	private final static String _LOCATION_PREFIX = "/resource/datareader-conf/";
	
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
			File file = null;
			try {
				file = new File( this.getClass().getResource(_LOCATION_PREFIX + fileNames[i]).toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(file!=null && file.exists()){
				Document doc = reader.read(file);
				documents.add(doc);
			}
		}
		return documents;
	}

}
