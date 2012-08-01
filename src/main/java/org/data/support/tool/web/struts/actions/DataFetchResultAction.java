package org.data.support.tool.web.struts.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.data.support.tool.common.file.handler.DefaultDefinitionHandler;
import org.data.support.tool.dao.JsonDataAccessor;

import com.opensymphony.xwork2.ActionSupport;

public class DataFetchResultAction extends ActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = -2027495765135108434L;
	
	private HttpServletRequest request;
	private List rows;
	private int total;
	
	private JsonDataAccessor jsonAccessor;
	
	private DefaultDefinitionHandler handler;

	public void setJsonAccessor(JsonDataAccessor jsonAccessor) {
		this.jsonAccessor = jsonAccessor;
	}

	public void setHandler(DefaultDefinitionHandler handler) {
		this.handler = handler;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
	
	public List getRows(){
		return rows;
	}
	

	public String execute() {
		
		int pageIndex = Integer.parseInt(request.getParameter("page"));
		int rowIndex  = Integer.parseInt(request.getParameter("rows"));
		
		this.rows = new ArrayList();
		
		String sql = handler.defaultReturn().get("Scenario").toString();
		
		List cols2 = new ArrayList();
		cols2.add("id");
        cols2.add("name");
        cols2.add("description");
        cols2.add("goal");
        cols2.add("type");
        
        this.rows = jsonAccessor.queryJsonMapResult(sql, cols2, (pageIndex-1)*rowIndex, rowIndex);
		
        /*List<Map<String, Object>> result = jsonAccessor.queryJsonMapResult(sql, cols2, (pageIndex-1)*rowIndex, rowIndex);
        
        ListIterator iter = result.listIterator();
        
        while(iter.hasNext()){
        	rows.add(iter.next());
        }*/
        
        /*for(int i = ( pageIndex - 1 ) * rowIndex; i< pageIndex * rowIndex; i++){
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("id", i); 
			map.put("name", 'F');
			map.put("goal","student" + i);
			map.put("type", 11+i);
			map.put("description", "1983-11-4");   
			this.rows.add(map);
		}*/
		
		total = jsonAccessor.getTotalRecords(sql);
		return SUCCESS;
	}


	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

}
