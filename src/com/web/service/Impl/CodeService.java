package com.web.service.Impl;

import java.util.List;

import com.web.dao.IDao;

public class CodeService implements com.web.service.CodeService {

	private  IDao codeDao;
	
	public IDao getCodeDao() {
		return codeDao;
	}

	public void setCodeDao(IDao codeDao) {
		this.codeDao = codeDao;
	}

	@Override
	public List getCodeList() {
		// TODO Auto-generated method stub
		return  codeDao.getListByHQL(" from IdentifyCode");
	}

}
