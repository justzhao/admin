package com.web.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.web.dao.IDao;
import com.web.entity.User;
import com.web.service.IAuthorityService;
import com.web.util.MD5Util;
import com.web.util.SecurityManager;

public class AuthorityServiceImpl implements IAuthorityService {
	
	private IDao userDao;
	private IDao roleDao;
	public IDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IDao userDao) {
		this.userDao = userDao;
	}
	public IDao getRoleDao() {
		return roleDao;
	}
	public void setRoleDao(IDao roleDao) {
		this.roleDao = roleDao;
	}
	@Override
	public List getPageList(int start, int number) {
		// TODO Auto-generated method stub
		return userDao.getPageHql("from User  order by id asc", start, number);
	}
	@Override
	public List getPageListByCondition(int start, int number, User u) {
		// TODO Auto-generated method stub
		
		  List<Object> paramList = new ArrayList<Object>();  
		     
	         String hql = "from User u  where 1=1" ;
	         if(u.getName()!=null&&!u.getName().equals(""))
	         {
	        	 hql =hql +" and   u.name like ?";
	        	 paramList.add("%"+u.getName()+"%");
	         }
	         
	         if(u.getRole().getId()!=0)
	         {
	        	 hql =hql +" and   u.role.id = ?";
	        	 paramList.add(u.getRole().getId());
	         }
	         hql=hql+"  order by id asc";
	         return userDao.getPageHql(hql, start, number, paramList.toArray());
		

	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return userDao.countByHql("select count(*) from User").intValue();
	}
	@Override
	public int getCountByCondition(User u) {
		// TODO Auto-generated method stub
		  List<Object> paramList = new ArrayList<Object>();  
     
         String hql = "select count(*) from User u  where 1=1" ;
         if(u.getName()!=null&&!u.getName().equals(""))
         {
        	 hql =hql +" and   u.name like ?";
        	 paramList.add("%"+u.getName()+"%");
         }
         
         if(u.getRole().getId()!=0)
         {
        	 hql =hql +" and   u.role.id = ?";
        	 paramList.add(u.getRole().getId());
         }
         return userDao.countByHql(hql, paramList.toArray()).intValue();
}
	@Override
	public boolean delUser(User u) {
		// TODO Auto-generated method stub
		userDao.delete(u);
		return true;
	}
	@Override
	public boolean updateUser(User u) {
		// TODO Auto-generated method stub
		if(u.getName()=="")
		{
			User user=(User) userDao.get(u.getId());
			u.setPass(MD5Util.generatePassword(user.getPass()));
			userDao.evict(user);
		}else
		{
			u.setPass(MD5Util.generatePassword(u.getPass()));
		}
		userDao.saveOrUpdate(u);
		return true;
	}
	@Override
	public List getRoleList() {
		// TODO Auto-generated method stub
		return roleDao.getListByHQL("from Role");
	}
	@Override
	public boolean saveUser(User u) {
		// TODO Auto-generated method stub
		u.setPass(MD5Util.generatePassword(u.getPass()));
		userDao.save(u);
		return true;
	}
	
	@Override
	public boolean checkUser(User u) {
		// TODO Auto-generated method stub
	//
		User uu=(User) userDao.getByHQL("from User where name=? and pass=?", u.getName(),MD5Util.generatePassword(u.getPass()));
	  if(uu!=null)
	  {
		  //µÇÂ¼³É¹¦
		  ActionContext.getContext().getSession().put("user", uu);
		//  SecurityManager.login(uu);
		  return true;
	  }else
	  {
		return false;
	  }
	}
	@Override
	public boolean checkUserName(String name) {
		// TODO Auto-generated method stub
		
		if(userDao.countByHql("select count(*) from User where name=?", name).intValue()>0)
		{
			return true;
		}else{
			return  false;
		}
	
	}
	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return (User) userDao.get(id);
	}

}
