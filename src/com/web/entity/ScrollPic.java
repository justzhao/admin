package com.web.entity;

public class ScrollPic {
	private int id;
	private String name;
	private String info;
	private String urls;
	private Boolean effective;
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
	@Override
	public String toString() {
		return "ScrollPic [id=" + id + ", name=" + name + ", info=" + info
				+ ", urls=" + urls + ", effective=" + effective + "]";
	}
	
	
	
	

}
