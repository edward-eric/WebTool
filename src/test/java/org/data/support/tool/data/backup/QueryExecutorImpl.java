package org.data.support.tool.data.backup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;

public class QueryExecutorImpl implements QueryExecutor{
	
	private Resource resource;
	private IOFileFilter regexFilter;	
	private SAXReader saxReader;
	protected List<Document> docs;
	protected Map<String, Object> defaultMap;
	
	
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setRegexFilter(IOFileFilter regexFilter) {
		this.regexFilter = regexFilter;
	}
	
	
	public void init() {
		saxReader = new SAXReader();
		docs = new ArrayList<Document>();
		defaultMap = new HashMap<String, Object>();
		try {
			process();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void process() throws IOException, DocumentException {
		
		if(resource.getFile().isDirectory()){
			Collection<File> filesInDefinitionDirectory = FileUtils.listFiles(resource.getFile(), regexFilter, null);
			Iterator<File> iteratorForFiles = filesInDefinitionDirectory.iterator();
			while(iteratorForFiles.hasNext()){
				docs.add(saxReader.read(iteratorForFiles.next()));
			}
		}else if(resource.getFile().isFile()){
			docs.add(saxReader.read(resource.getFile()));
		}else{
			throw new RuntimeException("Resource Definition can't be parsed correctly, " +
					"please verify your definitions in spring configuration files: " +
					resource.getFile().getName());
		}
		
		Iterator<Document> iter = docs.iterator();
		
		while(iter.hasNext()){
			processDoc(iter.next());
		}
		
	}
	
	public void processDoc(Document doc){
		List<Element> els = doc.getRootElement().elements("query");
         Iterator<Element> iter = els.iterator();
		
		while(iter.hasNext()){
			processElement(iter.next());
		}
	}
	
	public void processElement(Element e){
		e.attributeValue("");
	}

}
