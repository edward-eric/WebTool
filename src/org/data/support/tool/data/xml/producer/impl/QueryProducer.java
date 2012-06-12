package org.data.support.tool.data.xml.producer.impl;

import java.util.ListIterator;

import org.data.support.tool.data.xml.metadata.Column;
import org.data.support.tool.data.xml.metadata.Constraint;
import org.data.support.tool.data.xml.metadata.Join;
import org.data.support.tool.data.xml.metadata.Query;
import org.data.support.tool.data.xml.producer.IProducer;

public class QueryProducer implements IProducer {
	
	/**
	 * Local query instance for this producer
	 */
	private Query qury = null;
	
	/**
	 * output sql used for 
	 */
	private String outsql;

	private final static String _DESC_DB = " DESC ";
	private final static String _ASC_DB = " ASC ";
	
	private final static String _SEPERATOR_DB = ".";
	private final static String _AS_DB = " AS ";
	private final static String _INNER_JOIN_DB = " INNER JOIN ";
	private final static String _OUTER_JOIN_DB = " LEFT OUTER JOIN ";
	private final static String _ON_DB = " ON ";
	private final static String _AND_DB = " AND ";
	private final static String _LINE_DB = "\n";
	private final static String _SPACE_DB = " ";
	private final static String _EQUAL_DB = " = ";
	private final static String _FROM_DB = " FROM ";
	private final static String _SELECT_DB = "SELECT ";
	private final static String _SELECT_SEP_DB = ",";

	@Override
	public String outputSQL(Query query) {
		qury = query;
		return composeQuery();
	}
	
	protected String composeQuery(){
		return "WITH DTTMP.X ( " + getPrefixQuery() + " ) AS ( " + _LINE_DB +
		       _SELECT_DB + getColumnsQuery() + getFromQuery() + getJoinsQuery() +
		       getOrderByQuery() + " ) " + _LINE_DB + " SELECT * FROM DTTMP.X";
	}
	
	protected String composeColumnQuery(Column column){
		return column.getFrom() + _SEPERATOR_DB + column.getColumnName() +
		       _AS_DB + column.getName();
	}
	
	protected String getColumnsQuery(){
		StringBuilder columnsql = new StringBuilder();
		ListIterator<Column> iter = qury.getColumns().listIterator();
		while(iter.hasNext()){
			columnsql.append(composeColumnQuery(iter.next()));
			if(iter.hasNext()){
				columnsql.append(_SELECT_SEP_DB + _LINE_DB);
			}
		}
		return columnsql.toString();
	}
	
	protected String composeJoinQuery(Join join){
		String condition = _INNER_JOIN_DB;
		if(join.isOuter()){
			condition = _OUTER_JOIN_DB;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(condition + join.getTable() + _SPACE_DB + join.getAlias() + _LINE_DB);
		sql.append(_ON_DB);
		ListIterator<Constraint> iter = join.getConstraints().listIterator();
		while(iter.hasNext()){
			sql.append(composeConstraint(iter.next()));
			if(iter.hasNext()){
				sql.append(_AND_DB);
			}
		}
		return sql.toString();
	}
	
	protected String getJoinsQuery() {
		StringBuilder joinsql = new StringBuilder();
		ListIterator<Join> iter = qury.getJoins().listIterator();
		while(iter.hasNext()){
			joinsql.append(composeJoinQuery(iter.next()));
		}
		return joinsql.toString();
	}
	
	protected String getFromQuery(){
		return _FROM_DB + qury.getBaseTable() + _SPACE_DB + qury.getAlias() + _LINE_DB;
	}
	
	protected String composeConstraint(Constraint con){
		return _SPACE_DB + con.getSrc() + _EQUAL_DB + con.getDest() + _LINE_DB;
	}
	
	
	protected String getOrderByQuery(){
		StringBuilder orderBySql = new StringBuilder();
		ListIterator<Column> iter = qury.getColumns().listIterator();
		boolean _flag = false;
		while(iter.hasNext()){
			Column column = iter.next();
			if(column.isOrderby()){
				if(_flag == true){
					orderBySql.append(_SELECT_SEP_DB + _LINE_DB);
				}else{
					_flag = true;
					orderBySql.append(" ORDER BY ");					
				}
				orderBySql.append(composeOrderByQuery(column));
			}
		}
		return orderBySql.toString();
	}
	
	protected String composeOrderByQuery(Column column){
		String _trend = _DESC_DB ;
		if(column.isTrend()){
			_trend = _ASC_DB;
		}
		return column.getFrom() + _SEPERATOR_DB + column.getColumnName() + _trend;
	}
	
	protected String getPrefixQuery(){
		StringBuilder _prefixQuery = new StringBuilder();
		ListIterator<Column> iter = qury.getColumns().listIterator();
		while(iter.hasNext()){
			_prefixQuery.append(iter.next().getName());
			if(iter.hasNext()){
				_prefixQuery.append(_SELECT_SEP_DB);
			}
		}
		return _prefixQuery.toString();
	}
	

}
