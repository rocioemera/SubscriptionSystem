package com.subscriptionmng.data;

import java.util.List;
import java.util.Set;

import com.subscriptionmng.model.admin.SubscriptionPkgImp;

public interface SubscriptionMapper {
	public SubscriptionPkgImp findById(long subscriptionPkg_ID);
	public List<SubscriptionPkgImp> listSubscription();
	public List<SubscriptionPkgImp> listSubscription(String search);
	public boolean insertSubscription(SubscriptionPkgImp subs);
	public  boolean updateSubscription(SubscriptionPkgImp subs);
	public boolean deleteSubscription(long subscriptionPkg_ID);
	public SubscriptionPkgImp findByIdWithItems(long subscriptionPkg_ID);
}
