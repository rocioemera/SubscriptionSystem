package com.subscriptionmng.service;

public interface Subscription_Stock {
	public Subscription_Stock readSubscriptionStock(long subscriptionStock);
	public void separateSubscription(long subscriptionStock) throws Exception;
}
