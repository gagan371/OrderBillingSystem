/**
 * 
 */
package com.mckinsey.billing.data;

import java.util.ArrayList;
import java.util.List;

import com.mckinsey.billing.common.CategoryType;
import com.mckinsey.billing.model.OrderItem;

/**
 * Factory class for creating list of {@link OrderItem}
 * 
 */
public class OrderItemFactory {

	/**
	 * Method for creating List of OrderItems of category type <b>Grocery</b>
	 * only {@link CategoryType#GROCERY}.
	 * 
	 * @param isGroceriesValueMoreThan100$
	 *            Parameter controlling whether to create orders having order
	 *            value > 100$.
	 * @return List of {@link OrderItem}.
	 */
	public static List<OrderItem> createGroceryItems(final boolean isGroceriesValueMoreThan100$) {

		final List<OrderItem> groceriesItem = new ArrayList<OrderItem>();

		final OrderItem orderItem1 = new OrderItem();
		orderItem1.setItemCategory(CategoryType.GROCERY);
		orderItem1.setItemDescription("Potatoes");
		orderItem1.setItemQuantity(isGroceriesValueMoreThan100$ == true ? 20 : 1);
		orderItem1.setItemRate(2.50);

		final OrderItem orderItem2 = new OrderItem();
		orderItem2.setItemCategory(CategoryType.GROCERY);
		orderItem2.setItemDescription("Brocelli");
		orderItem2.setItemQuantity(isGroceriesValueMoreThan100$ == true ? 20 : 1);
		orderItem2.setItemRate(25.00);

		final OrderItem orderItem3 = new OrderItem();
		orderItem3.setItemCategory(CategoryType.GROCERY);
		orderItem3.setItemDescription("WaterMellon");
		orderItem3.setItemQuantity(isGroceriesValueMoreThan100$ == true ? 20 : 1);
		orderItem3.setItemRate(15.00);

		groceriesItem.add(orderItem1);
		groceriesItem.add(orderItem2);
		groceriesItem.add(orderItem3);

		return groceriesItem;
	}

	/**
	 * Method for creating orders containing items of category type
	 * <b>NonGrocery</b> {@link CategoryType#NONGROCERY} and <b>Grocery</b>
	 * {@link CategoryType#GROCERY}
	 * 
	 * @return List of {@link OrderItem}.
	 */
	public static List<OrderItem> createOrderWithGroceryAndNonGroceryItems() {
		final List<OrderItem> orderItemList1 = createGroceryItems(false);
		final List<OrderItem> orderItemList2 = createNonGroceryItems(false);
		orderItemList1.addAll(orderItemList2);
		return orderItemList1;

	}

	/**
	 * Method for creating orders containing items of category type
	 * <b>NonGrocery</b> only {@link CategoryType#NONGROCERY}.
	 * 
	 * @param orderValueGreaterThan100$
	 *            Parameter controlling whether to create orders having order
	 *            value > 100$.
	 * @return List of {@link OrderItem}.
	 */
	public static List<OrderItem> createNonGroceryItems(boolean orderValueGreaterThan100$) {
		final List<OrderItem> orderItemsList = new ArrayList<OrderItem>();
		final OrderItem orderItem1 = new OrderItem();
		orderItem1.setItemCategory(CategoryType.NONGROCERY);
		orderItem1.setItemDescription("Chairs");
		orderItem1.setItemQuantity(orderValueGreaterThan100$ == true ? 10 : 1);
		orderItem1.setItemRate(25.00);

		final OrderItem orderItem2 = new OrderItem();
		orderItem2.setItemCategory(CategoryType.NONGROCERY);
		orderItem2.setItemDescription("Table");
		orderItem2.setItemQuantity(orderValueGreaterThan100$ == true ? 10 : 1);
		orderItem2.setItemRate(40.00);

		orderItemsList.add(orderItem1);
		orderItemsList.add(orderItem2);
		return orderItemsList;
	}

}