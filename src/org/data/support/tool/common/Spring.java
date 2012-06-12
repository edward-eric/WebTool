package org.data.support.tool.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Spring {
	private static ApplicationContext context;

	private Spring() {
	}

	public static ApplicationContext getContext() {
		if (context == null) {
			context = new FileSystemXmlApplicationContext("resource/appctx.xml");
		}
		return context;
	}

	public static <T> T getBean(Class<T> e) {
		return (T) getContext().getBean(e);
	}

}
