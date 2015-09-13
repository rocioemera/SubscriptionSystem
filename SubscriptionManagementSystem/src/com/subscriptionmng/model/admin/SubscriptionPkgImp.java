package com.subscriptionmng.model.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.subscriptionmng.data.SubscriptionMapperImp;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
@Table(name="Subscriptionpkg")
public class SubscriptionPkgImp implements Serializable, SubscriptionPkg{
	
	private static final long serialVersionUID = 1L;
	
	private long ID;
	private String name;
	private String description;
	private int price;
	private List<ItemImp> items = new ArrayList<ItemImp>();
	
	//DAO for subscription
	private SubscriptionMapperImp subscriptionMapper;
	
	
	@Id
    @Column(name = "subscriptionpkg_ID")
    @GeneratedValue
    public long getID() {
		return ID;
	}

	public void setID(long iD) {
		this.ID = iD;
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
	
	@Column(name = "price")
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	@ManyToMany
	@JoinTable(name="Subscriptionpkg_Item",
		joinColumns={@JoinColumn(name="subscriptionpkg_ID")},
		inverseJoinColumns={@JoinColumn(name="item_ID")})
	public List<ItemImp> getItems() {
		return items;
	}
	
	public void setItems(List<ItemImp> items) {
		this.items = items;
	}
	
	@Transient
	public SubscriptionMapperImp getSubscriptionMapper() {
		return subscriptionMapper;
	}

	public void setSubscriptionMapper(SubscriptionMapperImp subscriptionMapper) {
		this.subscriptionMapper = subscriptionMapper;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SubscriptionPkg [ID=" + ID + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", items=" + items + "]";
	}

	@Override
	public boolean equals(Object other){
		boolean result=false;
		if(other instanceof ItemImp && ((SubscriptionPkgImp)other).getID() == this.getID()){
		    result=true;
		}
		return result;
	}
	
	//INTERFACES
	public boolean createNewSubscription(SubscriptionPkgImp subs){
		//TODO: ADD VALIDATIONS
		subscriptionMapper.insertSubscription(subs);
		return true;
	}
	
	public boolean modifySubscription(SubscriptionPkgImp subs) {
		//TODO: ADD VALIDATIONS
		subscriptionMapper.updateSubscription(subs);
		return false;
	}
	
	public List<SubscriptionPkgImp> listSubscription(){
		return subscriptionMapper.listSubscription();
	}
	
	public boolean removeSubscription(long ID){
		//TODO: ADD VALIDATIONS
		subscriptionMapper.deleteSubscription(ID);
		return true;
	}
	
	public SubscriptionPkgImp readSubscription(long ID){
		return subscriptionMapper.findById(ID);
	}
	
	public SubscriptionPkgImp readSubscriptionWithItems(long ID){
		return subscriptionMapper.findByIdWithItems(ID);
	}

}
