package com.subscriptionmng.service;

import java.util.List;

import com.subscriptionmng.model.admin.SubscriptionPkgImp;

public interface SubscriptionPkg {
	public boolean createNewSubscription(SubscriptionPkgImp subs);
	public List<SubscriptionPkgImp> listSubscription();
	public List<SubscriptionPkgImp> listSubscription(String Search);
	public boolean modifySubscription(SubscriptionPkgImp subs);
	public boolean removeSubscription(long ID);
	public SubscriptionPkgImp readSubscription(long ID);
	public SubscriptionPkgImp readSubscriptionWithItems(long ID);
}
