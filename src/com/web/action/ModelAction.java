package com.web.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionSupport;
import com.web.entity.Model;
import com.web.service.ModelService;
import com.web.util.Tools;

public class ModelAction extends ActionSupport {

	private ModelService modelService;
    private Model model;
	private JSONArray  models;
	private File file;
	private String fileFileName; //文件名称
    private String fileContentType;
	
	
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

	public JSONArray getModels() {
		return models;
	}

	public void setModels(JSONArray models) {
		this.models = models;
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
		   HttpServletResponse response = ServletActionContext.getResponse();
			
		 String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
	    

	        
	       String url= Tools.saveFile(file, fileFileName, fileContentType, realpath);
	      
	       model.setUrl(url);
	       modelService.saveModel(model);
	      
	       response.setContentType("text/html;charset=utf-8"); 
	        PrintWriter pw = response.getWriter();  
	        pw.print("操作成功"); 

		return NONE;
	}
	public String getModelList()
	{
		
		return SUCCESS;
	}
	
	
	
}
