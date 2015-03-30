package com.web.service;

import java.util.List;
import java.util.Set;

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
     * 同时删除多个模型
     * @param ids
     * @return
     */
    public boolean delModels(String ids);
    
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
    
    /**
     * 先把对应的模型包打包
     * @param ids
     * @return
     * @throws Exception 
     */
    public String zipModes(String ids) throws Exception;
    
    /**
     * 打包的时候取消要删除已经下载的模型
     * @param ids
     * @return
     */
    public boolean cancelZip(String ids);
    
    /**
     * 更新数据包
     * @param ms
     * @return
     */
    public  boolean updatePacket(Set<Model> ms);
}
