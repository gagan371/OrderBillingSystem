/**
 * 
 */
package com.mckinsey.billing.service;

import com.mckinsey.billing.model.ProductOrder;

/**
 * Billing Service interface representing the API method available to end user
 * for calculating the Net payable amount that a user has to pay after
 * considering various levels of discounts. *
 */
public interface BillingService {

	/**
	 * Method for calculating the net payable amount that the Customer has to
	 * pay ,after applying all the levels of discount services.
	 * 
	 * @param order
	 *            {@link ProductOrder} for which net payable amount has to be
	 *            calculated.
	 * @return Net Payable Amount that customer has to pay.
	 */
	public Double getNetPayableAmount(final ProductOrder order);
}
