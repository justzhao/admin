package com.web.service.Impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.web.dao.IDao;
import com.web.entity.Model;
import com.web.entity.Packet;
import com.web.service.IPacketService;
import com.web.util.Qiniu;
import com.web.util.Tools;

public class PacketServiceImpl implements IPacketService {
	private IDao packetDao;

	public IDao getPacketDao() {
		return packetDao;
	}

	public void setPacketDao(IDao packetDao) {
		this.packetDao = packetDao;
	}

	@Override
	public void savePacket(Packet p) throws Exception {
		// TODO Auto-generated method stub
		String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
		//数据打包，  首先需要打包的文件有哪些， 识别码，还有数据包。 先从七牛云上下载好对应的包下来。
		//生成xml
		List<String> paths=new ArrayList<String>();
		
		//上传缩略图和说明图到七牛云
		Qiniu.uploadFile(p.getThumbPic(), realpath+"//"+p.getThumbPic());
		Qiniu.uploadFile(p.getDescPic(), realpath+"//"+p.getThumbPic());
		
		
		Iterator t1=p.getModels().iterator() ;
	
        while(t1.hasNext())
        {
        	Model m=(Model) t1.next();
        	Qiniu.downloadFile(m.getUrl());

        	paths.add(m.getUrl());
        }
		
        p.setUrl( Tools.ZipFiles(paths));
		packetDao.save(p);
		
	}

	

	

}
