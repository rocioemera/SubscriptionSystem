package com.subscriptionmng.service;

import java.util.List;

import com.subscriptionmng.data.ItemMapperImp;
import com.subscriptionmng.model.admin.ItemImp;

public interface Item {
	public boolean createNewItem(ItemImp item);
	public List<ItemImp> listItem();
	public boolean modifyItem(ItemImp item);
	public boolean removeItem(long ID);
	public ItemImp readItem(long ID);
}
