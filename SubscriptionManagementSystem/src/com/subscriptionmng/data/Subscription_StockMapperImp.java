package com.subscriptionmng.data;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.subscriptionmng.model.admin.Subscription_StockImp;

public class Subscription_StockMapperImp implements Subscription_StockMapper{
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public Subscription_StockImp findByID(long subscriptionStock) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(Subscription_StockImp.class,subscriptionStock);
	}

	@Override
	public Subscription_StockImp getFirstAvailableSubscription(long subscriptionID) {
		// TODO Auto-generated method stub
		//return the first available subscription in stock for purchase
		//in the query use select for update for block the register
		//TODO: Add limit to the query to increase performance
		
		String queryString ="SELECT ss FROM Subscription_StockImp ss "
				+ " , SubscriptionPkgImp s "
				+ " WHERE ss.subscription.ID = s.ID AND s.ID=(:subscriptionID)"
				+ " AND ss.stock>0 ";
				//+ " and ss.status='AVAILABLE')";
		
		//hibernateTemplate.setMaxResults(1);
		List<Subscription_StockImp> list = hibernateTemplate.findByNamedParam(
				queryString, "subscriptionID", subscriptionID);
		
		if(list.size()>0){
			Subscription_StockImp ss = list.get(0);
			Subscription_StockImp ps=hibernateTemplate.load(Subscription_StockImp.class, ss.getID(), LockMode.UPGRADE);
		
			String updateString ="UPDATE Subscription_StockImp ss "
					+ "  SET ss.stock=ss.stock-1 "
					+ "WHERE ss.ID = ?";
			int numberInstanceUpdate=hibernateTemplate.bulkUpdate(updateString, ss.getID());
			if(numberInstanceUpdate!=0){
				//there is not item in stock
				return ss;
			}else{
				return null;
			}
		}else{
			return null;	
		}	
		
	}
	
	public void modifySubscriptionStock(Subscription_StockImp subStock){
		hibernateTemplate.update(subStock);
	}
}
