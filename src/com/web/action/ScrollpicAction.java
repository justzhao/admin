package com.web.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.entity.ScrollPic;
import com.web.service.ScrollpicService;


public class ScrollpicAction extends ActionSupport {
	
	private File file;
	//private List<File> file;
	private String fileFileName; //�ļ�����
    private String fileContentType;
    private ScrollpicService picService;
    
    private  ScrollPic pic;
	private JSONArray pics;
	



	public JSONArray getPics() {
		return pics;
	}


	public void setPics(JSONArray pics) {
		this.pics = pics;
	}


	public ScrollPic getPic() {
		return pic;
	}


	public void setPic(ScrollPic pic) {
		this.pic = pic;
	}


	public ScrollpicService getPicService() {
		return picService;
	}


	public void setPicService(ScrollpicService picService) {
		this.picService = picService;
	}


	public File getFile() {
		return file;
	}


	public void setFile(File file) {
		this.file = file;
	}





	public String getFileFileName() {
		return fileFileName;
	}


	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}


	public String getFileContentType() {
		return fileContentType;
	}


	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}


	public String uploadImg() throws IOException
	{
		

		
		   HttpServletResponse response = ServletActionContext.getResponse();
		
		 String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
	    

	        
	       String url=  picService.savePic(file, fileFileName, fileContentType, realpath);
	      
	      
	       response.setContentType("text/html;charset=utf-8"); 
	        PrintWriter pw = response.getWriter();  
	        pw.print(url); 

       	return NONE;
	}
	public String saveScroll() throws Exception
	{
		System.out.println("this is"+pic.getInfo()+pic.getName()+pic.getUrls());
		picService.saveScrollPic(pic);
		   HttpServletResponse response = ServletActionContext.getResponse();
		   response.setContentType("text/html;charset=utf-8");   
	        PrintWriter pw = response.getWriter();  
	        pw.print("����ɹ�"); 
		return NONE;
	}
	public String getPicList()
	{
	
		pics=JSONArray.fromObject(picService.getPicList());
		return SUCCESS;
	}


}