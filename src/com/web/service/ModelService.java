package com.web.service;

import java.util.List;

import com.web.entity.Model;

public interface ModelService  {
	
	/**
	 * ���ģ��
	 * @param m
	 * @return
	 */
	public boolean saveModel (Model m) throws Exception ;
	
	/**
	 * ��ȡ���е�ģ��
	 * @return
	 */
    public List getModelList();
}
