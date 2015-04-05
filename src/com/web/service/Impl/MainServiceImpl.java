package com.web.service.Impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

		int role=Tools.getCurrentUser().getRole().getId();

		
	List<Menu>  menus= menuDao.getListBySQL("select * from tbl_menu where pid=?", 0);
	


		Menu mm=(Menu) menuDao.get(6);
	    for(int i=0;i<menus.size();i++)
	    {
	    	Menu m=menus.get(i);
	       Set<Menu> ss=m.getChildren();
	       
	       
	       if(ss.contains(mm))
	       {
	    		if(role==2)
	    		{
	    	   ss.remove(mm);
	    	    }
	       }
	       

	       Set<Menu> sss=new TreeSet<Menu>();
	       for(Menu mmm :ss)
	       {
	    	  
	    	   sss.add(mmm);
	       }
	       
	    

        m.setChildren(sss);

	
    	
    }

	return menus;

	
	}

}
