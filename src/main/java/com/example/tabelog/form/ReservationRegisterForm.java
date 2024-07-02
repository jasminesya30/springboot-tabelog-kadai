package com.example.tabelog.form;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationRegisterForm {
	private Integer houseId;
	private Integer userId;
	private LocalDate reservationDate;
	private LocalTime reservationTime;
	private Integer numberOfPeople;
	private Integer amount;

	// コンストラクタやその他のメソッド

	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	public void setReservationTime(LocalTime reservationTime) {
		this.reservationTime = reservationTime;
	}

}
