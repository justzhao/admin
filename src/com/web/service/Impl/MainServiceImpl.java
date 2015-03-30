package com.web.service.Impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.web.dao.IDao;
import com.web.entity.Menu;
import com.web.entity.User;
import com.web.util.Tools;

public class MainServiceImpl implements com.web.service.IMainService {

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
		int role=Tools.getCurrentUser().getRole().getId();

		
	List<Menu>  menus= menuDao.getListBySQL("select * from tbl_menu where pid=?", 0);
	
	if(role==2)
	{
		
		Menu mm=(Menu) menuDao.get(6);
	    for(int i=0;i<menus.size();i++)
	    {
	    	Menu m=menus.get(i);
	       Set<Menu> ss=m.getChildren();
	       
	       if(ss.contains(mm))
	       {
	    	   ss.remove(mm);
	       }
	     
	       m.setChildren(ss);
	    }
	
    	
    }

	return menus;

	
	}

}
