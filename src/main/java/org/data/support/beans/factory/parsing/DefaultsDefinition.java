/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.data.support.beans.factory.parsing;

import org.springframework.beans.BeanMetadataElement;

/**
 * Marker interface for a defaults definition,
 * extending BeanMetadataElement to inherit source exposure.
 *
 * <p>Concrete implementations are typically based on 'document defaults',
 * for example specified at the root tag level within an XML document.
 * 
 * @author chen
 * @created 2013-12-29
 * @since 2013
 */
public interface DefaultsDefinition extends BeanMetadataElement {

}
