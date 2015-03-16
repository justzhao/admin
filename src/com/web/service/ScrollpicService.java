package com.web.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.web.entity.ScrollPic;

public interface ScrollpicService {
	

	/**
	 * 保存一组轮播图
	 * @param pic
	 * @return
	 */
	
	public boolean saveScrollPic(ScrollPic pic) throws Exception;
	
	/**
	 * 获取所有的 图像组
	 * @return
	 */
	public List getPicList();
  /**
   * 获取某页的数据
   * @param start
   * @param end
   * @return
   */
   
	public List getPageList(int start,int number);
	
	/**
	 * 获取数据总数
	 * @return
	 */
	public int getCount();
	
	
	public boolean delScrollPic(ScrollPic p);

}
