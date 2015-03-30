package com.web.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.entity.ScrollPic;
import com.web.service.IScrollpicService;
import com.web.util.JsonDateValueProcessor;
import com.web.util.Tools;


public class ScrollpicAction extends ActionSupport {
	
	private File file;
	//private List<File> file;
	private String fileFileName; //文件名称
    private String fileContentType;
    private IScrollpicService picService;
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


	public IScrollpicService getPicService() {
		return picService;
	}


	public void setPicService(IScrollpicService picService) {
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

	      
	      
	       response.setContentType("text/html;charset=utf-8"); 
	        PrintWriter pw = response.getWriter();  
	        pw.print(url); 

       	return NONE;
	}
	public String saveScroll() throws Exception
	{
		 PrintWriter pw = Tools.getPw();  
			pic.setOwner(Tools.getCurrentUser().getName());
		 try {
			  pw.print(picService.saveScrollPic(pic));
		} catch (RuntimeException e) {
			// TODO: handle exception
			  pw.print(false); 
		}
		
	    
	

	       
	      
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
        
        if(pic==null)
        {
        jsonMap.put("total", picService.getCount());
        jsonMap.put("rows", picService.getPageList(start, number));
        }else
        {
        	 jsonMap.put("total", picService.getCountByCondition(pic));
             jsonMap.put("rows", picService.getPageListByCondition(start, number, pic));
        }
        
        JsonConfig jsonConfig = new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
      
        pics= JSONObject.fromObject(jsonMap,jsonConfig);
		return SUCCESS;
	}
	public String deScrollPic() throws IOException{
		
		PrintWriter pw=Tools.getPw();
		
		try {
		pw.print(picService.delScrollPic(pic));
		
		} catch (RuntimeException e) {
			// TODO: handle exception
			e.printStackTrace();
			pw.print(false);
			
		}
		return NONE;
	}
	
	public String updateScroll() throws Exception
	{
		PrintWriter pw=Tools.getPw();
	
	try{
		 pw.print(picService.updateScroll(pic));
	}catch (RuntimeException e) {
		// TODO: handle exception
		pw.print(false);
	}
	
	
		
	
		return NONE;
	}


}
