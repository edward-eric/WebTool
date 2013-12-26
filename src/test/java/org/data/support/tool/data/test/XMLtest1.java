package org.data.support.tool.data.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.data.support.tool.common.SpringUtil;
import org.data.support.tool.common.data.reverse.bo.Column;
import org.data.support.tool.common.data.reverse.bo.Schema;
import org.data.support.tool.common.data.reverse.bo.Table;
import org.data.support.tool.common.file.handler.DefaultDefinitionHandler;
import org.data.support.tool.dao.JsonDataAccessor;
import org.data.support.tool.data.IDBAccessor;
import org.data.support.tool.data.xml.mgr.DefMgr;
import org.data.support.tool.xml.grid.util.XMLParser;
import org.hibernate.loader.ColumnEntityAliases;
import org.springframework.jdbc.core.JdbcTemplate;
public class XMLtest1 {
	
	private static String url = "jdbc:db2://localhost:50000/dtdev";  
    private static String user = "dtuser";  
    private static String password = "Welcome1";  
    private static String driver = "com.ibm.db2.jcc.DB2Driver";

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

//		/DefMgr mgr = SpringUtil.getBean(DefMgr.class);
		
/*		DefaultDefinitionHandler handler = SpringUtil.getBean("queryDefinitionHandler",DefaultDefinitionHandler.class);
		
		System.out.println(handler.defaultReturn());


		dbtest(handler.defaultReturn().get("Scenario").toString());
		*/
//		try {
//			Class.forName(driver);
//			Connection connection = DriverManager.getConnection(url, user, password); 
//			
//			DatabaseMetaData meta = connection.getMetaData();
//			
//			ResultSet catalogs = meta.getTables(null, "DTOPT", "%", new String[]{"TABLE"});
			 
//		    while (catalogs.next()) {
//			      String tableSchema = catalogs.getString(2);    // "TABLE_SCHEM"
//			      String tableName = catalogs.getString(3);
//			      //ResultSet set2 = meta.get
//			      System.out.println(tableSchema + "." + tableName);
//			    }
		    
		    
//		    ResultSet columns = meta.getColumns(null, "DTOPT%", "ZONE", "%");
//		    
//		    while (columns.next()) {
//			      //ResultSet set2 = meta.get
//			      System.out.println(columns.getString(2) + " "+ columns.getString(3) + " "+ columns.getString(4) + " " + columns.getString(5) + " " + columns.getString(6) + " " + columns.getString(7)
//			    		  + " " + columns.getString(8) + " " + columns.getString(9)
//			    		  + " " + columns.getString(10)
//			    		  + " " + columns.getString(11)
//			    		  + " " + columns.getString(12)
//			    		  + " " + columns.getString(13)
//			    		  + " " + columns.getString(14)
//			    		  + " " + columns.getString(15)
//			    		  + " " + columns.getString(16));
//			    }
//		    
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
			
		Connection connection;
		JdbcTemplate template = (JdbcTemplate) SpringUtil.getBean("jdbcTemplate");
//		
		try {
			 connection = template.getDataSource().getConnection();
			 DatabaseMetaData meta = connection.getMetaData();
			 
			 //ResultSet rs = meta.getSchemas(null, "DT%" );
			 
			 /*while(rs.next()){
				 System.out.println(rs.getString(1));
			 }*/
			 
			 //ResultSet rs = meta.getProcedures(null, "DTOPT", null);
			 ResultSet rs = meta.getTables(null, "SYSCAT", "PROCEDURES", new String[]{"VIEW"});
			 ResultSetMetaData rsmd = rs.getMetaData();
			 if(rsmd.getColumnCount() > 0){
				 PreparedStatement ps = connection.prepareStatement("select ProcName, Text from syscat.procedures where procschema = 'DTOPT'");
				 ResultSet ps2 = ps.executeQuery();
				 ResultSetMetaData rsmd2 = ps2.getMetaData();
				 
				 int count = rsmd2.getColumnCount();
				 while(ps2.next()){
					 System.out.println("=================================>");
					 for(int i =1; i<=count; i++){
						 System.out.println(ps2.getString(i));
					 }
					 System.out.println("=================================>");
					 //System.out.println(rs.getString(2) + "." + rs.getString(3) + " " + rs.getString(9));
				 }
				 
				 ps2.close();
			 }
			 
			 
			 rs.close();
			 
			 
			 System.out.println(meta.supportsStoredProcedures());
			 
//			 
//			 
//			 ResultSet schemaset = meta.getSchemas(null, "DTOPT%");
//			while (schemaset.next()) {
//				String schemaName = schemaset.getString(1); // "TABLE_SCHEM"
//				
//				System.out.println("===> " + schemaName);
//				
//				ResultSet tableset = meta.getTables(null, schemaName, "ZONE%", new String[]{"TABLE"});
//				
//				Map<String, Table> tables = new HashMap<String, Table>();
//				while(tableset.next()){
//					String tableName  = tableset.getString(3);
//					System.out.println("============> " + tableName);
//					ResultSet columnset = meta.getColumns(null, schemaName, tableName, "%");
//					Map<String, Column> columns = new HashMap<String, Column>();
//		            while(columnset.next()){
//		            	String ColumnName = columnset.getString(4);
//		            	
//		            	System.out.println("=======================> " + ColumnName);
//		            	String type = columnset.getString(6);
//		            	Boolean isnull = columnset.getBoolean(11);
//		            	int size = columnset.getInt(7);
//		            	
//		            	Column column = new Column(ColumnName, type, isnull, size);
//		            	columns.put(ColumnName, column);
//		            }
//		            
//		            Table table = new Table();
//		            table.setName(tableName);
//		            table.setColumns(columns);
//		            
//		            tables.put(tableName, table);
//				}
//				
//				
//				Schema schema = new Schema();
//				schema.setName(schemaName);
//				schema.setTables(tables);
//				
//				schemas.put(schemaName, schema);
//				
//			}
//			
//			System.out.println(schemas.size());
//			
//			Table tableee = schemas.get("DTOPT").getTables().get("ZONE");
//			
//			
//			System.out.println(tableee.getName());
//			Map<String, Column> columnsee = tableee.getColumns();
//			
//			Iterator it = columnsee.entrySet().iterator();
//		    while (it.hasNext()) {
//		        Map.Entry pairs = (Map.Entry)it.next();
//		        System.out.println(pairs.getKey() + " = " + ((Column)pairs.getValue()).getType());
//		        it.remove(); // avoids a ConcurrentModificationException
//		    }
			 

			 
//			    System.out.println(meta.getTableTypes());
			    
//			    ResultSet catalogs = meta.getTables(null, "DTOPT", "%", new String[]{"TABLE", "VIEW"});
//			 
//			    while (catalogs.next()) {
//				      String tableSchema = catalogs.getString(1);    // "TABLE_SCHEM"
//				      //ResultSet set2 = meta.get
//				      System.out.println("====>" + tableSchema);
//				    }
			    
			    
		} catch (SQLException e) {
//			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*XMLParser parser = new XMLParser("gridmeta.xsd");
		
		parser.loadFileToDocument("pricegrids.xml");*/
		
		
		
	}

	private static void dbtest(String sql) {
		
		JsonDataAccessor accessor = SpringUtil.getBean(JsonDataAccessor.class);
		List cols2 = new ArrayList();
		cols2.add("id");
        cols2.add("name");
        cols2.add("description");
		List<Map<String, Object>> schemas2 = accessor.queryJsonMapResult(sql, cols2, 5, 7);
		ListIterator iter = schemas2.listIterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
		System.out.println(accessor.getTotalRecords(sql));
		
	}

}
