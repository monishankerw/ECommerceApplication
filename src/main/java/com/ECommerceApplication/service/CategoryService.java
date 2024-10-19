package com.ECommerceApplication.service;


import com.ECommerceApplication.dto.CategoryDTO;
import com.ECommerceApplication.entity.Category;
import com.ECommerceApplication.payload.CategoryResponse;

public interface CategoryService {

	CategoryDTO createCategory(Category category);

	CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	CategoryDTO updateCategory(Category category, Long categoryId);

	String deleteCategory(Long categoryId);
}