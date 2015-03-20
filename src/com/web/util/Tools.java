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

	/**
	 * ɽ���ļ������صĹ���
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
	 * ɾ���ļ�
	 * @param path
	 */
	public static void delFile(String path)
	{
		File file = new File(path);
		   if(file.exists()){
		     file.delete();
		   }
	}
	
	/**
	 *��ȡ����ַ���
	 * @return
	 */
	public  static  String getRandomFileName() {  
		  
        SimpleDateFormat simpleDateFormat;  
  
        simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
  
        Date date = new Date();  
  
        String str = simpleDateFormat.format(date);  

        Random random = new Random();  
  
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// ��ȡ5λ�����  
  
        return   str+rannum;// ��ǰʱ��  
    } 
	
	/**
	 * ��ȡ������
	 * @param fileName
	 * @return
	 */
	 private static String getExtention(String fileName)  {   
         int pos = fileName.lastIndexOf( "." );   
        return fileName.substring(pos);   
} 
	 /**
	  * ��ȡ�ļ�����
	  * @param fileName
	  * @return
	  */
	 private static String getFileName(String fileName)  {   
         int pos = fileName.lastIndexOf( "." );   
       
        return fileName.substring(0, pos);   
} 
	 /**
	  * ��ȡʱ��
	  * @return
	  */
	 public static Date getDate()
	 {
		 return new Date();
	 }
	 
		/**
		 * ��ȡ�����
		 * @return
		 * @throws IOException
		 */
		public static PrintWriter getPw() throws IOException{
			   HttpServletResponse response = ServletActionContext.getResponse();
		       response.setContentType("text/html;charset=utf-8"); 
		        return  response.getWriter();  
		}
		/**
		 * ѹ���ļ�
		 * @param paths
		 * @return
		 * @throws Exception 
		 */
		public static String ZipFiles(List<String> paths) throws Exception
		{
			String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
			String zipName="arone"+getRandomFileName()+".zip";
			File zip=new File(realpath+"//"+zipName);
			
			byte[] buf = new byte[1024];
		    try {

		      ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip));
		    
		     out.setEncoding("gbk");

		      for (int i = 0; i < paths.size(); i++) {
		        FileInputStream in = new FileInputStream(realpath+"//"+paths.get(i));

		        out.putNextEntry(new ZipEntry(paths.get(i)));
	
		        int len;
		        while ( (len = in.read(buf)) > 0) {
		          out.write(buf, 0, len);
		        }
		        

		        out.closeEntry();
		        in.close();
		        //ɾ�������������ļ�
		        delFile(realpath+"//"+paths.get(i));
		      }
		
		      out.close();
		      System.out.println("ѹ�����.");
		      //�ϴ�����ţ��
		      Qiniu.uploadFile(zipName, realpath+"//"+zipName);
		      
		    }
		    catch (IOException e) {
		      e.printStackTrace();
		    }
			
			return zipName;
		}
}
