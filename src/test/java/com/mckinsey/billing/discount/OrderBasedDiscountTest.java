package com.mckinsey.billing.discount;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mckinsey.billing.common.DiscountFactory;
import com.mckinsey.billing.common.DiscountType;

/**
 * Test class to validate the OrderBasedDiscounts applied on the given Order
 * value.<br>
 * Criteria :<li> For every $100 on the bill, there would be a $ 5 discount (e.g.
 * for $ 990, you get $ 45 as a discount).
 * 
 * 
 */
public class OrderBasedDiscountTest {
	private IDiscount discountObj;

	/**
	 * Setting the appropriate discount type
	 * {@link DiscountType#ORDER_BASED_DISCOUNT}
	 */
	@Before
	public void before() {
		discountObj = DiscountFactory.getDiscountType(DiscountType.ORDER_BASED_DISCOUNT, null);
	}

	/**
	 * Test validation:<br>
	 * <li>If the order value < 100, then applying the discount should not
	 * result in modified order amount. <li>If the order value > 100, For every
	 * $100 on the bill, there would be a $ 5 discount.
	 */
	@Test
	public void testOrderBasedDiscount() {
		Assert.assertEquals(87.50, discountObj.applyDiscount(87.50), 0.01);
		Assert.assertEquals(95.50, discountObj.applyDiscount(100.50), 0.01);
		Assert.assertEquals(90.00, discountObj.applyDiscount(90.00), 0.01);
		Assert.assertEquals(220.50, discountObj.applyDiscount(230.50), 0.01);
		Assert.assertEquals(300.00, discountObj.applyDiscount(315.00), 0.01);
	}

}
