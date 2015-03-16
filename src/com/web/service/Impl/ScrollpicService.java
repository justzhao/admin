package com.web.service.Impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

public class ScrollpicService implements com.web.service.ScrollpicService {
	
	
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
		
		// 先把文件上传到七牛云
	
		 String path = ServletActionContext.getServletContext().getRealPath("/upload");
		 
		 System.out.println("the reapath is "+path+"\\"+pic.getUrls());
	
		 String []url=pic.getUrls().split(",");
		 
		 for(int i=0;i<url.length;i++)
		 {
			 //上传
	           Qiniu.uploadFile(url[i], path+"\\"+url[i]);	
	           Tools.delFile(path+"\\"+url[i]);
	           //上传完之后需要删除本地的，暂时不弄。
		 }
		if(pic.getEffective()==true)
		{
			
	
			ScrollPic p=   (ScrollPic) spicDao.getByHQL("from ScrollPic where effective=?", true);
			if(p!=null)
			{
			p.setEffective(false);
			spicDao.saveOrUpdate(p);
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

	
	


}
