package com.pedro0505.dev.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro0505.dev.course.entities.Order;
import com.pedro0505.dev.course.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	public List<Order> findAll() {
		return this.repository.findAll();
	}

	public Order findById(Long id) {
		Optional<Order> Order = this.repository.findById(id);

		return Order.get();
	}
}
