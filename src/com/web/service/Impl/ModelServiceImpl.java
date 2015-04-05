package com.web.service.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.web.dao.IDao;
import com.web.entity.Model;
import com.web.entity.Packet;

import com.web.util.Qiniu;
import com.web.util.Tools;

public class ModelServiceImpl implements com.web.service.IModelService {

	  private IDao modelDao;
	  
	  private IDao packetDao;

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

	@Override
	public boolean saveModel(Model m) throws Exception {
		

		  Qiniu.uploadFile(m.getUrl());	
	
		  modelDao.save(m);
		return true;
	}
  @Override
public List getModelList() {
	// TODO Auto-generated method stub
	return modelDao.getListByHQL("from Model where id!=1");											
}
 @Override
public List getPageList(int start, int number) {
	// TODO Auto-generated method stub
	return modelDao.getPageHql("from Model where id!=1", start, number);
}
  
  @Override
public int getCount() {
	// TODO Auto-generated method stub
	return modelDao.countByHql("select count(*) from Model where id!=1").intValue();
}
  @Override
public boolean checkName( String name) {
	// TODO Auto-generated method stub
	  
	  
	  return  modelDao.countByHql("select count(*) from Model m where  m.id!=1 and m.name=?", name)==0;

}
  @Override
public boolean delModel(Model m) {
	// TODO Auto-generated method stub
	  //这里当然不仅仅是从数据库里面删除，还需要重新把对应的包删除，删除七牛云上的东西，
	 modelDao.delete(m);
	 
	 
	return true;
}
  
  @Override
public boolean delModels(String ids) {
	// TODO Auto-generated method stub
	  
	  
	  
	  //仅仅是删除数据库中模型，需要做的是先删除七牛云上的东西。
	  
	  if(ids!=null)
	  {
	  String []id=ids.split(",");
	  for(int i=0;i<id.length;i++)
	  {
		  
		  //先解除该模型和已经数据包之间的关系
		  List<Packet> ps=packetDao.getListByHQL("select p  from Packet p , Model m where p in  elements(m.packets)  and m.id=? ", Integer.parseInt(id[i]));

		  for(int j=0;j<ps.size();j++){
			  
			 packetDao.querySql("update tbl_model_packet set modelId=1 where modelId=? ", Integer.parseInt(id[i]));
		  }
		  
		  Model m=(Model) modelDao.get(Integer.parseInt(id[i]));
		  //删除七牛云
		  Qiniu.deleteFile(m.getUrl());
	  
		  modelDao.deleteById(Integer.parseInt(id[i]));
	
	  
	  }
	  }
	  return true;
}
  
  @Override
public int getCountByCondition(Model m) {
	// TODO Auto-generated method stub
	  
	 
	  List<Object> paramList = new ArrayList<Object>();  
                    
	  String hql = "select count(*) from Model m  where 1=1 and m.id!=1" ;
	  if(m.getName()!=null&&!m.getName().equals(""))
	  {
		  hql =hql +" and   m.name like ?";
		  paramList.add("%"+m.getName()+"%");
	  }
	  if(m.getOwner()!=null&&!m.getName().equals(""))
	  {
		  hql=hql+" and m.owner like ?";
		  paramList.add("%"+m.getOwner()+"%");
	  }

	  
	    //按是否被打包

	    if(m.getSearchFlag()==0)
	    {//否
	    	
			hql =hql +" and  m.packaged = ?";
			
			paramList.add(false);
	    }else if(m.getSearchFlag()==1)
	    {//是
			hql =hql +" and  m.packaged = ?";
			
			paramList.add(true);
	    }else{
	    	
	    }
	  
	  
	  if(m.getCreateDate()!=null &&!m.getCreateDate().equals(""))
	  {
		  hql=hql+" and  m.createDate >=?";
		  paramList.add(m.getCreateDate());
	  }
	  if(m.getEndDate()!=null&&!m.getEndDate().equals(""))
	  {
		  hql=hql+" and  m.createDate <=?";
		  paramList.add(m.getEndDate());
	  }
	  
	  if(m.getCode()!=null&&m.getCode().getId()!=0)
	  {
		  hql=hql+" and  m.code.id =?";
		  paramList.add(m.getCode().getId());
	  }
	
    //System.out.println("the size is "+modelDao.countByHql(hql, paramList.toArray()).intValue());
	return modelDao.countByHql(hql, paramList.toArray()).intValue();

	  
	//  return modelDao.countByHql("select count(*) from Model m where 1=1 and  m.name=?", m.getName()).intValue();
}
  
