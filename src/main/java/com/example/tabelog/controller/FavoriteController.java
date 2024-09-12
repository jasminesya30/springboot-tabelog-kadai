package com.example.tabelog.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tabelog.entity.Favorite;
import com.example.tabelog.entity.House;
import com.example.tabelog.entity.User;
import com.example.tabelog.security.UserDetailsImpl;
import com.example.tabelog.service.FavoriteService;
import com.example.tabelog.service.HouseService;
import com.example.tabelog.service.UserService;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {

	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private UserService userService;

	@Autowired
	private HouseService houseService;

	@PostMapping("/{houseId}")
	@ResponseBody
	public FavoriteResponse addFavorite(@PathVariable("houseId") Integer houseId,
			@AuthenticationPrincipal UserDetails userDetails) {
		FavoriteResponse response = new FavoriteResponse();
		try {
			if (userDetails == null) {
				response.setSuccess(false);
				response.setMessage("ユーザーが認証されていません。");
				return response;
			}

			User user = userService.findByUsername(userDetails.getUsername());
			if (user == null) {
				response.setSuccess(false);
				response.setMessage("ユーザーが存在しません。");
				return response;
			}

			House house = houseService.findById(houseId);
			if (house == null) {
				response.setSuccess(false);
				response.setMessage("ハウスが存在しません。");
				return response;
			}

			boolean isFavoriteAdded = favoriteService.toggleFavorite(houseId, user.getUsername());

			response.setSuccess(true);
			response.setMessage(isFavoriteAdded ? "お気に入りに追加しました。" : "既にお気に入りに追加されています。");

		} catch (Exception e) {
			e.printStackTrace(); // これをログに記録することを検討する
			response.setSuccess(false);
			response.setMessage("お気に入りの追加に失敗しました。");
		}
		return response;
	}

	@GetMapping
	public String getFavorites(Model model) {
		User currentUser = getCurrentUser();
		if (currentUser == null) {
			// ユーザーがログインしていない場合の処理
			return "redirect:/login"; // ログインページにリダイレクト
		}

		try {
			List<Favorite> favorites = favoriteService.getFavoritesByUser(currentUser);
			model.addAttribute("favorites", favorites);
			return "favorites";
		} catch (IllegalArgumentException e) {
			// ユーザーがnullの場合の処理
			model.addAttribute("errorMessage", "Unexpected error occurred: " + e.getMessage());
			return "errorPage"; // エラーページの表示
		}
	}

	private User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetailsImpl) {
			return ((UserDetailsImpl) principal).getUser();
		}
		return null;
	}

	@PostMapping("/houses/{id}/favorites/add")
	public ResponseEntity<String> addFavorite(@PathVariable("id") Integer houseId, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		House house = houseService.findById(houseId);
		boolean isFavoriteAdded = favoriteService.addFavorite(user, house);
		return isFavoriteAdded ? ResponseEntity.ok("Added to favorites")
				: ResponseEntity.badRequest().body("Already added to favorites");
	}

	@PostMapping("/houses/{id}/favorites/delete")
	public ResponseEntity<String> removeFavorite(@PathVariable("id") Integer houseId, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		House house = houseService.findById(houseId);
		boolean isFavoriteRemoved = favoriteService.removeFavorite(user, house);
		return isFavoriteRemoved ? ResponseEntity.ok("Removed from favorites")
				: ResponseEntity.badRequest().body("Not found in favorites");
	}

	public static class FavoriteResponse {
		private boolean success;
		private String message;

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
