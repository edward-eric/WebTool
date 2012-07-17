package org.data.support.tool.data.test;

import java.io.IOException;
import java.util.Map;

import org.data.support.tool.common.SpringUtil;
import org.data.support.tool.common.file.handler.DefaultDefinitionHandler;
import org.data.support.tool.web.xml.metadata.View;
import org.dom4j.DocumentException;

public class FileUtil {
	
	public static void main(String[] args) throws IOException, DocumentException{
		
		/*String[] exts = {"xml", "properties"};
		Resource resource = new ClassPathResource("/resource/");
		
		Collection<File> files = FileUtils.listFiles(resource.getFile(), exts, false);*/
		
		/*Pattern pattern = Pattern.compile("[a-zA-Z]+def.xml");
		
		Matcher matcher = pattern.matcher("testdef.xml");

		System.out.println(matcher.matches());*/
		
			
		
		DefaultDefinitionHandler utilizer = SpringUtil.getBean("viewDefinitionHandler", DefaultDefinitionHandler.class);
		
		/*Set<Object> views = utilizer.getObjectSet();
		
		Iterator<Object> iter = views.iterator();
		
		while(iter.hasNext()){
			System.out.println(((View)iter.next()).getSubviewList().size());
		}*/
		
		Map views = utilizer.defaultReturn();
		
		View view = (View) views.get("ScenarioResult");
		
		System.out.println(view);
		
		utilizer.refresh();
		
		Map views2 = utilizer.defaultReturn();
		
        View view2 = (View) views2.get("ScenarioResult");
		
		System.out.println(view2);
		
		/*Iterator<Entry<K, V>> iter = ((View)views.get("ScenarioResult")).getSubviewMap().entrySet().iterator();
		
		while(iter.hasNext()){
			SubView sub = (SubView)iter.next();
			if(sub==null){
				continue;
			}
			System.out.println(sub.getTitle());
		}*/
		
		
	}

}
