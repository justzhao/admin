package com.web.entity;

import java.util.Date;



public class Model {
	
	private int id;
	private  String name;
	private Boolean animation;
	private String info;
	private int size;
	private String rotate;
	private String offset;
	private String url;
	private Date createDate;
	private IdentifyCode code;
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


	public Boolean getAnimation() {
		return animation;
	}
	public void setAnimation(Boolean animation) {
		this.animation = animation;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getRotate() {
		return rotate;
	}
	public void setRotate(String rotate) {
		this.rotate = rotate;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
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
	public IdentifyCode getCode() {
		return code;
	}
	public void setCode(IdentifyCode code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "Model [id=" + id + ", name=" + name + ", animation="
				+ animation + ", info=" + info + ", size=" + size + ", rotate="
				+ rotate + ", offset=" + offset + ", url=" + url
				+ ", createDate=" + createDate + ", code=" + code + "]";
	}
	
	


}
