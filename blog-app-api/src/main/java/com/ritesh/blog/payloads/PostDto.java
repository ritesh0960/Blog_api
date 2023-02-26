package com.ritesh.blog.payloads;

import java.util.ArrayList;
import java.util.List;

import com.ritesh.blog.entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {

	private String title;

	private String content;

	private String imagename;

	private UserDto user;

	private CategoryDto category;
	
	private List<Comment> comments = new ArrayList<>();

}
