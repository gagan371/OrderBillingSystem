/**
 * 
 */
package com.mckinsey.billing.common;

import java.util.List;

import com.mckinsey.billing.model.OrderItem;
import com.mckinsey.billing.model.ProductOrder;

/**
 * Util class for providing utility functions.
 */
public final class BillingUtil {

	/**
	 * Method for getting the total order amount.
	 * 
	 * @param productOrder
	 *            ProductOrder
	 * @return Total order amount based on the items in the order
	 */
	public static Double getOrderAmount(final ProductOrder productOrder) {
		final List<OrderItem> billingItemsList = productOrder.getBillingItemsList();
		Double total = 0.0;
		if (billingItemsList != null && !billingItemsList.isEmpty()) {
			for (final OrderItem item : billingItemsList) {
				total += (item.getItemQuantity() * item.getItemRate());
			}
		}
		return total;
	}

}
