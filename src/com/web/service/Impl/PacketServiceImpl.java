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
import com.web.entity.Theme;
import com.web.service.IPacketService;
import com.web.util.Qiniu;
import com.web.util.Tools;
import com.web.util.XmlTreeUtil;

public class PacketServiceImpl implements IPacketService {
	private IDao packetDao;
	private IDao modelDao;
	private IDao codeDao;
	
	private IDao themeDao;

	public IDao getModelDao() {
		return modelDao;
	}

	public void setModelDao(IDao modelDao) {
		this.modelDao = modelDao;
	}

	public IDao getPacketDao() {
		return packetDao;
	}

	public void setPacketDao(IDao packetDao) {
		this.packetDao = packetDao;
	}

	
	public IDao getCodeDao() {
		return codeDao;
	}

	public void setCodeDao(IDao codeDao) {
		this.codeDao = codeDao;
	}
	

	public IDao getThemeDao() {
		return themeDao;
	}

	public void setThemeDao(IDao themeDao) {
		this.themeDao = themeDao;
	}

	@Override
	public boolean savePacket(Packet p)  {
		// TODO Auto-generated method stub


			  
			  try {
					 List<String> paths=new ArrayList<String>();
						
					  //获取所有的模型
					  Set<Model>  models=p.getModels();
					  
					  Iterator ite = models.iterator();
					  while(ite.hasNext())
					  {
						  Model  m = (Model) ite.next();
					      
						  //更新识别码打包状态
						  m.getCode().setPacked(true);
				   Qiniu.downloadFile(m.getCode().getUrl());
				   
				   //加入识别码的路径
		    	     paths.add(m.getCode().getUrl());
		    	     
					  codeDao.merge(m.getCode());
		    	        
		    	        //更新包的打包状态
					  m.setPackaged(true);
				       modelDao.saveOrUpdate(m);
				       
				       //添加路径
			  	       paths.add(m.getUrl());
			  	     codeDao.evict(m.getCode());
				  }
				  
				  
				  List<Model> modellist = new ArrayList<Model>(models);
				  //把所有的模型的属性生成xml .
				XmlTreeUtil.parseNodeToXML(modellist);
				
				//所有的文件都在upload目录下面，现在要做的就是把 xml，说明图，识别码，模型打成zip包。
				
				//  XmlTreeUtil.parseNodeToXML(treeNodes)
				String realpath = ServletActionContext.getServletContext().getRealPath("/upload");


				//上传缩略住到七牛云
				Qiniu.uploadFile(p.getThumbPic());
			   //上传缩略图上到七牛云
				Qiniu.uploadFile(p.getThumbUp());
				 //上传缩略图下到七牛云
				Qiniu.uploadFile(p.getThumbFooter());
				 //上传缩略图文字到七牛云
				Qiniu.uploadFile(p.getThumbWord());
				 //加入说明图路径
				 paths.add(p.getDescPic());
				 //加入人物
				 paths.add(p.getCharacter());
				 //加入背景
				 paths.add(p.getBackground());
		           //加入xml文件
				 paths.add(XmlTreeUtil.NAME);
				 
				 
		   //paths 是需要所有打包的文件的路径，这里过滤下。
				 
				 paths=Tools.removeDuplicate(paths);

		    
				//把说明文件压缩到zip中
				Tools.ZipFiles(paths,p.getUrl());
				Qiniu.uploadFile(p.getUrl());
				
				//更新一下对应主题组的状态。
				Theme t=(Theme) themeDao.get(p.getTheme().getId());
		       t.setFlag(true);
		       themeDao.saveOrUpdate(t);
				 
				packetDao.save(p);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
			  
    	  
    	     
    	  
		
		return true;
		
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

		    if(p.getSearchFlag()==0)
		    {//否
		    	
				hql =hql +" and p.testPacket = ?";
				
				paramList.add(false);
		    }
		    if(p.getSearchFlag()==1)
		    {//是
				hql =hql +" and  p.testPacket= ?";
				
				paramList.add(true);
		    }
		    
		    
		    if(p.getEffectiveFlag()==0)
		    {//否
		    	
				hql =hql +" and p.effective = ?";
				
				paramList.add(false);
		    }
		    if(p.getEffectiveFlag()==1)
		    {//是
				hql =hql +" and  p.effective= ?";
				
				paramList.add(true);
		    }
		    
		    
		    if(p.getDevice()!=-1){
		    	
		    	hql=hql+ " and p.device=?";
		    	paramList.add(p.getDevice());
		    }
		    
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

		  
		    if(p.getSearchFlag()==0)
		    {//否
				hql =hql +" and p.testPacket = ?";
				paramList.add(false);
		    } 
		    
		    if(p.getSearchFlag()==1)
		    {//是
				hql =hql +" and  p.testPacket= ?";
				paramList.add(true);
		    }
		    
		    
		    if(p.getEffectiveFlag()==0)
		    {//否
				hql =hql +" and p.effective = ?";
				paramList.add(false);
		    } 
		    
		    if(p.getEffectiveFlag()==1)
		    {//是
				hql =hql +" and  p.effective= ?";
				paramList.add(true);
		    }
        
		    if(p.getDevice()!=-1){
		    	
		    	hql=hql+ " and p.device=?";
		    	paramList.add(p.getDevice());
		    }    
		    
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
        
        hql=hql+"  order by id asc";
		return packetDao.getListByHQL(hql, paramList.toArray());
	}
	
	@Override
	public boolean delPacketById(int id) {
		// TODO Auto-generated method stub
		//需要删除七牛云上的包，xml，缩略图，
		Packet p=(Packet) packetDao.get(id);

		Qiniu.deleteFile(p.getThumbPic());

		Qiniu.deleteFile(p.getUrl());
		packetDao.delete(p);
		
	      
		return true;
	}
	@Override
	public boolean updatePacket(Packet p) throws Exception {
		// TODO Auto-generated method stub
	//需要把原来的theme更新为未设置
	//获取原来的packet.
		
		try {
			Packet pp=(Packet) packetDao.get(p.getId());
			Theme tt=pp.getTheme();
			tt.setFlag(false);
			themeDao.saveOrUpdate(tt);
			
			packetDao.evict(pp);
	       Theme t=(Theme) themeDao.get(p.getTheme().getId());
	        t.setFlag(true);
	        themeDao.saveOrUpdate(t);
			
			packetDao.saveOrUpdate(p);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

		
		return true;
	}

	

	

}
