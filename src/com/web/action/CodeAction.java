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

public class CodeAction extends ActionSupport {
	
	private ICodeService codeService;
	
	private JSONArray codes;
	
	private File file;   //
	 
	private String fileFileName;  //
	
	private String fileContentType;  //
	
	private IdentifyCode code;

	private JSONObject results;//返回的json 
    
	private String rows;//每页显示的记录数 
       
    private String page;//当前第几页  
    

   

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
		
		//当前页 
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page); 
        //每页显示条数 
        int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows); 
        //每页的开始记录  第一页为1  第二页为number +1  
        int start = (intPage-1)*number; 
           
       // List<IdentifyCode> list = codeService.getPageHql(start,number);//每页的数据，放入list 
        
        Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map 
        //判断首次加载 还是查询
        if(code==null){
        	jsonMap.put("total", codeService.getCount());//total键 存放总记录数，必须的 
            jsonMap.put("rows", codeService.getPageHql(start,number));//rows键 存放每页记录 list
        }else{
        	jsonMap.put("total",codeService.getCountByCondition(code));
            jsonMap.put("rows", codeService.getPageListByCondition(code, start, number));
        }
         

        results = JSONObject.fromObject(jsonMap,jsonConfig);//格式化result   一定要是JSONObject
		return SUCCESS;
	}
	
	public String checkName() throws Exception{
		 HttpServletRequest request=ServletActionContext.getRequest();
		 HttpServletResponse respn=ServletActionContext.getResponse();
		 PrintWriter pw=respn.getWriter();
		 String codeName=request.getParameter("codeName");
	//	 System.out.println("name "+codeName);
		 Long countNum = codeService.isRptByName(codeName); //查找数据库有无此记录
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
	    PrintWriter pw = Tools.getPw();
		code.setCreateDate(new Date());
		code.setOwner(Tools.getCurrentUser().getName());
	
		try {
			   pw.print(codeService.saveCodeTo(code)); 
		} catch (RuntimeException e) {
			// TODO: handle exception
			pw.print(false);
		}

    
     
		return NONE;
	}
	
	public String updateCode() throws Exception {
	
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter pw = response.getWriter();  
		response.setContentType("text/html;charset=utf-8"); 
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String url = request.getParameter("url");
			String owner = request.getParameter("owner");
			
			IdentifyCode code = new IdentifyCode(id,name,url);
			code.setOwner(owner);
			IdentifyCode oldCode = codeService.getCodeByID(id);
			//新增判断两个URL是否相同 
			String oldUrl = oldCode.getUrl();
			if(!oldUrl.equals(url)){

				
				codeService.deltFrom(oldUrl);
				
				codeService.saveCodeTo(code);
			}else {
				codeService.updateCode(code);
			}
	        pw.print(true);
			
		} catch (RuntimeException e) {
			// TODO: handle exception
			pw.print(false);
		}
		

		
		return NONE;
	}
	
	public String deleteCode() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8"); 
		
		PrintWriter pw=response.getWriter();	
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));

			IdentifyCode code = codeService.getCodeByID(id);
	     	codeService.deltFrom(code.getUrl());
	       pw.print(codeService.deleteCode(code));  
		} catch (RuntimeException e) {
			// TODO: handle exception
			pw.print(false);
		}

			
		return NONE;		
	}
	
	public String uploadImg() throws IOException
	{
		

		HttpServletResponse response = ServletActionContext.getResponse();
		
		String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
		
	    response.setContentType("text/html;charset=utf-8"); 
	    PrintWriter pw = response.getWriter();  
	    
	    try {
			
	    	String url = Tools.saveFile(file, fileFileName, fileContentType, realpath); //realPath
           pw.print(url);  //没有上传文件，返回
		} catch (RuntimeException e) {
			// TODO: handle exception
			pw.print(false);
		}
		

	
	   	return NONE;
	}
	
	
	

}
