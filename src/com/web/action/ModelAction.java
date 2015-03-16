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
import com.web.service.ModelService;

import com.web.util.JsonDateValueProcessor;
import com.web.util.Tools;

public class ModelAction extends ActionSupport {

	private ModelService modelService;
    private Model model;
	private JSONObject  models;
	private File file;
	private String fileFileName; //文件名称
    private String fileContentType;
    private int rows;
    private int page;
	
	public ModelService getModelService() {
		return modelService;
	}

	public void setModelService(ModelService modelService) {
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
	    

	        
	       String url= Tools.saveFile(file, fileFileName, fileContentType, realpath);
	      
	       model.setUrl(url);
	       model.setCreateDate(Tools.getDate());
	       modelService.saveModel(model);
	      
	
	        PrintWriter pw =Tools.getPw();;
	        pw.print("操作成功"); 

		return NONE;
	}
	public String getModelList()
	{
		
	


	  JsonConfig jsonConfig = new JsonConfig();
	  jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());

	 models= JSONObject.fromObject( modelService.getModelList(),jsonConfig);
		
	//  models=JSONObject.
	  return SUCCESS;
	}
	public String getPageList()
	{
		
		int intPage = page == 0 ? 1:page; 
        //每页显示条数 
        int number = rows == 0 ? 10:rows;  
        int start = (intPage-1)*number;  
        Map<String, Object> jsonMap = new HashMap<String, Object>();
       jsonMap.put("total", modelService.getCount());
       jsonMap.put("rows", modelService.getPageList(start, number));

 	  JsonConfig jsonConfig = new JsonConfig();
 	  jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
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
		
		pw.print(modelService.delModel(model));
		
		
		return NONE;
	}
	
	
	

	
	
}
