package com.web.service.Impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.web.dao.IDao;
import com.web.entity.Model;
import com.web.entity.Packet;
import com.web.service.IPacketService;
import com.web.util.Qiniu;
import com.web.util.Tools;

public class PacketServiceImpl implements IPacketService {
	private IDao packetDao;

	public IDao getPacketDao() {
		return packetDao;
	}

	public void setPacketDao(IDao packetDao) {
		this.packetDao = packetDao;
	}

	@Override
	public void savePacket(Packet p) throws Exception {
		// TODO Auto-generated method stub
		//模型包已经生成，现在要做的就是把xml 文件和，说明图。缩略图不放在包里面
		
		String realpath = ServletActionContext.getServletContext().getRealPath("/upload");

	  
		
		//上传缩略图和说明图到七牛云
		Qiniu.uploadFile(p.getThumbPic(), realpath+"//"+p.getThumbPic());
	

		 List<String> paths=new ArrayList<String>();
		 paths.add(p.getDescPic()); //加入说明图路径
		 
		 //加入模型路径
		  Set<Model>  models=p.getModels();
		  Iterator iterator = models.iterator();
		  
		    while (iterator.hasNext()) {  
      	     Model  m = (Model) iterator.next();
     	       paths.add(m.getUrl());

		      }

		//把说明文件压缩到zip中
		Tools.ZipFiles(paths,p.getUrl());
		
     
		packetDao.save(p);
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
	 return  packetDao.countByHql("select count(*) from Packet").intValue();
	}
	
	@Override
	public List getPageList(int start, int number) {
		// TODO Auto-generated method stub
		return packetDao.getPageHql("from Packet", start, number) ;

	}
	@Override
	public int getCountByCondition(Packet p) {
		// TODO Auto-generated method stub
		

		  List<Object> paramList = new ArrayList<Object>();  
	                  
		  String hql = "select count(*) from Packet p  where 1=1" ;
		  if(p.getName()!=null&&!p.getName().equals(""))
		  {
			  hql=hql+" and p.name like ?";
			  paramList.add("%"+p.getName()+"%");
		  }
          hql=hql+" and p.effective=?";
          paramList.add(p.isEffective());
          
          if(p.getCreateDate()!=null&&!p.getCreateDate().equals(""))
          {
        	  hql=hql+" and p.createDate>=?";
        	  paramList.add(p.getCreateDate());
          }
          if(p.getEndDate()!=null&&!p.getEndDate().equals(""))
          {
        	  hql=hql+" and p.createDate<=?";
        	  paramList.add(p.getEndDate());
          }
          if(p.getCount()!=0)
          {
        	  hql=hql+" and p.count<=?";
        	  paramList.add(p.getCount());
          }
          if(p.getEndcount()!=0)
          {
        	  hql=hql+" and p.count>=?";
        	  paramList.add(p.getDevice());
          }
		//System.out.println("the hql is "+hql);
		return packetDao.countByHql(hql, paramList.toArray()).intValue();
	}
	@Override
	public List getPageListByCondition(Packet p, int start, int number) {
		// TODO Auto-generated method stub
		

		  List<Object> paramList = new ArrayList<Object>();  
	                  
		  String hql = "from Packet p  where 1=1" ;
		  if(p.getName()!=null&&!p.getName().equals(""))
		  {
			  hql=hql+" and p.name like ?";
			  paramList.add("%"+p.getName()+"%");
		  }
        hql=hql+" and p.effective=?";
        paramList.add(p.isEffective());
        
        if(p.getCreateDate()!=null&&!p.getCreateDate().equals(""))
        {
      	  hql=hql+" and p.createDate>=?";
      	  paramList.add(p.getCreateDate());
        }
        if(p.getEndDate()!=null&&!p.getEndDate().equals(""))
        {
      	  hql=hql+" and p.createDate<=?";
      	  paramList.add(p.getEndDate());
        }
        if(p.getCount()!=0)
        {
      	  hql=hql+" and p.count<=?";
      	  paramList.add(p.getCount());
        }
        if(p.getEndcount()!=0)
        {
      	  hql=hql+" and p.count>=?";
      	  paramList.add(p.getDevice());
        }
		return packetDao.getListByHQL(hql, paramList.toArray());
	}
	
	@Override
	public boolean delPacketById(int id) {
		// TODO Auto-generated method stub
		//需要删除七牛云上的包，xml，缩略图，
		Packet p=(Packet) packetDao.get(id);
		Qiniu.deleteFile(p.getDescPic());
		Qiniu.deleteFile(p.getThumbPic());
		Qiniu.deleteFile(p.getXml());
		Qiniu.deleteFile(p.getUrl());
		packetDao.delete(p);
		
	      
		return true;
	}
	@Override
	public boolean updatePacket(Packet p) throws Exception {
		// TODO Auto-generated method stub
	
		Packet pc=(Packet) packetDao.load(p.getId());
		if(!pc.getThumbPic().equals(p.getThumbPic()))
		{
			Qiniu.deleteFile(pc.getThumbPic());
			 String path = ServletActionContext.getServletContext().getRealPath("/upload");
	
			Qiniu.uploadFile(p.getThumbPic(),path+"\\"+p.getThumbPic());
		}
	
		//packetDao.ge
		
		packetDao.merge(p);
		
		return true;
	}

	

	

}
