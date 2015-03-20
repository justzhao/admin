package com.web.service;

import java.util.List;

import com.web.entity.Model;

public interface IModelService  {
	
	/**
	 * 添加模型
	 * @param m
	 * @return
	 */
	public boolean saveModel (Model m) throws Exception ;
	
	/**
	 * 获取所有的模型
	 * @return
	 */
    public List getModelList();
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
    public List getPageListByCondition(Model m,int start,int number);
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
    public int getCountByCondition(Model m);
    /**
     * 检查名字是否存在 存在返回false，不存在返回true
     * @return
     */
    public boolean checkName(String name);
    
    /**
     * 删除某个模型
     * @param id
     * @return
     */
    public boolean delModel(Model m);

    /**
     * 根据id获取模型
     * @param m
     * @return
     */
    public Model getModelById(int id);
    
    /**
     * 更新模型
     * @param m
     * @return
     */
    public boolean updateModel(Model m);
}
