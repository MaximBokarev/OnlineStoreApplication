package com.maxim.bokarev.test3.data;

import java.util.Set;

public class OrderData {

	private long number;
	private Set<OrderEntryData> entries;
	private double orderTotal;

	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public Set<OrderEntryData> getEntries() {
		return entries;
	}
	public void setEntries(Set<OrderEntryData> entries) {
		this.entries = entries;
	}
	public double getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

}
