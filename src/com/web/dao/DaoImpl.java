package com.web.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.RowMapper;

import com.web.util.PageResults;



import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;




public class DaoImpl<T,ID extends Serializable> implements IDao<T, ID> {
	
   @Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	   private Class<T> entityClass;
	   

	 
	    public void setEntityClass(Class<T> entityClass) {
			this.entityClass = entityClass;
		
		}


		@SuppressWarnings("unchecked")
		protected Class getEntityClass() {
	        if (entityClass == null) {
	            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	        }
	        return entityClass;
	    }   
	


	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
	  
		 this.getSession().save(t);
	}

	@Override
	public void saveOrUpdate(T t) {
		// TODO Auto-generated method stub
		this.getSession().saveOrUpdate(t);
	//	this.getSession().
	}

	@Override
	public T load(ID id) {
		// TODO Auto-generated method stub
		 T load = (T) this.getSession().load(getEntityClass(), id);
	        return load;
	}

	@Override
	public T get(ID id) {
	    T load = (T) this.getSession().get(getEntityClass(), id);
        return load;
	}

	@Override
	public boolean contains(T t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		this.getSession().delete(t);
		

	}

	@Override
	public boolean deleteById(ID Id) {
		// TODO Auto-generated method stub
		T t = get(Id);
        if(t == null){
            return false;
        }
        delete(t);
       return true;
	}

	@Override
	public void deleteAll(Collection<T> entities) {
		// TODO Auto-generated method stub
		for(Object entity : entities) {
            this.getSession().delete(entity);
        }
	}

	@Override
	public void queryHql(String hqlString, Object... values) {
		// TODO Auto-generated method stub
		 Query query = this.getSession().createQuery(hqlString);
	        if (values != null)
	        {
	            for (int i = 0; i < values.length; i++)
	            {
	                query.setParameter(i, values[i]);
	            }
	        }
	        query.executeUpdate();
	}

	@Override
	public void querySql(String sqlString, Object... values) {
		// TODO Auto-generated method stub
		Query query = this.getSession().createSQLQuery(sqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        query.executeUpdate();
		
	}

	@Override
	public T getByHQL(String hqlString, Object... values) {
        Query query = this.getSession().createQuery(hqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return (T) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getBySQL(String sqlString, Object... values) {
        Query query = this.getSession().createSQLQuery(sqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
                
            }
        }
        return (T) query.uniqueResult();
	}

	@Override
	public List<T> getListByHQL(String hqlString, Object... values) {
        Query query = this.getSession().createQuery(hqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
	}

	@Override
	public List<T> getListBySQL(String sqlString, Object... values) {
		// TODO Auto-generated method stub
        Query query = this.getSession().createSQLQuery(sqlString).addEntity(getEntityClass());
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        
        return query.list();
	}

	@Override
	public List findListBySql(String sql, RowMapper map, Object... values) {
	
	      return null;

	}

	@Override
	public void refresh(T t) {
		// TODO Auto-generated method stub
		this.getSession().refresh(t);
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		 this.getSession().update(t);
		
	}

	@Override
	public Long countByHql(String hql, Object... values) {
        Query query = this.getSession().createQuery(hql);
        if(values != null){
            for(int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return (Long) query.uniqueResult();
	}
	
    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
  
   

    }
    
    public Session getSession() {
        //需要开启事物，才能得到CurrentSession
 
       return sessionFactory.getCurrentSession();
    	
    //return sessionFactory.openSession();
    }



	@Override
	public PageResults<T> findPageByFetchedHql(String hql, String countHql,
			int pageNo, int pageSize, Object... values) {
		// TODO Auto-generated method stub
		  PageResults<T> retValue = new PageResults<T>();
	        Query query = this.getSession().createQuery(hql);
	        if(values != null){
	            for(int i = 0; i < values.length; i++) {
	                query.setParameter(i, values[i]);
	            }
	        }
	        int currentPage = pageNo > 1 ? pageNo : 1;
	        retValue.setCurrentPage(currentPage);
	        retValue.setPageSize(pageSize);
	        if (countHql == null)
	        {
	            ScrollableResults results = query.scroll();
	            results.last();
	            retValue.setTotalCount(results.getRowNumber() + 1);// 设置总记录数
	        }
	        else
	        {
	            Long count = countByHql(countHql, values);
	            retValue.setTotalCount(count.intValue());
	        }
	        retValue.resetPageNo();
	        @SuppressWarnings("unchecked")
			List<T> itemList = query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
	        if (itemList == null)
	        {
	            itemList = new ArrayList<T>();
	        }
	        retValue.setResults(itemList);
	         
	        return retValue;
	}
	
	@Override
		public List getPageHql(String hql, int start, int number,Object... values) {
			// TODO Auto-generated method stub
		Query query = this.getSession().createQuery(hql);
		if(values != null){
            for(int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
	
		query.setFirstResult(start);   
		query.setMaxResults(number);
		return query.list();
		}
	
	public void merge(T t) {
		
		this.getSession().merge(t);
		
		
		
	}

	public void evict(T t) {
		
		this.getSession().evict(t);
	}
	@Override
		public Set filter(Set s,String hql) {
			// TODO Auto-generated method stub
		return   new HashSet( this.getSession().createFilter(s,hql).list());
	
		}

}
