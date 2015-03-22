package com.web.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
	
	
	@Override
	public int getCountByCondition(IdentifyCode code) {
		// TODO Auto-generated method stub
	    List<Object> paramList = new ArrayList<Object>();  
    	
	    String hql = "select count(*) from IdentifyCode code  where 1=1" ;
	    //按名字搜索
	    if(code.getName()!=null&&!code.getName().equals(""))
	    {
			hql =hql +" and  code.name like ?";
			
			paramList.add("%"+code.getName()+"%");
	    }
	    //按开始时间
	    if(code.getCreateDate()!=null &&!code.getCreateDate().equals(""))
	    {
			hql=hql+" and code.createDate >=?";
			
			paramList.add(code.getCreateDate());
	    }
	    //按结束时间
	    if(code.getEndDate()!=null&&!code.getEndDate().equals(""))
	    {
			hql=hql+" and  code.createDate <=?";
			
			paramList.add(code.getEndDate());
	    }
	    //按上传用户
	    if(code.getOwner()!=null&&!code.getOwner().equals(""))
	    {
			hql =hql +" and  code.owner like ?";
			
			paramList.add("%"+code.getOwner()+"%");
	    }
	    //按是否被打包
	    if(code.isPacked()||!code.isPacked())
	    {
			hql =hql +" and  code.packed = ?";
			
			paramList.add(code.isPacked());
	    }
	    System.out.println("the hql is "+hql);
	    
	    System.out.println("the size is "+codeDao.countByHql(hql, paramList.toArray()).intValue());
	    
	    return codeDao.countByHql(hql, paramList.toArray()).intValue();
	}
	
	@Override
	public List getPageListByCondition(IdentifyCode code, int start, int number) {
		// TODO Auto-generated method stub
		ArrayList  paramList = new ArrayList();  
		  
		String hql = "from IdentifyCode code  where 1=1" ;
	    //按名字搜索
	    if(code.getName()!=null&&!code.getName().equals(""))
	    {
			hql =hql +" and  code.name like ?";
			
			paramList.add("%"+code.getName()+"%");
	    }
	    //按开始时间
	    if(code.getCreateDate()!=null &&!code.getCreateDate().equals(""))
	    {
			hql=hql+" and code.createDate >=?";
			
			paramList.add(code.getCreateDate());
	    }
	    //按结束时间
	    if(code.getEndDate()!=null&&!code.getEndDate().equals(""))
	    {
			hql=hql+" and  code.createDate <=?";
			
			paramList.add(code.getEndDate());
	    }
	  //按上传用户
	    if(code.getOwner()!=null&&!code.getOwner().equals(""))
	    {
			hql =hql +" and  code.owner like ?";
			
			paramList.add("%"+code.getOwner()+"%");
	    }
	  //按是否被打包
	    if(code.isPacked()||!code.isPacked())
	    {
			hql =hql +" and  code.packed = ?";
			
			paramList.add(code.isPacked());
	    }
	    return codeDao.getPageHql(hql, start, number,paramList.toArray());
	}

 

}
