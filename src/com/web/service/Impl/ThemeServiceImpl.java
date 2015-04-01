package com.web.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.web.dao.IDao;
import com.web.entity.Model;
import com.web.entity.Theme;
import com.web.service.IThemeService;
import com.web.util.Qiniu;

public class ThemeServiceImpl implements IThemeService {

	private IDao themeDao;
	
	
	
	
	public IDao getThemeDao() {
		return themeDao;
	}




	public void setThemeDao(IDao themeDao) {
		this.themeDao = themeDao;
	}




	@Override
	public boolean saveTheme(Theme t) {
		// TODO Auto-generated method stub
		
		try {
			//sÉÏ´«µ½ÆßÅ£ÔÆ
			Qiniu.uploadFile(t.getThumb());
			Qiniu.uploadFile(t.getUp());
			Qiniu.uploadFile(t.getFooter());
			Qiniu.uploadFile(t.getWord());
			
			themeDao.save(t);
			
		} catch (Exception e) {
			// TODO: handle exception
			
			return false;
		}


		
		return true;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return themeDao.countByHql("select count(*) from Theme ").intValue();
	}
	@Override
	public int getCountByCondition(Theme t) {
		// TODO Auto-generated method stub
		

		String hql="select count(*) from Theme where 1=1";
		ArrayList<Object> paramList=new ArrayList<Object>();
		if(t.getOwner()!=null&&!t.getOwner().equals(""))
		{
			hql=hql+" and owner like ?";
			paramList.add("%"+t.getOwner()+"%");
		}
		
		return themeDao.countByHql(hql, paramList.toArray()).intValue();
	}
	@Override
	public List getPageList(int start, int number) {
		// TODO Auto-generated method stub
		return themeDao.getPageHql("from Theme", start, number);
	}
	@Override
	public List getPageListByCondition(Theme t, int start, int number) {
		// TODO Auto-generated method stub
		String hql=" from Theme where 1=1";
		ArrayList<Object> paramList=new ArrayList<Object>();
		if(t.getOwner()!=null&&!t.getOwner().equals(""))
		{
			hql=hql+" and owner like ?";
			paramList.add("%"+t.getOwner()+"%");
		}
		
		return themeDao.getPageHql(hql, start, number, paramList.toArray());
	}
	
	@Override
	public boolean delTheme(int id) {
		// TODO Auto-generated method stub
		
	   Theme t=(Theme) themeDao.get(id);
	   Qiniu.deleteFile(t.getThumb());
	   Qiniu.deleteFile(t.getUp());
	   Qiniu.deleteFile(t.getFooter());
	   Qiniu.deleteFile(t.getWord());
	   themeDao.delete(t);
		return true;
	}
	
	@Override
	public boolean updateTheme(Theme t) {
		// TODO Auto-generated method stub
		
		
		try {
			
			Theme tt=(Theme) themeDao.get(t.getId());
			if(!t.getThumb().equals(tt.getThumb()))
			{
				Qiniu.uploadFile(t.getThumb());
				Qiniu.deleteFile(tt.getThumb());
			}
			if(!t.getUp().equals(tt.getUp()))
			{
				Qiniu.uploadFile(t.getUp());
				Qiniu.deleteFile(tt.getUp());
			}
			if(!t.getFooter().equals(tt.getFooter()))
			{
				Qiniu.uploadFile(t.getFooter());
				Qiniu.deleteFile(tt.getFooter());
			}
			if(!t.getWord().equals(tt.getWord()))
			{
				Qiniu.uploadFile(t.getWord());
				Qiniu.deleteFile(tt.getWord());
			}
			
		  themeDao.merge(t);
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		return true;
	}
@Override
public List getThemeList() {
	// TODO Auto-generated method stub

	return themeDao.getListByHQL("from Theme where flag=0");
	
	//	return null;
}
	
}
