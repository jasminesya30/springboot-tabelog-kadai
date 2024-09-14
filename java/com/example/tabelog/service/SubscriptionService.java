package com.example.tabelog.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Subscription;
import com.stripe.model.SubscriptionCollection;
import com.stripe.param.SubscriptionCancelParams;
import com.stripe.param.SubscriptionCreateParams;

import jakarta.annotation.PostConstruct;

@Service
public class SubscriptionService {

	@Value("${stripe.api-key}")
	private String stripeApiKey;

	// 初期化メソッド：Stripe APIキーを設定
	@PostConstruct
	public void init() {
		Stripe.apiKey = stripeApiKey; // Stripe APIキーを初期化
	}

	// サブスクリプションの作成メソッド
	public Subscription createSubscription(String customerId, String priceId) throws StripeException {
		// サブスクリプション作成パラメータを設定
		SubscriptionCreateParams params = SubscriptionCreateParams.builder()
				.setCustomer(customerId) // 顧客IDを設定
				.addItem(
						SubscriptionCreateParams.Item.builder()
								.setPrice(priceId) // プランIDではなくPrice IDを使用
								.setQuantity(1L)
								.build())
				.build();

		// Stripe API でサブスクリプションを作成し、返却
		return Subscription.create(params);
	}

	// サブスクリプションのキャンセルメソッド
	public Subscription cancelSubscription(String subscriptionId) throws StripeException {
		// サブスクリプションを取得
		Subscription subscription = Subscription.retrieve(subscriptionId);

		// キャンセルのパラメータを設定
		SubscriptionCancelParams params = SubscriptionCancelParams.builder()
				.setProrate(true) // 使用量に応じて返金するかどうか
				.build();

		// サブスクリプションをキャンセルし、返却
		return subscription.cancel(params);
	}

	// 顧客のサブスクリプション一覧を取得するメソッド
	public SubscriptionCollection getSubscriptions(String customerId) throws StripeException {
		// リスト取得のためのパラメータを設定
		Map<String, Object> params = new HashMap<>();
		params.put("customer", customerId);

		// 顧客のサブスクリプション一覧を取得し、返却
		return Subscription.list(params);
	}
}
