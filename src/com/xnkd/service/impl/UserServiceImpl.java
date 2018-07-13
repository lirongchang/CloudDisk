package com.xnkd.service.impl;

import java.util.List;
import java.util.Map;

import com.xnkd.dao.*;
import com.xnkd.dao.impl.*;
import com.xnkd.po.*;
import com.xnkd.service.*;

public class UserServiceImpl implements UserService{
	
	private UserDao userDao;
	
	public UserServiceImpl(){
		userDao=new UserDaoImpl();
	}

	@Override
	public User getUser(Map<String, Object> params) {
		return userDao.selectUser(params);
	}

	@Override
	public Integer insertUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public Integer deleteUser(Integer userId) {
		return userDao.deleteUser(userId);
	}

	@Override
	public Integer updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public User selectByUsername(String userName) {
		return userDao.selectByUsername(userName);
	}
}
