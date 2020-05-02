package com.maxim.bokarev.test3.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxim.bokarev.test3.converters.ProductConverter;
import com.maxim.bokarev.test3.data.ProductData;
import com.maxim.bokarev.test3.services.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {
	

	private ProductService productService;
	private ProductConverter productConverter;
	
	@GetMapping
	public Collection<ProductData> getProducts(){
		return productConverter.toDataCollection(productService.getProducts());
	}
	
	@PostMapping
	public ProductData addProduct(@RequestBody ProductData product) {
		return productConverter.toData(
				productService.addProduct(
						productConverter.toEntity(product)));
	}
	
	@GetMapping("/{sku}")
	public ProductData getProduct(@PathVariable("sku") long sku) {
		return productConverter.toData(productService.getProduct(sku));
	}
	
	@PostMapping("/{sku}")
	public ProductData overrideProduct(@PathVariable("sku") long sku, @RequestBody ProductData product) {
		return productConverter.toData(
				productService.updateProduct(sku,
						productConverter.toEntity(product)));
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setProductConverter(ProductConverter productConverter) {
		this.productConverter = productConverter;
	}
	

}
