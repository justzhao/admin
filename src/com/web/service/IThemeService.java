package com.web.service;

import java.util.List;

import com.web.entity.Model;
import com.web.entity.Theme;

public interface IThemeService {
	
	/*
	 * 
	 * ����һ��������
	 */
	public boolean saveTheme(Theme t);
	
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
    public List getPageListByCondition(Theme t ,int start,int number);
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
    public int getCountByCondition(Theme t);
    
    /**
     * ɾ��ĳ��������
     * @param id
     * @return
     */
    public boolean delTheme(int id);
    
    /**
     * ����һ��������
     * @param t
     * @return
     */
    public boolean updateTheme(Theme t);
    
    /**
     * ��ȡ���е�������
     * @return
     */
    public  List getThemeList();

}
