package com.web.service;

import org.springframework.transaction.annotation.Transactional;

import com.web.entity.User;

public interface IUserService {

	public  boolean userRegiseter(User u);

}
