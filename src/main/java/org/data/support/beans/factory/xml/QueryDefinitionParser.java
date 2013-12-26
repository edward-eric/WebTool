package org.data.support.beans.factory.xml;

import org.data.support.beans.factory.config.QueryDefinition;
import org.w3c.dom.Element;

public interface QueryDefinitionParser {
	
	QueryDefinition parse(Element e, ParserContext context);

}
