package com.web.service;

import java.util.List;

import com.web.entity.Model;

public interface IModelService  {
	
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
     * ����������ȡ��¼��
     * @param start
     * @param number
     * @return
     */
    public List getPageListByCondition(Model m,int start,int number);
    /**
     * ����ܼ�¼��
     * @return
     */
    public int getCount();
    /**
     * ����������ȡ��¼��
     * @param m
     * @return
     */
    public int getCountByCondition(Model m);
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

    /**
     * ����id��ȡģ��
     * @param m
     * @return
     */
    public Model getModelById(int id);
    
    /**
     * ����ģ��
     * @param m
     * @return
     */
    public boolean updateModel(Model m);
}
