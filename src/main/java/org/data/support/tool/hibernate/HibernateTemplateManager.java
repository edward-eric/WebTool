package org.data.support.tool.hibernate;

import java.io.IOException;

import javax.persistence.QueryHint;

import org.apache.log4j.Logger;
import org.data.support.tool.common.SpringUtil;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class HibernateTemplateManager {
	
	private static Logger logger = Logger.getLogger(HibernateTemplateManager.class);
	
	private static HibernateTemplate instance = null;
	
	public synchronized static void initInstance() throws HibernateException, IOException
	{
		if ( instance == null )
		{
			instance = (HibernateTemplate) SpringUtil.getBean("template");
			
		}
	}
	
	public static synchronized void closeInstance() {
		instance.getSessionFactory().close();
	}
	
	public synchronized static HibernateTemplate getInstance() {
		return instance;
	}

}
