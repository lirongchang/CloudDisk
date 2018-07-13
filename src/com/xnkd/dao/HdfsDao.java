package com.xnkd.dao;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import com.xnkd.po.File;
import com.xnkd.po.User;

public class HdfsDao {
	
	private final String basePath = "/upload/";
	
	//HDFS访问地址
    private static String HDFS = "hdfs://192.168.110.135:9000";
    
    //hdfs路径
    private String hdfsPath="/upload/";
    
    //Hadoop系统配置
    private Configuration conf;
    
	/**
	 * 获得在hdfs中的目录
	 * @param file
	 * @param user
	 * @return
	 */
	private String formatPathMethod(User user, File file) {
		return basePath + user.getUsername() + "/" + file.getFileName();
	}
    

    //在根目录下创建文件夹
    public void mkdirs(String folder) throws IOException {
        Path path = new Path(folder);
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        if (!fs.exists(path)) {
            fs.mkdirs(path);
            //System.out.println("Create: " + folder);
        }
        fs.close();
    }
    
	/**
	 * 上传文件
	 * @param inputStream
	 * @param file
	 * @param user
	 */
	public void put(InputStream inputStream, File file, User user) {
		try {
			String formatPath = formatPathMethod(user, file);
			OutputStream outputStream = HdfsConn.getFileSystem().create(new Path(formatPath));
			IOUtils.copyBytes(inputStream, outputStream, 2048, true);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 下载文件
	 * @param user
	 * @param file
	 * @param local
	 */
	public boolean download(User user, File file, String local) {
		try {
			String formatPath = formatPathMethod(user, file);
			if (HdfsConn.getFileSystem().exists(new Path(formatPath))) {
				FSDataInputStream inputStream = HdfsConn.getFileSystem().open(new Path(formatPath));
				OutputStream outputStream = new FileOutputStream(local);
				IOUtils.copyBytes(inputStream, outputStream, 4096, true);
				System.out.println(local);
				return true;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	/**
	 * 删除文件或目录
	 * @param file
	 * @param user
	 */
	public void delete(User user,File file) {
		try {
			String formatPath = formatPathMethod(user, file);
			if (HdfsConn.getFileSystem().exists(new Path(formatPath))) {
				HdfsConn.getFileSystem().delete(new Path(formatPath), true);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 重命名文件
	 * @param file
	 * @param user
	 * @param newname
	 */
	public void rename(File file, User user, String newname) {
		try {
			String formatPath = formatPathMethod(user, file);
			file.setFileName(newname);
			String newformatPath = formatPathMethod(user, file);
			if (HdfsConn.getFileSystem().exists(new Path(formatPath))) {
				HdfsConn.getFileSystem().rename(new Path(formatPath), new Path(newformatPath));
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建文件夹
	 * @param file
	 * @param user
	 */
	public void mkDir(File file, User user) {
		try {
			String formatPath = formatPathMethod(user, file);
			if (!HdfsConn.getFileSystem().exists(new Path(formatPath))) {
				HdfsConn.getFileSystem().mkdirs(new Path(formatPath));
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 复制或者移动文件或者目录
	 * @param sourcePath
	 * @param destPath
	 * @param flag
	 */
	public void copyOrMove(User user, File sourceFile, File destFile, boolean flag) {
		try {
			String sourceFormatPath = formatPathMethod(user, sourceFile);
			String destFormatPath = formatPathMethod(user, destFile);
			FileUtil.copy(HdfsConn.getFileSystem(), new Path(sourceFormatPath), HdfsConn.getFileSystem(), new Path(destFormatPath), flag, true, HdfsConn.getConfiguration());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//某个文件夹的文件列表
    public FileStatus[] ls(User user) throws IOException {
    	String formatPath = basePath+user.getUsername();
    	if (HdfsConn.getFileSystem().exists(new Path(formatPath))) {
	        FileStatus[] list = HdfsConn.getFileSystem().listStatus(new Path(formatPath));
	        System.out.println("ls: " + formatPath);
	        System.out.println("==========================================================");
	        if(list != null)
	        for (FileStatus f : list) {
	            //System.out.printf("name: %s, folder: %s, size: %d\n", f.getPath(), f.isDir(), f.getLen());
	        	System.out.printf("%s, folder: %s, 大小: %dK\n", f.getPath().getName(), (f.isDir()?"目录":"文件"), f.getLen()/1024);
	        }
	        System.out.println("==========================================================");
	        HdfsConn.getFileSystem().close();
	        return  list;
	    }
	return null;    //否则文件目录不存在
  }
    
    
    
    
}
