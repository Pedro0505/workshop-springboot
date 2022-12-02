package com.pedro0505.dev.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedro0505.dev.course.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
