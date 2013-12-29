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

import java.util.EventListener;
/**
 * Interface that receives callbacks for default
 * registrations during a query definition reading process.
 * 
 * @author chen
 * @created 2013-12-29
 * @since 2013
 */
public interface ReaderEventListener extends EventListener {
	
	
	/**
	 * Notification that the given defaults has been registered.
	 * @param defaultsDefinition a descriptor for the defaults
	 */
	void defaultsRegistered(DefaultsDefinition defaultsDefinition);

}
