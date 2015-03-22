package com.web.action;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.opensymphony.xwork2.ActionSupport;
import com.web.entity.IdentifyCode;
import com.web.service.ICodeService;
import com.web.util.JsonDateValueProcessor;
import com.web.util.Tools;

public class CodeActionbak extends ActionSupport {
	
	private ICodeService codeService;
	
	private JSONArray codes;
	
	private File file;   //
	 
	private String fileFileName;  //
	
	private String fileContentType;  //
	
	private IdentifyCode code;

	private JSONObject results;//���ص�json 
    
	private String rows;//ÿҳ��ʾ�ļ�¼�� 
       
    private String page;//��ǰ�ڼ�ҳ  
    

   

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


	public IdentifyCode getCode() {
		return code;
	}


	public void setCode(IdentifyCode code) {
		this.code = code;
	}


	public JSONObject getResults() {
		return results;
	}


	public void setResults(JSONObject results) {
		this.results = results;
	}


	public String getRows() {
		return rows;
	}


	public void setRows(String rows) {
		this.rows = rows;
	}


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}


	public ICodeService getCodeService() {
		return codeService;
	}


	public void setCodeService(ICodeService codeService) {
		this.codeService = codeService;
	}


	public JSONArray getCodes() {
		return codes;
	}


	public void setCodes(JSONArray codes) {
		this.codes = codes;
	}


	public String getCodeList()
	{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
		codes=JSONArray.fromObject(codeService.getCodeList(),jsonConfig);
		return SUCCESS;
	}
	/**
	public String getCodeList()
	{
		JsonConfig jsonConfig = new JsonConfig(); 
		

		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
 
		

		return SUCCESS;
	}*/
	
	
	public String showContent(){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
		//��ǰҳ 
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page); 
        //ÿҳ��ʾ���� 
        int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows); 
        //ÿҳ�Ŀ�ʼ��¼  ��һҳΪ1  �ڶ�ҳΪnumber +1  
        int start = (intPage-1)*number; 
           
        List<IdentifyCode> list = codeService.getPageHql(start,number);//ÿҳ�����ݣ�����list 
        
        Map<String, Object> jsonMap = new HashMap<String, Object>();//����map 
        jsonMap.put("total", codeService.getCount());//total�� ����ܼ�¼��������� 
        jsonMap.put("rows", list);//rows�� ���ÿҳ��¼ list 
        results = JSONObject.fromObject(jsonMap,jsonConfig);//��ʽ��result   һ��Ҫ��JSONObject
		return SUCCESS;
	}
	
	public String checkName() throws Exception{
		 HttpServletRequest request=ServletActionContext.getRequest();
		 HttpServletResponse respn=ServletActionContext.getResponse();
		 PrintWriter pw=respn.getWriter();
		 String codeName=request.getParameter("codeName");
		 System.out.println("name "+codeName);
		 Long countNum = codeService.isRptByName(codeName); //�������ݿ����޴˼�¼
		 if(countNum==0){
			pw.print(true);
		 }else
		 {
			pw.print(false);
		 }
		 
		 return NONE;
		 
						
	}
	public String saveCode() throws Exception
	{
		//System.out.println("upload file is:"+code.getName()+code.getUrl());
		code.setCreateDate(new Date());
		codeService.saveCodeTo(code); //��ţ ���ݿ�
		
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setContentType("text/html;charset=utf-8");   
        PrintWriter pw = response.getWriter();  
        pw.print("����ɹ�"); 
		return NONE;
	}
	
	public String updateCode() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		response.setContentType("text/html;charset=utf-8"); 
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		
		IdentifyCode code = new IdentifyCode(id,name,url);
		
		System.out.println("update code id "+code.getId());
		System.out.println("update name id "+code.getName());
		System.out.println("update url id "+code.getUrl());
		System.out.println("update createDate id "+code.getCreateDate());
		
		/*update code id 2
		update name id 222222
		update url id 1366_768_5248_2015031911325899739.jpg
		update createDate id Fri Mar 20 09:51:32 CST 2015*/
		
		IdentifyCode oldCode = codeService.getCodeByID(id);
		String oldUrl = oldCode.getUrl();
		
		codeService.deltFrom(oldUrl);
		
		
		//code.setCreateDate(new Date());
		
		//System.out.println("update code id "+code.getId());
		
		codeService.saveCodeTo(code); //��ţ ���ݿ�
		//�ȴ�����
		//codeService.updateCode(code);
		
		PrintWriter pw = response.getWriter();  
        pw.print("���³ɹ�");
		
		return NONE;
	}
	
	public String deleteCode() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8"); 
		
		PrintWriter pw=response.getWriter();		
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("code id "+id);
		IdentifyCode code = codeService.getCodeByID(id);
		
		String key = code.getUrl();
		
		codeService.deltFrom(key);
		    		    
    	boolean suc = codeService.deleteCode(code);  //�������ִ��?
    	
    	if(suc){
    		pw.print("ɾ���ɹ�");
    		
    	} else{
	    	System.out.println("ɾ���ɹ�");
			pw.print("ɾ��ʧ��");
	    }
			
		return NONE;		
	}
	
	public String uploadImg() throws IOException
	{
		

		HttpServletResponse response = ServletActionContext.getResponse();
		
		String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
		
		System.out.println("realPath :"+realpath);//D:MyEclipse work webapps admin upload
		System.out.println("file fileNam is "+fileFileName);   //2014��9��.jpg 
	    //String url=  codeService.saveCode(file, fileFileName, fileContentType, realpath);
		
		String url = Tools.saveFile(file, fileFileName, fileContentType, realpath); //realPath
	    System.out.println("file name url is "+url); //2014��9��_2015031716150559040.jpg
	    
	    response.setContentType("text/html;charset=utf-8"); 
	    PrintWriter pw = response.getWriter();  
	    
	    pw.print(url);  //û���ϴ��ļ�������
	
	   	return NONE;
	}
	
	
	

}
