package com.web.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.web.entity.ScrollPic;

public interface IScrollpicService {
	

	/**
	 * ����һ���ֲ�ͼ
	 * @param pic
	 * @return
	 */
	
	public boolean saveScrollPic(ScrollPic pic) throws Exception;
	
	/**
	 * ��ȡ���е� ͼ����
	 * @return
	 */
	public List getPicList();
  /**
   * ��ȡĳҳ������
   * @param start
   * @param end
   * @return
   */
   
	public List getPageList(int start,int number);
	
	/**
	 * ����������ȡĳҳ����
	 * @param start
	 * @param number
	 * @return
	 */
	public List getPageListByCondition(int start,int number,ScrollPic pic);
	
	/**
	 * ��ȡ��������
	 * @return
	 */
	public int getCount();
	
	/**
	 * ����������ȡ����
	 * @param pic
	 * @return
	 */
   public int getCountByCondition(ScrollPic pic);
 

	/**
	 * ɾ��
	 * @param p
	 * @return
	 */
	public boolean delScrollPic(ScrollPic pic);

	/**
	 * ����
	 * @param p
	 * @return
	 * @throws Exception 
	 */
	public boolean updateScroll(ScrollPic p) throws Exception;
   
	

}
