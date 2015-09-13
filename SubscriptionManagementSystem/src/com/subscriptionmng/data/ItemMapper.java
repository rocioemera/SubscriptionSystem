package com.subscriptionmng.data;

import java.util.List;

import com.subscriptionmng.model.admin.ItemImp;

public interface ItemMapper {
	public ItemImp findById(long item_ID);
	public boolean insertItem(ItemImp item);
	public boolean updateItem(ItemImp item);
	public List<ItemImp> listItem();
	public boolean deleteItem(long item_ID);
}
