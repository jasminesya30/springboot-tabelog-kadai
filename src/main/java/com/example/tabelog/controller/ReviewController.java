package com.example.tabelog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tabelog.entity.Review;
import com.example.tabelog.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@PostMapping
	public ResponseEntity<Review> createReview(@Valid @RequestBody Review review) {
		Review savedReview = reviewService.saveReview(review);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
	}

	@GetMapping("/house/{houseId}")
	public ResponseEntity<List<Review>> getReviewsByHouseId(@PathVariable Integer houseId) {
		List<Review> reviews = reviewService.getReviewsByHouseId(houseId);
		return ResponseEntity.ok(reviews);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteReview(@PathVariable Integer id) {
		try {
			reviewService.deleteReview(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			// 適切なエラーハンドリングを実装
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/houses/{id}/reviews")
	public String showReviewForm(@PathVariable("id") Integer houseId, Model model) {
		Review review = new Review();
		// review.setRating(0); // 初期値を設定しない
		model.addAttribute("review", review);
		return "reviewForm";
	}

	@PostMapping("/houses/{id}/reviews")
	public String submitReview(@PathVariable("id") Integer houseId, @ModelAttribute Review review) {
		// レビューの保存処理
		Integer userId = 1;
		reviewService.saveReview(userId, houseId, review);
		return "redirect:/houses/" + houseId;
	}

}
