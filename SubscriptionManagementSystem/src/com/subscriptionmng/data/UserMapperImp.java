package com.subscriptionmng.data;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.subscriptionmng.model.admin.SubscriptionPkgImp;
import com.subscriptionmng.model.security.UserImp;

public class UserMapperImp extends GenericMapper implements UserMapper{
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public UserImp findById(long user_ID){
		UserImp user = null;
		user = hibernateTemplate.get(UserImp.class, new Long(user_ID));
		return user;
	}
	
	public UserImp getUser(String username){
		//subscriptionPkg = hibernateTemplate.get(SubscriptionPkgImp.class, new Long(subscriptionPkg_ID));
		String query="SELECT u FROM UserImp u WHERE u.username = (:username)";
		
		List<UserImp> userImp= hibernateTemplate.findByNamedParam(query, 
				"username", username);
		
		if(userImp.size()>0){
			System.out.println(userImp.size());
			return userImp.get(0);
		}else{
			System.out.println(":(");
			return null;
		}
	}
}
