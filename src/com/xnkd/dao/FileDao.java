package com.xnkd.dao;

import java.util.List;
import java.util.Map;

import com.xnkd.po.*;

public interface FileDao {
	
public File selectFile(Map<String,Object> params);
	
	public Integer uploadFile(File file);
	
	public Integer deleteFile(File file);
	
	public Integer downloadFile(File file);
}

