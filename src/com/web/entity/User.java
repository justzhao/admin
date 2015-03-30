package com.web.entity;

import java.util.Date;

public class User {
  
	private int id;
	private String name;
	private String pass;
	private Role role;
	private Date createDate;
	
	public User()
	{
		
	}
	public User(int id, String name,String pass, Role role) {
		super();
		this.id = id;
		this.pass=pass;
		this.name = name;
		this.role = role;
	}
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
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
