package com.subscriptionmng.service;

import com.subscriptionmng.model.admin.CustomerImp;
import com.subscriptionmng.model.admin.SubscriptionPkgImp;

public interface PurchaseSubscription {
	public void purchaseSubscription(SubscriptionPkgImp subs, 
			CustomerImp customer) throws Exception;
}
