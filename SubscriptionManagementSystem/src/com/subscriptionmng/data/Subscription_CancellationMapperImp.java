package com.subscriptionmng.data;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.subscriptionmng.model.admin.CustomerImp;
import com.subscriptionmng.model.admin.ItemImp;
import com.subscriptionmng.model.admin.Subscription_CancellationImp;

public class Subscription_CancellationMapperImp implements Subscription_CancellationMapper {
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@Override
	public Subscription_CancellationImp findByID(long ID) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(Subscription_CancellationImp.class,ID);
	}

	@Override
	public void insertSubscription_Cancellation(Subscription_CancellationImp subsCancellation) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(subsCancellation);
	}

	@Override
	public void modifySubscription_Cancellation(Subscription_CancellationImp subsCancellation) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(subsCancellation);
	}

	@Override
	public List<Subscription_CancellationImp> listSubscriptionCancellation() {
		// TODO Auto-generated method stub
		return hibernateTemplate.find("from Subscription_CancellationImp");
	}

	@Override
	public List<Subscription_CancellationImp> listSubscriptionCancellation(CustomerImp customer) {
		// TODO Auto-generated method stub
		String query=
				"SELECT sc FROM Subscription_CancellationImp sc "
				+ " WHERE sc.custSubscription.getCustomer().getID() = (:customerID)"
				+ " order by sc.dateReq asc";
		return hibernateTemplate.findByNamedParam(
				query, "customerID", customer.getID());
	}

	@Override
	public List<Subscription_CancellationImp> listSubscriptionCancellation(String Status) {
		// TODO Auto-generated method stub
		String query=
				"SELECT sc FROM Subscription_CancellationImp sc "
				+ " WHERE sc.reqStatus = (:status)"
				+ " order by sc.dateReq asc";
		return hibernateTemplate.findByNamedParam(
				query, "status", Status);
	}

}
