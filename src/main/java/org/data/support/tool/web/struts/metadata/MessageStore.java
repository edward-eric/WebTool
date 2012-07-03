package org.data.support.tool.web.struts.metadata;

public class MessageStore {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public MessageStore() {
		setMessage("Hello struts user");
	}

}
