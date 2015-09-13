package com.subscriptionmng.data;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.subscriptionmng.model.admin.ItemImp;

public abstract class GenericMapper {
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public abstract Object findById(long ID);
	
}
