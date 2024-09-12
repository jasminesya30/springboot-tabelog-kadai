package com.example.tabelog.service;

import java.time.LocalDateTime;
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
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		House house = houseRepository.findById(houseId)
				.orElseThrow(() -> new RuntimeException("House not found"));

		review.setUser(user);
		review.setHouse(house);
		review.setCreatedAt(LocalDateTime.now());

		System.out.println("Review created at: " + review.getCreatedAt()); // デバッグログ

		return reviewRepository.save(review);
	}

	public List<Review> getReviewsByHouseId(Integer houseId) {
		return reviewRepository.findByHouseId(houseId);
	}

	public void deleteReview(Integer id) {
		reviewRepository.deleteById(id);
	}
}