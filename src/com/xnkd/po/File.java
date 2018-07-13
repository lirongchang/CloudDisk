package com.xnkd.po;

import java.io.Serializable;
import java.sql.Date;

public class File implements Serializable{
	
	private Integer fileId;
	
	private String fileName;
	
	private String filePath;
	
	private String fileType;
	
	private String fileSize;
	
	private Date uploadTime;
	
	private Date updateTime;
	
	private Integer downloadNum;
	
	private Integer userId;
	
	/**
	 * 路径Id
	 */
	private Integer dirId;

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(Integer downloadNum) {
		this.downloadNum = downloadNum;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDirId() {
		return dirId;
	}

	public void setDirId(Integer dirId) {
		this.dirId = dirId;
	}
	
	
	
}
