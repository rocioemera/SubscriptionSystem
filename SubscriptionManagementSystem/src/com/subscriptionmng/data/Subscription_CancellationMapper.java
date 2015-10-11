package com.subscriptionmng.data;

import java.util.List;

import com.subscriptionmng.model.admin.CustomerImp;
import com.subscriptionmng.model.admin.Subscription_CancellationImp;

public interface Subscription_CancellationMapper  {
	public Subscription_CancellationImp findByID(long ID);
	public void insertSubscription_Cancellation(Subscription_CancellationImp subsCancellation);
	public void modifySubscription_Cancellation(Subscription_CancellationImp subsCancellation);
	public List<Subscription_CancellationImp> listSubscriptionCancellation();
	public List<Subscription_CancellationImp> listSubscriptionCancellation(CustomerImp customer);
	public List<Subscription_CancellationImp> listSubscriptionCancellation(String Status);
}
