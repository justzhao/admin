package com.web.entity;

import java.util.Date;
import java.util.Set;



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
	private Date endDate;
	private IdentifyCode code;
	private Set<Packet> packets;
	private String owner;
	private boolean packaged;
	private int searchFlag;
	
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
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public IdentifyCode getCode() {
		return code;
	}
	public void setCode(IdentifyCode code) {
		this.code = code;
	}
	
	public Set<Packet> getPackets() {
		return packets;
	}
	public void setPackets(Set<Packet> packets) {
		this.packets = packets;
	}
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	

	public boolean isPackaged() {
		return packaged;
	}
	public void setPackaged(boolean packaged) {
		this.packaged = packaged;
	}
	
	
	public int getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(int searchFlag) {
		this.searchFlag = searchFlag;
	}
	@Override
	public String toString() {
		return "Model [id=" + id + ", name=" + name + ", animation="
				+ animation + ", info=" + info + ", size=" + size + ", rotate="
				+ rotate + ", offset=" + offset + ", url=" + url
				+ ", createDate=" + createDate + ", code=" + code + "]";
	}
	
	


}
