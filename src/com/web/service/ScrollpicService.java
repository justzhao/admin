package com.web.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.web.entity.ScrollPic;

public interface ScrollpicService {
	
	/**
	 * 上传图片
	 * @param file
	 * @param fileName
	 * @param fileContentType
	 * @param realPath
	 * @return
	 * @throws IOException
	 */
	public String savePic(File file,String fileName,String fileContentType,String realPath)throws IOException;
	
	/**
	 * 保存一组轮播图
	 * @param pic
	 * @return
	 */
	
	public boolean saveScrollPic(ScrollPic pic) throws Exception;
	
	/**
	 * 获取所有的 图像组
	 * @return
	 */
	public List getPicList();


}
