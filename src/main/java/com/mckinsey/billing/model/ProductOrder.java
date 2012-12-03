/**
 * 
 */
package com.mckinsey.billing.model;

import java.util.Date;
import java.util.List;

import com.mckinsey.billing.common.BillingUtil;
import com.mckinsey.billing.discount.IDiscount;

/**
 * Class representing the User <b>Order</b> . <br>
 * It consists of several {@link OrderItem}.<br>
 * It holds information related to the {@link Customer} who has ordered it.
 * 
 */
public final class ProductOrder {

	private Customer customer;

	private List<OrderItem> billingItemsList;

	private Double orderAmount;

	private Double netPayableAmount;

	private Date orderDate;

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the billingItemsList
	 */
	public List<OrderItem> getBillingItemsList() {
		return billingItemsList;
	}

	/**
	 * @param billingItemsList
	 *            the billingItemsList to set
	 */
	public void setBillingItemsList(final List<OrderItem> billingItemsList) {
		this.billingItemsList = billingItemsList;
	}

	/**
	 * @return the orderAmount
	 */
	public Double getOrderAmount() {
		Double total = BillingUtil.getOrderAmount(this);
		setOrderAmount(total);
		return this.orderAmount;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return (Date) orderDate.clone();
	}

	/**
	 * @param orderAmount
	 *            the orderAmount to set
	 */
	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * @param orderDate
	 *            the orderDate to set
	 */
	public void setOrderDate(final Date orderDate) {
		this.orderDate = orderDate;
	}

	public void applyDiscount(final IDiscount discount) {
		final double reducedAmount = discount.applyDiscount(this.orderAmount);
		this.setNetPayableAmount(reducedAmount);
	}

	/**
	 * @return the netPayableAmount
	 */
	public Double getNetPayableAmount() {
		return new Double(netPayableAmount);
	}

	/**
	 * @param netPayableAmount
	 *            the netPayableAmount to set
	 */
	private void setNetPayableAmount(final Double netPayableAmount) {
		this.netPayableAmount = netPayableAmount;
	}

}
