/**
 * 
 */
package com.mckinsey.billing.discount;

/**
 * Discount wrapper class for handling the Order based discounts.<br>
 * Example : <li>For every $100 on the bill, there would be a $ 5 discount (e.g.
 * for $ 990, you get $ 45 as a discount).
 * 
 */
public class OrderBasedDiscount implements IDiscount {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mckinsey.billing.discount.IDiscount#applyDiscount(java.lang.Double)
	 */
	@Override
	public Double applyDiscount(final Double amount) {
		return (amount - (int) (amount / 100) * 5);
	}
}