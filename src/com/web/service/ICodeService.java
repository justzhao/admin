package com.web.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.web.entity.IdentifyCode;



public interface ICodeService {

	/**
	 * 获取所有的识别码
	 * @return
	 */
 public List getCodeList();
 
 
 /**
	 * 上传图片
	 * @param file
	 * @param fileName
	 * @param fileContentType
	 * @param realPath
	 * @return
	 * @throws IOException
	 */
	public String saveCode(File file,String fileName,String fileContentType,String realPath)throws IOException;
	
	/**
	 * 保存到数据库 七牛云
	 * @param pic
	 * @return
	 */
	
	public boolean saveCodeTo(IdentifyCode code) throws Exception;
	
	public Long isRptByName(String codeName);
 
	
	
	public List<IdentifyCode> getPageHql(int start, int number);

	public Long getCount();
	
	public IdentifyCode getCodeByID(Integer id);
	
	public boolean deleteCode(IdentifyCode code);

	public boolean updateCode(IdentifyCode code);

	public void deltFrom(String oldUrl);
	

	public int getCountByCondition(IdentifyCode code);


	public List getPageListByCondition(IdentifyCode code, int start,int number);
	
	

}
