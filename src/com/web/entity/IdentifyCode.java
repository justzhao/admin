package com.web.entity;

public class IdentifyCode {
	private int id;
	private String name;
	private String url;
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
	@Override
	public String toString() {
		return "IdentifyCode [id=" + id + ", name=" + name + ", url=" + url
				+ "]";
	}
	

}
