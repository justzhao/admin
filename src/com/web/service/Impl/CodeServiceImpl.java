package com.web.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import org.hibernate.HibernateException;

import com.web.dao.IDao;
import com.web.entity.IdentifyCode;
import com.web.entity.Model;
import com.web.util.Qiniu;
import com.web.util.Tools;

public class CodeServiceImpl implements com.web.service.ICodeService {

	private  IDao codeDao;
	private IDao modelDao;
	
	public IDao getCodeDao() {
		return codeDao;
	}

	public void setCodeDao(IDao codeDao) {
		this.codeDao = codeDao;
	}
	

	public IDao getModelDao() {
		return modelDao;
	}

	public void setModelDao(IDao modelDao) {
		this.modelDao = modelDao;
	}

	@Override
	public List getCodeList() {
		// TODO Auto-generated method stub
		return  codeDao.getListByHQL(" from IdentifyCode where id!=1");
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
		
	
	         String url = code.getUrl();

             Qiniu.uploadFile(url );
             //上传完之后删除本地的
           //  Tools.delFile(url);
		      codeDao.saveOrUpdate(code);  //更新数据库
			
			
		return true;
	}

	@Override
	public Long isRptByName(String codeName) {
		
		return codeDao.countByHql("select count(*) from IdentifyCode code where code.id!=1 and  code.name=?", codeName);
	}

	@Override
	public List<IdentifyCode> getPageHql(int start, int number) {
		return codeDao.getPageHql("from IdentifyCode code where code.id!=1", start, number);
	}

	@Override
	public Long getCount() {
		return codeDao.countByHql("select count(*) from IdentifyCode where id!=1");
	}

	@Override
	public IdentifyCode getCodeByID(Integer id) {
		IdentifyCode code =  (IdentifyCode) codeDao.get(id);
		
		return code;
	}

	@Override
	public boolean deleteCode(IdentifyCode code) {
		// 需要先更新有模型
		//直接去那个表里面查。
		
     List<Model> models=modelDao.getListByHQL("from Model where id!=1 and code.id=?",code.getId() );
     for(int i=0;i<models.size();i++)
     {
    IdentifyCode c=(IdentifyCode) codeDao.get(1);
    models.get(i).setCode(c);
     modelDao.saveOrUpdate(models.get(i));
     codeDao.evict(c);
     }
     
     codeDao.deleteById(code.getId());
		
			return true;
			

	

	}

	@Override
	public boolean updateCode(IdentifyCode code) {
		codeDao.saveOrUpdate(code);
		return true;
	}

	@Override
	public void deltFrom(String oldUrl) {
		Qiniu.deleteFile(oldUrl);
	}
	
	
	@Override
	public int getCountByCondition(IdentifyCode code) {
		// TODO Auto-generated method stub
	    List<Object> paramList = new ArrayList<Object>();  
    	
	    String hql = "select count(*) from IdentifyCode code  where 1=1 and code.id!=1" ;
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

	    if(code.getSearchFlag()==0)
	    {//否
	    	
			hql =hql +" and  code.packed = ?";
			
			paramList.add(false);
	    }else if(code.getSearchFlag()==1)
	    {//是
			hql =hql +" and  code.packed = ?";
			
			paramList.add(true);
	    }else{
	    	
	    }


	   
	    
	    return codeDao.countByHql(hql, paramList.toArray()).intValue();
	}
	
	@Override
	public List getPageListByCondition(IdentifyCode code, int start, int number) {
		// TODO Auto-generated method stub
		ArrayList  paramList = new ArrayList();  
		  
		String hql = "from IdentifyCode code  where 1=1 and code.id!=1" ;
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

	    if(code.getSearchFlag()==0)
	    {//否
	    	
			hql =hql +" and  code.packed = ?";
			
			paramList.add(false);
	    }else if(code.getSearchFlag()==1)
	    {//是
			hql =hql +" and  code.packed = ?";
			
			paramList.add(true);
	    }else{
	    	
	    }
	    
	    hql=hql+"  order by id asc";
	    return codeDao.getPageHql(hql, start, number,paramList.toArray());
	}

 

}
