/**
 * 
 */
package com.mckinsey.billing.common;

import com.mckinsey.billing.discount.IDiscount;
import com.mckinsey.billing.discount.OrderBasedDiscount;
import com.mckinsey.billing.discount.UserBasedDiscount;

/**
 * Factory class for instantiating the approppriate Discount object based on teh
 * given discount type.
 * 
 */
public final class DiscountFactory {

	private DiscountFactory() {
		// private constructor
	}

	private static IDiscount discountObj;

	/**
	 * Factory method for returning the appropriate Discount object based on the
	 * passed discount type.
	 * 
	 * @param discountType
	 *            The {@link DiscountType}
	 * @param customerType
	 *            The {@link CustomerType}
	 * @return Object implementation of  {@link IDiscount} which can be
	 *         {@link OrderBasedDiscount} or {@link UserBasedDiscount}.
	 */
	public static IDiscount getDiscountType(final DiscountType discountType, final CustomerType customerType) {
		if (discountType == DiscountType.USER_BASED_DISCOUNT) {
			discountObj = new UserBasedDiscount(customerType);
		} else if (discountType == DiscountType.ORDER_BASED_DISCOUNT) {
			discountObj = new OrderBasedDiscount();
		}
		return discountObj;
	}
}