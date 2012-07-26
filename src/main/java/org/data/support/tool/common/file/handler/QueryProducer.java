package org.data.support.tool.common.file.handler;

import org.data.support.tool.data.xml.metadata.Query;

public interface QueryProducer {
	
	public String outputSQL(Query query);

}
