package com.ritesh.blog.services;

import java.util.List;

import com.ritesh.blog.payloads.CategoryDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);

	void deleteCategory(int categoryId);

	List<CategoryDto> getAllCategory();

	CategoryDto getCategoryById(int categoryId);

}
