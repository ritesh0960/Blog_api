package com.ritesh.blog.services.impl;

//import java.awt.print.Pageable;
import java.util.List;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ritesh.blog.entities.Category;
import com.ritesh.blog.entities.Post;
import com.ritesh.blog.entities.User;
import com.ritesh.blog.exceptions.ResourceNotFoundException;
import com.ritesh.blog.payloads.GetAllPostResponse;
import com.ritesh.blog.payloads.PostDto;
import com.ritesh.blog.repositories.CategoryRepo;
import com.ritesh.blog.repositories.PostRepo;
import com.ritesh.blog.repositories.UserRepo;
import com.ritesh.blog.services.PostService;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;
import net.bytebuddy.asm.Advice.This;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.parser.Part.IgnoreCaseType;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userID, Integer categoryID) {
		User user = this.userRepo.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userID));
		Category category = this.categoryRepo.findById(categoryID)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryID));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setUser(user);
		post.setCategory(category);
		Post savedPost = this.postRepo.save(post);

		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postID) {

		Post post = this.postRepo.findById(postID)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postID));
		post.setContent(postDto.getContent());
		post.setImagename(postDto.getImagename());
		post.setTitle(postDto.getTitle());
		Post savedPost = this.postRepo.save(post);
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postID) {
		Post post = this.postRepo.findById(postID)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postID));
		this.postRepo.delete(post);

	}

	@Override
	public GetAllPostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		GetAllPostResponse getAllPostResponse = new GetAllPostResponse();

		org.springframework.data.domain.Sort sort = null;
		if (sortDir.equalsIgnoreCase("asc")) {
			sort = org.springframework.data.domain.Sort.by(sortBy).ascending();

		} else {
			{
				sort = org.springframework.data.domain.Sort.by(sortBy).descending();
			}
		}

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> posts = pagePost.getContent();
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		getAllPostResponse.setContent(postDtos);
		getAllPostResponse.setPageSize(pagePost.getSize());
		getAllPostResponse.setPageNumber(pagePost.getNumber());
		getAllPostResponse.setTotalPages(pagePost.getTotalPages());
		getAllPostResponse.setLastPage(pagePost.isLast());
		getAllPostResponse.setTotalPost(pagePost.getTotalElements());
		return getAllPostResponse;
	}

	@Override
	public PostDto getPostByID(Integer postID) {
		Post post = this.postRepo.findById(postID)
				.orElseThrow(() -> new ResourceNotFoundException("post", "id", postID));
		
		
		return this.modelMapper.map(post, PostDto.class);

	}

	@Override
	public List<PostDto> getPostByUser(Integer userID) {
		User user = this.userRepo.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userID));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryID) {
		Category category = this.categoryRepo.findById(categoryID)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryID));
		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

}
