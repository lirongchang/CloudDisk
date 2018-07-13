package com.xnkd.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xnkd.po.*;
import com.xnkd.service.*;
import com.xnkd.service.impl.*;

/**
 * 用户模块控制器
 * 
 * @author LRC
 *
 */
public class UserAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		userService=new UserServiceImpl();
	}

	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("username");
		String passWord=request.getParameter("password");
		
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("username", userName);
		params.put("password", passWord);
		
		User user=userService.getUser(params);
		if(null!=user){//登录成功
			request.getSession().setAttribute("username",userName);
			request.getRequestDispatcher("fileAction?method=showFile").forward(request, response);
		}else{//登录失败
			request.setAttribute("errorInfo", "login failure....");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
	
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession(); 
		session.removeAttribute("username"); 
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
	
	/**
	 * 注册方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("username");
		String passWord=request.getParameter("password");
		String email=request.getParameter("email");
		
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("username", userName);
		params.put("password", passWord);
		params.put("email", email);
		
		User user=userService.selectByUsername(userName);
		System.out.println("User:"+ user);
		System.out.println("username:" + userName + "passWord:" + passWord + "email" + email);
		if(user==null){//进行添加
			User addUser=new User();
			user=addUser;
			user.setUsername(userName);
			user.setPassword(passWord);
			user.setEmail(email);
			
			Integer result=userService.insertUser(user);
			if(result != null) {
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else {
				request.setAttribute("errorInfo", "register failure,user already exists....");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
			
		}else{
			request.setAttribute("errorInfo", "register failure....");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
	
	/**
	 * 通过用户名查询用户
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public User search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userName=request.getParameter("username");
		
		User user=userService.selectByUsername(userName);
		
		return user;
	}
	
	
	/**
	 * 通过userId删除用户
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public Integer deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId=request.getParameter("userId");
		
		return userService.deleteUser(Integer.parseInt(userId));
	}
	
	
	/**
	 * 添加用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("username");
		String passWord=request.getParameter("password");
		String email=request.getParameter("email");
		
		System.out.println("username:" + userName + "passWord:" + passWord + "email:" +email);
		User user=userService.selectByUsername(userName);
		
		if(user==null){//进行添加
			User addUser=new User();
			user=addUser;
			user.setUsername(userName);
			user.setPassword(passWord);
			Integer result=userService.insertUser(user);
			if(result != null) {
				request.getRequestDispatcher("/userlist.jsp").forward(request, response);
			}else {
				request.setAttribute("errorInfo", "addUser failure");
				request.getRequestDispatcher("/addUser.jsp").forward(request, response);
			}
			
		}else{
			request.setAttribute("errorInfo", "addUser failure,user already exist....");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
	
	/**
	 * 编辑用户
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public Integer editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("username");
		String passWord=request.getParameter("password");
		String email=request.getParameter("email");
		String userId=request.getParameter("userId");
		User user=new User();
		user.setUsername(userName);
		user.setPassword(passWord);
		user.setEmail(email);
		user.setUserId(Integer.parseInt(userId));
		return userService.updateUser(user);
	}
}
