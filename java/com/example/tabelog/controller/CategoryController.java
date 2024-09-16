package com.example.tabelog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tabelog.entity.Category;
import com.example.tabelog.entity.House;
import com.example.tabelog.form.CategoryEditForm;
import com.example.tabelog.form.CategoryRegisterForm;
import com.example.tabelog.service.CategoryService;
import com.example.tabelog.service.HouseService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

	private final CategoryService categoryService;
	private final HouseService houseService;

	@Autowired
	public CategoryController(CategoryService categoryService, HouseService houseService) {
		this.categoryService = categoryService;
		this.houseService = houseService;
	}

	@GetMapping
	public String listCategories(Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "admin/categories/category";
	}

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("categoryRegisterForm", new CategoryRegisterForm());
		return "admin/categories/register";
	}

	@PostMapping("/register")
	public String registerCategory(
			@Valid @ModelAttribute("categoryRegisterForm") CategoryRegisterForm categoryRegisterForm,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin/categories/register";
		}
		Category category = new Category();
		category.setName(categoryRegisterForm.getName());
		categoryService.save(category);
		return "redirect:/admin/categories";
	}

	@GetMapping("/show/{id}/edit")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		Optional<Category> category = categoryService.findById(id);
		if (category.isPresent()) {
			CategoryEditForm categoryEditForm = new CategoryEditForm();
			categoryEditForm.setName(category.get().getName());
			model.addAttribute("categoryEditForm", categoryEditForm);
			model.addAttribute("categoryId", id);
			return "admin/categories/show/edit";
		} else {
			return "redirect:/admin/categories";
		}
	}

	@PostMapping("/show/{id}/edit")
	public String editCategory(@PathVariable("id") Long id,
			@Valid @ModelAttribute("categoryEditForm") CategoryEditForm categoryEditForm, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("categoryId", id);
			return "admin/categories/show/edit";
		}
		Optional<Category> categoryOptional = categoryService.findById(id);
		if (categoryOptional.isPresent()) {
			Category category = categoryOptional.get();
			category.setName(categoryEditForm.getName());
			categoryService.save(category);
		}
		return "redirect:/admin/categories";
	}

	@GetMapping("/show/{id}")
	public String showCategory(@PathVariable Long id, Model model) {
		Optional<Category> categoryOptional = categoryService.findById(id);
		if (!categoryOptional.isPresent()) {
			return "redirect:/admin/categories"; // カテゴリが見つからない場合
		}
		Category category = categoryOptional.get();
		List<House> houses = houseService.findByCategoryId(id);

		model.addAttribute("category", category);
		model.addAttribute("houses", houses);

		return "admin/categories/show";
	}

	@PostMapping("/delete/{id}")
	public String deleteCategory(@PathVariable("id") Long id) {
		categoryService.deleteById(id);
		return "redirect:/admin/categories";
	}
}
