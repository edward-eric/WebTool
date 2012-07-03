package org.data.support.tool.web.struts.actions;

import org.data.support.tool.web.struts.metadata.MessageStore;

import com.opensymphony.xwork2.ActionSupport;

public class HelloWorldAction extends ActionSupport {
	
	private MessageStore ms;
	
	private String userName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private static int helloCount = 0;
	
	public int getHelloCount() {
		return helloCount;
	}

	public void setHelloCount(int helloCount) {
		HelloWorldAction.helloCount = helloCount;
	}

	public MessageStore getMs() {
		return ms;
	}

	public void setMs(MessageStore ms) {
		this.ms = ms;
	}
	
	public String execute() throws Exception {
		ms = new MessageStore();
		ms.setMessage(userName);
		helloCount++;
		return SUCCESS;
	}
	
	public void validate(){
		if(userName.length() < 10){
			addFieldError("userName", "user name can't be less than 10");
		}
	}

}
