package org.data.support.tool.data.xml.producer;

import org.data.support.tool.data.xml.metadata.Query;

public interface IProducer {
	
	public String outputSQL(Query query);

}
