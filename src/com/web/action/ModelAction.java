package com.web.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


import com.opensymphony.xwork2.ActionSupport;
import com.web.entity.Model;
import com.web.service.IModelService;

import com.web.util.JsonDateValueProcessor;
import com.web.util.Tools;
import com.web.util.XmlTreeUtil;

public class ModelAction extends ActionSupport {

	private IModelService modelService;
    private Model model;
	private JSONObject  models;
	private File file;
	private String fileFileName; //文件名称
    private String fileContentType;
    private int rows;
    private int page;
    
	

	

	public IModelService getModelService() {
		return modelService;
	}

	public void setModelService(IModelService modelService) {
		this.modelService = modelService;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}


	
	
	



	public JSONObject getModels() {
		return models;
	}

	public void setModels(JSONObject models) {
		this.models = models;
	}

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

	public String saveModel() throws Exception
	{
		
			
		 String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
	       //上传用户暂时写死
	       model.setOwner(Tools.getCurrentUser().getName());
	  
	       //上传的时间
	       model.setCreateDate(Tools.getDate());
	       PrintWriter pw =Tools.getPw();
	        
	       try {       
	    	   
	    	   String url= Tools.saveFile(file, fileFileName, fileContentType, realpath);
	           model.setUrl(url);
	        
	           pw.print(modelService.saveModel(model));
	
			
		} catch (RuntimeException e) {
			// TODO: handle exception
		       pw.print(false); 
		}
	
	 

		return NONE;
	}
	
	public String getModelById()
	{
	     JsonConfig jsonConfig = new JsonConfig();
		  jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
		models=JSONObject.fromObject(modelService.getModelById(model.getId()),jsonConfig);
		return SUCCESS;
	}
	public String getModelList()
	{
		
     JsonConfig jsonConfig = new JsonConfig();
	  jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
	  jsonConfig.setExcludes(new String[]{"packets"});

	 Map<String, Object> jsonMap = new HashMap<String, Object>();
	 jsonMap.put("list", modelService.getModelList());
	 models= JSONObject.fromObject( jsonMap,jsonConfig);
	  return SUCCESS;
	}
	public String getPageList() throws IOException
	{
		
		int intPage = page == 0 ? 1:page; 
        //每页显示条数 
        int number = rows == 0 ? 10:rows;  
        int start = (intPage-1)*number;  
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        if(model==null)
        {
        	
    //XmlTreeUtil.parseNodeToXML( modelService.getPageList(start, number));
       jsonMap.put("total", modelService.getCount());
       jsonMap.put("rows", modelService.getPageList(start, number));
        }else{
        	
        	jsonMap.put("total",modelService.getCountByCondition(model));
            jsonMap.put("rows", modelService.getPageListByCondition(model, start, number));
        }
 	  JsonConfig jsonConfig = new JsonConfig();
 	  jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
 	  jsonConfig.setExcludes(new String[]{"packets"});

        models= JSONObject.fromObject(jsonMap,jsonConfig);
		return SUCCESS;
		
		
	}
	
	public String checkName() throws IOException
	{

	        PrintWriter pw =Tools.getPw();
	        pw.print(modelService.checkName(model.getName())); 
	     	return NONE;
	}
	public String delModel() throws IOException
	{
		PrintWriter pw=Tools.getPw();
		
		
		try {
			String ids=ServletActionContext.getRequest().getParameter("ids");
			pw.print(modelService.delModels(ids));
		} catch (RuntimeException e) {
			// TODO: handle exception
			pw.print(false);
		}

		
		
		return NONE;
	}
	
	public String updateModel() throws IOException
	{
		
		PrintWriter pw=Tools.getPw();
		
		try {
			 if(file!=null)
			 {
				 String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
		       String url= Tools.saveFile(file, fileFileName, fileContentType, realpath);
		      
		       model.setUrl(url);
			 }
			 pw.print(modelService.updateModel(model));
			 
		} catch (RuntimeException e) {
			// TODO: handle exception
			pw.print(false);
		}

		 
		return NONE;
	}
	public String zipModels() throws Exception
	{
	
		PrintWriter pw=Tools.getPw();
	
		 try {
				
			 
			 String ids=ServletActionContext.getRequest().getParameter("ids");
         	pw.print(modelService.zipModes(ids));
			
		} catch (RuntimeException e) {
			// TODO: handle exception
			pw.print("");
		}
			return NONE;
	}
	public String cancelZips() throws Exception 
	{
		PrintWriter pw=Tools.getPw();
		
		 try {
				
			 
			 String ids=ServletActionContext.getRequest().getParameter("ids");
        	pw.print(modelService.cancelZip(ids));
			
		} catch (Exception e) {
			// TODO: handle exception
			pw.print(false);
		}
			return NONE;
	}
	

	
	
}
