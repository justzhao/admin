package com.web.action;

import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import net.sf.json.JSONArray;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.entity.User;
import com.web.service.IMainService;
import com.web.service.IUserService;
import com.web.util.Tools;

public class MainAction extends ActionSupport {
	private User user;

	private JSONArray menus;
	private IMainService mainService;
	private IUserService userService;
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
	public IMainService getMainService() {
		return mainService;
	}
	public void setMainService(IMainService mainService) {
		this.mainService = mainService;
	}
	@JSON(serialize =false)
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public String home()
	{
		
		Map request=(Map)ActionContext.getContext().get("request");
		   request.put("Menus", mainService.getMenu());
		   request.put("user", Tools.getCurrentUser());
		   
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
		

		menus=JSONArray.fromObject(mainService.getMenu());
		// System.out.println("the size is "+menus);
	
		
		return SUCCESS;
	}

}
