package com.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.web.entity.Packet;
import com.web.service.PacketService;



public class PacketAction extends ActionSupport {

	 private PacketService packetService;
     private Packet packet;
	public PacketService getPacketService() {
		return packetService;
	}
	public void setPacketService(PacketService packetService) {
		this.packetService = packetService;
	}
	public Packet getPacket() {
		return packet;
	}
	public void setPacket(Packet packet) {
		this.packet = packet;
	}
     
	 
	 
	 
}
