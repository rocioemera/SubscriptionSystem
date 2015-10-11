package com.subscriptionmng.model.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.subscriptionmng.data.ItemMapperImp;
import com.subscriptionmng.data.SubscriptionMapperImp;
import com.subscriptionmng.service.Item;

@Entity
@Table(name="Item")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "itemType",discriminatorType = DiscriminatorType.STRING)
public class ItemImp implements Serializable,Item{
	private Long ID;
	private String name;
	private String description;
	private String itemType;
	private Long version;
	//private ArrayList<SubscriptionPkg> subscriptionPkg; 
	
	//DAO for subscription
	private ItemMapperImp itemMapper;
	
	@Id
    @Column(name = "item_ID")
    @GeneratedValue
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		this.ID = iD;
	}
	
	@Version
    @Column(name="OPTLOCK",nullable = false,columnDefinition = "BIGINT default 0")
	//to manage optimistic lock strategies in long conversations
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "itemType",insertable=false, updatable=false)
	public String getItemType(){
		return itemType;
	}
	
	public void setItemType(String type) {
		this.itemType = type;
	}
	
	@Column(name = "stock",insertable=false, updatable=false)
	public Integer getStock(){
		return 0;
	}
	
	public void setStock(Integer stock){
		
	}
	
	@Column(name = "family",insertable=false, updatable=false)
	public String getFamily(){
		return "";
	}
	
	public void setFamily(String family){
		
	}
	
	@Override
	public boolean equals(Object other){
		boolean result=false;
		if(other instanceof ItemImp && ((ItemImp)other).getID() == this.getID()){
		    result=true;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Item [ID=" + ID + ", name=" + name + ", description=" + description + ", itemType=" + itemType + "]";
	}

	//@ManyToMany(mappedBy = "items")
	//public ArrayList<SubscriptionPkg> getSubscriptionPkg() {
	//	return subscriptionPkg;
	//}

	//public void setSubscriptionPkg(ArrayList<SubscriptionPkg> subscriptionPkg) {
	//	this.subscriptionPkg = subscriptionPkg;
	//}

	@Transient
	public ItemMapperImp getItemMapper() {
		return itemMapper;
	}

	public void setItemMapper(ItemMapperImp itemMapper) {
		this.itemMapper = itemMapper;
	}
	
//INTERFACES

	@Override
	public boolean createNewItem(ItemImp item) {
		// DO VALIDATION
		itemMapper.insertItem(item);
		return false;
	}

	@Override
	public List<ItemImp> listItem() {
		// TODO Auto-generated method stub
		return itemMapper.listItem();
	}

	@Override
	public boolean modifyItem(ItemImp item) {
		// TODO Auto-generated method stub
		item.toString();
		itemMapper.updateItem(item);
		return false;
	}

	@Override
	public boolean removeItem(long ID) {
		// TODO Auto-generated method stub
		//First check if the item does not belong to any subscription
		//If the item belong to a subscription it can not be update
		itemMapper.deleteItem(ID);
		return false;
	}
	
	public ItemImp readItem(long ID){
		return itemMapper.findById(ID);
	}
	
}
