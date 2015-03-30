package com.web.service;

import java.util.List;

import com.web.dao.IDao;
import com.web.entity.Packet;

public interface IPacketService {

	/**
	 * ���һ�����ݰ�
	 * @param p
	 * @return 
	 * @throws Exception
	 */
	public boolean savePacket(Packet p) throws Exception;
	

	/**
	 * ��ȡ������������
	 * @return
	 */
	public int getCount();
	
	/**
	 * ����������ȡ���е���������
	 * @param p
	 * @return
	 */
	public int getCountByCondition(Packet p);
	
	/**
	 * ��ȡÿҳ������
	 * @param start
	 * @param number
	 * @return
	 */
	public List getPageList(int start,int number);
	
	/**
	 * ����������ȡÿҳ������
	 * @param p
	 * @param start
	 * @param number
	 * @return
	 */
	public List getPageListByCondition(Packet p,int start,int number);
	
	/**
	 * ����idɾ�����ݰ�
	 * @param id
	 * @return
	 */
	public boolean delPacketById(int id);
	
	/**
	 * ���°�
	 * @param p
	 * @return
	 * @throws Exception 
	 */
	public boolean updatePacket(Packet p) throws Exception;
}
