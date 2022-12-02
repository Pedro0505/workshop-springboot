package com.pedro0505.dev.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro0505.dev.course.entities.Product;
import com.pedro0505.dev.course.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public List<Product> findAll() {
		return this.repository.findAll();
	}

	public Product findById(Long id) {
		Optional<Product> category = this.repository.findById(id);

		return category.get();
	}
}
