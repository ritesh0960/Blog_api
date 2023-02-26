package com.ritesh.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class GetAllPostResponse {

	private List<PostDto> content;

	private int pageNumber;

	private int pageSize;

	private long totalPost;

	private int totalPages;

	private boolean isLastPage;

}
