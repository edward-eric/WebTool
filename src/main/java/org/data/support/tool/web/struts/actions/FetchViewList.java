package org.data.support.tool.web.struts.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.data.support.tool.common.SpringUtil;
import org.data.support.tool.common.file.handler.DefaultFileHandler;
import org.data.support.tool.web.xml.metadata.View;
import org.dom4j.DocumentException;

import com.opensymphony.xwork2.ActionSupport;

public class FetchViewList extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3458090841311636102L;
	
	private List viewList;

	public List getViewList() {
		return viewList;
	}
	public void setViewList(List viewList) {
		this.viewList = viewList;
	}

	public String execute(){
		viewList = new ArrayList();
		
		
        DefaultFileHandler utilizer = SpringUtil.getBean("viewFileHandler", DefaultFileHandler.class);
		
		Set<Object> views;
		try {
			views = utilizer.getObjectSet();
			Iterator<Object> iter = views.iterator();
			
			while(iter.hasNext()){
				viewList.add(((View)iter.next()).getId());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return SUCCESS;
	}

}
