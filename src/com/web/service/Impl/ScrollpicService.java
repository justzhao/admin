package com.web.service.Impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

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
	public String savePic(File file, String fileName, String fileContentType,String realPath) throws IOException {
		// TODO Auto-generated method stub
        
        if(file==null)
        {
        	System.out.println("nulllllllllll");
        }
        if (file != null) {
        	System.out.println(" no nulllllllllll");
        	fileName=getFileName(fileName)+"_"+this.getRandomFileName()+getExtention(fileName);
        	
            File savefile = new File(new File(realPath), fileName);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(file, savefile);
    
        }
	
		return fileName;
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
		if(pic.getEffective()==1)
		{
			
	
			ScrollPic p=   (ScrollPic) spicDao.getByHQL("from ScrollPic where effective=?", 1);
			if(p!=null)
			{
			p.setEffective(0);
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
	
	
	
	public   String getRandomFileName() {  
		  
        SimpleDateFormat simpleDateFormat;  
  
        simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
  
        Date date = new Date();  
  
        String str = simpleDateFormat.format(date);  

        Random random = new Random();  
  
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数  
  
        return   str+rannum;// 当前时间  
    } 
	
	
	 private static String getExtention(String fileName)  {   
         int pos = fileName.lastIndexOf( "." );   
        return fileName.substring(pos);   
} 
	 private static String getFileName(String fileName)  {   
         int pos = fileName.lastIndexOf( "." );   
       
        return fileName.substring(0, pos);   
} 

}
