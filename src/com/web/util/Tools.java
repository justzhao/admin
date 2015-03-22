package com.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;


import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class Tools {
	
	private final static 	String SAVEPATH = ServletActionContext.getServletContext().getRealPath("/upload");

	/**
	 * 山传文件到本地的工具
	 * @param file
	 * @param fileName
	 * @param fileContentType
	 * @param realPath
	 * @return
	 * @throws IOException
	 */
	
	public  static String saveFile(File file, String fileName, String fileContentType,String realPath) throws IOException {
		// TODO Auto-generated method stub
        
        if(file==null)
        {
        	System.out.println("nulllllllllll");
        }
        if (file != null) {
        	System.out.println(" no nulllllllllll");
        	fileName=getFileName(fileName)+"_"+getRandomFileName()+getExtention(fileName);
        	
            File savefile = new File(new File(realPath), fileName);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(file, savefile);
    
        }
	
		return fileName;
	}
	
	/**
	 * 输入文件名字删除文件
	 * @param path
	 */
	public static void delFile(String path)
	{
		System.gc();
		path=SAVEPATH+"\\"+path;
		System.out.println("开始删除文件");
		File file = new File(path);
		   if(file.exists()){
			   System.out.println("正在删除:"+file.delete());
			
		   }
		  System.out.println("删除结束"); 
	}
	
	/**
	 *获取随机字符串
	 * @return
	 */
	public  static  String getRandomFileName() {  
		  
        SimpleDateFormat simpleDateFormat;  
  
        simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
  
        Date date = new Date();  
  
        String str = simpleDateFormat.format(date);  

       // Random random = new Random();  
  
      //  int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数  
  
        return   str;// 当前时间  
    } 
	
	/**
	 * 获取扩张名
	 * @param fileName
	 * @return
	 */
	 private static String getExtention(String fileName)  {   
         int pos = fileName.lastIndexOf( "." );   
        return fileName.substring(pos);   
} 
	 /**
	  * 获取文件名字
	  * @param fileName
	  * @return
	  */
	 private static String getFileName(String fileName)  {   
         int pos = fileName.lastIndexOf( "." );   
       
        return fileName.substring(0, pos);   
} 
	 /**
	  * 获取时间
	  * @return
	  */
	 public static Date getDate()
	 {
		 return new Date();
	 }
	 
		/**
		 * 获取输出流
		 * @return
		 * @throws IOException
		 */
		public static PrintWriter getPw() throws IOException{
			   HttpServletResponse response = ServletActionContext.getResponse();
		       response.setContentType("text/html;charset=utf-8"); 
		        return  response.getWriter();  
		}
		/**
		 * 输入文件名字压缩文件
		 * @param paths
		 * @return
		 * @throws Exception 
		 */
		public static String ZipFiles(List<String> paths,String zipName) throws Exception
		{

			 if(zipName.equals(""))
			 {
				 zipName="arone"+getRandomFileName()+".zip";
			 }
				 File zip=new File(SAVEPATH+"//"+zipName);
			
			byte[] buf = new byte[1024];
		    try {

		      ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip));
		    
		     out.setEncoding("gbk");

		      for (int i = 0; i < paths.size(); i++) {
		        FileInputStream in = new FileInputStream(SAVEPATH+"//"+paths.get(i));

		        out.putNextEntry(new ZipEntry(paths.get(i)));
	
		        int len;
		        while ( (len = in.read(buf)) > 0) {
		          out.write(buf, 0, len);
		        }
		        

		        out.closeEntry();
		        
		        in.close();
	
		   
		      }
		      
		      out.close();
		      
		      for(int i=0;i<paths.size();i++)
		      {
		    	     delFile(paths.get(i));
		      }
		      System.out.println("压缩完成.");
		      //上传到七牛云
		  //    Qiniu.uploadFile(zipName, realpath+"//"+zipName);
		      
		    }
		    catch (IOException e) {
		      e.printStackTrace();
		    }
			
			return zipName;
		}
}
