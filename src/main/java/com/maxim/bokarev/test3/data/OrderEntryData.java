package com.maxim.bokarev.test3.data;

public class OrderEntryData {

	private ProductData product;
	private int quantity;
	private double totalPrice;

	public ProductData getProduct() {
		return product;
	}
	public void setProduct(ProductData product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
