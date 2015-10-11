package com.subscriptionmng.data;

import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.subscriptionmng.model.admin.CustomerImp;
import com.subscriptionmng.model.admin.Customer_SubscriptionImp;
import com.subscriptionmng.model.security.UserImp;

public class CustomerMapperImp implements CustomerMapper{
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@Override
	public CustomerImp findById(long customer_ID) {
		// TODO Auto-generated method stub
		CustomerImp customer = null;
		customer = hibernateTemplate.get(CustomerImp.class, new Long(customer_ID));
		return customer;
	}

	public CustomerImp getCustomerByUsername(String username) {
		UserMapperImp userMapper = new UserMapperImp();
		userMapper.setSessionFactory(hibernateTemplate.getSessionFactory());
		
		UserImp user= userMapper.getUser(username);
		if(user != null){
			long customer_ID = user.getID();
			CustomerImp customer = hibernateTemplate.get(CustomerImp.class, new Long(customer_ID));
			return customer;
		}else{
			return null;
		}
	}
	
	@Override
	public List<Customer_SubscriptionImp> getCustomer_Subscription(CustomerImp customer) {
		// TODO Auto-generated method stub
		String query=
				"SELECT cs FROM Customer_SubscriptionImp cs "
				+ " WHERE cs.customer.ID = (:customerID)"
				+ " order by cs.datePurchase desc";
		
		List<Customer_SubscriptionImp> cusSubs =  hibernateTemplate.findByNamedParam(
						query, "customerID", customer.getID());
		return cusSubs;
	}

}
