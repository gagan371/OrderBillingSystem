package com.mckinsey.billing.service;

import org.junit.Assert;
import org.junit.Test;

import com.mckinsey.billing.common.CategoryType;
import com.mckinsey.billing.common.CustomerType;
import com.mckinsey.billing.common.DiscountFactory;
import com.mckinsey.billing.common.DiscountType;
import com.mckinsey.billing.data.CustomerFactory;
import com.mckinsey.billing.data.OrderFactory;
import com.mckinsey.billing.discount.IDiscount;
import com.mckinsey.billing.discount.OrderBasedDiscount;
import com.mckinsey.billing.discount.UserBasedDiscount;
import com.mckinsey.billing.model.ProductOrder;

/**
 * Test class to validate the different discount types applicable on the
 * different customer types.
 * 
 */
public class BillingServiceImplTest {

	private IDiscount discountObj;

	/**
	 * <li>Test case to validate null product order. <li>Since the product order
	 * is null, the test case should be expected to throw
	 * {@link IllegalArgumentException}.
	 * **/

	@Test(expected = IllegalArgumentException.class)
	public void testNullProductOrder() {
		final ProductOrder order = OrderFactory.createEmptyOrder();

		final BillingServiceImpl billingServiceImpl = new BillingServiceImpl();
		billingServiceImpl.getNetPayableAmount(order);

		Assert.fail("Control cannot reach here");
	}

	/**
	 * <b>Testing Environment</b> :<br>
	 * <li>Orders of only category type {@link CategoryType#GROCERY}. <li>Total
	 * Groceries order value is less than 100$ - as a result
	 * {@link DiscountType#ORDER_BASED_DISCOUNT} discount would not be
	 * applicable.<li>Customer is an employee store. <br>
	 * 
	 * <b>Expected Result:</b><br> <li>Since the order is compromised of only
	 * Grocery items, and that too the order is of value < 100$, no discount
	 * would be available to {@link CustomerType#STORE_EMPLOYEE}. i.e Total
	 * Order Amount == Net Payable Amount
	 * 
	 * */
	@Test
	public void testEmployeeDiscountOnOrdersWithOnlyGroceriesLessThan100$() {
		final ProductOrder order = OrderFactory.createOnlyGroceriesBasedOrder(false);

		order.setCustomer(CustomerFactory.createEmployeeCustomer());
		final Double actualOrderAmount = order.getOrderAmount();
		Assert.assertNotNull(actualOrderAmount);

		final BillingServiceImpl billingServiceImpl = new BillingServiceImpl();
		final Double netPayableAmount = billingServiceImpl.getNetPayableAmount(order);

		Assert.assertNotNull(netPayableAmount);
		Assert.assertEquals(actualOrderAmount, netPayableAmount.doubleValue(), .01);
	}

	/**
	 * <b>Testing Environment</b> :<br>
	 * <li>Orders of only category type {@link CategoryType#GROCERY}. <li>Total
	 * Groceries order value is less than 100$ - as a result
	 * {@link DiscountType#ORDER_BASED_DISCOUNT} discount would not be
	 * applicable.<li>Customer is an store affiliate. <br>
	 * 
	 * <b>Expected Result:</b><br> <li>Since the order is compromised of only
	 * Grocery items, and that too the order is of value < 100$, no discount
	 * would be available to {@link CustomerType#STORE_AFFILIATE}. i.e Total
	 * Order Amount == Net Payable Amount
	 * 
	 * */
	@Test
	public void testStoreAffiliateDiscountOnOrdersWithOnlyGroceriesLessThan100$() {
		final ProductOrder order = OrderFactory.createOnlyGroceriesBasedOrder(false);
		order.setCustomer(CustomerFactory.createAffiliatedCustomer());
		final Double actualOrderAmount = order.getOrderAmount();
		Assert.assertNotNull(actualOrderAmount);

		final BillingServiceImpl billingServiceImpl = new BillingServiceImpl();
		final Double netPayableAmount = billingServiceImpl.getNetPayableAmount(order);

		Assert.assertNotNull(netPayableAmount);
		Assert.assertEquals(actualOrderAmount, netPayableAmount.doubleValue(), .01);
	}

