package com.subscriptionmng.model.admin;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.print.attribute.standard.DateTimeAtCreation;

import com.subscriptionmng.data.Customer_SubscriptionMapperImp;
import com.subscriptionmng.service.Customer_Subscription;

@Entity
@Table(name="CUSTOMER_SUBSCRIPTION")
public class Customer_SubscriptionImp implements Customer_Subscription{
	private Long ID;
	private CustomerImp customer;
	private SubscriptionPkgImp subscription;
	private Date datePurchase;
	private String status;
	private Long version;
	
	private Customer_SubscriptionMapperImp custSubsMapp;
	
	public Customer_SubscriptionImp(){
		
	}
	
	public Customer_SubscriptionImp(SubscriptionPkgImp subscription
			,CustomerImp customer, Date datePurchase, String status){
		this.subscription=subscription;
		this.customer=customer;
		this.datePurchase=datePurchase;
		this.status=status;
	}
	
	@Id
    @Column(name = "CUSTOMER_SUBSCRIPTION_ID")
    @GeneratedValue
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
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
	
	@ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
	public CustomerImp getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerImp customer) {
		this.customer = customer;
	}
	
	@ManyToOne
    @JoinColumn(name = "subscriptionpkg_ID")
	public SubscriptionPkgImp getSubscription() {
		return subscription;
	}
	public void setSubscription(SubscriptionPkgImp subscription) {
		this.subscription = subscription;
	}
	
	@Column(name = "DATE_PURCHASE")
	@Temporal(TemporalType.DATE)
	public Date getDatePurchase() {
		return datePurchase;
	}
	public void setDatePurchase(Date datePurchase) {
		this.datePurchase = datePurchase;
	}
	
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Transient
	public Customer_SubscriptionMapperImp getCustSubsMapp() {
		return custSubsMapp;
	}
	public void setCustSubsMapp(Customer_SubscriptionMapperImp custSubsMapp) {
		this.custSubsMapp = custSubsMapp;
	}
	
	@Override
	public boolean equals(Object other){
		boolean result=false;
		if(other instanceof Customer_SubscriptionImp 
				&& ((Customer_SubscriptionImp)other).getID() == this.getID()){
		    result=true;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Customer [ customerID=" + customer.getID() 
				+ ", subscription=" + subscription.getName()
				+ ", dateCreation=" + datePurchase
				+ ", status" + status + 
				", version " + getVersion()+ "]";
	}
	
	//INTERFACE
	public void createNewCustomerSubscription(Customer_SubscriptionImp custSub){
		custSubsMapp.insertCustomer_Subscription(custSub);
	}

	@Override
	public Customer_SubscriptionImp readCustomerSubscription(long ID) {
		// TODO Auto-generated method stub
		return custSubsMapp.findById(ID);
	}
	
	
}
