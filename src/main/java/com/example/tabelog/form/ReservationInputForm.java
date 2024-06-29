package com.example.tabelog.form;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
	@NotBlank(message = "予約日と予約時刻を選択してください。")
	private String reservationDateTime;

	@NotNull(message = "予約人数を入力してください。")
	@Min(value = 1, message = "予約人数は1人以上に設定してください。")
	private Integer numberOfPeople;

	// 予約日を取得する
	public LocalDate getReservationDate() {
		String[] parts = getReservationDateTime().split(" ");
		return LocalDate.parse(parts[0]);
	}

	// 予約時刻を取得する
	public LocalTime getReservationTime() {
		String[] parts = getReservationDateTime().split(" ");
		// 時刻を30分単位に丸める（例示）
		int minutes = Integer.parseInt(parts[1].substring(parts[1].indexOf(":") + 1));
		if (minutes < 30) {
			return LocalTime.of(Integer.parseInt(parts[1].substring(0, parts[1].indexOf(":"))), 0);
		} else {
			return LocalTime.of(Integer.parseInt(parts[1].substring(0, parts[1].indexOf(":"))) + 1, 0);
		}
	}
}
