package com.xnkd.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.hadoop.fs.FileStatus;

import com.xnkd.dao.HdfsDao;
import com.xnkd.po.User;


public class FileAction extends BaseAction{
	
	private HdfsDao hdfs;
	/**
	 * 下载文件
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws URISyntaxException 
	 */
	public void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, URISyntaxException {
		hdfs=new HdfsDao();
		request.setCharacterEncoding("UTF-8");
		String local = "F:/download";
		String filePath = new String(request.getParameter("filePath").getBytes("ISO-8859-1"),"GB2312");
		System.out.println(filePath);
		User user=new User();
		String username=request.getParameter("username");
		user.setUsername(username);
		com.xnkd.po.File file2=new com.xnkd.po.File();
		String fileName=request.getParameter("fileName");
		file2.setFileName(fileName);
		System.out.println("开始下载文件");
		hdfs.download(user, file2, local);
		System.out.println("文件下载结束");
		// /upload/admin/file.txt
	}
	
	public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   hdfs=new HdfsDao();
	   request.setCharacterEncoding("UTF-8");
	   File file ;
	   int maxFileSize = 50 * 1024 *1024;   //50M
	   int maxMemSize = 50 * 1024 *1024;    //50M
	   ServletContext context = getServletContext();
	   String filePath = context.getInitParameter("file-upload");   //上传到服务器上的路径
	   
	   System.out.println("source file path:"+filePath+"");
	   
	   // 验证上传内容了类型
	   String contentType = request.getContentType();
	   if ((contentType.indexOf("multipart/form-data") >= 0)) {

	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      // 设置内存中存储文件的最大值
	      factory.setSizeThreshold(maxMemSize);
	      // 本地存储的数据大于 maxMemSize.
	      factory.setRepository(new File("F:\\temp"));

	      // 创建一个新的文件上传处理程序
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      // 设置最大上传的文件大小
	      upload.setSizeMax( maxFileSize );
	      try{ 
	         // 解析获取的文件
	         List<FileItem> fileItems = upload.parseRequest(request);  //解析request请求
	         // 处理上传的文件
	         Iterator<FileItem> i = fileItems.iterator();

	         System.out.println("begin to upload file to tomcat server</p>"); 
	         while ( i.hasNext () ) 
	         {
	            FileItem fi = (FileItem)i.next();
	            if ( !fi.isFormField () )	//如果不是表单域 ，就是文件上传元素
	            {
		            // 获取上传文件的参数
		            String fieldName = fi.getFieldName();   //文件域中name属性的值
		            String fileName = fi.getName();     //文件的全路径，绝对路径名加文件名
		            
		            String fn = fileName.substring( fileName.lastIndexOf("\\")+1);
		           
		            System.out.println("<br>"+fn+"<br>");
		            
		            boolean isInMemory = fi.isInMemory();
		            long sizeInBytes = fi.getSize();
		            // 写入文件
		            if( fileName.lastIndexOf("\\") >= 0 ){
			            file = new File( filePath , 
			            fileName.substring( fileName.lastIndexOf("\\"))) ;
		            }else{
			            file = new File( filePath ,
			            fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		            }
		            fi.write( file ) ;
		            
		            System.out.println("upload file to tomcat server success!");
		            
		            System.out.println("begin to upload file to hadoop hdfs</p>"); 
		           
		            //将tomcat上的文件上传到hadoop上
		            String username = (String) request.getSession().getAttribute("username");
		            System.out.println("username:" + username);
		            System.out.println("filePath" + filePath+"\\"+fn + "," + "/"+username+"/"+fn);
		            
		            String LocalfilePath=filePath+"\\"+fn;   //服务器上的绝对路径
		            
		            InputStream inputStream=new FileInputStream(LocalfilePath);
		            
		            User user=new User();
		            user.setUsername(username);
		            
		            com.xnkd.po.File file2=new com.xnkd.po.File();
		            file2.setFileName(fn);    //文件名
		            file2.setFilePath(fn); //指定文件路径为/upload/username/filename
		            
		            hdfs.put(inputStream, file2, user);
		            
		            System.out.println("upload file to hadoop hdfs success!");
		           
		            System.out.println("username-----"+username);
		            
		            request.getRequestDispatcher("fileAction?method=showFile").forward(request, response);
		         }
	         }
	      }catch(Exception ex) {
	    	 System.out.println("产生了异常！");
	         System.out.println(ex);
	      }
	   }else{
	      System.out.println("<p>No file uploaded</p>"); 
	
	   }
	}
	
	
	public void showFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, URISyntaxException {
		hdfs=new HdfsDao();
		request.setCharacterEncoding("UTF-8");
		User user=new User();
		String username = (String) request.getSession().getAttribute("username");
		user.setUsername(username);
		System.out.println("显示网盘中的文件");
		FileStatus[] documentList = hdfs.ls(user);
	    request.setAttribute("documentList",documentList);
	    request.getRequestDispatcher("main.jsp").forward(request,response);
	}
	
	public void deleteFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, URISyntaxException {
		hdfs=new HdfsDao();
		request.setCharacterEncoding("UTF-8");
		User user=new User();
		String username = (String) request.getSession().getAttribute("username");
		user.setUsername(username);
		com.xnkd.po.File file2=new com.xnkd.po.File();
		String fileName=request.getParameter("fileName");
		file2.setFileName(fileName);
		
		System.out.println("删除该文件");
		hdfs.delete(user,file2);
	    request.getRequestDispatcher("main.jsp").forward(request,response);
	}

}
