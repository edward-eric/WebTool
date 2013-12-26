package org.data.support.tool.xml.grid.metadata;

public class GridDataQuery {
	
	private String pre_execute_cmd;
	private String query_cmd;
	private String post_execute_cmd;
	
	public GridDataQuery(){}
	
	public String getPre_execute_cmd() {
		return pre_execute_cmd;
	}
	public void setPre_execute_cmd(String pre_execute_cmd) {
		this.pre_execute_cmd = pre_execute_cmd;
	}
	public String getQuery_cmd() {
		return query_cmd;
	}
	public void setQuery_cmd(String query_cmd) {
		this.query_cmd = query_cmd;
	}
	public String getPost_execute_cmd() {
		return post_execute_cmd;
	}
	public void setPost_execute_cmd(String post_execute_cmd) {
		this.post_execute_cmd = post_execute_cmd;
	}
}
