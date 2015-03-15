package com.web.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.entity.ScrollPic;
import com.web.service.ScrollpicService;
import com.web.util.Tools;


public class ScrollpicAction extends ActionSupport {
	
	private File file;
	//private List<File> file;
	private String fileFileName; //文件名称
    private String fileContentType;
    private ScrollpicService picService;
    private int rows;
    private int page;
    private  ScrollPic pic;
	private JSONObject pics;
	



	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}





	public JSONObject getPics() {
		return pics;
	}


	public void setPics(JSONObject pics) {
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
	    

	      String url=Tools.saveFile(file, fileFileName, fileContentType, realpath);   
	     //  String url=  picService.savePic(file, fileFileName, fileContentType, realpath);
	      
	      
	       response.setContentType("text/html;charset=utf-8"); 
	        PrintWriter pw = response.getWriter();  
	        pw.print(url); 

       	return NONE;
	}
	public String saveScroll() throws Exception
	{
		//System.out.println("this is"+pic.getInfo()+pic.getName()+pic.getUrls());
		picService.saveScrollPic(pic);
		   HttpServletResponse response = ServletActionContext.getResponse();
		   response.setContentType("text/html;charset=utf-8");   
	        PrintWriter pw = response.getWriter();  
	        pw.print("保存成功"); 
		return NONE;
	}
	public String getPicList()
	{
	
		pics=JSONObject .fromObject(picService.getPicList().toString());
		return SUCCESS;
	}
	public String getPageList()
	{
		
		//当前页 
        int intPage = page == 0 ? 1:page; 
        //每页显示条数 
        int number = rows == 0 ? 10:rows;  
        int start = (intPage-1)*number;  
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total", picService.getCount());
        jsonMap.put("rows", picService.getPageList(start, number));
      
        pics= JSONObject.fromObject(jsonMap);
		return SUCCESS;
	}


}
