package com.subscriptionmng.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
//import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.subscriptionmng.converter.CustomFormBinder;
import com.subscriptionmng.data.ItemMapperImp;
import com.subscriptionmng.data.SubscriptionMapperImp;
import com.subscriptionmng.model.admin.ItemImp;
import com.subscriptionmng.model.admin.SubscriptionPkgImp;
import com.subscriptionmng.model.menu.Menu;

//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.ModelAttribute;


public class SubscriptionController extends MultiActionController{
	private SubscriptionPkgImp subscription;
	private ItemImp item;
	public final String MODULE_NAME="Subscription";
	
	public SubscriptionPkgImp getSubscription() {
		return subscription;
	}

	public void setSubscription(SubscriptionPkgImp subscription) {
		this.subscription = subscription;
	}

	public ItemImp getItem() {
		return item;
	}

	public void setItem(ItemImp item) {
		this.item = item;
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder){
    	binder.registerCustomEditor(List.class, "items", 
    			new CustomFormBinder(item.getItemMapper(), List.class));
    }
	
	public ModelAndView addSubscription(HttpServletRequest request,
		HttpServletResponse response, SubscriptionPkgImp subscriptionV) throws Exception {
		System.out.println(subscription);
		subscription.createNewSubscription(subscriptionV);
		return new ModelAndView("redirect:listSubscription.htm");
    }
	
	public ModelAndView updateSubscription(HttpServletRequest request,
		HttpServletResponse response, SubscriptionPkgImp subscriptionV) throws Exception {
		System.out.println(subscription);
		subscription.modifySubscription(subscriptionV);
		return new ModelAndView("redirect:listSubscription.htm");
    }
	
	public ModelAndView displaySubscription(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
    	
		modelMap.addAttribute("MODULE_NAME",MODULE_NAME);
    	modelMap.addAttribute("AppMenu",getSubMenuModule());
    	modelMap.addAttribute("REQ_ACTION" ,"addSubscription.htm");
    	modelMap.addAttribute("FORM_SUBMIT_VALUE", "Register");
    	
    	//display empty form
    	modelMap.addAttribute("subscriptionV", new SubscriptionPkgImp());
    	modelMap.addAttribute("itemList",item.listItem());
		return new ModelAndView("formSubscriptionpkg", modelMap); 
    }
	
	public ModelAndView editSubscription(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
    	
		modelMap.addAttribute("MODULE_NAME",MODULE_NAME);
    	modelMap.addAttribute("AppMenu",getSubMenuModule());
    	modelMap.addAttribute("REQ_ACTION" ,"updateSubscription.htm");
    	modelMap.addAttribute("FORM_SUBMIT_VALUE", "Update");
    	
    	long ID=Long.parseLong(request.getParameter("ID"));
    	System.out.println("1");
    	//fill the form with subscription info
    	SubscriptionPkgImp esub=subscription.readSubscriptionWithItems(ID);
    	
    	modelMap.addAttribute("subscriptionV", esub);
    	//send the list of all items
    	modelMap.addAttribute("itemList", item.listItem());
    	
		return new ModelAndView("formSubscriptionpkg", modelMap); 
    }
	
	
	
	public ModelAndView deleteSubscription(HttpServletRequest request,
			HttpServletResponse response){
		long ID=Long.parseLong(request.getParameter("ID"));
		subscription.removeSubscription(ID);
		return new ModelAndView("redirect:listSubscription.htm");
	}
     
	public ModelAndView listSubscription(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		
		modelMap.addAttribute("MODULE_NAME",MODULE_NAME);
		modelMap.addAttribute("AppMenu",getSubMenuModule());
		
		modelMap.addAttribute("subscriptionList",subscription.listSubscription());
		return new ModelAndView("reportSubscriptionpkg", modelMap);
	}
	
	//FUNCTION FOR TEH VIEW
	
	public List<Menu> getSubMenuModule(){
		List<Menu> m=new ArrayList<Menu>();
		m.add(new Menu("List Subscription","subscriptionPkg/listSubscription.htm"));
		m.add(new Menu("Create Subscription","subscriptionPkg/displaySubscription.htm"));
		return m;
	}
	
}