	/**
	 * <b>Testing Environment</b> :<br>
	 * <li>Orders of only category type {@link CategoryType#GROCERY}. <li>Total
	 * Groceries order value is less than 100$ - as a result
	 * {@link DiscountType#ORDER_BASED_DISCOUNT} discount would not be
	 * applicable.<li>Customer is an Loyal customer. <br>
	 * 
	 * <b>Expected Result:</b><br> <li>Since the order is compromised of only
	 * Grocery items, and that too the order is of value < 100$, no discount
	 * would be available to {@link CustomerType#LOYAL_CUSTOMER}. i.e Total
	 * Order Amount == Net Payable Amount
	 * 
	 * */
	@Test
	public void testLoyalCustomerDiscountOnOrdersWithOnlyGroceriesLessThan100$() {
		final ProductOrder order = OrderFactory.createOnlyGroceriesBasedOrder(false);
		order.setCustomer(CustomerFactory.createLoyaltyCustomer());
		final Double actualOrderAmount = order.getOrderAmount();
		Assert.assertNotNull(actualOrderAmount);

		final BillingServiceImpl billingServiceImpl = new BillingServiceImpl();
		final Double netPayableAmount = billingServiceImpl.getNetPayableAmount(order);
		Assert.assertNotNull(netPayableAmount);

		Assert.assertEquals(actualOrderAmount, netPayableAmount.doubleValue(), .01);
	}

	/** Test cases related to only groceries orders worth more than 100$ */

	/**
	 * <b>Testing Environment</b> :<br>
	 * <li>Orders of only category type {@link CategoryType#GROCERY}. <li>Total
	 * Groceries order value is greater(>) than 100$ - as a result
	 * {@link DiscountType#ORDER_BASED_DISCOUNT} discount would be applicable.
	 * <li>Customer is an employee store. <br>
	 * 
	 * <b>Expected Result:</b><br> <li>Since the order is compromised of only
	 * Grocery items - no discount would be available to customer,but since the
	 * order is of value > 100$, OrderBasedDiscount will be applied .
	 * 
	 * @see {@link OrderBasedDiscount#applyDiscount(Double)}*
	 * */
	@Test
	public void testStoreEmployeeDiscountOnOrdersWithOnlyGroceriesMoreThan100$() {
		final ProductOrder order = OrderFactory.createOnlyGroceriesBasedOrder(true);
		order.setCustomer(CustomerFactory.createEmployeeCustomer());
		final Double actualOrderAmount = order.getOrderAmount();
		Assert.assertNotNull(actualOrderAmount);

		final BillingServiceImpl billingServiceImpl = new BillingServiceImpl();
		final Double netPayableAmount = billingServiceImpl.getNetPayableAmount(order);

		Assert.assertNotNull(netPayableAmount);

		Double calculatedDiscountedAmount = 0.0;
		if (actualOrderAmount >= 100.00) {
			// if the order is of more than 100$, we need to get the
			// OrderValueDiscount for this order
			discountObj = DiscountFactory.getDiscountType(DiscountType.ORDER_BASED_DISCOUNT, null);
			calculatedDiscountedAmount = discountObj.applyDiscount(actualOrderAmount);
		}

		Assert.assertEquals(calculatedDiscountedAmount, netPayableAmount.doubleValue(), .01);
	}

