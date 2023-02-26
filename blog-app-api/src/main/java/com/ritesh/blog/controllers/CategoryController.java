package com.ritesh.blog.controllers;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.ritesh.blog.payloads.ApiResponse;
import com.ritesh.blog.payloads.CategoryDto;
import com.ritesh.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// POST - create category

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createcategoryDto = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createcategoryDto, HttpStatus.CREATED);
	}

	// PUT - update category

	@PutMapping("/{categoryID}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryID) {
		CategoryDto updateCategoryDto = this.categoryService.updateCategory(categoryDto, categoryID);
		return ResponseEntity.ok(updateCategoryDto);
	}

	// GET - get category

	@GetMapping("/{categoryID}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryID) {
		CategoryDto categoryDto = this.categoryService.getCategoryById(categoryID);
		return ResponseEntity.ok(categoryDto);
	}

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		return ResponseEntity.ok(this.categoryService.getAllCategory());
	}

	// Delete - delete category

	@DeleteMapping("/{categoryID}")
	public ResponseEntity<CategoryDto> deleteCategory(@PathVariable Integer categoryID) {
		this.categoryService.deleteCategory(categoryID);
		return new ResponseEntity(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
	}
}
