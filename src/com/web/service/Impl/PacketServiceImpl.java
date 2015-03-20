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
		//���ݴ����  ������Ҫ������ļ�����Щ�� ʶ���룬�������ݰ��� �ȴ���ţ�������غö�Ӧ�İ�������
		//����xml
		List<String> paths=new ArrayList<String>();
		
		//�ϴ�����ͼ��˵��ͼ����ţ��
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
