package com.example.tabelog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tabelog.entity.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
	// 必要に応じてカスタムクエリを追加
	Subscription findByStripeSubscriptionId(String stripeSubscriptionId);
}
