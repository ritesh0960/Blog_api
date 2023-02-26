package com.ritesh.blog.services;

import java.util.List;

import com.ritesh.blog.entities.Post;
import com.ritesh.blog.payloads.GetAllPostResponse;
import com.ritesh.blog.payloads.PostDto;

public interface PostService {

	// create post
	PostDto createPost(PostDto postDto, Integer userID, Integer categoryID);

	// update post
	PostDto updatePost(PostDto postDto, Integer postID);

	// delete post
	void deletePost(Integer postID);

	// All Post
	GetAllPostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	// postById
	PostDto getPostByID(Integer postID);

	// postByuser
	List<PostDto> getPostByUser(Integer userID);

	// postByCategory
	List<PostDto> getPostByCategory(Integer categoryID);

	// search post
	List<PostDto> searchPosts(String keyword);

}
