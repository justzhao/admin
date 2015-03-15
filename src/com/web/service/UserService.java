package com.web.service;

import org.springframework.transaction.annotation.Transactional;

import com.web.entity.User;

public interface UserService {

	public  boolean userRegiseter(User u);

}
