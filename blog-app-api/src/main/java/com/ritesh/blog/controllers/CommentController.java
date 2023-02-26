package com.ritesh.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ritesh.blog.entities.Comment;
import com.ritesh.blog.payloads.ApiResponse;
import com.ritesh.blog.payloads.CommentDto;
import com.ritesh.blog.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	//POST-create comment
	@PostMapping("/user/{userId}/post/{postId}/comment")
	public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto,
			@PathVariable Integer userId,
			@PathVariable Integer postId)
	{
		CommentDto createCommentDto = this.commentService.createComment(commentDto,userId,postId);
		return new ResponseEntity<>(createCommentDto, HttpStatus.CREATED);
	}

	
	//PUT-update comment
	@PutMapping("/comment/{commentID}")
	public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentDto commentDto,@PathVariable Integer commentID)
	{
		CommentDto updatedCommentDto = this.commentService.updateComment(commentDto, commentID);
		return new ResponseEntity<>(updatedCommentDto, HttpStatus.OK);
	}
	
	//GET-find comment
//	@GetMapping("/comment/{commentID}")
//	public ResponseEntity<CommentDto> getCommentByID(@PathVariable Integer commentID)
//	{
//		CommentDto commentDto = this.commentService.getCommentById(commentID);
//		return new ResponseEntity<>(commentDto,HttpStatus.OK);
//	}
	
	//GET-find all comment
	@GetMapping("/comment")
//	public ResponseEntity<List<CommentDto>> getAllCommentByID()
//	{
//		List<CommentDto> allCommentDtos = this.commentService.getAllComment();
//		return new ResponseEntity<>(allCommentDtos,HttpStatus.OK);
//	}
	//DELETE - delete comment
	
	@DeleteMapping("/comment/{commentID}")
	public ResponseEntity<CommentDto> deleteComment(@PathVariable Integer commentID)
	{
		this.commentService.deleteComment(commentID);
		return new ResponseEntity(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	}
	
}
