package org.data.support.tool.data.test;


import java.util.ArrayList;
import java.util.List;

import org.data.support.tool.data.backup.Executor;
import org.data.support.tool.data.backup.Spring;

public class SpringTest {
	
	public static void main(String[] args){
		
		
		Executor executor = (Executor)Spring.getBean("dbExcecutor");
		
		List<String> colList = new ArrayList<String>();
		colList.add("scenariorunid");
		colList.add("status");
		
		executor.callSP("call dtopt.scenarioRunResultExtPrepAndPost(132, 'PREP', 0)");
		
		
		List l1 =  executor.query("select * from dtopt.scenariorun fetch first 10 rows only", null, colList);
		
		System.out.println(l1.size());
		
		
		
		
		/*SpringUtil.getBean(DefMgr.class);*/
		
		
/*		try {
			Properties props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("db.properties"));
			
			
			
			Map s = (Map)props;
			
			for(Object o : s.entrySet()){
				Map.Entry entry = (Map.Entry)o;
				System.out.println(entry.getKey() + " = " + entry.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
*/		
		
		
		/*HibernateFactoryManager manager = new HibernateFactoryManager();
		try {
			manager.initInstance();
			
			HibernateFactoryManager m1 = manager.getInstance();
			HibernateFactoryManager m2 = manager.getInstance();
			
			
			Session s1 = m1.getSession();
			Session s2 = m2.getSession();

			System.out.println(s1.getSessionFactory().equals(s2.getSessionFactory()));
			manager.closeInstance();
			
			AbstractDataSource;
			
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*HibernateTemplate template = null;
		CustomerContextHolder.setCustomType("local");
		try {
			HibernateTemplateManager.initInstance();
			template = HibernateTemplateManager.getInstance();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Object> results = template.findByNamedQueryAndNamedParam("fetchScenrioRunResultsByDemandGroup", new String[]{"scenarioRunId"}, new Object[]{43});
		
		Iterator<Object> result = results.iterator();
		while(result.hasNext()){
			System.err.println(result.next());
		}*/
		
		
		/*List<String> names = template.executeFind(new HibernateCallback<List<String>>() {
			@Override
			public List<String> doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createSQLQuery("select name from dtopt.scenario").list();
			}
		});
		
		Iterator<String> nameI = names.iterator();
		while(nameI.hasNext()){
			System.err.println(nameI.next());
		}*/
		//template.clear();
		
	}

}
