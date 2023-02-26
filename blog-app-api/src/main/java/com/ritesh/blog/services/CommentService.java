package com.ritesh.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ritesh.blog.entities.Comment;
import com.ritesh.blog.payloads.CommentDto;

public interface CommentService {
	
	public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);
	
	public CommentDto updateComment(CommentDto commentDto, Integer commentID);
	
	//public CommentDto getCommentById(Integer commentID);
	
	//public List<CommentDto> getAllComment();
	
	public void deleteComment(Integer commentID);
	
	//public List<CommentDto> getAllCommentByPost(Integer postID);
	
	//public List<CommentDto> getAllCommentByUser(Integer userID);

}
