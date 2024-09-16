package com.example.tabelog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tabelog.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Page<Category> findByNameContaining(String keyword, Pageable pageable);

	Optional<Category> findById(Long categoryId);

	Page<Category> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

}
