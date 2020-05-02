package com.maxim.bokarev.test3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxim.bokarev.test3.converters.OrderConverter;
import com.maxim.bokarev.test3.data.OrderData;
import com.maxim.bokarev.test3.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private OrderService orderService;
	private OrderConverter orderConverter;
	
	@PostMapping
	public OrderData createOrder(@RequestBody OrderData order) {
		return orderConverter.toData(
				orderService.create(
						orderConverter.toEntity(order)));
	}
	
	@GetMapping("/{number}")
	public OrderData readOrder(@PathVariable long number) {
		return orderConverter.toData(orderService.read(number));
	}

	@GetMapping("/{number}/invoice")
	public ResponseEntity<byte[]> printInvoice(@PathVariable long number) {
		return ResponseEntity.ok()
				.header("Content-Type", "application/pdf")
				.body(orderService.getInvoice(number));
	}

	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Autowired
	public void setOrderConverter(OrderConverter orderConverter) {
		this.orderConverter = orderConverter;
	}
	
}
