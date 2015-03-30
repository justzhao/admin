package com.web.service;

import java.util.List;

import com.web.dao.IDao;
import com.web.entity.Packet;

public interface IPacketService {

	/**
	 * 添加一个数据包
	 * @param p
	 * @return 
	 * @throws Exception
	 */
	public boolean savePacket(Packet p) throws Exception;
	

	/**
	 * 获取所有数据总量
	 * @return
	 */
	public int getCount();
	
	/**
	 * 根据条件获取所有的数据总量
	 * @param p
	 * @return
	 */
	public int getCountByCondition(Packet p);
	
	/**
	 * 获取每页的数据
	 * @param start
	 * @param number
	 * @return
	 */
	public List getPageList(int start,int number);
	
	/**
	 * 根据条件获取每页的数据
	 * @param p
	 * @param start
	 * @param number
	 * @return
	 */
	public List getPageListByCondition(Packet p,int start,int number);
	
	/**
	 * 根据id删除数据包
	 * @param id
	 * @return
	 */
	public boolean delPacketById(int id);
	
	/**
	 * 更新包
	 * @param p
	 * @return
	 * @throws Exception 
	 */
	public boolean updatePacket(Packet p) throws Exception;
}
