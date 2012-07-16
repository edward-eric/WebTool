package org.data.support.tool.web.struts.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.dom4j.DocumentException;

import com.opensymphony.xwork2.ActionSupport;

public class DataFetchResultAction extends ActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = -2027495765135108434L;
	
	private HttpServletRequest request;
	private List<Object> rows;
	private int total;
	
	private String viewName;
	
	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewTitle) {
		this.viewName = viewTitle;
	}


	private List<Object> subviews;
	
	private List<Object> headerColumns;
	
	public List<Object> getHeaderColumns() {
		return headerColumns;
	}

	public void setHeaderColumns(List<Object> headerColumns) {
		this.headerColumns = headerColumns;
	}
	
	public List<Object> getSubviews() {
		return subviews;
	}

	public void setSubviews(List<Object> subviews) {
		this.subviews = subviews;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}
	
	public List<Object> getRows(){
		return rows;
	}
	
	public String loadLeadingTable() throws IOException, DocumentException
	{
		
		/*DefaultFileHandler utilizer = SpringUtil.getBean("viewFileHandler", DefaultFileHandler.class);
		View view = (View)utilizer.getObjectMap().get(request.getParameter("viewid"));*/
		
		return SUCCESS;
	}
	
	public String loadView() throws IOException, DocumentException{
		/*DefaultFileHandler utilizer = SpringUtil.getBean("viewFileHandler", DefaultFileHandler.class);
		View view = (View)utilizer.getObjectMap().get(request.getParameter("viewid"));
		
		viewName = view.getTitle();
		subviews = new ArrayList<Object>();
		List subviews2 = view.getSubviewList();
		ListIterator iter = subviews2.listIterator();
		while(iter.hasNext()){
			SubView sub = (SubView)iter.next();
			if(sub==null){
				continue;
			}
			Map m = new HashMap();
			m.put("id", sub.getId());
			m.put("title", sub.getTitle());
			ListIterator iter2 = sub.getFieldList().listIterator();
			List fieldList2 = new ArrayList();
			while(iter2.hasNext()){
				Field field = (Field)iter2.next();
				Map m2 = new HashMap();
				m2.put("name", field.getName());
				m2.put("title", field.getTitle());
				m2.put("width", field.getWidth());
				fieldList2.add(m2);
			}
			m.put("subview", fieldList2);
			subviews.add(m);
		}*/
		return SUCCESS;
	}
	
	public String loadHeaderColumns(){
		headerColumns = new ArrayList<Object>();		
		return SUCCESS;
	}
	
	public String execute() {
		
		int pageIndex = Integer.parseInt(request.getParameter("page"));
		int rowIndex  = Integer.parseInt(request.getParameter("rows"));
		
		
		this.rows = new ArrayList<Object>();
		
        /*DefaultFileHandler utilizer = SpringUtil.getBean("viewFileHandler", DefaultFileHandler.class);
		
		Set<Object> views;
		try {
			views = utilizer.getObjectSet();
			Iterator<Object> iter = views.iterator();
			
			while(iter.hasNext()){
				View view = (View)iter.next();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		for(int i = ( pageIndex - 1 ) * rowIndex; i< pageIndex * rowIndex; i++){
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("id", i); 
			map.put("sex", 'F');
			map.put("name","student" + i);
			map.put("age", 11+i);
			map.put("birthday", "1983-11-4");
		    map.put("classname", "class" + i);      
			this.rows.add(map);
		}
		
		total = 200;
		

		return SUCCESS;
	}
	
	public String funcs()
	{
		return SUCCESS;
	}


	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

}
