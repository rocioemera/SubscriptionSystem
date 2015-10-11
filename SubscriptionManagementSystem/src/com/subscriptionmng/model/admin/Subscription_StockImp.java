package com.subscriptionmng.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Check;

import com.subscriptionmng.data.Subscription_StockMapperImp;
import com.subscriptionmng.exception.NoStockExeption;
import com.subscriptionmng.service.Subscription_Stock;

@Entity
@Table(name="SUBSCRIPTION_STOCK")
@Check(constraints = "stock >= 0")
public class Subscription_StockImp implements Subscription_Stock{
	private Long ID;
	private SubscriptionPkgImp subscription;
	private int stock;
	//private Long version;
	
	private Subscription_StockMapperImp subscriptionStockMapper;
	
	@Id
    @Column(name = "subscriptionStock_ID")
    @GeneratedValue
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	
	/**@Version
    @Column(name="OPTLOCK",nullable = false,columnDefinition = "BIGINT default 0")
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}*/
	
	@OneToOne
    @JoinColumn(name = "SUBSCRIPTION_ID")
	public SubscriptionPkgImp getSubscription() {
		return subscription;
	}
	public void setSubscription(SubscriptionPkgImp subscription) {
		this.subscription = subscription;
	}
	
	@Column(name="STOCK")
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@Transient
	public Subscription_StockMapperImp getSubscriptionStockMapper() {
		return subscriptionStockMapper;
	}
	public void setSubscriptionStockMapper(Subscription_StockMapperImp subscriptionStockMapper) {
		this.subscriptionStockMapper = subscriptionStockMapper;
	}
	
	/**@Override
	public String toString() {
		return "Subscription_Stocl [ID=" + ID + ", Subscription name=" + subscription.getName() +
				", Stokc " + stock +
				", version " + getVersion()+ "]";
	}*/
	
	//INTERFACE
	//this function is called when user want to do purchase a subscription
	//return the register that was separate for the user
	//the returned register is locked at the begin of the purchase transaction
	@Override
	public Subscription_Stock readSubscriptionStock(long subscriptionStock) 
	{
		// TODO Auto-generated method stub
		return subscriptionStockMapper.findByID(subscriptionStock);
	}
	
	@Override
	public void separateSubscription(long idSubscription) throws Exception{
		// TODO Auto-generated method stub
		//use select for update together with read_comminited isolation
		//to avoid the lost of information
		//once the ticket is selected other process can not select the same
		Subscription_StockImp subsStock
				=subscriptionStockMapper.getFirstAvailableSubscription(idSubscription);
		if(subsStock==null){
			System.out.println("There is not susbcription in stock");
			throw new RuntimeException("There is not susbcription in stock");
		}
		
		//separate the subscription to the user
		//subsStock.setStatus("UNAVAILABLE");
		//subscriptionStockMapper.modifySubscriptionStock(subsStock);
	}
	
}
