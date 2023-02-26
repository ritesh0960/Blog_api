package com.ritesh.blog.payloads;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ritesh.blog.entities.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor

@Setter
@Getter

public class UserDto {

	private int id;

	@NotEmpty
	@Size(min = 4, message = "name should be minimum of 4 charcters")
	private String name;

	@NotEmpty
	@Email(message = "Email format is not correct")
	private String email;

	@NotEmpty
	@Size(min = 4, max = 10, message = "Password must be between 3 to 10 chars")
	private String password;

	@NotEmpty
	private String about;
	
	private List<Post> posts = new ArrayList<>();
	
	
}
