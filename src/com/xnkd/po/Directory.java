package com.xnkd.po;

import java.io.Serializable;
import java.sql.Date;

public class Directory implements Serializable{
	
	private Integer dirId;
	
	private String dirName;
	
	private Date createTime;
	
	private Date updateTime;
	
	/**
	 * 文件夹下的子文件数量
	 */
	private Integer sonFileNum;
	
	/**
	 * 在hdfs上的路径
	 */
	private String hdfsPath;
	
	private Integer userId;

	public Integer getDirId() {
		return dirId;
	}

	public void setDirId(Integer dirId) {
		this.dirId = dirId;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getSonFileNum() {
		return sonFileNum;
	}

	public void setSonFileNum(Integer sonFileNum) {
		this.sonFileNum = sonFileNum;
	}

	public String getHdfsPath() {
		return hdfsPath;
	}

	public void setHdfsPath(String hdfsPath) {
		this.hdfsPath = hdfsPath;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
