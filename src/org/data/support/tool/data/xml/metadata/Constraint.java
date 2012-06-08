package org.data.support.tool.data.xml.metadata;

import java.io.Serializable;

public class Constraint implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7954308386282989893L;
	
	
	private String src;
	private String dest;
	
	
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	
	

}
