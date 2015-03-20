package com.web.service.Impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.internal.QueryImpl;

import antlr.build.Tool;

import com.opensymphony.xwork2.ActionContext;
import com.web.dao.IDao;
import com.web.entity.ScrollPic;
import com.web.util.Qiniu;
import com.web.util.Tools;

public class ScrollpicServiceImpl implements com.web.service.IScrollpicService {
	
	
   private IDao spicDao;
	public IDao getSpicDao() {
	return spicDao;
}

public void setSpicDao(IDao spicDao) {
	this.spicDao = spicDao;
}


	
	@Override
	public boolean saveScrollPic(ScrollPic pic) throws Exception {
		// TODO Auto-generated method stub
		
		//设置时间
		pic.setCreateDate(new Date());
		//设置上传者,暂时写死
		pic.setOwner("admin");
		
		// 先把文件上传到七牛云
	
		 String path = ServletActionContext.getServletContext().getRealPath("/upload");
		 
		 System.out.println("the reapath is "+path+"\\"+pic.getUrls());
		 Qiniu.uploadFile(pic.getUrls(), path+"\\"+pic.getUrls());
		 Tools.delFile(path+"\\"+pic.getUrls());
	
		 
		 
		 

		if(pic.getEffective()==true)
		{
			
	
			List<ScrollPic> p=    spicDao.getListByHQL ("from ScrollPic where effective=? ", true);
			if(p.size()>0)
			{
				for(int i=0;i<p.size();i++)
				{
					
				      if(p.get(i).getEffective())
				      {
				    	  p.get(i).setEffective(false);
				      }
				
						spicDao.saveOrUpdate(p.get(i));
				}

			}

		}
		
		if(pic.getTestPage()==true)
		{
			
	
			List<ScrollPic> p=    spicDao.getListByHQL ("from ScrollPic where testPage=? ", true);
			if(p.size()>0)
			{
				for(int i=0;i<p.size();i++)
				{
					
				      if(p.get(i).getTestPage())
				      {
				    	  p.get(i).setTestPage(false);
				      }
				
						spicDao.saveOrUpdate(p.get(i));
				}

			}

		}
		
		spicDao.save(pic);
		
		return false;
	}
	
	@Override
		public List getPicList() {
			// TODO Auto-generated method stub
			return spicDao.getListByHQL("from ScrollPic");
		}
	
	@Override
		public List getPageList(int start, int number) {
			// TODO Auto-generated method stub

			
			
	         return spicDao.getPageHql("from ScrollPic  order by id desc", start, number);
		}
	@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return spicDao.countByHql("select count(*) from ScrollPic").intValue();
		}
	
	@Override
		public boolean delScrollPic(ScrollPic p) {
			// TODO Auto-generated method stub
		/**
		 * 删除七牛云上的文件	
		 */
	    p=(ScrollPic) spicDao.get(p.getId());
	    String []urls=p.getUrls().split(",");
	    
	    for(int i=0;i<urls.length;i++)
	    {
	    	System.out.println("the url is "+urls[i]);
	    	Qiniu.deleteFile(urls[i]);
	    }
	    
		spicDao.delete(p);
		return true;
		}
	

	 @Override
		public boolean updateScroll(ScrollPic pic) throws Exception  {
			// TODO Auto-generated method stub
		 

			
			// 先把文件上传到七牛云

		 ScrollPic pp=(ScrollPic) spicDao.get(pic.getId());
		 if(!pp.getUrls().equals(pic.getUrls()))
		 {
		 
			 String path = ServletActionContext.getServletContext().getRealPath("/upload");
			 
			 System.out.println("the reapath is "+path+"\\"+pic.getUrls());
			 Qiniu.uploadFile(pic.getUrls(), path+"\\"+pic.getUrls());
			 Tools.delFile(path+"\\"+pic.getUrls());
		 }
			 
			 
			 

			if(pic.getEffective()==true)
			{
				
		
				List<ScrollPic> p=    spicDao.getListByHQL ("from ScrollPic where effective=? ", true);
				if(p.size()>0)
				{
					for(int i=0;i<p.size();i++)
					{
						
					      if(p.get(i).getEffective())
					      {
					    	  p.get(i).setEffective(false);
					      }
					
							spicDao.saveOrUpdate(p.get(i));
					}

				}

			}
			
			if(pic.getTestPage()==true)
			{
				
		
				List<ScrollPic> p=    spicDao.getListByHQL ("from ScrollPic where testPage=? ", true);
				if(p.size()>0)
				{
					for(int i=0;i<p.size();i++)
					{
						
					      if(p.get(i).getTestPage())
					      {
					    	  p.get(i).setTestPage(false);
					      }
					
							spicDao.saveOrUpdate(p.get(i));
					}

				}

			}
			
			spicDao.saveOrUpdate(pic);
		 
			return false;
		}
	 
	 @Override
		public int getCountByCondition(ScrollPic pic) {
			// TODO Auto-generated method stub
		  List<Object> paramList = new ArrayList<Object>();  
    
         String hql = "select count(*) from ScrollPic  sc  where 1=1" ;
           
         if(pic.getName()!=null&&!pic.getName().equals(""))
	   	  {
	   		  hql =hql +" and   sc.name like ?";
	   		  paramList.add("%"+pic.getName()+"%");
	   	  }
         if(pic.getOwner()!=null&&!pic.getOwner().equals(""))
         {
        	 hql=hql+" and sc.owner like ? ";
        	 paramList.add("%"+pic.getOwner()+"%");
         }
         if(pic.getCreateDate()!=null&&!pic.getCreateDate().equals(""))
         {
        	 hql=hql +" and sc.createDate>=?";
        	 paramList.add(pic.getCreateDate());
         }
         if(pic.getEndDate()!=null&&!pic.getEndDate().equals(""))
         {
        	 hql=hql+" and sc.createDate<=?";
        	 paramList.add(pic.getEndDate());
         }
       //  System.out.println("the size is "+spicDao.countByHql(hql, paramList.toArray()).intValue());
     	return spicDao.countByHql(hql, paramList.toArray()).intValue();
         

		}
	 
@Override
	public List getPageListByCondition(int start, int number, ScrollPic pic) {
		// TODO Auto-generated method stub
	 List<Object> paramList = new ArrayList<Object>();  
	    
     String hql = "from ScrollPic  sc  where 1=1" ;
       
     if(pic.getName()!=null&&!pic.getName().equals(""))
   	  {
   		  hql =hql +" and   sc.name like ?";
   		  paramList.add("%"+pic.getName()+"%");
   	  }
     if(pic.getOwner()!=null&&!pic.getOwner().equals(""))
     {
    	 hql=hql+" and sc.owner like ? ";
    	 paramList.add("%"+pic.getOwner()+"%");
     }
     if(pic.getCreateDate()!=null&&!pic.getCreateDate().equals(""))
     {
    	 hql=hql +" and sc.createDate>=?";
    	 paramList.add(pic.getCreateDate());
     }
     if(pic.getEndDate()!=null&&!pic.getEndDate().equals(""))
     {
    	 hql=hql+" and sc.createDate<=?";
    	 paramList.add(pic.getEndDate());
     }
   //  System.out.println("the size is "+spicDao.countByHql(hql, paramList.toArray()).intValue());
 	return spicDao.getPageHql(hql, start, number, paramList.toArray());
	}
	


}
