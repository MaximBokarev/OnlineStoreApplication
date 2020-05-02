package com.maxim.bokarev.test3.repositories;

import org.springframework.data.repository.CrudRepository;

import com.maxim.bokarev.test3.entities.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

	Order findByNumber(Long number);

}
