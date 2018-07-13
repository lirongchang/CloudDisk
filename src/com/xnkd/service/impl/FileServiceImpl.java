package com.xnkd.service.impl;

import java.util.Map;

import com.xnkd.dao.FileDao;
import com.xnkd.dao.impl.FileDaoImpl;
import com.xnkd.dao.impl.UserDaoImpl;
import com.xnkd.po.File;
import com.xnkd.service.FileService;

public class FileServiceImpl implements FileService{

	private FileDao fileDao;
	
	public FileServiceImpl(){
		fileDao=new FileDaoImpl();
	}

	@Override
	public File selectFile(Map<String, Object> params) {
		return (File) fileDao.selectFile(params);
	}

	@Override
	public Integer uploadFile(File file) {
		return fileDao.uploadFile(file);
	}

	@Override
	public Integer deleteFile(File file) {
		return fileDao.deleteFile(file);
	}

	@Override
	public Integer downloadFile(File file) {
		return fileDao.downloadFile(file);
	}

}
