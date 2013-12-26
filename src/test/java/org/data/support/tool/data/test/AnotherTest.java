package org.data.support.tool.data.test;

import java.util.regex.Pattern;

public class AnotherTest {
	
	
	
	private static final Pattern RBP_RESULT_PATTERN = Pattern.compile("\\s*,\\s*[p]?");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		 String[] lineItems = RBP_RESULT_PATTERN.split("Z000,p1,1.0,2.0");
		 
		 for(int i = 0 ; i < lineItems.length; i++){
			 System.out.println(lineItems[i]);
		 }
		
		

	}

}
