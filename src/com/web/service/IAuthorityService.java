package com.web.service;

import java.util.List;


import com.web.entity.ScrollPic;
import com.web.entity.User;

public interface IAuthorityService {
	
	/**
	 * ��ȡĳҳ������
	 * @param start
	 * @param number
	 * @return
	 */
	public List getPageList(int start,int number);
	/**
	 * ��������������ȡĳҳ������
	 * @param start
	 * @param number
	 * @param u
	 * @return
	 */
	public List getPageListByCondition(int start,int number,User u);
	
	/**
	 * ��ȡ�ܵļ�¼
	 * @return
	 */
	public int getCount();
	/**
	 * ����������ȡ��¼��
	 * @param u
	 * @return
	 */
	public int getCountByCondition(User u);
	/**
	 * ɾ���û�
	 * @param u
	 * @return
	 */
	public boolean delUser(User u);
	
	/**
	 * �����û�
	 * @param u
	 * @return
	 */
	public boolean updateUser(User u);

   /**
    * ��ȡ��ɫ�б�
    * @return
    */
	public List getRoleList();
	
	/**
	 * �����û�
	 * @param u
	 * @return
	 */
	public boolean saveUser(User u);
	
	/**
	 * ����û����������Ƿ����
	 * @param u
	 * @return
	 */
	public boolean checkUser(User u);
	/**
	 * ����û����Ƿ���� true ���� 
	 * @param name
	 * @return
	 */
	public boolean checkUserName(String name);
	/**
	 * ����id��ȡ�û�
	 * @param id
	 * @return
	 */
	public User getUserById(int id);
}
