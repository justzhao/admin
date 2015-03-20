package com.web.entity;

import java.util.Date;

public class ScrollPic {
	private int id;
	private String name;
	private String info;
	private String urls;
	private Boolean effective;
	private Boolean testPage;
	private Date createDate;
	private Date endDate; 
	
	private String owner;
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getUrls() {
		return urls;
	}
	public void setUrls(String urls) {
		this.urls = urls;
	}


	public Boolean getEffective() {
		return effective;
	}
	public void setEffective(Boolean effective) {
		this.effective = effective;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	public Boolean getTestPage() {
		return testPage;
	}
	public void setTestPage(Boolean testPage) {
		this.testPage = testPage;
	}
	
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	@Override
	public String toString() {
		return "ScrollPic [id=" + id + ", name=" + name + ", info=" + info
				+ ", urls=" + urls + ", effective=" + effective + "]";
	}
	
	
	
	

}
