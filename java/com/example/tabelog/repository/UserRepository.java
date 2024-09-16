package com.example.tabelog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByName(String name); // ユーザー名で検索するメソッド

	User findByEmail(String email); // メールアドレスで検索するメソッド（既存）

	Page<User> findByNameLikeOrFuriganaLike(String namePattern, String furiganaPattern, Pageable pageable); // 名前またはふりがなで検索するメソッド

}