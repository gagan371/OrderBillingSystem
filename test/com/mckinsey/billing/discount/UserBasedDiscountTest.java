package com.mckinsey.billing.discount;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mckinsey.billing.common.CustomerType;
import com.mckinsey.billing.common.DiscountFactory;
import com.mckinsey.billing.common.DiscountType;

/**
 * 
 * Test class to validate the UserBasedDiscount applied on the given Order
 * value.<br>
 * Example :<br>
 * <li>If the user is an employee of the store, he gets a 30% discount.<li>
 * If the user is an affiliate of the store, he gets a 10% discount. <li>If the
 * user has been a customer for over 2 years, he gets a 5% discount .
 * 
 * 
 */
public class UserBasedDiscountTest {

	private IDiscount discountObj;
	private CustomerType customerType;

	@Before
	public void before() {
		customerType = CustomerType.STORE_EMPLOYEE;
		discountObj = DiscountFactory.getDiscountType(DiscountType.USER_BASED_DISCOUNT, customerType);
	}

	/**
	 * Testing for employee based discount which is 30%
	 */
	@Test
	public void testUserBasedDiscountForStoreEmployee() {

		Assert.assertEquals(45.50, discountObj.applyDiscount(65.00), 0.01);
		Assert.assertEquals(56.35, discountObj.applyDiscount(80.5), 0.01);
	}

	/**
	 * Testing for store affiliated based discount which is 10%
	 */
	@Test
	public void testUserBasedDiscountForStoreAffiliation() {

		// changing the customer type to store affiliate
		if (discountObj instanceof UserBasedDiscount) {
			((UserBasedDiscount) discountObj).setCustomerType(CustomerType.STORE_AFFILIATE);
		}

		Assert.assertEquals(58.5, discountObj.applyDiscount(65.00), 0.01);
		Assert.assertEquals(72.45, discountObj.applyDiscount(80.5), 0.01);
	}

	/**
	 * Testing for loyal user (customer with store for more than 2 years) based
	 * discount which is 5%
	 */
	@Test
	public void testUserBasedDiscountForLoyalCustomers() {

		// changing the customer type to Loyal customer
		if (discountObj instanceof UserBasedDiscount) {
			((UserBasedDiscount) discountObj).setCustomerType(CustomerType.LOYAL_CUSTOMER);
		}

		Assert.assertEquals(61.75, discountObj.applyDiscount(65.00), 0.01);
		Assert.assertEquals(76.47, discountObj.applyDiscount(80.5), 0.01);
	}

	/**
	 * Testing for normal customer based discount which is 0%
	 */
	@Test
	public void testUserBasedDiscountForNormalCustomers() {

		// changing the customer type to Normal customer
		if (discountObj instanceof UserBasedDiscount) {
			((UserBasedDiscount) discountObj).setCustomerType(CustomerType.NORMAL_CUSTOMER);
		}

		Assert.assertEquals(65.00, discountObj.applyDiscount(65.00), 0.01);
		Assert.assertEquals(80.5, discountObj.applyDiscount(80.5), 0.01);
	}
}
