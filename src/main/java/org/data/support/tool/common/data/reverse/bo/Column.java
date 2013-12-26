package org.data.support.tool.common.data.reverse.bo;

public class Column {
	
	private String name;
	
	private String type;
	
	private boolean isNull;
	
	private int size;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Column(String name, String type, boolean isNull, int size) {
		super();
		this.name = name;
		this.type = type;
		this.isNull = isNull;
		this.size = size;
	}

}
