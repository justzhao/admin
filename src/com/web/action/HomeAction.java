package com.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.web.entity.User;
import com.web.service.UserService;

public class HomeAction extends ActionSupport {
	public User user;
	public UserService userService;
		public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
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

}
