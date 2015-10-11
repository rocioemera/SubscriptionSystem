package com.subscriptionmng.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.subscriptionmng.exception.CustomGenericException;
import com.subscriptionmng.model.admin.CustomerImp;
import com.subscriptionmng.model.admin.Customer_SubscriptionImp;
import com.subscriptionmng.model.admin.PurchaseSubscriptionImp;
import com.subscriptionmng.model.admin.SubscriptionPkgImp;
import com.subscriptionmng.model.admin.Subscription_CancellationImp;
import com.subscriptionmng.service.Customer;
import com.subscriptionmng.service.Customer_Subscription;
import com.subscriptionmng.service.PurchaseSubscription;
import com.subscriptionmng.service.SubscriptionPkg;
import com.subscriptionmng.service.Subscription_Cancellation;
import com.subscriptionmng.service.Subscription_Stock;

public class CustomerManageController  extends MultiActionController{
	private SubscriptionPkg subscription;
	private Customer customer;
	private Customer_Subscription cusSub;
	private Subscription_Cancellation subsCancellation;
	private PurchaseSubscription purchaseSubscription;
	
	//html message
	private String succesMessage="";
	private String errorMessage="";
	
	public final String MODULE_NAME="Home";
	
	public CustomerManageController(){
		succesMessage="";
		errorMessage="";
	}
	
	public SubscriptionPkg getSubscription() {
		return subscription;
	}
	public void setSubscription(SubscriptionPkg subscription) {
		this.subscription = subscription;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Customer_Subscription getCusSub() {
		return cusSub;
	}
	public void setCusSub(Customer_Subscription cusSub) {
		this.cusSub = cusSub;
	}
	public Subscription_Cancellation getSubsCancellation() {
		return subsCancellation;
	}
	public void setSubsCancellation(Subscription_Cancellation subsCancellation) {
		this.subsCancellation = subsCancellation;
	}
	public PurchaseSubscription getPurchaseSubscription() {
		return purchaseSubscription;
	}
	public void setPurchaseSubscription(PurchaseSubscription purchaseSubscription) {
		this.purchaseSubscription = purchaseSubscription;
	}

	public ModelAndView homeView(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		
		ModelMap modelMap = new ModelMap();
		
		modelMap.addAttribute("MODULE_NAME",MODULE_NAME);
		modelMap.addAttribute("AppMenu",null);
		
		String searhParam=request.getParameter("search");
		if(searhParam!=null && !searhParam.equals("")){
			modelMap.addAttribute("subscriptionList",subscription.listSubscription(searhParam));
		}else{
			modelMap.addAttribute("subscriptionList",subscription.listSubscription());
		}
		
		//setHTLM message
		setMessageHTML(modelMap);
		return new ModelAndView("home", modelMap);
    }
	
	public ModelAndView purchase(HttpServletRequest request,
			HttpServletResponse response,SubscriptionPkgImp subscriptionV) throws Exception{
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		CustomerImp customerBuy = customer.readCustomer(userDetail.getUsername());
		
		System.out.println(customerBuy);
		
		//TODO: CHECK FOR EXCEPTION AND HANDLE THEM
		try{
			purchaseSubscription.purchaseSubscription(subscriptionV,customerBuy);
			//if we are her everything was ok
			setSuccessMessage("Subscription was purchase");
			return new ModelAndView("redirect:mySubscription.htm");
		}catch(Exception e){
			//show error page
			//no esta bien :(
			//throw new RuntimeException("SolrServerException occurred! Rollback my transaction.");
			
			//System.out.println(e.getMessage());
			setErrorMsg(e.getMessage());
			return new ModelAndView("redirect:mySubscription.htm");
		}
	}
	
	public ModelAndView mySubscription(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		ModelMap modelMap = new ModelMap();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		CustomerImp customerBuy = customer.readCustomer(userDetail.getUsername());
		
		modelMap.addAttribute("myCusSubsriptionList",customer.getCustomer_SubscriptionImp(customerBuy));
		
		//setHTLM message
		setMessageHTML(modelMap);
		
		return new ModelAndView("mySubsriptionList", modelMap); 
	}
	
	public ModelAndView requestCancellation(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		CustomerImp customerBuy = customer.readCustomer(userDetail.getUsername());
		
		try{
			long ID=Long.parseLong(request.getParameter("ID"));
			Customer_SubscriptionImp mySubscripton=cusSub.readCustomerSubscription(ID); 
			
			subsCancellation.requestCancellation(mySubscripton);
			//request was successfull continue
			setSuccessMessage("Request was Done");
		}catch(Exception e){
			//show error page
			System.out.println(e.getMessage());
			setErrorMsg(e.getMessage());
		}
		return new ModelAndView("redirect:mySubscription.htm");
	}
	
	public ModelAndView listPendingRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("reqCancellationList",subsCancellation.listSubscriptionCancellation("PENDING"));
		//setHTLM message
		setMessageHTML(modelMap);
		
		return new ModelAndView("listPendingRequest", modelMap); 
	}
	
	public ModelAndView approveCancellation(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		try{
			//TODO: Instead of pass the ID pass object to the function
			//The problem with that approach is that we have to set a converter
			//to translate the object??
			//CHECK is that is true
			long ID=Long.parseLong(request.getParameter("ID"));
			Subscription_CancellationImp subCancellationV = 
					subsCancellation.readSubscriptionCancellation(ID);
			subsCancellation.approveCancellation(subCancellationV);
			//request was successfull continue
			setSuccessMessage("Request was Done");
		}catch(Exception e){
			//show error page
			System.out.println(e.getMessage());
			setErrorMsg(e.getMessage());
		}
		
		return new ModelAndView("redirect:listPendingRequest.htm");
	}
	
	public void setErrorMsg(String message){
		errorMessage=message;
	}
	
	public void setSuccessMessage(String message){
		succesMessage=message;
	}
	
	public void setMessageHTML(ModelMap model){
		if(errorMessage!=null && !errorMessage.isEmpty()){
			model.addAttribute("ERROR_MSG", errorMessage);
		}
		if(succesMessage!=null && !succesMessage.isEmpty()){
			model.addAttribute("SUCCESS_MSG", succesMessage);
		}
		//set message to "" to avoid this get set the next invocation
		errorMessage="";
		succesMessage="";
	}
}
