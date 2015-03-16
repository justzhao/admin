package com.web.service.Impl;

import java.util.List;

import com.web.dao.IDao;

public class MainService implements com.web.service.MainService {

	 private IDao menuDao;
	 
	public IDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(IDao menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public List getMenu() {
		// TODO Auto-generated method stub
	//	return menuDao.getListByHQL("from Menu m where m.pid =?",0);
		
	 return menuDao.getListBySQL("select * from tbl_menu where pid=? ", 0);
	
	}

}
