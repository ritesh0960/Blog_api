package com.ritesh.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ritesh.blog.entities.User;

//jparepository provides solutions or functions to perform all the operation on the database
public interface UserRepo extends JpaRepository<User, Integer> {
        
	Optional<User> findByEmail(String email);
}
