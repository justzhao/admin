package com.web.action;

import org.apache.struts2.json.annotations.JSON;

import net.sf.json.JSONArray;


import com.opensymphony.xwork2.ActionSupport;
import com.web.entity.User;
import com.web.service.MainService;
import com.web.service.UserService;

public class MainAction extends ActionSupport {
	private User user;

	private JSONArray menus;
	private MainService mainService;
	private UserService userService;
	@JSON(serialize =false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public JSONArray getMenus() {
		return menus;
	}
	public void setMenus(JSONArray menus) {
		this.menus = menus;
	}
	@JSON(serialize =false)
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@JSON(serialize =false)
	public MainService getMainService() {
		return mainService;
	}
	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}
	public String home()
	{
		
		System.out.println("index........");
		return SUCCESS;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		return SUCCESS;
		//return super.execute();
	}
	
	public String userRegister()
	{
		return SUCCESS;
	}
	public String registerAction()
	{
		userService.userRegiseter(user);
		return SUCCESS;
		
	}
	public String getAllMenu()
	{
		
	  System.out.println("the size is "+mainService.getMenu().size());
		menus=JSONArray.fromObject(mainService.getMenu());
		  System.out.println("the size is "+menus);
		return SUCCESS;
	}

}