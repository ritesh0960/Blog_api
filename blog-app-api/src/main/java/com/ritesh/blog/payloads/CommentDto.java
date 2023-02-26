package com.ritesh.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter

public class CommentDto {

	private int comment_id;
	
	
	@NotEmpty(message = "Comment cannot be empty")
	@Size(min = 10, message = "comment should be minimum of 10 chars")
	private String comment;
	
	
	
}
