package com.ritesh.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ritesh.blog.entities.Category;
import com.ritesh.blog.entities.Post;
import com.ritesh.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByCategory(Category category);

	List<Post> findByUser(User user);

	List<Post> findByTitleContaining(String keyword);

}
