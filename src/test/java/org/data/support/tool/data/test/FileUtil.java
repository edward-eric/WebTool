package org.data.support.tool.data.test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.data.support.tool.common.SpringUtil;
import org.data.support.tool.common.file.handler.DefaultFileHandler;
import org.data.support.tool.common.xml.processor.ClasspathFileProcessor;
import org.data.support.tool.web.xml.metadata.View;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class FileUtil {
	
	public static void main(String[] args) throws IOException, DocumentException{
		
		/*String[] exts = {"xml", "properties"};
		Resource resource = new ClassPathResource("/resource/");
		
		Collection<File> files = FileUtils.listFiles(resource.getFile(), exts, false);*/
		
		/*Pattern pattern = Pattern.compile("[a-zA-Z]+def.xml");
		
		Matcher matcher = pattern.matcher("testdef.xml");

		System.out.println(matcher.matches());*/
		
			
		
		DefaultFileHandler utilizer = SpringUtil.getBean("viewFileHandler", DefaultFileHandler.class);
		
		Set<Object> views = utilizer.getObjectSet();
		
		Iterator<Object> iter = views.iterator();
		
		while(iter.hasNext()){
			System.out.println(((View)iter.next()).getSubviewList().size());
		}
		
	}

}
