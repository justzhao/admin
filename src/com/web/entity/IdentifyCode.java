package com.web.entity;

import java.util.Date;



public class IdentifyCode {
	private int id;
	private String name;
	private String url;
	private Date createDate;

	
	public IdentifyCode() {
		
	}
	
	public IdentifyCode(int id, String name, String url) {
		this.id = id;
		this.name = name;
		this.url= url;
		this.createDate = new Date();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	


	@Override
	public String toString() {
		return "IdentifyCode [id=" + id + ", name=" + name + ", url=" + url
				+ "]";
	}
	

}
