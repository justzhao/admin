package com.web.service.Impl;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.web.dao.IDao;
import com.web.entity.Model;

import com.web.util.Qiniu;
import com.web.util.Tools;

public class ModelService implements com.web.service.ModelService {

	  private IDao modelDao;

	public IDao getModelDao() {
		return modelDao;
	}

	public void setModelDao(IDao modelDao) {
		this.modelDao = modelDao;
	}
	
	@Override
	public boolean saveModel(Model m) throws Exception {
		
		// 先吧文件上传到七牛云
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
	  //这里当然不仅仅是从数据库里面删除，还需要重新把对应的包删除，删除七牛云上的东西，
	 modelDao.delete(m);
	return true;
}
  
	  
	
	
}
