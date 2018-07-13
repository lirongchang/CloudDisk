package com.xnkd.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseDao {

	private static String url;
	
	private static String driver;
	
	private static String username;
	
	private static String passWord;
	
	static{
		username="root";
		passWord="root";
		driver="com.mysql.jdbc.Driver";
		url="jdbc:mysql://127.0.0.1:3306/cloudDisk?useUnicode=true&characterEncoding=utf8";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException 
	 */
	public Connection getConnection() throws SQLException{
		Connection conn=DriverManager.getConnection(url, username, passWord);
		return conn;
	}
	
	/**
	 * 执行通用增删改
	 * @return 影响行数
	 */
	public Integer executeDML(String sql,Object... params){
		Connection conn=null;
		PreparedStatement pstat=null;
		try {
			conn=getConnection();
			pstat=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				pstat.setObject(i+1, params[i]);
			}
			
			Integer count=pstat.executeUpdate();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(null!=pstat)pstat.close();
				if(null!=conn)conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 通用查询方法
	 * @param cla
	 * @param sql
	 * @param params
	 * @return
	 */
	public <T> List<T> executeDQL(Class<T> cla,String sql,Object... params){
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet res=null;
		
		List<T> list=new ArrayList<T>();
		try {
			conn=getConnection();
			pstat=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				pstat.setObject(i+1, params[i]);
			}
			
			res=pstat.executeQuery();
			ResultSetMetaData rsmd=res.getMetaData();
			Integer columnNum=rsmd.getColumnCount();
			
			if(Number.class.isAssignableFrom(cla)||String.class.isAssignableFrom(cla)||
					java.util.Date.class.isAssignableFrom(cla)){//封装简单类型
				T t=null;
				while(res.next()){
					t=(T)res.getObject(1);
					list.add(t);
				}
			}else if(Map.class.isAssignableFrom(cla)){//封装字典
				T t=null;
				Method putMethod=cla.getDeclaredMethod("put", Object.class,Object.class);
				while(res.next()){
					t=(T)cla.newInstance();
					for(int i=0;i<columnNum;i++){
						String fieldName=rsmd.getColumnLabel(i+1);
						Object fieldValue=res.getObject(fieldName);
						putMethod.invoke(t, fieldName,fieldValue);
					}
					list.add(t);
				}
			}else{//封装实体对象
				T t=null;
				while(res.next()){
					t=(T)cla.newInstance();
					for(int i=0;i<columnNum;i++){
						String fieldName=rsmd.getColumnLabel(i+1);
						Object fieldValue=res.getObject(fieldName);
						Field attrName=cla.getDeclaredField(fieldName);
						attrName.setAccessible(true);
						attrName.set(t, fieldValue);
					}
					list.add(t);
				}
			}
		} catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException e) {
			e.printStackTrace();
		}finally{
			try{
				if(null!=pstat)pstat.close();
				if(null!=conn)conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
