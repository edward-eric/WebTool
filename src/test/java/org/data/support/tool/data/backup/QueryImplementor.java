package org.data.support.tool.data.backup;

public class QueryImplementor {
	
	private String pre_execute;
	private String post_execute;
	private String sql;
	private String name;
	public String getPre_execute() {
		return pre_execute;
	}
	public void setPre_execute(String pre_execute) {
		this.pre_execute = pre_execute;
	}
	public String getPost_execute() {
		return post_execute;
	}
	public void setPost_execute(String post_execute) {
		this.post_execute = post_execute;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
