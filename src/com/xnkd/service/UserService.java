package com.xnkd.service;

import java.util.List;
import java.util.Map;

import com.xnkd.po.*;

public interface UserService {

	public User getUser(Map<String,Object> params);
	
	public Integer insertUser(User user);
	
	public Integer deleteUser(Integer userId);
	
	public Integer updateUser(User user);

	public User selectByUsername(String userName);
	
}
