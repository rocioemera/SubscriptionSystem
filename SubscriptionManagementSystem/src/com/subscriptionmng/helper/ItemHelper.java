package com.subscriptionmng.helper;

import com.subscriptionmng.model.admin.ItemImp;
import com.subscriptionmng.model.admin.ItemProduct;
import com.subscriptionmng.model.admin.ItemService;

public class ItemHelper {
	private long ID;
	private String name;
	private String description;
	private String itemType;
	private String family;
	private Integer stock;
	
	public ItemHelper(){
		
	}
	
	public ItemHelper(long ID, String name, 
			String description, String itemType, String family, Integer stock){
		this.ID=ID;
		this.name=name;
		this.description=description;
		this.itemType=itemType;
		this.family=family;
		this.stock=stock;
	}
	
	public ItemHelper(ItemProduct item){
		this(item.getID(),item.getName(),item.getDescription(),
				item.getItemType(),item.getFamily(),item.getStock());
	}
	
	public ItemHelper(ItemService item){
		this(item.getID(),item.getName(),item.getDescription(),
				item.getItemType(),"",0);
	}
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public ItemImp castToItem(){
		ItemImp item=null;
		if(itemType.equals("PRODUCT")){
			item=new ItemProduct();
		}else{
			item=new ItemService();
		}
		item.setID(ID);
		item.setName(name);
		item.setDescription(description);
		item.setFamily(family);
		item.setStock(stock);
		item.setItemType(itemType);
		return item;
	}
	
	public ItemHelper castToItemHelper(ItemImp item){
		if(item.getItemType().equals("PRODUCT")){
			return new ItemHelper((ItemProduct)item);
		}else{
			return new ItemHelper((ItemService)item);
		}
	}

	@Override
	public String toString() {
		return "ItemHelper [ID=" + ID + ", name=" + name + ", description=" + description + ", itemType=" + itemType
				+ ", family=" + family + ", stock=" + stock + "]";
	}
	
	
}
