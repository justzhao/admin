package com.web.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import com.web.dao.IDao;
import com.web.entity.IdentifyCode;
import com.web.util.Qiniu;
import com.web.util.Tools;

public class CodeServiceImpl implements com.web.service.ICodeService {

	private  IDao codeDao;
	
	public IDao getCodeDao() {
		return codeDao;
	}

	public void setCodeDao(IDao codeDao) {
		this.codeDao = codeDao;
	}

	@Override
	public List getCodeList() {
		// TODO Auto-generated method stub
		return  codeDao.getListByHQL(" from IdentifyCode");
	}
	
	@Override
	public String saveCode(File file, String fileName, String fileContentType,
			String realPath) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveCodeTo(IdentifyCode code) throws Exception {
			// 先把文件上传到七牛云
		
			 String path = ServletActionContext.getServletContext().getRealPath("/upload");
			 
			 System.out.println("the reapath is "+path+"\\"+code.getUrl());
	
			 String url = code.getUrl();
			 

			 
             Qiniu.uploadFile(url, path+"\\"+url);
             //上传完之后删除本地的
             Tools.delFile(path+"\\"+url);
             
			 codeDao.saveOrUpdate(code);  //更新数据库
			
			
		return false;
	}

	@Override
	public Long isRptByName(String codeName) {
		
		return codeDao.countByHql("select count(*) from IdentifyCode code where code.name=?", codeName);
	}

	@Override
	public List<IdentifyCode> getPageHql(int start, int number) {
		return codeDao.getPageHql("from IdentifyCode code", start, number);
	}

	@Override
	public Long getCount() {
		return codeDao.countByHql("select count(*) from IdentifyCode");
	}

	@Override
	public IdentifyCode getCodeByID(Integer id) {
		IdentifyCode code =  (IdentifyCode) codeDao.get(id);
		System.out.println("to delete code id "+code.getId());
		return code;
	}

	@Override
	public boolean deleteCode(IdentifyCode code) {
		try{
			codeDao.delete(code);
			//int a = 1/0;
			return true;
			
		}catch(HibernateException e){
			return false;
		}catch(Exception e){
			return false;
		}
		
		
	}

	@Override
	public boolean updateCode(IdentifyCode code) {
		codeDao.saveOrUpdate(code);
		return false;
	}

	@Override
	public void deltFrom(String oldUrl) {
		Qiniu.deleteFile(oldUrl);
	}

 

}
