package com.example.tabelog.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog.entity.House;
import com.example.tabelog.entity.Reservation;
import com.example.tabelog.entity.User;
import com.example.tabelog.form.ReservationInputForm;
import com.example.tabelog.form.ReservationRegisterForm;
import com.example.tabelog.repository.HouseRepository;
import com.example.tabelog.repository.ReservationRepository;
import com.example.tabelog.security.UserDetailsImpl;
import com.example.tabelog.service.ReservationService;
import com.example.tabelog.service.StripeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ReservationController {
	private final ReservationRepository reservationRepository;
	private final HouseRepository houseRepository;
	private final ReservationService reservationService;
	private final StripeService stripeService;

	public ReservationController(ReservationRepository reservationRepository, HouseRepository houseRepository,
			ReservationService reservationService, StripeService stripeService) {
		this.reservationRepository = reservationRepository;
		this.houseRepository = houseRepository;
		this.reservationService = reservationService;
		this.stripeService = stripeService;
	}

	@GetMapping("/reservations")
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			Model model) {
		User user = userDetailsImpl.getUser();
		Page<Reservation> reservationPage = reservationRepository.findByUserOrderByCreatedAtDesc(user, pageable);
		model.addAttribute("user", user);
		model.addAttribute("reservationInputForm", new ReservationInputForm());
		model.addAttribute("reservationPage", reservationPage);
		return "reservations/index";
	}

	@GetMapping("/houses/{id}/reservations/input")
	public String input(@PathVariable(name = "id") Integer id,
			@ModelAttribute @Validated ReservationInputForm reservationInputForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model) {
		House house = houseRepository.getReferenceById(id);
		Integer numberOfPeople = reservationInputForm.getNumberOfPeople();
		Integer capacity = house.getCapacity();

		if (numberOfPeople != null) {
			if (!reservationService.isWithinCapacity(numberOfPeople, capacity)) {
				FieldError fieldError = new FieldError(bindingResult.getObjectName(), "numberOfPeople",
						"予約人数が座席数を超えています。");
				bindingResult.addError(fieldError);
			}
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("house", house);
			model.addAttribute("errorMessage", "予約内容に不備があります。");
			return "houses/show";
		}

		redirectAttributes.addFlashAttribute("reservationInputForm", reservationInputForm);

		return "redirect:/houses/{id}/reservations/confirm";
	}

	@GetMapping("/houses/{id}/reservations/confirm")
	public String confirm(@PathVariable(name = "id") Integer id,
			@ModelAttribute ReservationInputForm reservationInputForm,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			HttpServletRequest httpServletRequest,
			Model model) {
		House house = houseRepository.getReferenceById(id);
		User user = userDetailsImpl.getUser();

		// 予約日を取得する
		LocalDate reservationDate = reservationInputForm.getReservationDate();

		// 予約時刻を取得する
		LocalTime reservationTime = reservationInputForm.getReservationTime();

		// houseオブジェクトのgetPriceメソッドが正しく呼び出されているか確認
		Integer pricePerPerson = house.getPrice();
		if (pricePerPerson == null) {
			throw new IllegalStateException("Price per person is not set for the house.");
		}

		// 料金目安を計算する
		Integer amount = reservationService.calculateAmount(reservationInputForm.getNumberOfPeople(), pricePerPerson);

		ReservationRegisterForm reservationRegisterForm = new ReservationRegisterForm(
				house.getId(),
				user.getId(),
				reservationDate,
				reservationTime,
				reservationInputForm.getNumberOfPeople(),
				amount);

		String sessionId = stripeService.createStripeSession(house.getName(), reservationRegisterForm,
				httpServletRequest);

		model.addAttribute("house", house);
		model.addAttribute("reservationRegisterForm", reservationRegisterForm);
		model.addAttribute("sessionId", sessionId);

		return "reservations/confirm";
	}

	/*
	@PostMapping("/houses/{id}/reservations/create")
	public String create(@ModelAttribute ReservationRegisterForm reservationRegisterForm) {
		reservationService.create(reservationRegisterForm);
	
		return "redirect:/reservations?reserved";
	}
	 */

	@PostMapping
	public String reserve(@ModelAttribute("reservationInputForm") ReservationInputForm reservationInputForm,
			Model model) {
		// 予約処理の実装
		model.addAttribute("reserved", true); // 予約完了メッセージを表示するために追加
		return "redirect:/reservations";
	}
}
