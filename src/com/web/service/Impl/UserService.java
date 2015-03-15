package com.web.service.Impl;

import com.web.dao.IDao;
import com.web.entity.User;

public class UserService implements com.web.service.UserService {

	private IDao userDao;
	
	
	public IDao getUserDao() {
		return userDao;
	}


	public void setUserDao(IDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public boolean userRegiseter(User u) {
		// TODO Auto-generated method stub
		
		userDao.save(u);
		return false;
	}

}
