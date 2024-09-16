package com.example.tabelog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// userId をオプションに変更し、指定がない場合の処理を追加
	@PostMapping("/houses/{id}/reviews")
	public String submitReview(@PathVariable("id") Integer houseId,
			@ModelAttribute Review review,
			@RequestParam(required = false) Integer userId) {
		// userId が指定されていない場合、デフォルト値を設定
		if (userId == null) {
			userId = 0; // デフォルトのユーザーIDを設定
		}
		reviewService.saveReview(userId, houseId, review);
		// System.out.println("Review created at: " + review.getCreatedAt()); // デバッグログ追加
		return "redirect:/houses/" + houseId;
	}

	@PostMapping("/submitReview")
	public String submitReview(@RequestParam("userId") Integer userId,
			@RequestParam("reviewText") String reviewText,
			RedirectAttributes redirectAttributes) {
		// レビューの保存処理

		Review review = new Review();
		review.setContent(reviewText);
		review.setRating(5); // 適切な評価を設定してください（ここでは仮に5としています）

		// 現在の日時を設定（テスト用）
		// review.setCreatedAt(LocalDateTime.now());

		// レビューの保存処理
		reviewService.saveReview(review);

		// デバッグログを追加
		// System.out.println("Review created at: " + review.getCreatedAt()); // 現在の日時をログ出力
		// メッセージをリダイレクト先に渡す
		redirectAttributes.addFlashAttribute("message", "レビューが投稿されました");

		return "redirect:/houses"; // メッセージを表示するためのページにリダイレクト

	}

}
