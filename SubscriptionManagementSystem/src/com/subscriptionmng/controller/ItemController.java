package com.subscriptionmng.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.subscriptionmng.data.ItemMapperImp;
import com.subscriptionmng.helper.ItemHelper;
import com.subscriptionmng.model.admin.ItemImp;
import com.subscriptionmng.model.admin.ItemProduct;
import com.subscriptionmng.model.menu.Menu;
import com.subscriptionmng.service.Item;

public class ItemController extends MultiActionController{
	private Item item;
	public final String MODULE_NAME="Items";
	
    public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ModelAndView addItem(HttpServletRequest request,
		HttpServletResponse response, ItemHelper itemV) throws Exception {
		item.createNewItem(itemV.castToItem());
		return new ModelAndView("redirect:listItem.htm");
    }
    
    public ModelAndView updateItem(HttpServletRequest request,
		HttpServletResponse response, ItemHelper itemV) throws Exception {
    	item.modifyItem(itemV.castToItem());
		return new ModelAndView("redirect:listItem.htm");
    } 
    
    //@RequestMapping(value= "/person/add", method = RequestMethod.POST)
    public ModelAndView displayItem(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		
		modelMap.addAttribute("MODULE_NAME",MODULE_NAME);
		modelMap.addAttribute("AppMenu",getSubMenuModule());
		modelMap.addAttribute("FORM_SUBMIT_VALUE", "Register");
		modelMap.addAttribute("REQ_ACTION" ,"addItem.htm");
		
    	//display empty form
    	modelMap.addAttribute("itemV", new ItemHelper());
		return new ModelAndView("formItem", modelMap); 
    }
     
	public ModelAndView listItem(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		
		modelMap.addAttribute("MODULE_NAME",MODULE_NAME);
		modelMap.addAttribute("AppMenu",getSubMenuModule());
		
		modelMap.addAttribute("itemList", item.listItem());
		//modelMap.addAttribute("item", new ItemHelper());
		return new ModelAndView("reportItem", modelMap);
	}
	
	public ModelAndView editItem(HttpServletRequest request,
			HttpServletResponse response){
		long itemID=Long.parseLong(request.getParameter("ID"));
		logger.info(itemID);
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("MODULE_NAME",MODULE_NAME);
		modelMap.addAttribute("AppMenu",getSubMenuModule());
		modelMap.addAttribute("FORM_SUBMIT_VALUE", "Update");
		modelMap.addAttribute("REQ_ACTION" ,"updateItem.htm");
		
    	//display form with data
    	modelMap.addAttribute("itemV", 
    			new ItemHelper().castToItemHelper(item.readItem(itemID)));
		return new ModelAndView("formItem", modelMap); 
	}
	
	public ModelAndView deleteItem(HttpServletRequest request,
			HttpServletResponse response){
		long itemID=Long.parseLong(request.getParameter("ID"));
		logger.info(itemID);
		item.removeItem(itemID);
		return new ModelAndView("redirect:listItem.htm");
	}
	
	//FUNCTION FOR TEH VIEW
	public List<Menu> getSubMenuModule(){
		List<Menu> m=new ArrayList<Menu>();
		m.add(new Menu("List Item","admin/item/listItem.htm"));
		m.add(new Menu("Create Item","admin/item/displayItem.htm"));
		return m;
	}
}
