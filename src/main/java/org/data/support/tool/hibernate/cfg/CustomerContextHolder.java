package org.data.support.tool.hibernate.cfg;

public class CustomerContextHolder {
	
	private static final ThreadLocal<String> contextHolderc = new ThreadLocal<String>();
	
	public static void setCustomType(String customType){
		contextHolderc.set(customType);
	}
	
	public static String getCustomType(){
		return (String)contextHolderc.get();
	}
	
	public static void clearCustomType(){
		contextHolderc.remove();
	}

}
