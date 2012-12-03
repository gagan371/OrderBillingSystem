/**
 * 
 */
package com.mckinsey.billing.service;

import java.util.List;

import com.mckinsey.billing.common.BillingUtil;
import com.mckinsey.billing.common.DiscountFactory;
import com.mckinsey.billing.common.DiscountType;
import com.mckinsey.billing.discount.IDiscount;
import com.mckinsey.billing.model.OrderItem;
import com.mckinsey.billing.model.ProductOrder;

public final class BillingServiceImpl extends BillingServiceTemplate {

	private IDiscount discountObj;

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.mckinsey.billing.service.BillingServiceTemplate#
	 * applyOrderValueBasedDiscount(com.mckinsey.billing.model.ProductOrder)
	 */
	@Override
	public void applyOrderValueBasedDiscount(final ProductOrder order) {

		discountObj = DiscountFactory.getDiscountType(DiscountType.ORDER_BASED_DISCOUNT, null);

		order.applyDiscount(discountObj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mckinsey.billing.service.BillingServiceTemplate#applyUserBasedDiscount
	 * (com.mckinsey.billing.model.ProductOrder)
	 */
	@Override
	public void applyUserBasedDiscount(final ProductOrder order) {

		discountObj = DiscountFactory.getDiscountType(DiscountType.USER_BASED_DISCOUNT, order.getCustomer()
				.getCustomerType());

		final List<OrderItem> billingItemsList = order.getBillingItemsList();
		for (final OrderItem item : billingItemsList) {
			if (!item.isGroceryItem()) {
				item.applyDiscount(discountObj);
			}
		}
		calculateUserBaseDiscountValue(order);
	}

	private void calculateUserBaseDiscountValue(final ProductOrder order) {
		final Double totalOrderValue = BillingUtil.getOrderAmount(order);
		order.setOrderAmount(totalOrderValue == 0.0 ? null : totalOrderValue);
	}
}
