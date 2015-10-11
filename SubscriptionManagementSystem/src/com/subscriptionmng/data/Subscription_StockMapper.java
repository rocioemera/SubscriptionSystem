package com.subscriptionmng.data;

import com.subscriptionmng.model.admin.Subscription_StockImp;

public interface Subscription_StockMapper {
	public Subscription_StockImp findByID(long subscriptionID);
	public void modifySubscriptionStock(Subscription_StockImp subsStock);
	public Subscription_StockImp getFirstAvailableSubscription(long subscriptionID);
}
	