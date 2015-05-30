package com.banshou.app.dao;

import com.banshou.app.domain.User;

public interface UserDao {
	public void addUser(User user);
	public boolean isExist(String openId);
	public User getUserInfo(String openId);
	public User login(String mobile, String pass);
	public void updateUser(User user);
	public void resetPassword(String mobile, String password);
}
