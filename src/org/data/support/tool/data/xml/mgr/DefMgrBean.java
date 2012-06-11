package org.data.support.tool.data.xml.mgr;

import java.net.MalformedURLException;
import java.util.List;

import org.data.support.tool.data.xml.metadata.Query;
import org.data.support.tool.data.xml.processor.FProcessor;
import org.data.support.tool.data.xml.processor.IProcessor;
import org.dom4j.Document;
import org.dom4j.DocumentException;

public class DefMgrBean implements DefMgr{
	
	private FProcessor fileProcessor;
	private IProcessor queryProcessor;
	
	public FProcessor getFileProcessor() {
		return fileProcessor;
	}
	public void setFileProcessor(FProcessor fileProcessor) {
		this.fileProcessor = fileProcessor;
	}
	public IProcessor getQueryProcessor() {
		return queryProcessor;
	}
	public void setQueryProcessor(IProcessor queryFrocessor) {
		this.queryProcessor = queryFrocessor;
	}



	public void processInput(){
		try {
			List<Document> docs = fileProcessor.getDocuments();
			List<Query> quries = queryProcessor.process(docs);
			System.out.println(quries);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
