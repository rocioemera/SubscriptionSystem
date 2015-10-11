package com.subscriptionmng.service;

import java.util.List;
import java.util.Set;

import com.subscriptionmng.model.admin.CustomerImp;
import com.subscriptionmng.model.admin.Customer_SubscriptionImp;

public interface Customer{
	public CustomerImp readCustomer(long ID);
	public CustomerImp readCustomer(String username);
	public List<Customer_SubscriptionImp> getCustomer_SubscriptionImp(CustomerImp customer);
}
