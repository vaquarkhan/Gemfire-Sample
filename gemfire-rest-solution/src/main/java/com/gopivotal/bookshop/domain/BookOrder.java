package com.gopivotal.bookshop.domain;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookOrder 
{
	private static final long serialVersionUID = 7526471155622776147L;

	private Integer orderNumber;
	// TODO-08: Add the appropriate JSON Formatting annotation to tell the converter how to interpret 
	//          the date from the JSON object being returned. Repeat for the shipDate field below as well.
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
	private Date orderDate;
	private float shippingCost;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
	private Date shipDate;
	private ArrayList <BookOrderItem> orderItems;
	private Integer customerNumber;
	private float totalPrice;
	
	
	
	public BookOrder() {}




	@Override
	public String toString() {
		return "BookOrder [orderNumber=" + orderNumber + ", orderDate="
				+ orderDate + ", shippingCost=" + shippingCost + ", shipDate="
				+ shipDate + ", orderItems=" + orderItems + ", customerNumber="
				+ customerNumber + ", totalPrice=" + totalPrice + "]";
	}




	public BookOrder(Integer orderNumber, Date orderDate, float shippingCost,
			Date shipDate, ArrayList<BookOrderItem> orderItems, Integer customerNumber,
			float totalPrice)
	{
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.shippingCost = shippingCost;
		this.shipDate = shipDate;
		this.orderItems = orderItems;
		this.customerNumber = customerNumber;
		this.totalPrice = totalPrice;
	}
	
	public float getTotalPrice()
	{
		return totalPrice;
	}



	public void setTotalPrice(float totalPrice)
	{
		this.totalPrice = totalPrice;
	}
	
	

	public Integer getCustomerNumber()
	{
		return customerNumber;
	}



	public void setCustomerNumber(Integer customerNumber)
	{
		this.customerNumber = customerNumber;
	}



	public void addOrderItem(BookOrderItem item)
	{
		if (orderItems == null)
		{
			orderItems = new ArrayList();
		}
		
		orderItems.add(item);
	}
	
	public Integer getOrderNumber()
	{
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber)
	{
		this.orderNumber = orderNumber;
	}
	public Date getOrderDate()
	{
		return orderDate;
	}
	public void setOrderDate(Date orderDate)
	{
		this.orderDate = orderDate;
	}
	public float getShippingCost()
	{
		return shippingCost;
	}
	public void setShippingCost(float shippingCost)
	{
		this.shippingCost = shippingCost;
	}
	public Date getShipDate()
	{
		return shipDate;
		
	}
	public void setShipDate(Date shipDate)
	{
		this.shipDate = shipDate;
	}
	public ArrayList<BookOrderItem> getOrderItems()
	{
		return orderItems;
	}
	public void setOrderItems(ArrayList<BookOrderItem> orderItems)
	{
		this.orderItems = orderItems;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((orderNumber == null) ? 0 : orderNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookOrder other = (BookOrder) obj;
		if (orderNumber == null)
		{
			if (other.orderNumber != null)
				return false;
		} else if (!orderNumber.equals(other.orderNumber))
			return false;
		return true;
	}
}
