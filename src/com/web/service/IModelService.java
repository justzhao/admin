package com.web.service;

import java.util.List;
import java.util.Set;

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
     * ͬʱɾ�����ģ��
     * @param ids
     * @return
     */
    public boolean delModels(String ids);
    
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
    
    /**
     * �ȰѶ�Ӧ��ģ�Ͱ����
     * @param ids
     * @return
     * @throws Exception 
     */
    public String zipModes(String ids) throws Exception;
    
    /**
     * �����ʱ��ȡ��Ҫɾ���Ѿ����ص�ģ��
     * @param ids
     * @return
     */
    public boolean cancelZip(String ids);
    
    /**
     * �������ݰ�
     * @param ms
     * @return
     */
    public  boolean updatePacket(Set<Model> ms);
}