	/**
	 * <b>Testing Environment</b> :<br>
	 * <li>Orders of only category type {@link CategoryType#GROCERY}. <li>Total
	 * Groceries order value is greater(>) than 100$ - as a result
	 * {@link DiscountType#ORDER_BASED_DISCOUNT} discount would be applicable.
	 * <li>Customer is an store affiliate. <br>
	 * 
	 * <b>Expected Result:</b><br> <li>Since the order is compromised of only
	 * Grocery items - no discount would be available to customer,but since the
	 * order is of value > 100$, OrderBasedDiscount will be applied .
	 * 
	 * @see {@link OrderBasedDiscount#applyDiscount(Double)}*
	 * */
	@Test
	public void testStoreAffiliateDiscountOnOrdersWithOnlyGroceriesMoreThan100$() {
		final ProductOrder order = OrderFactory.createOnlyGroceriesBasedOrder(true);
		order.setCustomer(CustomerFactory.createAffiliatedCustomer());
		final Double actualOrderAmount = order.getOrderAmount();
		final BillingServiceImpl billingServiceImpl = new BillingServiceImpl();
		final Double netPayableAmount = billingServiceImpl.getNetPayableAmount(order);

		Assert.assertNotNull(actualOrderAmount);
		Assert.assertNotNull(netPayableAmount);

		Double calculatedDiscountedAmount = 0.0;
		if (actualOrderAmount >= 100.00) {
			// if the order is of more than 100$, we need to get the
			// OrderValueDiscount for this order
			discountObj = DiscountFactory.getDiscountType(DiscountType.ORDER_BASED_DISCOUNT, null);
			calculatedDiscountedAmount = discountObj.applyDiscount(actualOrderAmount);
		}

		Assert.assertEquals(calculatedDiscountedAmount, netPayableAmount.doubleValue(), .01);
	}

	/**
	 * Test cases for orders including non grocery orders having order value <
	 * 100$
	 **/

	/**
	 * <b>Testing Environment</b> :<br>
	 * <li>Orders of only category type {@link CategoryType#NONGROCERY}. <li>
	 * Total Groceries order value is less than 100$ - as a result
	 * {@link DiscountType#ORDER_BASED_DISCOUNT} discount would not be
	 * applicable.<li>Customer is an store employee. <br>
	 * 
	 * <b>Expected Result:</b><br> <li>Since the order is compromised of Non
	 * Grocery items - So UserBasedDiscout will be applicable.
	 * 
	 * @see {@link UserBasedDiscount#applyDiscount(Double)}.
	 * 
	 * */
	@Test
	public void testStoreEmployeeDiscountOnNonGroceryOrdersLessThan100$() {
		final ProductOrder order = OrderFactory.createOrderWithOnlyNonGroceryItems(false);
		order.setCustomer(CustomerFactory.createEmployeeCustomer());
		final Double actualOrderAmount = order.getOrderAmount();
		final BillingServiceImpl billingServiceImpl = new BillingServiceImpl();
		final Double netPayableAmount = billingServiceImpl.getNetPayableAmount(order);

		Assert.assertNotNull(actualOrderAmount);
		Assert.assertNotNull(netPayableAmount);

		Double calculatedDiscountedAmount = 0.0;
		// for non grocery items we will find the user discount
		discountObj = DiscountFactory.getDiscountType(DiscountType.USER_BASED_DISCOUNT, CustomerType.STORE_EMPLOYEE);
		calculatedDiscountedAmount = discountObj.applyDiscount(actualOrderAmount);

		Assert.assertEquals(calculatedDiscountedAmount, netPayableAmount.doubleValue(), .01);
	}

	/**
	 * <b>Testing Environment</b> :<br>
	 * <li>Orders of only category type {@link CategoryType#NONGROCERY}. <li>
	 * Total Groceries order value is less than 100$ - as a result
	 * {@link DiscountType#ORDER_BASED_DISCOUNT} discount would not be
	 * applicable.<li>Customer is an store affiliate. <br>
	 * 
	 * <b>Expected Result:</b><br> <li>Since the order is compromised of Non
	 * Grocery items - So UserBasedDiscout will be applicable.
	 * 
	 * @see {@link UserBasedDiscount#applyDiscount(Double)}.
	 * 
	 * */
	@Test
	public void testStoreAffiliateDiscountOnNonGroceryOrdersLessThan100$() {
		final ProductOrder order = OrderFactory.createOrderWithOnlyNonGroceryItems(false);
		order.setCustomer(CustomerFactory.createAffiliatedCustomer());
		final Double actualOrderAmount = order.getOrderAmount();
		final BillingServiceImpl billingServiceImpl = new BillingServiceImpl();
		final Double netPayableAmount = billingServiceImpl.getNetPayableAmount(order);

		Assert.assertNotNull(actualOrderAmount);
		Assert.assertNotNull(netPayableAmount);

		Double calculatedDiscountedAmount = 0.0;
		// for non grocery items we will find the user discount
		discountObj = DiscountFactory.getDiscountType(DiscountType.USER_BASED_DISCOUNT, CustomerType.STORE_AFFILIATE);
		calculatedDiscountedAmount = discountObj.applyDiscount(actualOrderAmount);

		Assert.assertEquals(calculatedDiscountedAmount, netPayableAmount.doubleValue(), .01);
	}

