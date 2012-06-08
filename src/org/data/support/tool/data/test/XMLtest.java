package org.data.support.tool.data.test;

import java.io.File;
import java.net.MalformedURLException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;


public class XMLtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SAXReader reader = new SAXReader();
		
		File file = new File("resource/components.xml");
		
		try {
			Document document = reader.read(file);
			document.asXML();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
