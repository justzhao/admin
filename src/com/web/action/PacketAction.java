package com.web.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.web.entity.Model;
import com.web.entity.Packet;
import com.web.service.IModelService;
import com.web.service.IPacketService;
import com.web.util.Tools;



public class PacketAction extends ActionSupport {

	 private IPacketService packetService;
	 private IModelService modelService;
     private Packet packet;
     private JSONObject packets;
     private File thumb;
     private File desc;
     private String thumbFileName;
     private String thumbContentType;
     private String descFileName;
     private String descContentType;
     private Set<Model> models=new HashSet<Model>();

	public IPacketService getPacketService() {
		return packetService;
	}
	public void setPacketService(IPacketService iPacketService) {
		this.packetService = iPacketService;
	}
	
	public IModelService getModelService() {
		return modelService;
	}
	public void setModelService(IModelService modelService) {
		this.modelService = modelService;
	}
	public Packet getPacket() {
		return packet;
	}
	public void setPacket(Packet packet) {
		this.packet = packet;
	}
	
     public JSONObject getPackets() {
		return packets;
	}
	public void setPackets(JSONObject packets) {
		this.packets = packets;
	}
	public File getThumb() {
		return thumb;
	}
	public void setThumb(File thumb) {
		this.thumb = thumb;
	}
	public File getDesc() {
		return desc;
	}
	public void setDesc(File desc) {
		this.desc = desc;
	}
	
	
	public String getThumbFileName() {
		return thumbFileName;
	}
	public void setThumbFileName(String thumbFileName) {
		this.thumbFileName = thumbFileName;
	}
	public String getThumbContentType() {
		return thumbContentType;
	}
	public void setThumbContentType(String thumbContentType) {
		this.thumbContentType = thumbContentType;
	}
	public String getDescFileName() {
		return descFileName;
	}
	public void setDescFileName(String descFileName) {
		this.descFileName = descFileName;
	}
	public String getDescContentType() {
		return descContentType;
	}
	public void setDescContentType(String descContentType) {
		this.descContentType = descContentType;
	}
	

	public Set<Model> getModels() {
		return models;
	}
	public void setModels(Set<Model> models) {
		this.models = models;
	}
	public String savePacket() throws Exception
     {
		 String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
      packet.setThumbPic(Tools.saveFile(thumb, thumbFileName, thumbContentType, realpath)) ;
	   packet.setDescPic(Tools.saveFile(desc,descFileName,descContentType,realpath));

		 String modelid=ServletActionContext.getRequest().getParameter("modelsid");
		 if(modelid!=null)
		 {
			 String []modelsid=modelid.split(",");
		    for(int i=0;i<modelsid.length;i++)
		    {
		    packet.getModels().add( modelService.getModelById( Integer.parseInt( modelsid[i])));
		    	
		    }
		 
		 }

		 packet.setXml("fsafasdfasdf.zip");
	
	   packetService.savePacket(packet);
    	 return NONE;
     }
     
     public String getPagePacket(){
    	 
    	 return SUCCESS;
     }
	 
	 
	 
}
