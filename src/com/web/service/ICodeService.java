package com.web.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.web.entity.IdentifyCode;



public interface ICodeService {

	/**
	 * ��ȡ���е�ʶ����
	 * @return
	 */
 public List getCodeList();
 
 
 /**
	 * �ϴ�ͼƬ
	 * @param file
	 * @param fileName
	 * @param fileContentType
	 * @param realPath
	 * @return
	 * @throws IOException
	 */
	public String saveCode(File file,String fileName,String fileContentType,String realPath)throws IOException;
	
	/**
	 * ���浽���ݿ� ��ţ��
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
