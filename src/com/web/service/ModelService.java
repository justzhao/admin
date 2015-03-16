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
    /**
     * ��ȡָ���ε�����
     * @param start
     * @param number
     * @return
     */
    public List getPageList(int start,int number);
    
    /**
     * ����ܼ�¼��
     * @return
     */
    public int getCount();
    /**
     * ��������Ƿ���� ���ڷ���false�������ڷ���true
     * @return
     */
    public boolean checkName(String name);
    
    /**
     * ɾ��ĳ��ģ��
     * @param id
     * @return
     */
    public boolean delModel(Model m);
}
