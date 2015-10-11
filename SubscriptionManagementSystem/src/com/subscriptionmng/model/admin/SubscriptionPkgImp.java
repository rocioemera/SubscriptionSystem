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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Check;

import com.subscriptionmng.data.SubscriptionMapperImp;
import com.subscriptionmng.service.SubscriptionPkg;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
@Table(name="Subscriptionpkg")
public class SubscriptionPkgImp implements Serializable, SubscriptionPkg{
	
	private static final long serialVersionUID = 1L;
	
	private Long ID;
	private String name;
	private String description;
	private int price;
	private List<ItemImp> items = new ArrayList<ItemImp>();
	private Integer stock;
	private Long version;
	
	//relation subscription customer
	private Set<Customer_SubscriptionImp> customerSubscription = new HashSet<Customer_SubscriptionImp>(); 
	
	//save the individual stock of each subscription
	private Set<Subscription_StockImp> subcriptonStock = new HashSet<Subscription_StockImp>();
	
	//DAO for subscription
	private SubscriptionMapperImp subscriptionMapper;
	
	
	@Id
    @Column(name = "subscriptionpkg_ID")
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
	
	@OneToMany(mappedBy = "subscription")
	public Set<Customer_SubscriptionImp> getCustomerSubscription() {
		return customerSubscription;
	}
	public void setCustomerSubscription(Set<Customer_SubscriptionImp> customerSubscription) {
		this.customerSubscription = customerSubscription;
	}
	
	@OneToMany(mappedBy = "subscription")	
	public Set<Subscription_StockImp> getSubcriptonStock() {
		return subcriptonStock;
	}
	public void setSubcriptonStock(Set<Subscription_StockImp> subcriptonStock) {
		this.subcriptonStock = subcriptonStock;
	}
	
	@Column(name="stock",nullable = false,columnDefinition = "INT default 0")
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
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
				+ ", items=" + items + 
				", version " + getVersion()+ "]";
	}

	@Override
	public boolean equals(Object other){
		boolean result=false;
		if(other instanceof SubscriptionPkgImp && ((SubscriptionPkgImp)other).getID() == this.getID()){
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
	
	public List<SubscriptionPkgImp> listSubscription(String search){
		return subscriptionMapper.listSubscription(search);
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
