package com.example.tabelog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tabelog.entity.Favorite;
import com.example.tabelog.entity.House;
import com.example.tabelog.entity.User;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

	Optional<Favorite> findByUserIdAndHouse(Integer userId, House house);

	List<Favorite> findByUserId(Integer userId);

	Optional<Favorite> findByUserIdAndHouseId(Integer id, Integer houseId);

	public Favorite findByHouseAndUser(House house, User user);

//	public Page<Favorite> findByUserOrderByCreatedAtDesc(User user);
}
