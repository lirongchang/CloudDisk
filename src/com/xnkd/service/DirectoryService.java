package com.xnkd.service;

import java.util.Map;

import com.xnkd.po.Directory;

public interface DirectoryService {
	public Directory selectPath(Map<String,Object> params);
	
	public Integer deleteDirectory(Directory directory);
	
	public Integer updateDirectory(Directory directory);
	
	public Integer addDirectory(Directory directory);
}
