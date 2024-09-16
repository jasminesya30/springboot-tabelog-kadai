package com.example.tabelog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tabelog.entity.House;
import com.example.tabelog.entity.Review;
import com.example.tabelog.entity.User;
import com.example.tabelog.repository.HouseRepository;
import com.example.tabelog.repository.ReviewRepository;
import com.example.tabelog.repository.UserRepository;

@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final HouseRepository houseRepository;
	private final UserRepository userRepository;

	public ReviewService(ReviewRepository reviewRepository, HouseRepository houseRepository,
			UserRepository userRepository) {
		this.reviewRepository = reviewRepository;
		this.houseRepository = houseRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public Review saveReview(Review review) {
		return reviewRepository.save(review);
	}

	@Transactional
	public Review saveReview(User user, House house, Review review) {
		review.setUser(user);
		review.setHouse(house);
		return reviewRepository.save(review);
	}

	@Transactional
	public Review saveReview(Integer userId, Integer houseId, Review review) {
		// 新しい Review インスタンスを作成
		Review newReview = new Review();

		// ユーザーとハウスを取得
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		House house = houseRepository.findById(houseId)
				.orElseThrow(() -> new RuntimeException("House not found"));

		// フィールドを設定
		newReview.setUser(user);
		newReview.setHouse(house);
		newReview.setContent(review.getContent()); // もとのレビューから内容をコピー
		newReview.setRating(review.getRating()); // もとのレビューから評価をコピー
		// newReview.setCreatedAt(LocalDateTime.now()); // 現在の日時を設定

		// System.out.println("Review created at: " + newReview.getCreatedAt()); // デバッグログ

		// 新しいレビューとして保存
		return reviewRepository.save(newReview);
	}

	public List<Review> getReviewsByHouseId(Integer houseId) {
		return reviewRepository.findByHouseId(houseId);
	}

	public void deleteReview(Integer id) {
		reviewRepository.deleteById(id);
	}
}