package com.example.tabelog.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "favorites")
public class Favorite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "house_id", nullable = false)
	private House house;

	@Column(name = "created_at", insertable = false, updatable = false)
	private LocalDateTime createdAt;

	// コンストラクタ
	public Favorite() {
		// createdAt の初期化は @PrePersist で行う
	}

	// ゲッターとセッター
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	// createdAt の設定は @PrePersist で行うため、セッターは不要
	// public void setCreatedAt(LocalDateTime createdAt) {
	//     this.createdAt = createdAt;
	// }

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}

	// equals と hashCode メソッドのオーバーライド（任意）
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Favorite favorite = (Favorite) o;
		return id != null && id.equals(favorite.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
