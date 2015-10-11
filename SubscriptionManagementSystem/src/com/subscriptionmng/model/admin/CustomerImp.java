package com.subscriptionmng.model.admin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.subscriptionmng.data.CustomerMapperImp;
import com.subscriptionmng.model.security.UserImp;
import com.subscriptionmng.service.Customer;

@Entity
@Table(name="CUSTOMER")
@PrimaryKeyJoinColumn(name="USER_ID")
public class CustomerImp extends UserImp implements Customer{
	
	private static final long serialVersionUID = 1L;
	private String fullname;
	private String address;
	private String phoneNumber;
	
	private Set<Customer_SubscriptionImp> customerSubscription = new HashSet<Customer_SubscriptionImp>();
	
	private CustomerMapperImp customerMapper;
	
	@Column(name = "FULLNAME")
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "PHONENUMBER")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@OneToMany(mappedBy = "customer")
	public Set<Customer_SubscriptionImp> getCustomerSubscription() {
		return customerSubscription;
	}
	
	public void setCustomerSubscription(Set<Customer_SubscriptionImp> customerSubscription) {
		this.customerSubscription = customerSubscription;
	}
	
	@Transient
	public CustomerMapperImp getCustomerMapper() {
		return customerMapper;
	}
	
	public void setCustomerMapper(CustomerMapperImp customerMapper) {
		this.customerMapper = customerMapper;
	}
	
	@Override
	public boolean equals(Object other){
		boolean result=false;
		System.out.println(11);
		if(other instanceof CustomerImp && ((CustomerImp)other).getID() == this.getID()){
			System.out.println(12);
			result=true;
		}
		System.out.println(13);
		return result;
	}
	
	@Override
	public String toString() {
		return "Customer [ ID="+getID()+" fullname=" + fullname + ", address=" + address + 
				", phoneNumber" + phoneNumber + 
				", version " + getVersion()+ "]";
	}
	
	//INTERFACES
	
	@Override
	public CustomerImp readCustomer(long customer_ID) {
		// TODO Auto-generated method stub
		return customerMapper.findById(customer_ID);
	}
	@Override
	public CustomerImp readCustomer(String username) {
		// TODO Auto-generated method stub
		return customerMapper.getCustomerByUsername(username);
	}
	@Override
	public List<Customer_SubscriptionImp> getCustomer_SubscriptionImp(CustomerImp customer) {
		// TODO Auto-generated method stub
		return customerMapper.getCustomer_Subscription(customer);
	}
}
