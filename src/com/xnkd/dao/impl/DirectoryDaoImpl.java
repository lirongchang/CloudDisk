package com.xnkd.dao.impl;

import java.util.List;
import java.util.Map;

import com.xnkd.dao.BaseDao;
import com.xnkd.dao.DirectoryDao;
import com.xnkd.po.Directory;
import com.xnkd.po.File;

public class DirectoryDaoImpl extends BaseDao implements DirectoryDao {

	@Override
	public List<Directory> selectPath(Map<String, Object> params) {
		Integer dirId=(Integer) params.get("dirId");
		
		String sql="select * from directory where dirId=?";
		List<Directory> directoryList=this.executeDQL(Directory.class, sql, dirId);
		return directoryList;
	}

	@Override
	public Integer deleteDirectory(Directory directory) {
		Integer dirId=(Integer) directory.getDirId();
		
		String sql="delete from directory where dirId=?";
		return this.executeDML(sql, dirId);
	}

	@Override
	public Integer updateDirectory(Directory directory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addDirectory(Directory directory) {
		// TODO Auto-generated method stub
		return null;
	}

}
