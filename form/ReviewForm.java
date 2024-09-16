package com.example.tabelog.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewForm {

	@NotNull(message = "評価は必須です")
	@Min(value = 1, message = "評価は1以上でなければなりません")
	@Max(value = 5, message = "評価は5以下でなければなりません")
	private Integer rating;

	@NotBlank(message = "コメントは必須です")
	private String comment;

	@NotNull(message = "店舗IDは必須です")
	private Long houseId;

	// 必要に応じてユーザーIDなども追加
	// @NotNull(message = "ユーザーIDは必須です")
	// private Long userId;

	// ゲッターとセッター
	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	// 必要に応じてユーザーIDのゲッターとセッターも追加
	// public Long getUserId() {
	//     return userId;
	// }

	// public void setUserId(Long userId) {
	//     this.userId = userId;
	// }
}
