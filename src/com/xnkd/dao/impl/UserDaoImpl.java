package com.xnkd.dao.impl;

import java.util.List;
import java.util.Map;
import com.xnkd.po.*;
import com.xnkd.dao.*;


public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public User selectUser(Map<String, Object> params) {
		String userName=(String)params.get("username");
		String passWord=(String)params.get("password");
		
		String sql="select * from user where username=? and password=?";
		List<User> userList=this.executeDQL(User.class, sql, userName,passWord);
		return userList.size()==0?null:userList.get(0);
	}

	@Override
	public Integer insertUser(User user) {
		String userName=user.getUsername();
		String passWord=user.getPassword();
		String email=user.getEmail();
		String sql="insert into user(username,password,email) values(?,?,?)";
		return this.executeDML(sql, userName,passWord,email);
	}

	@Override
	public Integer deleteUser(Integer userId) {
		String sql="delete from user where userId=?";
		return this.executeDML(sql, userId);
	}

	@Override
	public Integer updateUser(User user) {
		String sql="update user set username=?,password=?,email=? where userId=?";
		return this.executeDML(sql, user);
	}

	@Override
	public User selectById(Integer userId) {
		String sql="select *from user where userId=?";
		return (User) this.executeDQL(User.class, sql, userId);
	}

	@Override
	public List<User> selectByname(String name) {
		String sql="select *from user where name=?";
		return this.executeDQL(User.class, sql, name);
	}

	@Override
	public User selectByUsername(String username) {
		String sql="select *from user where username=?";
		List<User> userList=this.executeDQL(User.class, sql, username);
		return userList.size()==0?null:userList.get(0);
	}
	
	@Override
	public Integer selectIdByUsername(String userName) {
		String sql="select *from user where username=?";
		return this.executeDQL(User.class, sql, userName).get(0).getUserId();
	}
}
