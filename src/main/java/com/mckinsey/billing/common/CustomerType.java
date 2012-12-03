/**
 * 
 */
package com.mckinsey.billing.common;

/**
 * Enum for the customer type, who owns the order.
 * 
 */
public enum CustomerType {

	/**
	 * Customer type can be
	 * LOYAL_CUSTOMER,STORE_EMPLOYEE,STORE_AFFILIATE,NORMAL_CUSTOMER
	 **/
	LOYAL_CUSTOMER(.05f), STORE_EMPLOYEE(0.3f), STORE_AFFILIATE(0.1f), NORMAL_CUSTOMER(0);

	private float customerTypeDiscount;

	/**
	 * Parameterized enum constructor.
	 * 
	 * @param customerTypeDiscount
	 *            Discount applicable for the given customer type.
	 */
	private CustomerType(float customerTypeDiscount) {
		this.customerTypeDiscount = customerTypeDiscount;
	}

	/**
	 * Method for getting the discount
	 * 
	 * @return Discount applicable for the given customer type.
	 */
	public float getCustomerTypeDiscount() {
		return customerTypeDiscount;
	}

}
