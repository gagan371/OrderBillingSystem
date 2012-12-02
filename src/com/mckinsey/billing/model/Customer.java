package com.mckinsey.billing.model;

import com.mckinsey.billing.common.CustomerType;

/**
 * Wrapper class representing the Order <b>Customer</b>. It has an attribute
 * {@link CustomerType} for distinguishing various types of customers.
 * 
 * 
 */
public class Customer {

	/** Customer type {@link CustomerType} **/
	private CustomerType customerType;

	/**
	 * @param customerType CustomerType
	 */
	public Customer(final CustomerType customerType) {
		super();
		this.customerType = customerType;
	}

	/**
	 * @return the customerType
	 */
	public CustomerType getCustomerType() {
		return customerType;
	}

	/**
	 * @param customerType
	 *            the customerType to set
	 */
	public void setCustomerType(final CustomerType customerType) {
		this.customerType = customerType;
	}
}
