package com.subscriptionmng.service;

import java.util.List;

import com.subscriptionmng.model.admin.Customer_SubscriptionImp;
import com.subscriptionmng.model.admin.Subscription_CancellationImp;

public interface Subscription_Cancellation {
	
	public void requestCancellation(Customer_SubscriptionImp custSubscripton);
	public void approveCancellation(Subscription_CancellationImp subsCancellation);
	public Subscription_CancellationImp readSubscriptionCancellation(long ID);
	public List<Subscription_CancellationImp> listSubscriptionCancellation();
	public List<Subscription_CancellationImp> listSubscriptionCancellation(String status);
}
