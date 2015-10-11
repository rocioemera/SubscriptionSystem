package com.subscriptionmng.service;

import java.util.Set;

import com.subscriptionmng.model.admin.Customer_SubscriptionImp;

public interface Customer_Subscription {
	public void createNewCustomerSubscription(Customer_SubscriptionImp custSub);
	public Customer_SubscriptionImp readCustomerSubscription(long ID);
}
