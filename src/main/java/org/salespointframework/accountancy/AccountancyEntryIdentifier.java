/*
 * Copyright 2017 the original author or authors.
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
package org.salespointframework.accountancy;

import java.util.Map;

import javax.persistence.Embeddable;

import org.salespointframework.core.SalespointIdentifier;

/**
 * {@link AccountancyEntryIdentifier} serves as an identifier type for {@link AccountancyEntry} objects. The main reason
 * for its existence is type safety for identifier across the Salespoint Framework. <br />
 * {@link AccountancyEntryIdentifier} instances serve as primary key attribute in {@link PersistentAccountancyEntry} ,
 * but can also be used as a key for non-persistent, {@link Map}-based implementations.
 * 
 * @author Hannes Weisbach
 * @author Oliver Gierke
 */
@Embeddable
public final class AccountancyEntryIdentifier extends SalespointIdentifier {

	private static final long serialVersionUID = 1767274605223942263L;

	/**
	 * Creates a new unique identifier for accountancy entries.
	 */
	AccountancyEntryIdentifier() {
		super();
	}

	/**
	 * Only needed for property editor, shouldn't be used otherwise.
	 * 
	 * @param identifier The string representation of the identifier.
	 */
	AccountancyEntryIdentifier(String identifier) {
		super(identifier);
	}
}
