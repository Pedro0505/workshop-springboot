package com.pedro0505.dev.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedro0505.dev.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
