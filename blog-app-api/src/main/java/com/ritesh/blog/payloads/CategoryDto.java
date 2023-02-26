package com.ritesh.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor

@Setter
@Getter
public class CategoryDto {

	private int category_id;

	@NotEmpty(message = "Cannot be empty")
	@Size(min = 10, max = 100, message = "Mesaage should be of atleast 10 chars")
	private String title;

	@Size(min = 10, message = "Atleast 10 characters required")
	private String description;

}
