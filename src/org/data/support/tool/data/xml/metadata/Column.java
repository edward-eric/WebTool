package org.data.support.tool.data.xml.metadata;

import java.io.Serializable;

public class Column implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8875564798949105409L;
	
	private String name;
	private String columnName;
	private boolean orderby;
	private boolean trend;
	private String from;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public boolean isOrderby() {
		return orderby;
	}
	public void setOrderby(boolean orderby) {
		this.orderby = orderby;
	}
	public boolean isTrend() {
		return trend;
	}
	public void setTrend(boolean trend) {
		this.trend = trend;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	public Column(String name, String columnName, boolean orderby,
			boolean trend, String from) {
		this.name = name;
		this.columnName = columnName;
		this.orderby = orderby;
		this.trend = trend;
		this.from = from;
	}

}
