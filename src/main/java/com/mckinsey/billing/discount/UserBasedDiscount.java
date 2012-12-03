/**
 * 
 */
package com.mckinsey.billing.discount;

import com.mckinsey.billing.common.CustomerType;

/**
 * Discount wrapper class for handling the User based discounts.<br>
 * Example :<br>
 * <li>If the user is an employee of the store, he gets a 30% discount.<li>
 * If the user is an affiliate of the store, he gets a 10% discount. <li>If the
 * user has been a customer for over 2 years, he gets a 5% discount .
 */
public final class UserBasedDiscount implements IDiscount {

	private CustomerType customerType;

	/**
	 * Parameterized constructor.
	 * 
	 * @param customerType
	 *            {@link CustomerType}
	 */
	public UserBasedDiscount(final CustomerType customerType) {
		this.customerType = customerType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mckinsey.billing.discount.IDiscount#applyDiscount(java.lang.Double)
	 */
	@Override
	public Double applyDiscount(final Double itemRate) {
		return (itemRate - itemRate * customerType.getCustomerTypeDiscount());
	}

	/**
	 * @param customerType
	 *            the customerType to set
	 */
	protected void setCustomerType(final CustomerType customerType) {
		this.customerType = customerType;
	}

}
