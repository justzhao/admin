package com.web.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.web.util.PageResults;




public interface IDao<T, ID extends Serializable> {
	
	/**
     * <����ʵ��>
     * <��������ʵ��>
     * @param t ʵ�����
     */
	@Transactional
    public abstract void save(T t);
 
    /**
     * <������߸���ʵ��>
     * @param t ʵ��
     */
	@Transactional
    public abstract void saveOrUpdate(T t);
 
    /**
     * <load>
     * <����ʵ���load����>
     * @param id ʵ���id
     * @return ��ѯ������ʵ��
     */
	@Transactional
    public abstract T load(ID id);
 
    /**
     * <get>
     * <���ҵ�get����>
     * @param id ʵ���id
     * @return ��ѯ������ʵ��
     */
    @Transactional
    public abstract T get(ID id);
 
    /**
     * <contains>
     * @param t ʵ��
     * @return �Ƿ����
     */
    @Transactional
    public abstract boolean contains(T t);
 
    /**
     * <delete>
     * <ɾ�����е�t����>
     * @param t ʵ��
     */
    @Transactional
    public abstract void delete(T t);
 
    /**
     * <����IDɾ������>
     * @param Id ʵ��id
     * @return �Ƿ�ɾ���ɹ�
     */
    @Transactional
    public abstract boolean deleteById(ID Id);
 
    /**
     * <ɾ������>
     * @param entities ʵ���Collection����
     */
    @Transactional
    public abstract void deleteAll(Collection<T> entities);
     
    /**
     * <ִ��Hql���>
     * @param hqlString hql
     * @param values ������������
     */
    @Transactional
    public abstract void queryHql(String hqlString, Object... values);
     
    /**
     * <ִ��Sql���>
     * @param sqlString sql
     * @param values ������������
     */
    @Transactional
    public abstract void querySql(String sqlString, Object... values);
 
    /**
     * <����HQL������Ψһʵ��>
     * @param hqlString HQL���
     * @param values ����������Object����
     * @return ��ѯʵ��
     */
    @Transactional
    public abstract T getByHQL(String hqlString, Object... values);
 
    /**
     * <����SQL������Ψһʵ��>
     * @param sqlString SQL���
     * @param values ����������Object����
     * @return ��ѯʵ��
     */
    @Transactional
    public abstract T getBySQL(String sqlString, Object... values);
 
    /**
     * <����HQL��䣬�õ���Ӧ��list>
     * @param hqlString HQL���
     * @param values ����������Object����
     * @return ��ѯ���ʵ���List����
     */
    @Transactional
    public abstract List<T> getListByHQL(String hqlString, Object... values);
 
    /**
     * <����SQL��䣬�õ���Ӧ��list>
     * @param sqlString HQL���
     * @param values ����������Object����
     * @return ��ѯ���ʵ���List����
     */
    @Transactional
    public abstract List<T> getListBySQL(String sqlString, Object... values);
     
    /**
     * ��sql���õ�List
     * @param sql
     * @param map
     * @param values
     * @return List
     */
    @Transactional
    public List findListBySql(final String sql, final RowMapper map, final Object... values);
 
    /**
     * <refresh>
     * @param t ʵ��
     */
    @Transactional
    public abstract void refresh(T t);
 
    /**
     * <update>
     * @param t ʵ��
     */
    @Transactional
    public abstract void update(T t);
 
    /**
     * <����HQL�õ���¼��>
     * @param hql HQL���
     * @param values ����������Object����
     * @return ��¼����
     */
    @Transactional
    public abstract Long countByHql(String hql, Object... values);
     
    /**
     * <HQL��ҳ��ѯ>
     * @param hql HQL���
     * @param countHql ��ѯ��¼������HQL���
     * @param pageNo ��һҳ
     * @param pageSize һҳ������
     * @param values ����Object�������
     * @return PageResults�ķ�װ�࣬���������ҳ�����Ϣ�Լ���ѯ������List����
     */
    public abstract PageResults<T> findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize, Object... values);

}
