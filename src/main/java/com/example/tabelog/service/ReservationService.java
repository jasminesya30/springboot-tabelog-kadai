package com.example.tabelog.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tabelog.entity.House;
import com.example.tabelog.entity.Reservation;
import com.example.tabelog.entity.User;
import com.example.tabelog.repository.HouseRepository;
import com.example.tabelog.repository.ReservationRepository;
import com.example.tabelog.repository.UserRepository;

@Service
public class ReservationService {
	private final ReservationRepository reservationRepository;
	private final HouseRepository houseRepository;
	private final UserRepository userRepository;

	public ReservationService(ReservationRepository reservationRepository, HouseRepository houseRepository,
			UserRepository userRepository) {
		this.reservationRepository = reservationRepository;
		this.houseRepository = houseRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public void create(Map<String, String> paymentIntentObject) {
		// 新しい予約エンティティを作成します
		Reservation reservation = new Reservation();

		Integer houseId = Integer.valueOf(paymentIntentObject.get("houseId"));
		Integer userId = Integer.valueOf(paymentIntentObject.get("userId"));

		// 予約に関連する家のエンティティを取得します
		House house = houseRepository.getReferenceById(houseId);

		// 予約を行うユーザーのエンティティを取得します
		User user = userRepository.getReferenceById(userId);

		// 予約日と時間をフォームから取得し、LocalDateとLocalTimeに変換
		LocalDate reservationDate = LocalDate.parse(paymentIntentObject.get("reservationDate"));
		LocalTime reservationTime = LocalTime.parse(paymentIntentObject.get("reservationTime"));

		Integer numberOfPeople = Integer.valueOf(paymentIntentObject.get("numberOfPeople"));
		Integer amount = Integer.valueOf(paymentIntentObject.get("amount"));

		// 予約エンティティに関連する情報を設定します
		reservation.setHouse(house);
		reservation.setUser(user);
		reservation.setReservationDate(reservationDate);
		reservation.setReservationTime(reservationTime);
		reservation.setNumberOfPeople(numberOfPeople);
		reservation.setAmount(amount);

		// 予約エンティティをデータベースに保存します
		reservationRepository.save(reservation);
	}

	// 予約人数が定員以下かどうかをチェックする
	public boolean isWithinCapacity(Integer numberOfPeople, Integer capacity) {
		return numberOfPeople <= capacity;
	}

	// 人数に基づいて料金目安を計算する
	public Integer calculateAmount(Integer numberOfPeople, Integer pricePerPerson) {
		int amount = pricePerPerson * numberOfPeople;
		return amount;
	}

}
