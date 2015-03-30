package com.web.service;

import java.util.List;


import com.web.entity.ScrollPic;
import com.web.entity.User;

public interface IAuthorityService {
	
	/**
	 * 获取某页的数据
	 * @param start
	 * @param number
	 * @return
	 */
	public List getPageList(int start,int number);
	/**
	 * 根据搜索条件获取某页的数据
	 * @param start
	 * @param number
	 * @param u
	 * @return
	 */
	public List getPageListByCondition(int start,int number,User u);
	
	/**
	 * 获取总的记录
	 * @return
	 */
	public int getCount();
	/**
	 * 根据条件获取记录数
	 * @param u
	 * @return
	 */
	public int getCountByCondition(User u);
	/**
	 * 删除用户
	 * @param u
	 * @return
	 */
	public boolean delUser(User u);
	
	/**
	 * 更新用户
	 * @param u
	 * @return
	 */
	public boolean updateUser(User u);

   /**
    * 获取角色列表
    * @return
    */
	public List getRoleList();
	
	/**
	 * 保存用户
	 * @param u
	 * @return
	 */
	public boolean saveUser(User u);
	
	/**
	 * 检测用户名和密码是否存在
	 * @param u
	 * @return
	 */
	public boolean checkUser(User u);
	/**
	 * 检测用户名是否存在 true 存在 
	 * @param name
	 * @return
	 */
	public boolean checkUserName(String name);
	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	public User getUserById(int id);
}
