package org.data.support.tool.data.xml.mgr;

import java.net.MalformedURLException;
import java.util.List;

import org.data.support.tool.data.xml.metadata.Query;
import org.data.support.tool.data.xml.processor.FProcessor;
import org.data.support.tool.data.xml.processor.IProcessor;
import org.data.support.tool.data.xml.producer.IProducer;
import org.dom4j.Document;
import org.dom4j.DocumentException;

public class DefMgrBean implements DefMgr{
	
	private FProcessor fileProcessor;
	private IProcessor queryProcessor;
	
	private IProducer queryProducer;
	
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
	
	public IProducer getQueryProducer() {
		return queryProducer;
	}
	public void setQueryProducer(IProducer queryProducer) {
		this.queryProducer = queryProducer;
	}
	
	public void processInput(){
		try {
			List<Document> docs = fileProcessor.getDocuments();
			List<Query> quries = queryProcessor.process(docs);
			System.out.println(queryProducer.outputSQL(quries.get(0)));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
