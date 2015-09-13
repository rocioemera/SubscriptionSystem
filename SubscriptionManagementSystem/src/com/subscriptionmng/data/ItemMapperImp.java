package com.subscriptionmng.data;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.subscriptionmng.model.admin.ItemImp;
import com.subscriptionmng.model.admin.ItemProduct;
import com.subscriptionmng.model.admin.ItemService;

public class ItemMapperImp extends GenericMapper implements ItemMapper{
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public ItemImp findById(long item_ID){
		ItemImp item = null;
		item = hibernateTemplate.get(ItemImp.class, new Long(item_ID));
		return item;
	}

	public boolean insertItem(ItemImp item) {
		hibernateTemplate.save(item);
		return true;
	}
	
	public boolean updateItem(ItemImp item) {
		item.toString();
		hibernateTemplate.update(item);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemImp> listItem() {
		return hibernateTemplate.find("from ItemImp");
	}
	
	public boolean deleteItem(long item_ID){
		ItemImp item = hibernateTemplate.load(ItemImp.class, new Long(item_ID));
		if(item!=null){
			hibernateTemplate.delete(item);
		}
		return true;
	}
}
