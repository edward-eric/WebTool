package org.data.support.tool.data.backup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Spring {
	
	private static final String _CONFIG_PATTERN = "appctx.xml";
	
	
	private static ApplicationContext ctx;

	private Spring() {
	}

	public static ApplicationContext getInstance(){
		if(ctx == null){
			ctx = new ClassPathXmlApplicationContext(_CONFIG_PATTERN);
		}
		return ctx;
	}

	public static <T> T getBean(String name,Class<T> clazz){
		return (T)getInstance().getBean(name, clazz);
	}
	
	/**
	 * Get bean instance with class type
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz){
		return (T)getInstance().getBean(clazz);
	}
	
	/**
	 * Get bean instance with name, cast to Object
	 * @param untrusted
	 * @return
	 */
	public static Object getBean(String name1){
		return getInstance().getBean(name1);
	}

}
