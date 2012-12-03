package com.mckinsey.billing.model;

import java.io.Serializable;

import com.mckinsey.billing.common.CategoryType;
import com.mckinsey.billing.discount.IDiscount;

/**
 * Class holding the detail related to individual <b> Item</b> that user has in
 * his Order.
 * 
 */

public final class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/** category type of the order which could be Grocery OR Non Grocery. **/
	private CategoryType itemCategory;

	/** Item rate for this item. **/
	private Double itemRate;

	/** Item quantity **/
	private Integer itemQuantity;

	/**
	 * Item description for this item. It could be any thing like
	 * 'Lettuce','Shoes'.
	 **/
	private String itemDescription;

	/**
	 * @return the itemCategory
	 */
	public CategoryType getItemCategory() {
		return itemCategory;
	}

	/**
	 * @param itemCategory
	 *            the itemCategory to set
	 */
	public void setItemCategory(CategoryType itemCategory) {
		this.itemCategory = itemCategory;
	}

	/**
	 * @return the itemRate
	 */
	public Double getItemRate() {
		return itemRate;
	}

	/**
	 * @param itemRate
	 *            the itemRate to set
	 */
	public void setItemRate(Double itemRate) {
		this.itemRate = itemRate;
	}

	/**
	 * @return the itemQuantity
	 */
	public Integer getItemQuantity() {
		return itemQuantity;
	}

	/**
	 * @param itemQuantity
	 *            the itemQuantity to set
	 */
	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * @param itemDescription
	 *            the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	/**
	 * Method for applying the discount on the given Item Object based on the
	 * discount type
	 * 
	 * @param discountType
	 */
	public void applyDiscount(IDiscount discountType) {
		double revisedAmount = discountType.applyDiscount(this.getItemRate());
		this.setItemRate(revisedAmount);
	}

	public boolean isGroceryItem() {
		return CategoryType.GROCERY == this.getItemCategory();
	}

}
