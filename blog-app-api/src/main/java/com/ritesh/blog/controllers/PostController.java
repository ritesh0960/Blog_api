package com.ritesh.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ritesh.blog.entities.Post;
import com.ritesh.blog.payloads.ApiResponse;
import com.ritesh.blog.payloads.GetAllPostResponse;
import com.ritesh.blog.payloads.PostDto;
import com.ritesh.blog.services.FileService;
import com.ritesh.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	// Add post
	@PostMapping("/user/{userID}/category/{categoryID}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userID,
			@PathVariable Integer categoryID) {
		PostDto postDto2 = this.postService.createPost(postDto, userID, categoryID);
		return new ResponseEntity<>(postDto2, HttpStatus.CREATED);

	}

	// Update Post

	@PutMapping("/post/{postID}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postID) {
		PostDto updatedPost = this.postService.updatePost(postDto, postID);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);

	}

	// Delete Post
	@DeleteMapping("/post/{postID}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postID) {
		this.postService.deletePost(postID);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
	}

	// Get Post By postID
	@GetMapping("/post/{postID}")
	public ResponseEntity<PostDto> getPostByID(@PathVariable Integer postID) {
		PostDto postDto = this.postService.getPostByID(postID);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}

	// Get All Post
	@GetMapping("/post")
	public ResponseEntity<GetAllPostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = "8", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		GetAllPostResponse getAllPostResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<GetAllPostResponse>(getAllPostResponse, HttpStatus.OK);
	}

	// Get Post by user
	@GetMapping("/user/{userID}/post")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userID) {
		List<PostDto> postsDtos = this.postService.getPostByUser(userID);
		return new ResponseEntity<List<PostDto>>(postsDtos, HttpStatus.OK);
	}

	// Get post By category
	@GetMapping("/category/{categoryID}/post")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryID) {
		List<PostDto> postDtos = this.postService.getPostByCategory(categoryID);
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}

	// Get Post by search string
	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostbyTitle(@PathVariable String keyword) {
		List<PostDto> postDtos = this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}
	
	//post image upload
	
	@PostMapping("/post/image/upload/{postID}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile file,
			@PathVariable Integer postID) throws IOException{
		
		PostDto postDto =  this.postService.getPostByID(postID);
		
		String filename = this.fileService.uploadImage(path, file);
		
		postDto.setImagename(filename);
		PostDto savedPostDto = this.postService.updatePost(postDto, postID);
		
		return new ResponseEntity<PostDto>(savedPostDto,HttpStatus.OK);
		
		}
	
	//method to serve file
	
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);	
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}


}
