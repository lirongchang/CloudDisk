package com.xnkd.dao;

import java.util.List;
import java.util.Map;

import com.xnkd.po.*;

public interface DirectoryDao {
	
	public List<Directory> selectPath(Map<String,Object> params);
	
	public Integer deleteDirectory(Directory directory);
	
	public Integer updateDirectory(Directory directory);
	
	public Integer addDirectory(Directory directory);
	
}
