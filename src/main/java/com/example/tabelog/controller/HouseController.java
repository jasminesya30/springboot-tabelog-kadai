package com.example.tabelog.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tabelog.entity.House;
import com.example.tabelog.entity.Review;
import com.example.tabelog.entity.User;
import com.example.tabelog.form.ReservationInputForm;
import com.example.tabelog.repository.HouseRepository;
import com.example.tabelog.service.ReviewService;
import com.example.tabelog.service.UserService;

@Controller
@RequestMapping("/houses")
public class HouseController {
	private final HouseRepository houseRepository;
	private final ReviewService reviewService;
	private final UserService userService;

	public HouseController(HouseRepository houseRepository, ReviewService reviewService, UserService userService) {
		this.houseRepository = houseRepository;
		this.reviewService = reviewService;
		this.userService = userService;
	}

	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "area", required = false) String area,
			@RequestParam(name = "price", required = false) Integer price,
			@RequestParam(name = "order", required = false) String order,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			Model model) {
		Page<House> housePage;

		if (keyword != null && !keyword.isEmpty()) {
			if (order != null && order.equals("priceAsc")) {
				housePage = houseRepository.findByNameLikeOrAddressLikeOrderByPriceAsc("%" + keyword + "%",
						"%" + keyword + "%", pageable);
			} else {
				housePage = houseRepository.findByNameLikeOrAddressLikeOrderByCreatedAtDesc("%" + keyword + "%",
						"%" + keyword + "%", pageable);
			}
		} else if (area != null && !area.isEmpty()) {
			if (order != null && order.equals("priceAsc")) {
				housePage = houseRepository.findByAddressLikeOrderByPriceAsc("%" + area + "%", pageable);
			} else {
				housePage = houseRepository.findByAddressLikeOrderByCreatedAtDesc("%" + area + "%", pageable);
			}
		} else if (price != null) {
			if (order != null && order.equals("priceAsc")) {
				housePage = houseRepository.findByPriceLessThanEqualOrderByPriceAsc(price, pageable);
			} else {
				housePage = houseRepository.findByPriceLessThanEqualOrderByCreatedAtDesc(price, pageable);
			}
		} else {
			if (order != null && order.equals("priceAsc")) {
				housePage = houseRepository.findAllByOrderByPriceAsc(pageable);
			} else {
				housePage = houseRepository.findAllByOrderByCreatedAtDesc(pageable);
			}
		}

		model.addAttribute("housePage", housePage);
		model.addAttribute("keyword", keyword);
		model.addAttribute("area", area);
		model.addAttribute("price", price);
		model.addAttribute("order", order);

		return "houses/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model) {
		House house = houseRepository.findById(id).orElseThrow(() -> new RuntimeException("House not found"));
		List<Review> reviews = reviewService.getReviewsByHouseId(id);

		model.addAttribute("house", house);
		model.addAttribute("reviews", reviews);
		model.addAttribute("reservationInputForm", new ReservationInputForm());
		model.addAttribute("review", new Review()); // 空のレビューオブジェクトを渡す

		return "houses/show";
	}

	@PostMapping("/{id}/reviews")
	public String addReview(@PathVariable(name = "id") Integer id,
			@ModelAttribute Review review,
			@RequestParam(required = false) Integer userId) {
		try {
			// House を取得
			House house = houseRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("House not found"));

			// userId が指定されていない場合のデフォルトユーザーIDを設定
			if (userId == null) {
				userId = 1; // デフォルトユーザーID
			}

			// User を取得
			User user = userService.findById(userId)
					.orElseThrow(() -> new RuntimeException("User not found"));

			// Review を設定
			review.setHouse(house);
			review.setUser(user);
			review.setCreatedAt(LocalDateTime.now());

			// Review を保存
			reviewService.saveReview(userId, id, review);

			return "redirect:/houses/" + id;
		} catch (RuntimeException e) {
			// エラーハンドリング、適宜ログを出力したり、エラーページへ遷移したりする処理
			e.printStackTrace(); // エラーメッセージをコンソールに出力（開発中のみ）
			return "error"; // エラーページにリダイレクト
		}
	}

	@PostMapping("/{houseId}/addReview")
	public String addReview(@RequestParam("userId") Integer userId,
			@RequestParam("houseId") Integer houseId,
			@RequestParam("content") String content,
			@RequestParam("rating") Integer rating) {
		Review review = new Review();
		review.setContent(content);
		review.setRating(rating);
		review.setCreatedAt(LocalDateTime.now());

		House house = houseRepository.findById(houseId).orElseThrow(() -> new RuntimeException("House not found"));
		User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		review.setHouse(house);
		review.setUser(user);
		reviewService.saveReview(review);

		reviewService.saveReview(userId, houseId, review);
		return "redirect:/houses/" + houseId;
	}

}