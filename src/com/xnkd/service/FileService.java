package com.xnkd.service;

import java.util.Map;

import com.xnkd.po.File;

public interface FileService {
	
	public File selectFile(Map<String,Object> params);
	
	public Integer uploadFile(File file);
	
	public Integer deleteFile(File file);
	
	public Integer downloadFile(File file);
}
