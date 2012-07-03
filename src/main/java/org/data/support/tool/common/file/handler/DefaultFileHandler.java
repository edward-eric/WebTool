package org.data.support.tool.common.file.handler;

import java.io.IOException;
import java.util.Set;

import org.dom4j.DocumentException;

public interface DefaultFileHandler {
	
	public Set<Object> getObjectSet() throws IOException, DocumentException;
}
