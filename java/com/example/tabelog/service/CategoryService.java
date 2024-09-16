package com.example.tabelog.service;

import java.util.List;
import java.util.Optional;

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
}
