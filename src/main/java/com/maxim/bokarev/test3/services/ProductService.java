package com.maxim.bokarev.test3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maxim.bokarev.test3.entities.Product;
import com.maxim.bokarev.test3.repositories.ProductRepository;


@Service
public class ProductService {
	
	private	ProductRepository productRepository;
	
	public Iterable<Product> getProducts(){
		return productRepository.findAll();
						
	}
	
	public Product addProduct(Product product) {
		product.setSku(this.getSKU());
		return productRepository.save(product);
	}
	
	public Product getProduct(long sku) {
		Product product = productRepository.findBysku(sku);
		return product;
	}
	
	public Product updateProduct(long sku, Product srcProduct) {
		Product dstProduct = productRepository.findBysku(sku);
		dstProduct.setName(srcProduct.getName());
		dstProduct.setStockBalance(srcProduct.getStockBalance());
		dstProduct.setPrice(srcProduct.getPrice());
		return productRepository.save(dstProduct);
	}

	void reduceStockBalance(Long id, int delta) {
		Product product = productRepository.findById(id)
				.orElseThrow();
		product.setStockBalance(product.getStockBalance() - delta);
		productRepository.save(product);
	}

	private long getSKU() {
		return System.currentTimeMillis();
	}

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
}
