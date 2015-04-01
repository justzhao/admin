package com.web.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.web.entity.Theme;
import com.web.service.IThemeService;
import com.web.util.JsonDateValueProcessor;
import com.web.util.Tools;

public class ThemeAction extends ActionSupport {
	
	private IThemeService themeService;
	private Theme theme;
	private JSONObject  themes;
    private int rows;
    private int page;
	private File thumb;
    private File thumbUp;
    private File thumbFooter;
    private File thumbWord;
    
    private String thumbContentType;
    private String thumbUpContentType;
    private String thumbFooterContentType;
    private String thumbWordContentType;
    
    private String thumbFileName;

    private String thumbUpFileName;
    private String thumbFooterFileName;
    private String thumbWordFileName;
   


	public JSONObject getThemes() {
		return themes;
	}

	public void setThemes(JSONObject themes) {
		this.themes = themes;
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

	public IThemeService getThemeService() {
		return themeService;
	}

	public void setThemeService(IThemeService themeService) {
		this.themeService = themeService;
	}
	
	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
  
	
	public File getThumb() {
		return thumb;
	}

	public void setThumb(File thumb) {
		this.thumb = thumb;
	}

	public File getThumbUp() {
		return thumbUp;
	}

	public void setThumbUp(File thumbUp) {
		this.thumbUp = thumbUp;
	}

	public File getThumbFooter() {
		return thumbFooter;
	}

	public void setThumbFooter(File thumbFooter) {
		this.thumbFooter = thumbFooter;
	}

	public File getThumbWord() {
		return thumbWord;
	}

	public void setThumbWord(File thumbWord) {
		this.thumbWord = thumbWord;
	}

	public String getThumbContentType() {
		return thumbContentType;
	}

	public void setThumbContentType(String thumbContentType) {
		this.thumbContentType = thumbContentType;
	}

	public String getThumbUpContentType() {
		return thumbUpContentType;
	}

	public void setThumbUpContentType(String thumbUpContentType) {
		this.thumbUpContentType = thumbUpContentType;
	}

	public String getThumbFooterContentType() {
		return thumbFooterContentType;
	}

	public void setThumbFooterContentType(String thumbFooterContentType) {
		this.thumbFooterContentType = thumbFooterContentType;
	}

	public String getThumbWordContentType() {
		return thumbWordContentType;
	}

	public void setThumbWordContentType(String thumbWordContentType) {
		this.thumbWordContentType = thumbWordContentType;
	}

	public String getThumbFileName() {
		return thumbFileName;
	}

	public void setThumbFileName(String thumbFileName) {
		this.thumbFileName = thumbFileName;
	}

	public String getThumbUpFileName() {
		return thumbUpFileName;
	}

	public void setThumbUpFileName(String thumbUpFileName) {
		this.thumbUpFileName = thumbUpFileName;
	}

	public String getThumbFooterFileName() {
		return thumbFooterFileName;
	}

	public void setThumbFooterFileName(String thumbFooterFileName) {
		this.thumbFooterFileName = thumbFooterFileName;
	}

	public String getThumbWordFileName() {
		return thumbWordFileName;
	}

	public void setThumbWordFileName(String thumbWordFileName) {
		this.thumbWordFileName = thumbWordFileName;
	}

	public String saveTheme() throws Exception
	{
		 PrintWriter pw=Tools.getPw();
		 
		 try {
			 String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
			 theme.setThumb(Tools.saveFile(thumb, thumbFileName, thumbContentType, realpath));
			 theme.setUp(Tools.saveFile(thumbUp, thumbUpFileName, thumbUpContentType, realpath));
			 theme.setFooter(Tools.saveFile(thumbFooter, thumbFooterFileName, thumbFooterContentType, realpath));
			 theme.setWord(Tools.saveFile(thumbWord,thumbWordFileName, thumbWordContentType, realpath));
			 theme.setOwner(Tools.getCurrentUser().getName());
			 
			pw.print(themeService.saveTheme(theme));
		} catch (Exception e) {
			// TODO: handle exception
			pw.print(false);
		}
		
		
		return NONE;
	}
	
	public  String getPageList()
	{
		
		
		int intPage = page == 0 ? 1:page; 
        //每页显示条数 
        int number = rows == 0 ? 10:rows;  
        int start = (intPage-1)*number;  
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        if(theme==null)
        {
        	

       jsonMap.put("total", themeService.getCount());
       jsonMap.put("rows", themeService.getPageList(start, number));
        }else{
        	
        	jsonMap.put("total",themeService.getCountByCondition(theme));
            jsonMap.put("rows", themeService.getPageListByCondition(theme, start, number));
        }
 	  JsonConfig jsonConfig = new JsonConfig();
 	  jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
 	   jsonConfig.setExcludes(new String[]{"theme","models"});
      themes= JSONObject.fromObject(jsonMap,jsonConfig);
		return SUCCESS;
	
	}
public String getThemeList()
{
	   Map<String, Object> jsonMap = new HashMap<String, Object>();
	   jsonMap.put("themes", themeService.getThemeList());
	   

	   JsonConfig jsonConfig = new JsonConfig();
	  jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
	  jsonConfig.setExcludes(new String[]{"packet"});

      themes= JSONObject.fromObject(jsonMap,jsonConfig);
	return SUCCESS;
}
	
	
	public String delTheme() throws IOException
	{
		PrintWriter pw=Tools.getPw();
		
		
		try {
			

			
			
			pw.print(themeService.delTheme(theme.getId()));
			
		} catch (Exception e) {
			// TODO: handle exception
			pw.print(false);
		}
		return NONE;
	}
	
	public String updateTheme() throws IOException
	{
		PrintWriter pw=Tools.getPw();
		
		
		try {
			
			
			 String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
		if(thumb!=null)
		{
			 theme.setThumb(Tools.saveFile(thumb, thumbFileName, thumbContentType, realpath));
		}
		if(thumbUp!=null)
		{
			 theme.setUp(Tools.saveFile(thumbUp, thumbUpFileName, thumbUpContentType, realpath));
		}
		if(thumbFooter!=null)
		{
			 theme.setFooter(Tools.saveFile(thumbFooter, thumbFooterFileName, thumbFooterContentType, realpath));
		}
		if(thumbWord!=null)
		{
		theme.setWord(Tools.saveFile(thumbWord,thumbWordFileName, thumbWordContentType, realpath));
		}	
			
		pw.print(themeService.updateTheme(theme));

		} catch (Exception e) {
			// TODO: handle exception
			pw.print(false);
		}
		return NONE;
	}
	
	

}
