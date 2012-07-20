package org.data.support.tool.web.struts.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.data.support.tool.common.SpringUtil;
import org.data.support.tool.common.file.handler.DefaultDefinitionHandler;
import org.data.support.tool.web.xml.metadata.Field;
import org.data.support.tool.web.xml.metadata.SubView;
import org.data.support.tool.web.xml.metadata.View;

import com.opensymphony.xwork2.ActionSupport;

public class FetchViewHeaders extends ActionSupport implements ServletRequestAware{
	
	private List headerColumns;
	
	private String viewID;
	
	private String subViewTitle;
	
	private String subViewID;
	
	private HttpServletRequest req;
	
	private DefaultDefinitionHandler utilizer;

	public List getHeaderColumns() {
		return headerColumns;
	}

	public void setHeaderColumns(List headerColumns) {
		this.headerColumns = headerColumns;
	}
	
	public String getSubViewTitle() {
		return subViewTitle;
	}
	
	public String getSubViewID() {
		return subViewID;
	}

	public void setSubViewID(String subViewID) {
		this.subViewID = subViewID;
	}
	
	public String getViewID() {
		return viewID;
	}

	public void setViewID(String viewID) {
		this.viewID = viewID;
	}

	public void setSubViewTitle(String subViewTitle) {
		this.subViewTitle = subViewTitle;
	}
	
	public void setUtilizer(DefaultDefinitionHandler utilizer) {
		this.utilizer = utilizer;
	}

	public String execute(){
		
		String selectedView = req.getParameter("viewid");
		String selectedSubView = req.getParameter("subviewid");
		
		Map<String, Object> viewMap = utilizer.defaultReturn();
		View selectedViewInstance = (View) viewMap.get(selectedView);
		SubView sub;
		
		if(selectedViewInstance.getLeadingSubView()!=null && selectedSubView == null){
			String leadingSubView = selectedViewInstance.getLeadingSubView();
			sub = selectedViewInstance.getSubviewMap().get(leadingSubView);			
		} else{
			sub = selectedViewInstance.getSubviewMap().get(selectedViewInstance.getSubviewMap().get(selectedSubView).getNextSubView());
		}
		
		subViewTitle = sub.getTitle();
		subViewID = sub.getId();
		viewID = selectedView;
		
		ListIterator<Field> iter = sub.getFieldList().listIterator();
		
		headerColumns = new ArrayList();
		
		while(iter.hasNext()){
			Field f = iter.next();
			Map m = new HashMap();
			m.put("field", f.getName());
			m.put("title", f.getTitle());
			m.put("width", Integer.valueOf(f.getWidth()));
			headerColumns.add(m);
		}
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;		
	}

}
