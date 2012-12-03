package com.mckinsey.billing.data;

import java.util.Date;

import com.mckinsey.billing.common.CategoryType;
import com.mckinsey.billing.model.ProductOrder;

/**
 * 
 *Factory class for creating {@link ProductOrder}. <br>
 *Order Items can be of type {@link OrderFactory CategoryType#GROCERY} and
 * {@link CategoryType#NONGROCERY}.
 * 
 */
public final class OrderFactory {

	/**
	 * Method for creating Empty Order i.e null order.
	 */
	public static ProductOrder createEmptyOrder() {
		return null;
	}

	/**
	 * Method for creating orders containing items of category type
	 * <b>Grocery</b> only {@link CategoryType#GROCERY}.
	 * 
	 * @param isGroceriesValueMoreThan100$
	 *            Parameter controlling whether to create orders having order value
	 *            > 100$.
	 * @return @ link ProductOrder} containing items of category type
	 *         {@link CategoryType#GROCERY}
	 */
	public static ProductOrder createOnlyGroceriesBasedOrder(final boolean isGroceriesValueMoreThan100$) {
		final ProductOrder groceriesOrder = new ProductOrder();
		groceriesOrder.setBillingItemsList(OrderItemFactory.createGroceryItems(isGroceriesValueMoreThan100$));
		groceriesOrder.setOrderDate(new Date());
		return groceriesOrder;
	}

	/**
	 * Method for creating orders containing items of category type
	 * <b>NonGrocery</b> only {@link CategoryType#NONGROCERY}.
	 * 
	 * @param isGroceriesValueMoreThan100$
	 *            Parameter controlling whether to create orders having order
	 *            value > 100$.
	 * @return @ link ProductOrder} containing items of category type
	 *         {@link CategoryType#NONGROCERY}
	 */
	public static ProductOrder createOrderWithOnlyNonGroceryItems(final boolean orderValueGreaterThan100$) {
		final ProductOrder orderWithGroceryAndNonGroceryItems = new ProductOrder();
		orderWithGroceryAndNonGroceryItems.setBillingItemsList(OrderItemFactory
				.createNonGroceryItems(orderValueGreaterThan100$));
		orderWithGroceryAndNonGroceryItems.setOrderDate(new Date());
		return orderWithGroceryAndNonGroceryItems;
	}

	/**
	 * Method for creating orders containing items of category type
	 * <b>NonGrocery</b> {@link CategoryType#NONGROCERY} and <b>Grocery</b>
	 * {@link CategoryType#GROCERY}.
	 * 
	 * @return {@link ProductOrder} containing items of category type
	 *         {@link CategoryType#NONGROCERY} and {@link CategoryType#GROCERY}.
	 * 
	 */
	public static ProductOrder createOrderWithGroceryAndNonGroceryItems() {
		final ProductOrder orderWithGroceryAndNonGroceryItems = new ProductOrder();
		orderWithGroceryAndNonGroceryItems.setBillingItemsList(OrderItemFactory
				.createOrderWithGroceryAndNonGroceryItems());
		orderWithGroceryAndNonGroceryItems.setOrderDate(new Date());
		return orderWithGroceryAndNonGroceryItems;
	}

}