package com.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.entity.User;
import com.web.service.IAuthorityService;
import com.web.service.Impl.UserServiceImpl;
import com.web.util.JsonDateValueProcessor;
import com.web.util.MD5Util;
import com.web.util.SecurityManager;
import com.web.util.Tools;

public class AuthorityAction extends ActionSupport {
	private User user;
	private IAuthorityService authorityService;

	private JSONObject results;//���ص�json 
    
	private String rows;//ÿҳ��ʾ�ļ�¼�� 
       
    private String page;//��ǰ�ڼ�ҳ  
    private JSONArray roles;
    
	
	public JSONArray getRoles() {
		return roles;
	}

	public void setRoles(JSONArray roles) {
		this.roles = roles;
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

	public IAuthorityService getAuthorityService() {
		return authorityService;
	}
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setAuthorityService(IAuthorityService authorityService) {
		this.authorityService = authorityService;
	}
	
	public String homeLogin()
	{
		return SUCCESS;
	}
	
	public String checkUser()
	{
		if(authorityService.checkUser(user))
		{
			
			  System.out.println("this is login success");

		
             //  System.out.println("the usernamei is "+Tools.getCurrentUser().getName());

		return SUCCESS;
		}else
		{
			return ERROR;
		}
	}
	
	public String getPageList(){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
		 jsonConfig.setExcludes(new String[]{"users"}); 
		//��ǰҳ 
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page); 
        //ÿҳ��ʾ���� 
        int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows); 
        //ÿҳ�Ŀ�ʼ��¼  ��һҳΪ1  �ڶ�ҳΪnumber +1  
        int start = (intPage-1)*number; 
           
 
        
        Map<String, Object> jsonMap = new HashMap<String, Object>();//����map 

        if(user==null){
        	jsonMap.put("total", authorityService.getCount());//total�� ����ܼ�¼��������� 
            jsonMap.put("rows", authorityService.getPageList(start, number));//rows�� ���ÿҳ��¼ list
        }else{
        	jsonMap.put("total",authorityService.getCountByCondition(user));
            jsonMap.put("rows", authorityService.getPageListByCondition(start, number,user));
        }
         

        results = JSONObject.fromObject(jsonMap,jsonConfig);//��ʽ��result   һ��Ҫ��JSONObject
		return SUCCESS;
	}
	
	public String getRoleList()
	{
		JsonConfig jsonConfig = new JsonConfig();
		

		
		jsonConfig.setExcludes(new String[]{"users"});

		
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
		
		roles=JSONArray.fromObject(authorityService.getRoleList(),jsonConfig);
		return SUCCESS;
	}
	public String saveUser() throws IOException
	{
		PrintWriter pw=Tools.getPw();
		
		try {
			user.setCreateDate(Tools.getDate());
			pw.print(authorityService.saveUser(user));
		} catch (RuntimeException e) {
			// TODO: handle exception
			pw.print(false);
		}
	
		
		return NONE;
	}
	public String deleteUser() throws IOException
	{
		PrintWriter pw=Tools.getPw();
		try {
			pw.print(authorityService.delUser(user));
		} catch (RuntimeException e) {
			// TODO: handle exception
			pw.print(false);
		}
		return NONE;
	}
	public String updateUser() throws IOException
	{	
		PrintWriter pw=Tools.getPw();
		
		try {
			pw.print(authorityService.updateUser(user));
		} catch (RuntimeException e) {
			// TODO: handle exception
			pw.print(false);
		}
	
		return NONE;
	}
	
	public String checkUserName() throws IOException
	{		
		PrintWriter pw=Tools.getPw();
		
		try {
		
	         pw.print(!authorityService.checkUserName(user.getName()));
		} catch (RuntimeException e) {
		
			pw.print(false);
		}

		
		return NONE;
	}
	
	public String loginOut()
	{
	//	SecurityManager.logout();
		
		 ActionContext.getContext().getSession().clear();
		return NONE;
	}
	
	public String changPass() throws IOException
	{
		PrintWriter pw=Tools.getPw();
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			 String pass=request.getParameter("pass");
			
			 user=Tools.getCurrentUser();
			 user.setPass(pass);
	
			  pw.print(authorityService.updateUser(user));
		} catch (RuntimeException e) {
			// TODO: handle exception
			pw.print(false);
		}

		return NONE;
	}

}
