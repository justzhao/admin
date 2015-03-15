package com.web.service.Impl;

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
	  
	
	
}
