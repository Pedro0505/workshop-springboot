package com.pedro0505.dev.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro0505.dev.course.entities.Category;
import com.pedro0505.dev.course.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public List<Category> findAll() {
		return this.repository.findAll();
	}

	public Category findById(Long id) {
		Optional<Category> category = this.repository.findById(id);

		return category.get();
	}
}
