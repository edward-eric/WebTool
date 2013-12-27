package org.data.support.beans.factory.xml;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.data.support.beans.factory.support.QueryDefinitionDefaults;
import org.springframework.beans.factory.parsing.ParseState;
import org.springframework.beans.factory.xml.DocumentDefaultsDefinition;
import org.springframework.util.Assert;
import org.w3c.dom.Element;

public class QueryDefinitionParserDelegate{
	
	public static final String BEAN_ELEMENT = "bean";
	
	public static final String ID_ATTRIBUTE = "id";
	
	public static final String NAME_ATTRIBUTE = "name";
	
	public static final String SQL_QUERY = "querySql";
	
	public static final String SP_PRE_EXE = "preexecute";
	
	public static final String SP_POST_EXE = "postexecute";
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private final XmlReaderContext readerContext;
	
	private final DocumentDefaultsDefinition defaults = new DocumentDefaultsDefinition();
	
	private final ParseState parseState = new ParseState();
	
	/**
	 * Stores all used query names so we can enforce uniqueness on a per file basis.
	 */
	private final Set<String> usedNames = new HashSet<String>();
	
	/**
	 * Create a new QueryDefinitionParserDelegate associated with the
	 * supplied {@link XmlReaderContext}.
	 */
	public QueryDefinitionParserDelegate(XmlReaderContext readerContext){
		Assert.notNull(readerContext, "XmlReaderContext must not be null");
		this.readerContext = readerContext;
	}
	
	/**
	 * Return the defaults definition object, or <code>null</code> if the
	 * defaults have been initialized yet.
	 */
	public DocumentDefaultsDefinition getDefaults() {
		return this.defaults;
	}
	
	/**
	 * Get the {@link XmlReaderContext} associated with this helper instance.
	 */
	public final XmlReaderContext getReaderContext() {
		return this.readerContext;
	}
	
	/**
	 * Return the default settings for bean definitions as indicated within
	 * the attributes of the top-level <code>&lt;queries/&gt;</code> element.
	 */
	public QueryDefinitionDefaults getQueryDefinitionDefaults() {
		return new QueryDefinitionDefaults();
	}
	
	/**
	 * Populate the given DocumentDefaultsDefinition instance with the default settings.
	 */
	protected void populateDefaults(DocumentDefaultsDefinition defaults, Element root) {
		defaults.setSource(this.readerContext.extractSource(root));
	}
	
	/**
	 * Initialize the default settings.
	 * @see #populateDefaults(DocumentDefaultsDefinition, org.w3c.dom.Element)
	 * @see #getDefaults()
	 */
	public void initDefaults(Element root) {
		populateDefaults(this.defaults, root);
		this.readerContext.fireDefaultsRegistered(this.defaults);
	}

	

}
