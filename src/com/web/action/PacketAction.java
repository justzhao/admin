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
import com.web.util.Qiniu;
import com.web.util.Tools;



public class PacketAction extends ActionSupport {

	 private IPacketService packetService;
	 private IModelService modelService;
     private Packet packet;
     private JSONObject packets;
     
     private File thumb;
     private File desc;
     private File thumbUp;
     private File thumbFooter;
     private File thumbWord;
     private File character;
     private File background;
     
     private String thumbFileName;
     private String descFileName;
     private String thumbUpFileName;
     private String thumbFooterFileName;
     private String thumbWordFileName;
     private String characterFileName;
     private String backgroundFileName;
     
     
     private String thumbContentType;
     private String descContentType;
     private String thumbUpContentType;
     private String thumbFooterContentType;
     private String thumbWordContentType;
     private String characterContentType;
     private String backgroundContentType;
     
     
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
	

	public File getThumbUp() {
		return thumbUp;
	}
	public void setThumbUp(File thumbUp) {
		this.thumbUp = thumbUp;
	}
	public File getThumbFooter() {
		return thumbFooter;
	}
	public void setThumbFooter(File thumbFooter) {
		this.thumbFooter = thumbFooter;
	}
	public File getThumbWord() {
		return thumbWord;
	}
	public void setThumbWord(File thumbWord) {
		this.thumbWord = thumbWord;
	}
	public File getCharacter() {
		return character;
	}
	public void setCharacter(File character) {
		this.character = character;
	}
	public File getBackground() {
		return background;
	}
	public void setBackground(File background) {
		this.background = background;
	}
	public String getThumbUpFileName() {
		return thumbUpFileName;
	}
	public void setThumbUpFileName(String thumbUpFileName) {
		this.thumbUpFileName = thumbUpFileName;
	}
	public String getThumbFooterFileName() {
		return thumbFooterFileName;
	}
	public void setThumbFooterFileName(String thumbFooterFileName) {
		this.thumbFooterFileName = thumbFooterFileName;
	}
	public String getThumbWordFileName() {
		return thumbWordFileName;
	}
	public void setThumbWordFileName(String thumbWordFileName) {
		this.thumbWordFileName = thumbWordFileName;
	}
	public String getCharacterFileName() {
		return characterFileName;
	}
	public void setCharacterFileName(String characterFileName) {
		this.characterFileName = characterFileName;
	}
	public String getBackgroundFileName() {
		return backgroundFileName;
	}
	public void setBackgroundFileName(String backgroundFileName) {
		this.backgroundFileName = backgroundFileName;
	}
	public String getThumbUpContentType() {
		return thumbUpContentType;
	}
	public void setThumbUpContentType(String thumbUpContentType) {
		this.thumbUpContentType = thumbUpContentType;
	}
	public String getThumbFooterContentType() {
		return thumbFooterContentType;
	}
	public void setThumbFooterContentType(String thumbFooterContentType) {
		this.thumbFooterContentType = thumbFooterContentType;
	}
	public String getThumbWordContentType() {
		return thumbWordContentType;
	}
	public void setThumbWordContentType(String thumbWordContentType) {
		this.thumbWordContentType = thumbWordContentType;
	}
	public String getCharacterContentType() {
		return characterContentType;
	}
	public void setCharacterContentType(String characterContentType) {
		this.characterContentType = characterContentType;
	}
	public String getBackgroundContentType() {
		return backgroundContentType;
	}
	public void setBackgroundContentType(String backgroundContentType) {
		this.backgroundContentType = backgroundContentType;
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
		 //上传用户先写死。
		 packet.setOwner(Tools.getCurrentUser().getName());

		 //设置上传用户的时间
		 packet.setCreateDate(Tools.getDate());
		 //xml s
		packet.setXml("armodels.xml");
		 PrintWriter pw=Tools.getPw();
		try {
			
			 String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
		
			 /**
			 //保存缩略图主题
				 packet.setThumbPic(Tools.saveFile(thumb, thumbFileName, thumbContentType, realpath)) ;
				 //保存缩略图上
				 packet.setThumbUp(Tools.saveFile(thumbUp, thumbUpFileName, thumbUpContentType, realpath));
				 //保存缩略图下
				 packet.setThumbFooter(Tools.saveFile(thumbFooter, thumbFooterFileName, thumbFooterContentType, realpath));
				 //保存缩略图文字
				 packet.setThumbWord(Tools.saveFile(thumbWord,thumbWordFileName, thumbWordContentType, realpath));
				 //保存人物
				  * 
				  * */

				  //保存人物角色
				 packet.setCharacter(Tools.saveFile(character, characterFileName, characterContentType, realpath));
				 //保存背景
				 packet.setBackground(Tools.saveFile(background,backgroundFileName, backgroundContentType, realpath));
			   //保存说明图
				 packet.setDescPic(Tools.saveFile(desc,descFileName,descContentType,realpath));

				 //保存所有的模型
				 String modeids=ServletActionContext.getRequest().getParameter("ids");
				 if(modeids!=null)
				 {
				 String []id=modeids.split(",");
				 for(int i=0;i<id.length;i++)
				 {
					 packet.getModels().add(modelService.getModelById(Integer.parseInt(id[i])));
				 }
				 }


			
				 //添加模型
			pw.print(packetService.savePacket(packet));
		} catch (RuntimeException e) {
			// TODO: handle exception
			e.printStackTrace();
			pw.print(false);
		}
		
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

         }
         JsonConfig jsonConfig = new JsonConfig();
    	  jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
   
    	  jsonConfig.setExcludes(new String[]{"models","packet"});
    	  packets= JSONObject.fromObject(jsonMap,jsonConfig);
         
    	 return SUCCESS;
     }
     
     public String delPacket() throws Exception
     {
    	 
    	 PrintWriter pw=Tools.getPw();
    	 
    	 try {
    		 pw.print(packetService.delPacketById(packet.getId()));
		} catch (RuntimeException e) {
			// TODO: handle exception
			pw.print(false);
		}
    
    	 return NONE;
     }
     
     public String updatePacket() throws Exception
     { 
    	 
    	 PrintWriter pw=Tools.getPw();
    	 
    	 try {
    		 String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
        
    		 
    		 /**
    		 if(thumb!=null)
        	 {
        		
        		 packet.setThumbPic(Tools.saveFile(thumb, thumbFileName, thumbContentType, realpath)) ;
        	 }
        	 
        	 if(thumbUp!=null)
        	 {
        		 packet.setThumbUp(Tools.saveFile(thumbUp, thumbUpFileName, thumbUpContentType, realpath));
        	 }
        	 if(thumbFooter!=null)
        	 {
        		 packet.setThumbFooter(Tools.saveFile(thumbFooter, thumbFooterFileName, thumbFooterContentType, realpath));
        	 }
        	 if(thumbWord!=null)
        	 {
        		 packet.setThumbWord(Tools.saveFile(thumbWord, thumbWordFileName, thumbWordContentType, realpath));
        	 }
        	 */
        	 
        	 
        	 
        	 
        	 pw.print(packetService.updatePacket(packet));
		} catch (RuntimeException e) {
			// TODO: handle exception
			e.printStackTrace();
			pw.print(false);
		}
    	 

    	 return NONE;
     }
	 
	 
	 
}
