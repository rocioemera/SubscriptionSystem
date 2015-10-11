package com.subscriptionmng.data;

import com.subscriptionmng.model.admin.Customer_SubscriptionImp;

public interface Customer_SubscriptionMapper {
	public Customer_SubscriptionImp findById(long custSubsID);
	public void insertCustomer_Subscription(Customer_SubscriptionImp custSub);
	public void modifyCustomer_Subscription(Customer_SubscriptionImp custSub);
}
