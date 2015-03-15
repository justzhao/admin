package com.web.service;

import java.util.List;

import com.web.entity.Model;

public interface ModelService  {
	
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
}
