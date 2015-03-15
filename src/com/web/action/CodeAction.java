package com.web.action;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionSupport;
import com.web.service.CodeService;

public class CodeAction extends ActionSupport {
	
	private CodeService codeService;
	
	private JSONArray codes;
	
	
	public CodeService getCodeService() {
		return codeService;
	}


	public void setCodeService(CodeService codeService) {
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
		codes=JSONArray.fromObject(codeService.getCodeList());
		return SUCCESS;
	}

}
