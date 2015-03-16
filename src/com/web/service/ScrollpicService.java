package com.web.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.web.entity.ScrollPic;

public interface ScrollpicService {
	

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
	 * ��ȡ��������
	 * @return
	 */
	public int getCount();
	
	
	public boolean delScrollPic(ScrollPic p);

}
