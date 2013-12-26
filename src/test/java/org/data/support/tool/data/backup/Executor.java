package org.data.support.tool.data.backup;

import java.util.List;

public interface Executor {
	
	
	public List<List<Object>> query(String sql, Object[] params,
			final List<String> colNames);
	
	
	public void callSP(String spString);

}
