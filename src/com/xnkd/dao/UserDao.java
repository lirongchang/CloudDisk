package com.xnkd.dao;

import java.util.List;
import java.util.Map;
import com.xnkd.po.*;


public interface UserDao {
	
	public User selectUser(Map<String,Object> params);
	
	public Integer insertUser(User user);
	
	public Integer deleteUser(Integer userId);
	
	public Integer updateUser(User user);
	
	public User selectById(Integer userId);
	
	public List<User> selectByname(String name);
	
	public User selectByUsername(String username);
	
	public Integer selectIdByUsername(String username);

}