	/**
	 * <b>Testing Environment</b> :<br>
	 * <li>Orders of only category type {@link CategoryType#NONGROCERY}. <li>
	 * Total Groceries order value is less than 100$ - as a result
	 * {@link DiscountType#ORDER_BASED_DISCOUNT} discount would not be
	 * applicable.<li>Customer is an Loyal Customer. <br>
	 * 
	 * <b>Expected Result:</b><br> <li>Since the order is compromised of Non
	 * Grocery items - So UserBasedDiscout will be applicable.
	 * 
	 * @see {@link UserBasedDiscount#applyDiscount(Double)}.
	 * 
	 * */

	@Test
	public void testLoyalCustomerDiscountOnNonGroceryOrdersLessThan100$() {
		final ProductOrder order = OrderFactory.createOrderWithOnlyNonGroceryItems(false);
		order.setCustomer(CustomerFactory.createLoyaltyCustomer());
		final Double actualOrderAmount = order.getOrderAmount();
		final BillingServiceImpl billingServiceImpl = new BillingServiceImpl();
		final Double netPayableAmount = billingServiceImpl.getNetPayableAmount(order);

		Assert.assertNotNull(actualOrderAmount);
		Assert.assertNotNull(netPayableAmount);

		Double calculatedDiscountedAmount = 0.0;
		// for non grocery items we will find the user discount
		discountObj = DiscountFactory.getDiscountType(DiscountType.USER_BASED_DISCOUNT, CustomerType.LOYAL_CUSTOMER);
		calculatedDiscountedAmount = discountObj.applyDiscount(actualOrderAmount);

		Assert.assertEquals(calculatedDiscountedAmount, netPayableAmount.doubleValue(), .01);
	}

	/**
	 * <b>Testing Environment</b> :<br>
	 * <li>Orders of only category type {@link CategoryType#NONGROCERY}. <li>
	 * Total order value is less(<) than 100$ <li>Customer is an normal
	 * customer. <br>
	 * 
	 * <b>Expected Result:</b><br> <li>Since the order is compromised of Non
	 * Grocery items - So {@link DiscountType#USER_BASED_DISCOUNT} will be
	 * applicable.But since the customer is a normal cutomer, he will not be
	 * getting any discounts.
	 * 
	 * <li>Total order value is less(<) than 100$ - as a result
	 * {@link DiscountType#ORDER_BASED_DISCOUNT} discount would not be
	 * applicable.
	 * */
	@Test
	public void testNormalCustomerDiscountOnNonGroceryOrdersLessThan100$() {
		final ProductOrder order = OrderFactory.createOrderWithOnlyNonGroceryItems(false);
		order.setCustomer(CustomerFactory.createNormalCustomer());
		final Double actualOrderAmount = order.getOrderAmount();
		final BillingServiceImpl billingServiceImpl = new BillingServiceImpl();
		final Double netPayableAmount = billingServiceImpl.getNetPayableAmount(order);

		Assert.assertNotNull(actualOrderAmount);
		Assert.assertNotNull(netPayableAmount);

		Double calculatedDiscountedAmount = 0.0;
		// for non grocery items we will find the user discount
		discountObj = DiscountFactory.getDiscountType(DiscountType.USER_BASED_DISCOUNT, CustomerType.NORMAL_CUSTOMER);
		calculatedDiscountedAmount = discountObj.applyDiscount(actualOrderAmount);

		Assert.assertEquals(calculatedDiscountedAmount, netPayableAmount.doubleValue(), .01);
	}

