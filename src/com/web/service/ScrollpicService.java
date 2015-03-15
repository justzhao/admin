package com.web.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.web.entity.ScrollPic;

public interface ScrollpicService {
	
	/**
	 * �ϴ�ͼƬ
	 * @param file
	 * @param fileName
	 * @param fileContentType
	 * @param realPath
	 * @return
	 * @throws IOException
	 */
	public String savePic(File file,String fileName,String fileContentType,String realPath)throws IOException;
	
	/**
	 * ����һ���ֲ�ͼ
	 * @param pic
	 * @return
	 */
	
	public boolean saveScrollPic(ScrollPic pic) throws Exception;
	
	/**
	 * ��ȡ���е� ͼ����
	 * @return
	 */
	public List getPicList();


}
