package com.banshou.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banshou.app.dao.UserDao;
import com.banshou.app.domain.User;
import com.banshou.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	@Transactional
	public boolean isExist(String openId) {
		return false;
	}

	@Override
	@Transactional
	public User getUserInfo(String openId) {
		return userDao.getUserInfo(openId);
	}

	@Override
	public User login(String mobile, String pass) {
		return userDao.login(mobile, pass);
	}

}
