package com.maxim.bokarev.test3.data;

public class ProductData {

	private long sku;
	private Long stockBalance;
	private String name;
	private double price;

	public long getSku() {
		return sku;
	}
	public void setSku(long sku) {
		this.sku = sku;
	}
	public Long getStockBalance() {
		return stockBalance;
	}
	public void setStockBalance(Long stockBalance) {
		this.stockBalance = stockBalance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
