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
						
					  //��ȡ���е�ģ��
					  Set<Model>  models=p.getModels();
					  
					  Iterator ite = models.iterator();
					  while(ite.hasNext())
					  {
						  Model  m = (Model) ite.next();
					      
						  //����ʶ������״̬
						  m.getCode().setPacked(true);
				   Qiniu.downloadFile(m.getCode().getUrl());
				   
				   //����ʶ�����·��
		    	     paths.add(m.getCode().getUrl());
		    	     
					  codeDao.merge(m.getCode());
		    	        
		    	        //���°��Ĵ��״̬
					  m.setPackaged(true);
				       modelDao.saveOrUpdate(m);
				       
				       //���·��
			  	       paths.add(m.getUrl());
			  	     codeDao.evict(m.getCode());
				  }
				  
				  
				  List<Model> modellist = new ArrayList<Model>(models);
				  //�����е�ģ�͵���������xml .
				XmlTreeUtil.parseNodeToXML(modellist);
				
				//���е��ļ�����uploadĿ¼���棬����Ҫ���ľ��ǰ� xml��˵��ͼ��ʶ���룬ģ�ʹ��zip����
				
				//  XmlTreeUtil.parseNodeToXML(treeNodes)
				String realpath = ServletActionContext.getServletContext().getRealPath("/upload");


				//�ϴ�����ס����ţ��
				Qiniu.uploadFile(p.getThumbPic());
			   //�ϴ�����ͼ�ϵ���ţ��
				Qiniu.uploadFile(p.getThumbUp());
				 //�ϴ�����ͼ�µ���ţ��
				Qiniu.uploadFile(p.getThumbFooter());
				 //�ϴ�����ͼ���ֵ���ţ��
				Qiniu.uploadFile(p.getThumbWord());
				 //����˵��ͼ·��
				 paths.add(p.getDescPic());
				 //��������
				 paths.add(p.getCharacter());
				 //���뱳��
				 paths.add(p.getBackground());
		           //����xml�ļ�
				 paths.add(XmlTreeUtil.NAME);
				 
				 
		   //paths ����Ҫ���д�����ļ���·������������¡�
				 
				 paths=Tools.removeDuplicate(paths);

		    
				//��˵���ļ�ѹ����zip��
				Tools.ZipFiles(paths,p.getUrl());
				Qiniu.uploadFile(p.getUrl());
				
				//����һ�¶�Ӧ�������״̬��
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
		    {//��
		    	
				hql =hql +" and p.testPacket = ?";
				
				paramList.add(false);
		    }
		    if(p.getSearchFlag()==1)
		    {//��
				hql =hql +" and  p.testPacket= ?";
				
				paramList.add(true);
		    }
		    
		    
		    if(p.getEffectiveFlag()==0)
		    {//��
		    	
				hql =hql +" and p.effective = ?";
				
				paramList.add(false);
		    }
		    if(p.getEffectiveFlag()==1)
		    {//��
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
		    {//��
				hql =hql +" and p.testPacket = ?";
				paramList.add(false);
		    } 
		    
		    if(p.getSearchFlag()==1)
		    {//��
				hql =hql +" and  p.testPacket= ?";
				paramList.add(true);
		    }
		    
		    
		    if(p.getEffectiveFlag()==0)
		    {//��
				hql =hql +" and p.effective = ?";
				paramList.add(false);
		    } 
		    
		    if(p.getEffectiveFlag()==1)
		    {//��
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
		//��Ҫɾ����ţ���ϵİ���xml������ͼ��
		Packet p=(Packet) packetDao.get(id);

		Qiniu.deleteFile(p.getThumbPic());

		Qiniu.deleteFile(p.getUrl());
		packetDao.delete(p);
		
	      
		return true;
	}
	@Override
	public boolean updatePacket(Packet p) throws Exception {
		// TODO Auto-generated method stub
	//��Ҫ��ԭ����theme����Ϊδ����
	//��ȡԭ����packet.
		
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
