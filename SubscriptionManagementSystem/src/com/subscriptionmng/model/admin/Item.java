package com.subscriptionmng.model.admin;

import java.util.List;

public interface Item {
	public boolean createNewItem(ItemImp item);
	public List<ItemImp> listItem();
	public boolean modifyItem(ItemImp item);
	public boolean removeItem(long ID);
	public ItemImp readItem(long ID);
}
