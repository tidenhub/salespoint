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
package org.salespointframework.order;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.salespointframework.AbstractIntegrationTests;
import org.salespointframework.payment.Cash;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Hannes Weissbach
 * @author Paul Henke
 * @author Oliver Gierke
 */
public class OrderTests extends AbstractIntegrationTests {

	@Autowired UserAccountManager userAccountManager;
	@Autowired OrderManager<Order> orderManager;

	UserAccount user;
	Order order;

	private static int foobar = 0;

	@Before
	public void before() {

		user = userAccountManager.create("OrderTests " + foobar, "password");
		order = new Order(user, Cash.CASH);
		foobar++;
	}

	@Test
	public void orderStatusOpentest() {
		assertEquals(OrderStatus.OPEN, order.getOrderStatus());
	}

	@Test
	public void cancelOrderTest() {
		boolean result = orderManager.cancelOrder(order);
		assertEquals(OrderStatus.CANCELLED, order.getOrderStatus());
		assertTrue(result);
	}

	@Test
	public void cancelOrderTest2() {
		orderManager.cancelOrder(order);
		boolean result = orderManager.cancelOrder(order);
		assertEquals(OrderStatus.CANCELLED, order.getOrderStatus());
		assertFalse(result);
	}

	@Test
	public void cancelOrderTest3() {
		orderManager.payOrder(order);
		boolean result = orderManager.cancelOrder(order);
		assertEquals(OrderStatus.PAID, order.getOrderStatus());
		assertFalse(result);
	}

	@Test
	public void cancelOrderTest4() {
		orderManager.payOrder(order);
		orderManager.completeOrder(order);
		boolean result = orderManager.cancelOrder(order);
		assertEquals(OrderStatus.COMPLETED, order.getOrderStatus());
		assertFalse(result);
	}

	@Test
	public void payOrderTest() {
		boolean result = orderManager.payOrder(order);
		assertEquals(OrderStatus.PAID, order.getOrderStatus());
		assertTrue(result);
	}

	@Test
	public void payOrderTest2() {
		orderManager.payOrder(order);
		boolean result = orderManager.payOrder(order);
		assertEquals(OrderStatus.PAID, order.getOrderStatus());
		assertFalse(result);
	}

	@Test
	public void completeOrderTest() {

		orderManager.payOrder(order);
		orderManager.completeOrder(order);

		assertEquals(OrderStatus.COMPLETED, order.getOrderStatus());
	}

	@Test
	public void completeOrderTest2() {

		try {
			orderManager.completeOrder(order);
		} catch (OrderCompletionFailure o_O) {
			assertEquals(OrderStatus.OPEN, order.getOrderStatus());
		}
	}
}
