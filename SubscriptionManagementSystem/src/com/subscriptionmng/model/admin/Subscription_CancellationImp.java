package com.subscriptionmng.model.admin;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.subscriptionmng.data.Customer_SubscriptionMapperImp;
import com.subscriptionmng.data.Subscription_CancellationMapperImp;
import com.subscriptionmng.exception.CustomGenericException;
import com.subscriptionmng.service.Subscription_Cancellation;

@Entity
@Table(name="SUBSCRIPTION_CANCELLATION")
public class Subscription_CancellationImp implements Subscription_Cancellation{
	
	private Long ID;
	private Customer_SubscriptionImp custSubscription;
	private Date dateReq;
	private Date dateApproval;
	private String reqStatus;
	private Long version;
	
	private Subscription_CancellationMapperImp subsCancellationMapper;
	private Customer_SubscriptionMapperImp custSubscriptionMapper;

	@Id
    @Column(name = "CANCELLATION_ID")
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

	@OneToOne
    @JoinColumn(name = "CUSTOMER_SUBSCRIPTION_ID")
	public Customer_SubscriptionImp getCustSubscription() {
		return custSubscription;
	}

	public void setCustSubscription(Customer_SubscriptionImp custSubscription) {
		this.custSubscription = custSubscription;
	}

	@Column(name = "DATE_REQ")
	@Temporal(TemporalType.DATE)
	public Date getDateReq() {
		return dateReq;
	}

	public void setDateReq(Date dateReq) {
		this.dateReq = dateReq;
	}

	@Column(name = "DATE_APPROVAL")
	@Temporal(TemporalType.DATE)
	public Date getDateApproval() {
		return dateApproval;
	}

	public void setDateApproval(Date dateApproval) {
		this.dateApproval = dateApproval;
	}

	@Column(name = "APPROVAL_STATUS")
	public String getReqStatus() {
		return reqStatus;
	}

	public void setReqStatus(String ReqStatus) {
		this.reqStatus = ReqStatus;
	}

	@Transient
	public Subscription_CancellationMapperImp getSubsCancellationMapper() {
		return subsCancellationMapper;
	}

	public void setSubsCancellationMapper(Subscription_CancellationMapperImp subsCancellationMapper) {
		this.subsCancellationMapper = subsCancellationMapper;
	}
	
	@Transient
	public Customer_SubscriptionMapperImp getCustSubscriptionMapper() {
		return custSubscriptionMapper;
	}

	public void setCustSubscriptionMapper(Customer_SubscriptionMapperImp custSubscriptionMapper) {
		this.custSubscriptionMapper = custSubscriptionMapper;
	}

	@Override
	public boolean equals(Object other){
		boolean result=false;
		if(other instanceof Subscription_CancellationImp 
				&& ((Subscription_CancellationImp)other).getID() == this.getID()){
		    result=true;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Cancellation [ customer=" + custSubscription.getCustomer().getFullname() 
				+ ", subscription=" + custSubscription.getSubscription().getName()
				+ ", dateReq=" + dateReq
				+ ", status" + reqStatus + 
				", version " + getVersion()+ "]";
	}
	
	//INTERFACE
	public Subscription_CancellationImp readSubscriptionCancellation(long ID){
		return subsCancellationMapper.findByID(ID);
	}
	
	public List<Subscription_CancellationImp> listSubscriptionCancellation(){
		return subsCancellationMapper.listSubscriptionCancellation();
	}
	
	public List<Subscription_CancellationImp> listSubscriptionCancellation(String Status){
		return subsCancellationMapper.listSubscriptionCancellation(Status);
	}
	@Override
	public void requestCancellation(Customer_SubscriptionImp custSubscripton) {
		// TODO Auto-generated method stub
		if(!custSubscripton.getStatus().equals("ACTIVE")){
			//subscription must be active to be cancelled
			//throw exception
			System.out.println("Subscription is no active 2");
			throw new RuntimeException("Subscription is no active");
		}
		
		Subscription_CancellationImp subsCancell=new Subscription_CancellationImp();
		subsCancell.setCustSubscription(custSubscripton);
		subsCancell.setDateReq(new Date());
		subsCancell.setReqStatus("PENDING");
		System.out.println(subsCancell);
		subsCancellationMapper.insertSubscription_Cancellation(subsCancell);
	
		//modify custSubscripton state to PENDING
		custSubscripton.setStatus("PENDING");
		System.out.println(custSubscripton);
		custSubscriptionMapper.modifyCustomer_Subscription(custSubscripton);
	}

	@Override
	public void approveCancellation(Subscription_CancellationImp subsCancellation) {
		//TODO: WHERE I HAVE TO CHECK THAT THE REQUEST CANCELLATION REALLY EXIST?
		
		if(!subsCancellation.getReqStatus().equals("PENDING")){
			//throw exception
			throw new RuntimeException("Invalid Subscription");
		}
		
		subsCancellation.setReqStatus("APPROVED");
		subsCancellation.setDateApproval(new Date());
		subsCancellationMapper.modifySubscription_Cancellation(subsCancellation);
		
		//modify customer subscription status to CANCELL
		Customer_SubscriptionImp custSubscription=subsCancellation.getCustSubscription();
		custSubscription.setStatus("CANCELLED");
		custSubscriptionMapper.modifyCustomer_Subscription(custSubscription);
	}
}
