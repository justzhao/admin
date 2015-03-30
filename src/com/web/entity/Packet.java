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
	private String thumbUp;
	private String thumbFooter;
	private String thumbWord;
	private String character;
	private String background;
	
	
	
	private String version;
	private int count;
	private String descPic;
	
	
	private int device;
	
	private Date endDate;
	private int  endcount;
	
	private int searchFlag;
	
	private String owner;
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
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getEndcount() {
		return endcount;
	        
	}
	public void setEndcount(int endcount) {
		this.endcount = endcount;
	}
	
	public int getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(int searchFlag) {
		this.searchFlag = searchFlag;
	}
	
	public String getThumbUp() {
		return thumbUp;
	}
	public void setThumbUp(String thumbUp) {
		this.thumbUp = thumbUp;
	}
	public String getThumbFooter() {
		return thumbFooter;
	}
	public void setThumbFooter(String thumbFooter) {
		this.thumbFooter = thumbFooter;
	}
	public String getThumbWord() {
		return thumbWord;
	}
	public void setThumbWord(String thumbWord) {
		this.thumbWord = thumbWord;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	@Override
	public String toString() {
		return "Packet [id=" + id + ", name=" + name + ", url=" + url
				+ ", info=" + info + ", effective=" + effective + ", xml="
				+ xml + ", createDate=" + createDate + ", thumbPic=" + thumbPic
				+ ", version=" + version + ", count=" + count + ", descPic="
				+ descPic + ", device=" + device + ", endDate=" + endDate
				+ ", endcount=" + endcount + ", owner=" + owner + "]";
	}
	
	

	
	

}
