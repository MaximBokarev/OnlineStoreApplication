package com.maxim.bokarev.test3.converters;

import org.springframework.stereotype.Component;

import com.maxim.bokarev.test3.data.ProductData;
import com.maxim.bokarev.test3.entities.Product;

@Component
public class ProductConverter implements Converter<Product, ProductData> {

	@Override
	public ProductData toData(Product entity) {
		ProductData data = new ProductData();
		data.setName(entity.getName());
		data.setPrice(entity.getPrice());
		data.setSku(entity.getSku());
		data.setStockBalance(entity.getStockBalance());
		return data;
	}

	@Override
	public Product toEntity(ProductData data) {
		Product entity = new Product();
		entity.setSku(data.getSku());
		entity.setName(data.getName());
		entity.setPrice(data.getPrice());
		entity.setStockBalance(data.getStockBalance());
		return entity;
	}

}
