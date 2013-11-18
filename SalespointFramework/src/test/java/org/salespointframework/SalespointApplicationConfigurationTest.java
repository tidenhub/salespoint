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
package org.salespointframework;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.salespointframework.core.catalog.Catalog;
import org.salespointframework.core.inventory.Inventory;
import org.salespointframework.core.order.OrderManager;
import org.salespointframework.core.time.TimeService;
import org.salespointframework.core.useraccount.AuthenticationManager;
import org.salespointframework.core.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

/**
 * Integration test to bootstrap the application configuration.
 * 
 * @author Oliver Gierke
 */
@Transactional
public class SalespointApplicationConfigurationTest extends AbstractIntegrationTests {

	@Autowired Inventory inventory;
	@Autowired OrderManager orderManager;
	@Autowired Catalog catalog;
	@Autowired TimeService timeService;
	@Autowired UserAccountManager userAccountManager;
	@Autowired AuthenticationManager authenticationManager;
	@Autowired List<HandlerMethodArgumentResolver> argumentResolvers;

	// @Autowired ProductRepository<Product> repository;

	@Test
	public void createsApplicationComponents() {

		assertThat(inventory, is(notNullValue()));
		assertThat(orderManager, is(notNullValue()));
		assertThat(catalog, is(notNullValue()));
		assertThat(userAccountManager, is(notNullValue()));
		assertThat(timeService, is(notNullValue()));
		assertThat(authenticationManager, is(notNullValue()));
		assertThat(argumentResolvers, hasSize(1));
		
		// assertThat(repository, is(notNullValue()));
	}
}