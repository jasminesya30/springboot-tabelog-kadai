package com.example.tabelog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tabelog.entity.Favorite;
import com.example.tabelog.entity.House;
import com.example.tabelog.entity.User;
import com.example.tabelog.repository.FavoriteRepository;
import com.example.tabelog.repository.HouseRepository;
import com.example.tabelog.repository.UserRepository;

@Service
public class FavoriteService {

	@Autowired
	private FavoriteRepository favoriteRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HouseRepository houseRepository;

	public boolean toggleFavorite(Integer houseId, String name) {
		User user = userRepository.findByName(name);

		// Houseエンティティを取得
		House house = houseRepository.findById(houseId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid house ID: " + houseId));

		// お気に入りを取得
		Optional<Favorite> favoriteOpt = favoriteRepository.findByUserIdAndHouse(user.getId(), house);
		if (favoriteOpt.isPresent()) {
			// お気に入りを削除
			favoriteRepository.delete(favoriteOpt.get());
			return false; // お気に入り解除
		} else {
			// お気に入りを追加
			Favorite favorite = new Favorite();
			favorite.setUser(user); // ユーザーを設定
			favorite.setHouse(house); // ハウスを設定
			favoriteRepository.save(favorite);
			return true; // お気に入り登録
		}
	}

	public boolean addFavorite(User user, House house) {
		// お気に入りを追加するロジック
		Favorite favorite = new Favorite();
		favorite.setUser(user);
		favorite.setHouse(house);
		favoriteRepository.save(favorite);
		return true; // 成功したことを示す
	}

	public boolean removeFavorite(User user, House house) {
		// お気に入りを削除するロジック
		Optional<Favorite> favoriteOpt = favoriteRepository.findByUserIdAndHouse(user.getId(), house);
		if (favoriteOpt.isPresent()) {
			favoriteRepository.delete(favoriteOpt.get());
			return true; // 成功したことを示す
		}
		return false; // 削除対象が存在しなかった
	}

		public List<Favorite> getFavoritesByUser(User user) {
			if (user == null) {
				throw new IllegalArgumentException("User cannot be null");
			}
			// ユーザーがnullでない場合の処理
			return favoriteRepository.findByUserId(user.getId());
		}
	// 指定したユーザーが指定した民宿をすでにお気に入りに追加済みかどうかをチェックする
	public boolean isFavorite(House house, User user) {
		return favoriteRepository.findByHouseAndUser(house, user) != null;
	}

}
