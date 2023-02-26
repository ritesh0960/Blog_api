package com.ritesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ritesh.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
