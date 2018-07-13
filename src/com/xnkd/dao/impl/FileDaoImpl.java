package com.xnkd.dao.impl;

import java.util.List;
import java.util.Map;

import com.xnkd.dao.BaseDao;
import com.xnkd.dao.FileDao;
import com.xnkd.dao.UserDao;
import com.xnkd.po.File;
import com.xnkd.po.User;

public class FileDaoImpl extends BaseDao implements FileDao {

	private static final long serialVersionUID = 1L;

	@Override
	public File selectFile(Map<String, Object> params) {
		String fileName=(String)params.get("fileName");
		String filePath=(String)params.get("filePath");
		
		String sql="select * from file where filePath=? and fileName=?";
		File fileList=(File) this.executeDQL(File.class, sql, filePath,fileName);
		return fileList;
	}

	@Override
	public Integer uploadFile(File file) {
		
		return null;
	}

	@Override
	public Integer deleteFile(File file) {
		String filePath=file.getFilePath();
		String fileName=file.getFileName();
		String sql="delete from file where filePath=? and fileName=?"; 
		return this.executeDML(sql, filePath,fileName);
	}

	@Override
	public Integer downloadFile(File file) {
		return null;
	}

}
