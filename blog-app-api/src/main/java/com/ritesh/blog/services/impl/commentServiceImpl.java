package com.ritesh.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.engine.query.spi.sql.NativeSQLQueryCollectionReturn;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ritesh.blog.entities.Comment;
import com.ritesh.blog.entities.Post;
import com.ritesh.blog.entities.User;
import com.ritesh.blog.exceptions.ResourceNotFoundException;
import com.ritesh.blog.payloads.CommentDto;
import com.ritesh.blog.repositories.PostRepo;
import com.ritesh.blog.repositories.UserRepo;
import com.ritesh.blog.repositories.commentRepo;
import com.ritesh.blog.services.CommentService;

import net.bytebuddy.asm.Advice.This;

@Service
public class commentServiceImpl implements CommentService {

	@Autowired
	private commentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Override
	public CommentDto createComment(CommentDto commentDto,Integer userId, Integer postId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUsercomment(user);
		
		Comment savedComment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer commentID) {
		
		Comment comment = this.commentRepo.findById(commentID).orElseThrow(()-> new ResourceNotFoundException("comment", "id", commentID));
		Comment comment2 = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setComment(comment2.getComment());
		Comment updatedComment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(updatedComment, CommentDto.class);
	}

//	@Override
//	public CommentDto getCommentById(Integer commentID) {
//		
//		Comment comment = this.commentRepo.findById(commentID).orElseThrow(()-> new ResourceNotFoundException("comment", "id", commentID));
//	    
//		return this.modelMapper.map(comment, CommentDto.class);
//	   
//	}

//	@Override
//	public List<CommentDto> getAllComment() {
//		List<Comment> comments = this.commentRepo.findAll();
//		List<CommentDto> allCommentDtos = comments.stream().map(comment->this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
//		return allCommentDtos;
//	}

	@Override
	public void deleteComment(@PathVariable Integer commentID) {
		Comment comment = this.commentRepo.findById(commentID).orElseThrow(()-> new ResourceNotFoundException(
				"comment", "id", commentID));
		this.commentRepo.delete(comment);

	}
	
//	public CommentDto commentTocommentDTO(Comment comment) {
//		ModelMapper modelMapper = new ModelMapper();
//		CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
//		return commentDto;
//	}

	
//	public Comment commentDTOTocomment(CommentDto commentDto) {
//		ModelMapper modelMapper = new ModelMapper();
//		Comment comment = modelMapper.map(commentDto, Comment.class);
//		return comment;
//	}

//	@Override
//	public List<CommentDto> getAllCommentByPost(Integer postID) {
//		
//	    Post post = this.postRepo.findById(postID).orElseThrow(()-> new ResourceNotFoundException("post", "id", postID));
//	    List<Comment> comments = this.commentRepo.findByPost(post);
//	    List<CommentDto> commentDtos = comments.stream().map(comment->this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
//	    
//		
//		return commentDtos;
//	}

//	@Override
//	public List<CommentDto> getAllCommentByUser(Integer userID) {
//		
//		User user = this.userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("user", "id", userID));
//		
//		List<Comment> comments = this.commentRepo.findByUsercomment(user);
//		
//		List<CommentDto> commentDtos = comments.stream().map(comment->this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
//		
//		
//		return commentDtos;
//	}
}
