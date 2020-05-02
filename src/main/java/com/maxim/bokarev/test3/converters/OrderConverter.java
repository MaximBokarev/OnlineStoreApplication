package com.maxim.bokarev.test3.converters;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maxim.bokarev.test3.data.OrderData;
import com.maxim.bokarev.test3.data.OrderEntryData;
import com.maxim.bokarev.test3.entities.Order;
import com.maxim.bokarev.test3.entities.OrderEntry;

@Component
public class OrderConverter implements Converter<Order, OrderData> {

	private ProductConverter productConverter;

	@Override
	public OrderData toData(Order entity) {
		OrderData data = new OrderData();
		data.setNumber(entity.getNumber());
		data.setOrderTotal(entity.getOrderTotal());
		data.setEntries(new HashSet<>());

		for(OrderEntry entityEntry: entity.getEntries()) {
			OrderEntryData dataEntry = new OrderEntryData();
			dataEntry.setQuantity(entityEntry.getQuantity());
			dataEntry.setTotalPrice(entityEntry.getTotalPrice());
			dataEntry.setProduct(productConverter.toData(entityEntry.getProduct()));
			data.getEntries().add(dataEntry);
		}

		return data;
	}

	@Override
	public Order toEntity(OrderData data) {
		Order entity = new Order();
		entity.setOrderTotal(data.getOrderTotal());
		entity.setEntries(new HashSet<>());

		for(OrderEntryData dataEntry: data.getEntries()) {
			OrderEntry entityEntry = new OrderEntry();
			entityEntry.setQuantity(dataEntry.getQuantity());
			entityEntry.setTotalPrice(dataEntry.getTotalPrice());
			entityEntry.setProduct(productConverter.toEntity(dataEntry.getProduct()));
			entity.getEntries().add(entityEntry);
		}

		return entity;
	}

	@Autowired
	public void setProductConverter(ProductConverter productConverter) {
		this.productConverter = productConverter;
	}

}
