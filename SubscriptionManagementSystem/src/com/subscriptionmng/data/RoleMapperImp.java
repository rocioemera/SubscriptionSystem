package com.subscriptionmng.data;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.subscriptionmng.model.security.RoleImp;

public class RoleMapperImp extends GenericMapper implements RoleMapper{
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public RoleImp findById(long role_ID){
		RoleImp role = null;
		role = hibernateTemplate.get(RoleImp.class, new Long(role_ID));
		return role;
	}
}
