package org.data.support.tool.web.xml.metadata;

import java.io.Serializable;

/**
 * Field class represents column object in preference definition page.
 * @author eric.chen
 */
public class Field implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5462482418327010544L;
	
	private String name;
	private String title;
	private String width;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
}
