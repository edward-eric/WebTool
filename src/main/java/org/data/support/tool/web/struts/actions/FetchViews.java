package org.data.support.tool.web.struts.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.data.support.tool.common.SpringUtil;
import org.data.support.tool.common.file.handler.DefaultDefinitionHandler;

import com.opensymphony.xwork2.ActionSupport;

public class FetchViews extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3458090841311636102L;
	
	private List<String> viewList;

	public void setViewList(List<String> viewList) {
		this.viewList = viewList;
	}

	public List<String> getViewList() {
		return viewList;
	}




	public String execute(){
		viewList = new ArrayList<String>();
		
		
        DefaultDefinitionHandler utilizer = SpringUtil.getBean("viewDefinitionHandler", DefaultDefinitionHandler.class);
		
		Map<String, Object> views;
		views = utilizer.defaultReturn();
		Iterator<String> iter = views.keySet().iterator();
			
		while(iter.hasNext()){
			viewList.add(iter.next());
		}
		return SUCCESS;
	}

}
