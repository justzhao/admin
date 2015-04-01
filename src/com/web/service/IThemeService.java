package com.web.service;

import java.util.List;

import com.web.entity.Model;
import com.web.entity.Theme;

public interface IThemeService {
	
	/*
	 * 
	 * 保存一个主题组
	 */
	public boolean saveTheme(Theme t);
	
    /**
     * 获取指定段的数据
     * @param start
     * @param number
     * @return
     */
    public List getPageList(int start,int number);
    
    /**
     * 根据条件获取记录数
     * @param start
     * @param number
     * @return
     */
    public List getPageListByCondition(Theme t ,int start,int number);
    /**
     * 获得总记录数
     * @return
     */
    public int getCount();
    /**
     * 根据条件获取记录数
     * @param m
     * @return
     */
    public int getCountByCondition(Theme t);
    
    /**
     * 删除某个主题组
     * @param id
     * @return
     */
    public boolean delTheme(int id);
    
    /**
     * 更新一个主题组
     * @param t
     * @return
     */
    public boolean updateTheme(Theme t);
    
    /**
     * 获取所有的主题组
     * @return
     */
    public  List getThemeList();

}
