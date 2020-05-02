package com.maxim.bokarev.test3.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maxim.bokarev.test3.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	Product findBysku(Long sku);

}
