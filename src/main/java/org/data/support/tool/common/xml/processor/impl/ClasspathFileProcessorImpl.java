package org.data.support.tool.common.xml.processor.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.data.support.tool.common.xml.processor.ClasspathFileProcessor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;

public class ClasspathFileProcessorImpl implements ClasspathFileProcessor {
	
	//Spring classpath resource
	private Resource resource;
	
    //regex filter
	private IOFileFilter regexFilter;

    // setter method for regex filter
	public void setRegexFilter(IOFileFilter regexFilter) {
		this.regexFilter = regexFilter;
	}
	// setter method for classpath resource
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * 
	 * @return dom4j document
	 */
	public Collection<Document> getDocumentsCollection() throws IOException, DocumentException {
		SAXReader reader = new SAXReader(true);
		if(resource.getFile().isDirectory()){
			Collection<File> files = FileUtils.listFiles(resource.getFile(), regexFilter, null );
			Collection<Document> docs = new ArrayList<Document>();
			Iterator<File> iter = files.iterator();
			while(iter.hasNext()){
				docs.add(reader.read(iter.next()));
			}
			return docs;
		}else{
			throw new IOException("Resource is not valid directory, please verify in your configuration file.");
		}
	}  

}
