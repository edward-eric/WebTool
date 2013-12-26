package org.data.support.tool.hibernate.cfg;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DynamicDataSource.class);

	@Override
	protected Object determineCurrentLookupKey() {
		logger.debug("Switch data source to: " + CustomerContextHolder.getCustomType());
		return CustomerContextHolder.getCustomType();
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}
}
