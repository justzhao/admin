package com.web.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.web.entity.ScrollPic;

public interface IScrollpicService {
	

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
	 * 根据条件获取某页数据
	 * @param start
	 * @param number
	 * @return
	 */
	public List getPageListByCondition(int start,int number,ScrollPic pic);
	
	/**
	 * 获取数据总数
	 * @return
	 */
	public int getCount();
	
	/**
	 * 根据条件获取总数
	 * @param pic
	 * @return
	 */
   public int getCountByCondition(ScrollPic pic);
 

	/**
	 * 删除
	 * @param p
	 * @return
	 */
	public boolean delScrollPic(ScrollPic pic);

	/**
	 * 更新
	 * @param p
	 * @return
	 * @throws Exception 
	 */
	public boolean updateScroll(ScrollPic p) throws Exception;
   
	

}
