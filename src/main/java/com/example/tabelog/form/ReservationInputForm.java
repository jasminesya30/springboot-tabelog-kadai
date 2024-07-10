package com.example.tabelog.form;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
	@NotNull(message = "予約日時を選択してください。")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime reservationDateTime;

	@NotNull(message = "予約人数を入力してください。")
	@Min(value = 1, message = "予約人数は1人以上に設定してください。")
	private Integer numberOfPeople;

	// 予約日を取得する
	public LocalDate getReservationDate() {
		if (reservationDateTime == null) {
			return null;
		}
		return reservationDateTime.toLocalDate();
	}

	// 予約時刻を取得する
	public LocalTime getReservationTime() {
		if (reservationDateTime == null) {
			return null;
		}
		return reservationDateTime.toLocalTime();
	}

	// 時刻を30分単位に丸めて取得する
	public LocalTime getRoundedReservationTime() {
		if (reservationDateTime == null) {
			return null;
		}

		int minutes = reservationDateTime.getMinute();
		if (minutes < 30) {
			return LocalTime.of(reservationDateTime.getHour(), 0);
		} else {
			return LocalTime.of(reservationDateTime.getHour(), 30);
		}
	}
}