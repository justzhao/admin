package com.web.service.Impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.web.dao.IDao;
import com.web.entity.Model;

import com.web.util.Qiniu;
import com.web.util.Tools;

public class ModelServiceImpl implements com.web.service.IModelService {

	  private IDao modelDao;

	public IDao getModelDao() {
		return modelDao;
	}

	public void setModelDao(IDao modelDao) {
		this.modelDao = modelDao;
	}
	
	@Override
	public boolean saveModel(Model m) throws Exception {
		
		// �Ȱ��ļ��ϴ�����ţ��
		 String path = ServletActionContext.getServletContext().getRealPath("/upload");
		  Qiniu.uploadFile(m.getUrl(), path+"\\"+m.getUrl());	
		  Tools.delFile(path+"\\"+m.getUrl());
		  modelDao.save(m);
		return false;
	}
  @Override
public List getModelList() {
	// TODO Auto-generated method stub
	return modelDao.getListByHQL("from Model");											
}
 @Override
public List getPageList(int start, int number) {
	// TODO Auto-generated method stub
	return modelDao.getPageHql("from Model", start, number);
}
  
  @Override
public int getCount() {
	// TODO Auto-generated method stub
	return modelDao.countByHql("select count(*) from Model").intValue();
}
  @Override
public boolean checkName( String name) {
	// TODO Auto-generated method stub
	  
	  
	  return  modelDao.countByHql("select count(*) from Model m where m.name=?", name)==0;

}
  @Override
public boolean delModel(Model m) {
	// TODO Auto-generated method stub
	  //���ﵱȻ�������Ǵ����ݿ�����ɾ��������Ҫ���°Ѷ�Ӧ�İ�ɾ����ɾ����ţ���ϵĶ�����
	 modelDao.delete(m);
	 
	 
	return true;
}
  
  @Override
public boolean delModels(String ids) {
	// TODO Auto-generated method stub
	  
	  if(ids!=null)
	  {
	  String []id=ids.split(",");
	  for(int i=0;i<id.length;i++)
	  {
	  modelDao.deleteById(Integer.parseInt(id[i]));
	  }
	  }
	  return true;
}
  
  @Override
public int getCountByCondition(Model m) {
	// TODO Auto-generated method stub
	  
	 
	  List<Object> paramList = new ArrayList<Object>();  
                    	//  select count(*) from Model
	  String hql = "select count(*) from Model m  where 1=1" ;
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
   
	  hql=hql+" and m.packaged=?";
	  paramList.add(m.isPackaged());
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
	
    //System.out.println("the size is "+modelDao.countByHql(hql, paramList.toArray()).intValue());
	return modelDao.countByHql(hql, paramList.toArray()).intValue();

	  
	//  return modelDao.countByHql("select count(*) from Model m where 1=1 and  m.name=?", m.getName()).intValue();
}
  
  @Override
public List getPageListByCondition(Model m, int start, int number) {
	// TODO Auto-generated method stub

	  ArrayList  paramList = new ArrayList();  
	  
	  String hql = " from Model m  where 1=1";
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
   
	  hql=hql+" and m.packaged=?";
	  paramList.add(m.isPackaged());
	  
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
	
	  modelDao.saveOrUpdate (m);
	  return true;
}
  
  @Override
public String zipModes(String ids) throws Exception {
	// TODO Auto-generated method stub
	  
	  //1 ��Ҫ�Ȼ�ȡÿ��model������ţ�������ض�Ӧ���ļ�
	  if(ids!=null)
	  {
		  String id[]=ids.split(",");
		  List <String> paths=new ArrayList<String>();
		  for(int i=0;i<id.length;i++)
		  {
			  Model m=(Model) modelDao.get(Integer.parseInt(id[i]));
			  paths.add(m.getUrl());
			  Qiniu.downloadFile(m.getUrl());
			  
		  }
		  //2���  (�Ȳ����)
	//  return	  Tools.ZipFiles(paths,"");
		
		  return Tools.getRandomFileName()+".zip";
		  
	  }
	  
	return "";
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