  @Override
public List getPageListByCondition(Model m, int start, int number) {
	// TODO Auto-generated method stub

	  ArrayList  paramList = new ArrayList();  
	  
	  String hql = " from Model m  where 1=1 and m.id!=1";
	  if(m.getName()!=null&&!m.getName().equals(""))
	  {
		  hql =hql +" and m.name like ?";
		  paramList.add("%"+m.getName()+"%");
	  }
	  if(m.getOwner()!=null)
	  {
		  hql=hql+" and m.owner like ?";
		  paramList.add("%"+m.getOwner()+"%");
	  }
   
	  /**
	  hql=hql+" and m.packaged=?";
	  paramList.add(m.isPackaged());
	  */
	  
	    //按是否被打包

	    if(m.getSearchFlag()==0)
	    {//否
	    	
			hql =hql +" and  m.packaged = ?";
			
			paramList.add(false);
	    }else if(m.getSearchFlag()==1)
	    {//是
			hql =hql +" and   m.packaged= ?";
			
			paramList.add(true);
	    }else{
	    	
	    }
	  
	  if(m.getCreateDate()!=null &&!m.getCreateDate().equals(""))
	  {
		  hql=hql+" and  m.createDate >=?";
		  paramList.add(m.getCreateDate());
	  }
	  if(m.getEndDate()!=null&&!m.getEndDate().equals(""))
	  {
		  hql=hql+" and  m.createDate <=?";
		  paramList.add(m.getEndDate());
	  }
	  
	  if(m.getCode()!=null&&m.getCode().getId()!=0)
	  {
		  hql=hql+" and  m.code.id =?";
		  paramList.add(m.getCode().getId());
	  }
	  hql=hql+"  order by id asc";
	  return modelDao.getPageHql(hql, start, number,paramList.toArray());

}
  
  @Override
public Model getModelById(int id) {
	// TODO Auto-generated method stub
	return (Model) modelDao.get(id);
}
  
  @Override
public boolean updateModel(Model m) {
	// TODO Auto-generated method stub
	//判断是否模型文件有更新的话，就需要删除原来的。
	  
	  
	  
	  try {
		  //权宜之计，evit没有用了总是报两个实体的错误
		  
		  String oriurl=(String) modelDao.getBySQL("select url from tbl_model where id=?", m.getId());
		  System.out.println("the url is "+oriurl);
		  if(!m.getUrl().equals(oriurl))
		  {
			  Qiniu.deleteFile(oriurl);
			  Qiniu.uploadFile(m.getUrl());
		  }
		  
		  /**
  Model mm=(Model) modelDao.get(m.getId());
		 
		  
		  if(!mm.getUrl().equals(m.getUrl()))
		  {
			  Qiniu.deleteFile(mm.getUrl());
			  Qiniu.uploadFile(m.getUrl());
			 
		  }
		  modelDao.get
		  modelDao.evict(mm);
		  
		//  modelDao.refresh(m);*/
		  modelDao.update(m);
	} catch (Exception e) {
		// TODO: handle exception
		
		return false;
	}

	  return true;
}
  
  @Override
public String zipModes(String ids) {
	// TODO Auto-generated method stub
	  
	  //1 需要先获取每个model，从七牛云上下载对应的文件
	  if(ids!=null)
	  {
		  String id[]=ids.split(",");
		  List <String> paths=new ArrayList<String>();
		  for(int i=0;i<id.length;i++)
		  {	  
			  
			  try {
			  Model m=(Model) modelDao.get(Integer.parseInt(id[i]));
			  paths.add(m.getUrl());
		
				Qiniu.downloadFile(m.getUrl());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return "";
			}
			  
		  }

		
		  return Tools.getRandomFileName()+".zip";
		  
	  }
	  
	return "";
}
  
  
  @Override
public boolean cancelZip(String ids) {
	// TODO Auto-generated method stub
	//删除已经下载的模型
	  
	  if(ids!=null)
	  {
		  String id[]=ids.split(",");
		for(int i=0;i<id.length;i++)
		{
			
			Model m=(Model) modelDao.get(Integer.valueOf( id[i]));
			
			Tools.delFile(m.getUrl());
		}
	  }
	  
	  return true;
}
  @Override
public boolean updatePacket(Set<Model> ms) {
	// TODO Auto-generated method stub
	  Iterator iterator = ms.iterator();
	  while(iterator.hasNext())
	  {
		  Model  m = (Model) iterator.next();
	       m.setPackaged(true);
	       modelDao.saveOrUpdate(m);
	  }
	  
	return false;
}

	  
	
	
}
