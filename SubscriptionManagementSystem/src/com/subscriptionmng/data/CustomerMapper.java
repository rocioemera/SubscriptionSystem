package com.subscriptionmng.data;

import java.util.List;
import java.util.Set;

import com.subscriptionmng.model.admin.CustomerImp;
import com.subscriptionmng.model.admin.Customer_SubscriptionImp;

public interface CustomerMapper {
	public CustomerImp findById(long customer_ID);
	public List<Customer_SubscriptionImp> getCustomer_Subscription(CustomerImp customer);
}
