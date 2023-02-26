package com.ritesh.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ritesh.blog.entities.Comment;
import com.ritesh.blog.entities.Post;
import com.ritesh.blog.entities.User;

public interface commentRepo extends JpaRepository<Comment, Integer> {
          
	List<Comment> findByPost(Post post);
	
	List<Comment> findByUsercomment(User user);
	
	
}
