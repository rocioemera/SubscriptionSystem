package com.subscriptionmng.model.admin;

import java.util.Date;

import com.subscriptionmng.exception.NoStockExeption;
import com.subscriptionmng.service.Customer_Subscription;
import com.subscriptionmng.service.PurchaseSubscription;
import com.subscriptionmng.service.SubscriptionPkg;
import com.subscriptionmng.service.Subscription_Stock;

public class PurchaseSubscriptionImp implements PurchaseSubscription{
	private Customer_Subscription customerSubscription;
	private Subscription_Stock subscription_Stock;
	private SubscriptionPkg subscription;
	
	public Customer_Subscription getCustomerSubscription() {
		return customerSubscription;
	}
	public void setCustomerSubscription(Customer_Subscription customerSubscription) {
		this.customerSubscription = customerSubscription;
	}
	public Subscription_Stock getSubscription_Stock() {
		return subscription_Stock;
	}
	public void setSubscription_Stock(Subscription_Stock subscription_Stock) {
		this.subscription_Stock = subscription_Stock;
	}
	public SubscriptionPkg getSubscription() {
		return subscription;
	}
	public void setSubscription(SubscriptionPkg subscription) {
		this.subscription = subscription;
	}
	
	public void purchaseSubscription(SubscriptionPkgImp subs, CustomerImp customer)
			throws Exception{
		//purchase can not be complete if stock of the subscription is 0
		//TODO: Check stocks
		//ADD stock to subscription
		String Status = "ACTIVE";
		Date datePurchase = new Date();
		
		System.out.println("Subscription to buy "+subs.toString());
		
		//TODO: Compare that VERSION of the subscription selected by the user
		//is equal to the version in the database
		//subs.getVersion != currentStateSubs.getVersion
		System.out.println("");
		System.out.println("Subscription Received "+subs);
		System.out.println("Subscription in database "+subscription.readSubscription(subs.getID()));
		
		if(!subs.getVersion().equals(subscription.readSubscription(subs.getID()).getVersion())){
			System.out.println("Subscription Version does not match");
			throw new RuntimeException("Subscription Version does not match");
		}
		
		//Throw exception can not purchase because there is no stock
		subscription_Stock.separateSubscription(subs.getID());
		
		//create the register that associate customer with the subscription
		//internally hibernate do the check of version numbers
		Customer_SubscriptionImp custSub=
				new Customer_SubscriptionImp(subs,customer,datePurchase,Status);
		customerSubscription.createNewCustomerSubscription(custSub);
		
		
	}
}
