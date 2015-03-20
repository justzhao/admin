package com.web.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Packet {
	
	private int id;
	private String name;
	private String url;
	private String info;
	private boolean  effective;
	private String xml;
	private  Date createDate;
	private String thumbPic;
	private String version;
	private int count;
	private String descPic;
	private int device;
	private Set<Model> models=new HashSet<Model>();
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public boolean isEffective() {
		return effective;
	}
	public void setEffective(boolean effective) {
		this.effective = effective;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getThumbPic() {
		return thumbPic;
	}
	public void setThumbPic(String thumbPic) {
		this.thumbPic = thumbPic;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getDescPic() {
		return descPic;
	}
	public void setDescPic(String descPic) {
		this.descPic = descPic;
	}
	public int getDevice() {
		return device;
	}
	public void setDevice(int device) {
		this.device = device;
	}
	public Set<Model> getModels() {
		return models;
	}
	public void setModels(Set<Model> models) {
		this.models = models;
	}
	

	
	

}
