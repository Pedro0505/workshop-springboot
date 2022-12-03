package com.pedro0505.dev.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro0505.dev.course.entities.User;
import com.pedro0505.dev.course.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return this.repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> user = this.repository.findById(id);
		
		return user.get();
	}
	
	public User insert(User user) {
		return this.repository.save(user);
	}
	
	public void delete(Long id) {
		this.repository.deleteById(id);
	}
	
	public User update(Long id, User user) {
		User entity = this.repository.getReferenceById(id);
		
		this.updateDate(entity, user);
		
		return this.repository.save(entity);
	}

	private void updateDate(User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setPhone(user.getPhone());
	}
}
