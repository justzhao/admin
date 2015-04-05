package com.web.entity;

import java.util.HashSet;
import java.util.Set;

public class Menu  implements Comparable {
	private int menuid;
	private int pid;
	private String id;
	private String text;
	
	private String iconCls;

	private Set<Menu> children=new HashSet<Menu>();

	public int getMenuid() {
		return menuid;
	}

	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set<Menu> getChildren() {
		return children;
	}

	public void setChildren(Set<Menu> children) {
		this.children = children;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		
        Menu m = (Menu) o;
        int result = menuid > m.getMenuid() ? 1 : (menuid ==m.getMenuid()  ? 0 : -1);

        return result;
		

	}
    

	
	
	
}
