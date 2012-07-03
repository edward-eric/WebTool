package org.data.support.tool.common.file.handler.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.data.support.tool.common.file.handler.DefaultFileHandler;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;

public abstract class DefaultFileHandlerImpl implements DefaultFileHandler{
	
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
	
	protected Set<Object> returnObjectSet = new HashSet<Object>();
	
	protected List<Document> docs = new ArrayList<Document>();

	protected void handleFiles() throws IOException, DocumentException {
		docs.clear();
		SAXReader reader = new SAXReader(true);
		if(resource.getFile().isDirectory()){
			Collection<File> files = FileUtils.listFiles(resource.getFile(), regexFilter, null );
			Iterator<File> iter = files.iterator();
			while(iter.hasNext()){
				docs.add(reader.read(iter.next()));
			}
		}else{
			throw new IOException("Resource is not valid directory, please verify in your configuration file.");
		}
	}
	
	protected void processDocuments(){
		ListIterator<Document> iter = docs.listIterator();
		while(iter.hasNext()){
			processRootElement(iter.next().getRootElement());
		}
	}
	
	protected abstract void processRootElement(Element root);
	
	@Override
	public Set<Object> getObjectSet() throws IOException, DocumentException{
		handleFiles();
		processDocuments();
		return returnObjectSet;
	}

}
