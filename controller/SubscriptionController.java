package com.example.tabelog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tabelog.service.SubscriptionService;
import com.stripe.exception.StripeException;
import com.stripe.model.Subscription;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	// サブスクリプションページの表示
	@GetMapping
	public String viewSubscriptions(Model model, @RequestParam(required = false) String customerId) {
		if (customerId == null || customerId.isEmpty()) {
			model.addAttribute("message", "顧客IDが提供されていません。");
			return "subscriptions/subscription"; // サブスクリプションページのテンプレート名
		}

		try {
			// 顧客のサブスクリプション一覧を取得
			var subscriptions = subscriptionService.getSubscriptions(customerId);
			model.addAttribute("subscriptions", subscriptions);
			model.addAttribute("customerId", customerId);
		} catch (StripeException e) {
			model.addAttribute("error", "サブスクリプションの取得中にエラーが発生しました。");
		}

		return "subscriptions/subscription"; // サブスクリプションページのテンプレート名
	}

	// サブスクリプションの作成
	@PostMapping("/create")
	public String createSubscription(@RequestParam("customerId") String customerId,
			@RequestParam("priceId") String priceId, Model model) {
//		if (customerId == null || customerId.isEmpty() || priceId == null || priceId.isEmpty()) {
//			model.addAttribute("error", "顧客IDまたは価格IDが提供されていません。");
//			return "subscriptions/subscription";
//		}

		try {
			// サブスクリプションの作成
			Subscription subscription = subscriptionService.createSubscription(customerId, priceId);
			model.addAttribute("subscription", subscription);
			return "redirect:/subscriptions?customerId=" + customerId;
		} catch (StripeException e) {
			model.addAttribute("error", "サブスクリプションの作成中にエラーが発生しました: " + e.getMessage());
			return "subscriptions/subscription";
		}
	}

	// サブスクリプションのキャンセル
	@PostMapping("/cancel")
	public String cancelSubscription(@RequestParam String subscriptionId, @RequestParam String customerId,
			Model model) {
		if (subscriptionId.isEmpty() || customerId.isEmpty()) {
			model.addAttribute("error", "サブスクリプションIDまたは顧客IDが提供されていません。");
			return "subscriptions/subscription";
		}

		try {
			// サブスクリプションのキャンセル
			Subscription subscription = subscriptionService.cancelSubscription(subscriptionId);
			model.addAttribute("subscription", subscription);
			return "redirect:/subscriptions?customerId=" + customerId;
		} catch (StripeException e) {
			model.addAttribute("error", "サブスクリプションのキャンセル中にエラーが発生しました: " + e.getMessage());
			return "subscriptions/subscription";
		}
	}
}
