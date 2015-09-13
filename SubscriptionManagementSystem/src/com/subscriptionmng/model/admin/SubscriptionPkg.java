package com.subscriptionmng.model.admin;

import java.util.List;

public interface SubscriptionPkg {
	public boolean createNewSubscription(SubscriptionPkgImp subs);
	public List<SubscriptionPkgImp> listSubscription();
	public boolean modifySubscription(SubscriptionPkgImp subs);
	public boolean removeSubscription(long ID);
	public SubscriptionPkgImp readSubscription(long ID);
	public SubscriptionPkgImp readSubscriptionWithItems(long ID);
}
