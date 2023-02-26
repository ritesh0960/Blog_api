package com.ritesh.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.loading.PrivateClassLoader;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ritesh.blog.entities.Category;
import com.ritesh.blog.payloads.CategoryDto;
import com.ritesh.blog.repositories.CategoryRepo;
import com.ritesh.blog.services.CategoryService;

import net.bytebuddy.asm.Advice.This;

import com.ritesh.blog.exceptions.*;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	// @Autowired
	// private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.categoryDtoToCategory(categoryDto);
		Category savedCategory = this.categoryRepo.save(category);
		return categoryTocategoryDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		category.setDescription(categoryDto.getDescription());
		category.setTitle(categoryDto.getTitle());
		Category savedCategory = this.categoryRepo.save(category);
		CategoryDto categorydto1 = this.categoryTocategoryDto(savedCategory);
		return categorydto1;
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
		this.categoryRepo.delete(category);

	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> Categories = this.categoryRepo.findAll();
		List<CategoryDto> allCategoryDto = Categories.stream().map(category -> this.categoryTocategoryDto(category))
				.collect(Collectors.toList());

		return allCategoryDto;
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		CategoryDto categoryDto = this.categoryTocategoryDto(category);

		return categoryDto;
	}

	private Category categoryDtoToCategory(CategoryDto categoryDto) {
		ModelMapper modelMapper = new ModelMapper();
		Category category = modelMapper.map(categoryDto, Category.class);
		return category;
	}

	private CategoryDto categoryTocategoryDto(Category category) {
		ModelMapper modelMapper = new ModelMapper();
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
