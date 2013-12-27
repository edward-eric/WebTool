package org.data.support.beans.factory.xml;

import org.data.support.beans.factory.config.QueryDefinition;
import org.data.support.beans.factory.support.QueryDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlReaderContext;



public final class ParserContext {

	private final XmlReaderContext readerContext;

	private final QueryDefinitionParserDelegate delegate;


	public ParserContext(XmlReaderContext readerContext, QueryDefinitionParserDelegate delegate) {
		this.readerContext = readerContext;
		this.delegate = delegate;
	}


	public final XmlReaderContext getReaderContext() {
		return this.readerContext;
	}

	public final QueryDefinitionRegistry getRegistry() {
		return this.readerContext.getRegistry();
	}

	public final QueryDefinitionParserDelegate getDelegate() {
		return this.delegate;
	}

	public final BeanDefinition getContainingBeanDefinition() {
		return this.containingBeanDefinition;
	}

	public final boolean isNested() {
		return (this.containingBeanDefinition != null);
	}

	public boolean isDefaultLazyInit() {
		return QueryDefinitionParserDelegate.TRUE_VALUE.equals(this.delegate.getDefaults().getLazyInit());
	}

	public Object extractSource(Object sourceCandidate) {
		return this.readerContext.extractSource(sourceCandidate);
	}

	public CompositeComponentDefinition getContainingComponent() {
		return (!this.containingComponents.isEmpty() ?
				(CompositeComponentDefinition) this.containingComponents.lastElement() : null);
	}

	public void pushContainingComponent(CompositeComponentDefinition containingComponent) {
		this.containingComponents.push(containingComponent);
	}

	public CompositeComponentDefinition popContainingComponent() {
		return (CompositeComponentDefinition) this.containingComponents.pop();
	}

	public void popAndRegisterContainingComponent() {
		registerComponent(popContainingComponent());
	}

	public void registerComponent(ComponentDefinition component) {
		CompositeComponentDefinition containingComponent = getContainingComponent();
		if (containingComponent != null) {
			containingComponent.addNestedComponent(component);
		}
		else {
			this.readerContext.fireComponentRegistered(component);
		}
	}

	public void registerBeanComponent(BeanComponentDefinition component) {
		BeanDefinitionReaderUtils.registerBeanDefinition(component, getRegistry());
		registerComponent(component);
	}

}

