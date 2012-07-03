package org.data.support.tool.web.struts.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.data.support.tool.common.SpringUtil;
import org.data.support.tool.common.file.handler.DefaultFileHandler;
import org.data.support.tool.web.xml.metadata.View;
import org.dom4j.DocumentException;

import com.opensymphony.xwork2.ActionSupport;

public class DataFetchResultAction extends ActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = -2027495765135108434L;
	
	private HttpServletRequest request;
	private List<Object> rows;
	private int total;
	private View view;
	
	
	
	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
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
	
	
	public String execute() {
		
		int pageIndex = Integer.parseInt(request.getParameter("page"));
		int rowIndex  = Integer.parseInt(request.getParameter("rows"));
		
		
		this.rows = new ArrayList<Object>();
		
        DefaultFileHandler utilizer = SpringUtil.getBean("viewFileHandler", DefaultFileHandler.class);
		
		Set<Object> views;
		try {
			views = utilizer.getObjectSet();
			Iterator<Object> iter = views.iterator();
			
			while(iter.hasNext()){
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
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
