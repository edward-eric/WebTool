package org.data.support.tool.common.file.handler.impl;

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
import org.data.support.tool.common.file.handler.DefaultDefinitionHandler;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;

public abstract class DefaultDefinitionHandlerImpl implements DefaultDefinitionHandler {
	
	private boolean needValidation = false;
	private Resource resource;
	private IOFileFilter regexFilter;	
	private SAXReader saxReader;
	private boolean isInitialized = false;
	protected List<Document> docs;
	protected Map<String, Object> defaultMap;

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setRegexFilter(IOFileFilter regexFilter) {
		this.regexFilter = regexFilter;
	}
	
	public void setNeedValidation(boolean needValidation) {
		this.needValidation = needValidation;
	}

	@Override
	public void init() {
		saxReader = new SAXReader(needValidation);
		docs = new ArrayList<Document>();
		defaultMap = new HashMap<String, Object>();
		convert();
		process();
	}

	@Override
	public void convert() {
		try {
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
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public abstract void process();

	@Override
	public Map<String, Object> defaultReturn() {
		if(!defaultMap.isEmpty() && isInitialized){
			init();
		}
		return defaultMap;
	}
	
	public void refresh(){
		isInitialized = true;
	}

}
