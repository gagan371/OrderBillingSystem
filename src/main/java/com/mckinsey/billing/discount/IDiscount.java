package com.mckinsey.billing.discount;

import com.mckinsey.billing.model.ProductOrder;

/**
 * Interface specifying the method to be applied on the {@link ProductOrder}, to
 * get the discounted value.
 * 
 */
public interface IDiscount {

	/**
	 * Method that needs tobe overriden by the implementors to apply the
	 * discount on the given amount.
	 * 
	 * @param amount
	 *            Amount on which discount has to be applied.
	 * @return Revised amount after the applying the discount.
	 */
	public Double applyDiscount(final Double amount);

}
