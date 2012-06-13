package org.data.support.tool.common;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Spring Util class give support for users to get a static application context
 * There are three wrapped methods to extract bean class from context
 * @author eric.chen
 */
public class SpringUtil {
	
	/**
	 * Spring configuration pattern in resource folder
	 */
	private static final String _CONFIG_PATTERN = "resource/spring-conf/spring-*.xml";
	
	/**
	 * Singleton instance
	 */
	private static ApplicationContext ctx;
	
	public SpringUtil() {}
	
	/**
	 * Get singleton instance for ctx
	 * @return singleton instance represents for Application context with a specified folder
	 */
	public static ApplicationContext getInstance(){
		if(ctx == null){
			ctx = new FileSystemXmlApplicationContext(_CONFIG_PATTERN);
		}
		return ctx;
	}
	
	/**
	 * Get Bean instance with name and Class type
	 * @param <T>
	 * @param name
	 * @param clazz
	 * @return
	 */
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
	 * @param name
	 * @return
	 */
	public static Object getBean(String name){
		return getInstance().getBean(name);
	}
	
	

}
