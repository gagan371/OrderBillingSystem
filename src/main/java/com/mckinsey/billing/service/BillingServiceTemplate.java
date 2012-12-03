/**
 * 
 */
package com.mckinsey.billing.service;

import com.mckinsey.billing.common.DiscountType;
import com.mckinsey.billing.model.ProductOrder;

/**
 * Billing template class defining the template(steps) for calculating the
 * discounted price for a given {@link ProductOrder}.
 * 
 */
public abstract class BillingServiceTemplate implements BillingService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mckinsey.billing.service.BillingService#getNetPayableAmount(com.mckinsey
	 * .billing.model.ProductOrder)
	 */
	public Double getNetPayableAmount(final ProductOrder order) {

		if (order == null) {
			throw new IllegalArgumentException("Product cannot be null");
		}

		applyUserBasedDiscount(order);

		applyOrderValueBasedDiscount(order);

		return order.getNetPayableAmount();
	}

	/**
	 * Method for applying the OrderValueDiscount of type :
	 * {@link DiscountType#ORDER_BASED_DISCOUNT} <br>
	 * Rule:For every $100 on the bill, there would be a $ 5 discount (e.g. for
	 * $ 990, you get $ 45 as a discount).
	 * 
	 * @param order
	 *            {@link ProductOrder} on which this discount has to be applied.
	 */
	public abstract void applyOrderValueBasedDiscount(final ProductOrder order);

	/**
	 * Method for applying the UserBasedDicount of type :
	 * {@link DiscountType#USER_BASED_DISCOUNT} <br>
	 * Rule:<br>
	 * <br>
	 * 1. If the user is an employee of the store, he gets a 30% discount.<br>
	 * 2. If the user is an affiliate of the store, he gets a 10% discount.<br>
	 * 3. If the user has been a customer for over 2 years, he gets a 5%
	 * discount .
	 * 
	 * @param order
	 *            {@link ProductOrder} on which this discount has to be applied.
	 */
	public abstract void applyUserBasedDiscount(final ProductOrder order);

}
