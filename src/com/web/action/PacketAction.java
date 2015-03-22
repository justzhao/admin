package com.web.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.opensymphony.xwork2.ActionSupport;
import com.web.entity.Model;
import com.web.entity.Packet;
import com.web.service.IModelService;
import com.web.service.IPacketService;
import com.web.util.JsonDateValueProcessor;
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
     private int rows;
     private int page;
     
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
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String savePacket() throws Exception
     {
		 String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
      packet.setThumbPic(Tools.saveFile(thumb, thumbFileName, thumbContentType, realpath)) ;
	   packet.setDescPic(Tools.saveFile(desc,descFileName,descContentType,realpath));

		 String modeids=ServletActionContext.getRequest().getParameter("ids");
		 if(modeids!=null)
		 {
		 String []id=modeids.split(",");
		 for(int i=0;i<id.length;i++)
		 {
			 packet.getModels().add(modelService.getModelById(Integer.parseInt(id[i])));
		 }
		 }

		 //上传用户先写死。
		 packet.setOwner("admin");

		 //xml s
		 packet.setXml("test.xml");
	
		 //添加模型
	   packetService.savePacket(packet);
	   // 然后需要更新模型标知已经打包
	   modelService.updatePacket(packet.getModels());
	   
	   
    	 return NONE;
     }
     
     public String getPagePacket(){
    	 
    	 
    	 int intPage = page == 0 ? 1:page; 
         //每页显示条数 
         int number = rows == 0 ? 10:rows;  
         int start = (intPage-1)*number;  
         Map<String, Object> jsonMap = new HashMap<String, Object>();
         //System.out.println("the is "+packet.toString());
         if(packet==null)
         {
        	 jsonMap.put("total", packetService.getCount());
        	 jsonMap.put("rows", packetService.getPageList(start, number));
         }else
         {
        	 
        	 
        	 jsonMap.put("total", packetService.getCountByCondition(packet));
        	 jsonMap.put("rows",packetService.getPageListByCondition(packet, start, number));
        	 /**
        	 if(packet.getName().equals("")&&packet.getOwner().equals("")&&packet.getCreateDate()==null&&packet.getEndDate()==null&&packet.getEndcount()==0&&packet.getCount()==0)
        	 {
            	 jsonMap.put("total", packetService.getCount());
            	 jsonMap.put("rows", packetService.getPageList(start, number));
        	 }else
        	 {
        	 jsonMap.put("total", packetService.getCountByCondition(packet));
        	 jsonMap.put("rows",packetService.getPageListByCondition(packet, start, number));
        	 }*/
         }
         JsonConfig jsonConfig = new JsonConfig();
    	  jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
    	  jsonConfig.setExcludes(new String[]{"models"});
    	  packets= JSONObject.fromObject(jsonMap,jsonConfig);
         
    	 return SUCCESS;
     }
     
     public String delPacket() throws Exception
     {
    	 
    	 PrintWriter pw=Tools.getPw();
    	 pw.print(packetService.delPacketById(packet.getId()));
    	 return NONE;
     }
     
     public String updatePacket() throws Exception
     {
    	 
    	 if(thumb!=null)
    	 {
    		 String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
    		 packet.setThumbPic(Tools.saveFile(thumb, thumbFileName, thumbContentType, realpath)) ;
    	 }
    	 packetService.updatePacket(packet);
    	 return NONE;
     }
	 
	 
	 
}
