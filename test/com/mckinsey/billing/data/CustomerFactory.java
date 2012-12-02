/**
 * 
 */
package com.mckinsey.billing.data;

import com.mckinsey.billing.common.CustomerType;
import com.mckinsey.billing.model.Customer;

/**
 * Factory class for creating different types of Customers.
 * {@link CustomerType#LOYAL_CUSTOMER},{@link CustomerType#NORMAL_CUSTOMER},
 * {@link CustomerType#STORE_AFFILIATE},{@link CustomerType#STORE_EMPLOYEE}
 * 
 * 
 */
public final class CustomerFactory {

	/**
	 * Method for creating store employee.
	 * 
	 * @return Customer of type {@link CustomerType#STORE_EMPLOYEE}.
	 */
	public static Customer createEmployeeCustomer() {

		final Customer storeEmployee = new Customer(CustomerType.STORE_EMPLOYEE);
		return storeEmployee;

	}

	/**
	 * Method for creating store affiliate.
	 * 
	 * @return Customer of type {@link CustomerType#STORE_AFFILIATE}.
	 */
	public static Customer createAffiliatedCustomer() {
		final Customer storeAffiliated = new Customer(CustomerType.STORE_AFFILIATE);
		return storeAffiliated;
	}

	/**
	 * Method for creating Normal customer.
	 * 
	 * @return Customer of type {@link CustomerType#NORMAL_CUSTOMER}.
	 */
	public static Customer createNormalCustomer() {
		final Customer normalCustomer = new Customer(CustomerType.NORMAL_CUSTOMER);
		return normalCustomer;
	}

	/**
	 * Method for creating Loyalty customer (have been shopping with store for
	 * more than 2 years).
	 * 
	 * @return Customer of type {@link CustomerType#LOYAL_CUSTOMER}.
	 */
	public static Customer createLoyaltyCustomer() {
		final Customer loyaltyCustomer = new Customer(CustomerType.LOYAL_CUSTOMER);
		return loyaltyCustomer;
	}
}
