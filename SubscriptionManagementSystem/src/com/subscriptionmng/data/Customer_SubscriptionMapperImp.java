package com.subscriptionmng.data;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.subscriptionmng.model.admin.Customer_SubscriptionImp;
import com.subscriptionmng.model.admin.ItemImp;

public class Customer_SubscriptionMapperImp implements Customer_SubscriptionMapper{
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public Customer_SubscriptionImp findById(long custSub_ID) {
		// TODO Auto-generated method stub
		Customer_SubscriptionImp custSub = null;
		custSub = hibernateTemplate.get(Customer_SubscriptionImp.class, new Long(custSub_ID));
		return custSub;
	}

	@Override
	public void insertCustomer_Subscription(Customer_SubscriptionImp custSub) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(custSub);
	}

	@Override
	public void modifyCustomer_Subscription(Customer_SubscriptionImp custSub) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(custSub);
	}
	
	
}
