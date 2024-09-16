package com.example.tabelog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.tabelog.entity.Category;
import com.example.tabelog.repository.CategoryRepository;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Optional<Category> findById(Long id) {
		return categoryRepository.findById(id);
	}

	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
	}

	public Page<Category> searchCategories(String keyword, Pageable pageable) {
		if (keyword == null || keyword.isEmpty()) {
			return categoryRepository.findAll(pageable);
		} else {
			return categoryRepository.findByNameContainingIgnoreCase(keyword, pageable);
		}
	}

}
