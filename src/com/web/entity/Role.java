package com.web.entity;

import java.util.List;

public class Role {
  
	private int id;
	private String name;
	private List<User> users;
	
	
	public Role()
	{
		
	}
	public Role(int id, String name, List<User> users) {
		super();
		this.id = id;		
		this.name = name;
		this.users = users;
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
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
