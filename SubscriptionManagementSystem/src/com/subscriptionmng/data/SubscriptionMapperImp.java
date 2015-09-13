package com.subscriptionmng.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.subscriptionmng.model.admin.ItemImp;
import com.subscriptionmng.model.admin.SubscriptionPkgImp;

public class SubscriptionMapperImp extends GenericMapper implements SubscriptionMapper{
	
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public SubscriptionPkgImp findById(long subscriptionPkg_ID){
		SubscriptionPkgImp subscriptionPkg = null;
		//load the package with his information except the list 
		//because of the get - (get is eager load)
		//the list of item is not loaded to the object because in an association we
		//use lazy load=true (this is by default)
		subscriptionPkg = hibernateTemplate.get(SubscriptionPkgImp.class, new Long(subscriptionPkg_ID));
		return subscriptionPkg;
	}
	
	@SuppressWarnings("unchecked")
	public List<SubscriptionPkgImp> listSubscription() {
		//load the subscriptionpkg information but not load the list of items
		//of the package
		return hibernateTemplate.find("from SubscriptionPkgImp");
	}

	public boolean insertSubscription(SubscriptionPkgImp subs){
		hibernateTemplate.save(subs);
		return true;
	}
	
	public  boolean updateSubscription(SubscriptionPkgImp subs){
		hibernateTemplate.update(subs);
		return true;
	}
	
	public boolean deleteSubscription(long subscriptionPkg_ID){
		SubscriptionPkgImp sub = hibernateTemplate.load(SubscriptionPkgImp.class, new Long(subscriptionPkg_ID));
		if(sub!=null){
			hibernateTemplate.delete(sub);
		}
		return true;
	}
	
	public SubscriptionPkgImp findByIdWithItems(long subscriptionPkg_ID){
		
		SubscriptionPkgImp subscriptionPkg = null;
		//subscriptionPkg = hibernateTemplate.get(SubscriptionPkgImp.class, new Long(subscriptionPkg_ID));
		String query="SELECT s FROM SubscriptionPkgImp s JOIN FETCH s.items " +
				 			"WHERE s.ID = (:subscription_id)";
		
		List<SubscriptionPkgImp> subscriptions= hibernateTemplate.findByNamedParam(query, 
				"subscription_id", subscriptionPkg_ID);
		
		if(subscriptions.size()>0){
			System.out.println(subscriptions.size());
			return subscriptions.get(0);
		}else{
			System.out.println(":(");
			return null;
		}
		
	}

}