	/**
	 * <b>Testing Environment</b> :<br>
	 * <li>Orders of only category type {@link CategoryType#NONGROCERY}. <li>
	 * Total order value is greater(>) than 100$ <li>Customer is an store
	 * employee. <br>
	 * 
	 * <b>Expected Result:</b><br> <li>Since the order is compromised of Non
	 * Grocery items - So {@link DiscountType#USER_BASED_DISCOUNT} will be
	 * applicable.
	 * 
	 * <li>Total order value is greater(>) than 100$ - as a result
	 * {@link DiscountType#ORDER_BASED_DISCOUNT} discount would be applicable.
	 * */
	@Test
	public void testStoreEmployeeDiscountOnNonGroceryOrdersMoreThan100$() {
		final ProductOrder order = OrderFactory.createOrderWithOnlyNonGroceryItems(true);
		order.setCustomer(CustomerFactory.createEmployeeCustomer());
		final Double actualOrderAmount = order.getOrderAmount();
		final BillingServiceImpl billingServiceImpl = new BillingServiceImpl();
		final Double netPayableAmount = billingServiceImpl.getNetPayableAmount(order);

		Assert.assertNotNull(actualOrderAmount);
		Assert.assertNotNull(netPayableAmount);

		// 1 .. for non grocery items we will find the user discount
		discountObj = DiscountFactory.getDiscountType(DiscountType.USER_BASED_DISCOUNT, CustomerType.STORE_EMPLOYEE);
		Double calculatedDiscountedAmount = discountObj.applyDiscount(actualOrderAmount);

		// 2.. if the order value is more than 100$, apply the order value
		// discount
		discountObj = DiscountFactory.getDiscountType(DiscountType.ORDER_BASED_DISCOUNT, null);
		calculatedDiscountedAmount = discountObj.applyDiscount(calculatedDiscountedAmount);

		Assert.assertEquals(calculatedDiscountedAmount, netPayableAmount.doubleValue(), .01);
	}

	/**
	 * <b>Testing Environment</b> :<br>
	 * <li>Orders of only category type {@link CategoryType#NONGROCERY}. <li>
	 * Total order value is greater(>) than 100$ <li>Customer is an Loyal
	 * customer. <br>
	 * 
	 * <b>Expected Result:</b><br> <li>Since the order is compromised of Non
	 * Grocery items - So {@link DiscountType#USER_BASED_DISCOUNT} will be
	 * applicable.
	 * 
	 * <li>Total order value is greater(>) than 100$ - as a result
	 * {@link DiscountType#ORDER_BASED_DISCOUNT} discount would be applicable.
	 * */
	@Test
	public void testLoyalCustomerDiscountOnNonGroceryOrdersMoreThan100$() {
		final ProductOrder order = OrderFactory.createOrderWithOnlyNonGroceryItems(true);
		order.setCustomer(CustomerFactory.createLoyaltyCustomer());
		final Double actualOrderAmount = order.getOrderAmount();
		final BillingServiceImpl billingServiceImpl = new BillingServiceImpl();
		final Double netPayableAmount = billingServiceImpl.getNetPayableAmount(order);

		Assert.assertNotNull(actualOrderAmount);
		Assert.assertNotNull(netPayableAmount);

		// 1 .. for non grocery items we will find the user discount
		discountObj = DiscountFactory.getDiscountType(DiscountType.USER_BASED_DISCOUNT, CustomerType.LOYAL_CUSTOMER);
		Double calculatedDiscountedAmount = discountObj.applyDiscount(actualOrderAmount);

		// 2.. if the order value is more than 100$, apply the order value
		// discount
		discountObj = DiscountFactory.getDiscountType(DiscountType.ORDER_BASED_DISCOUNT, null);
		calculatedDiscountedAmount = discountObj.applyDiscount(calculatedDiscountedAmount);

		Assert.assertEquals(calculatedDiscountedAmount, netPayableAmount.doubleValue(), .01);
	}
}
