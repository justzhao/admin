package com.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import com.opensymphony.xwork2.ActionContext;
import com.web.entity.User;

public class Tools {
	
	private final static 	String SAVEPATH = ServletActionContext.getServletContext().getRealPath("/upload");

	
	public static void main(String[] args) {
		

		
	}
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
	
	
	public static String renameFile(String path,String newName)
	{
		path=SAVEPATH+"\\"+path;
		newName=SAVEPATH+"\\"+newName;
		File file = new File(path);
		file.renameTo(new File(newName));
		return "";
	}
	/**
	 * �����ļ�����ɾ���ļ�
	 * @param path
	 */
	public static void delFile(String path)
	{
		System.gc();
		path=SAVEPATH+"\\"+path;
		System.out.println("��ʼɾ���ļ�");
		File file = new File(path);
		
		   if(file.exists()){
			   System.out.println("����ɾ��:"+file.delete());
			
		   }
		  System.out.println("ɾ������"); 
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

       // Random random = new Random();  
  
      //  int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// ��ȡ5λ�����  
  
        return   str;// ��ǰʱ��  
    } 
	
	/**
	 * ��ȡ������
	 * @param fileName
	 * @return
	 */
	public static String getExtention(String fileName)  {   
         int pos = fileName.lastIndexOf( "." );   
        return fileName.substring(pos);   
} 
	 /**
	  * ��ȡ�ļ�����
	  * @param fileName
	  * @return
	  */
	 public static String getFileName(String fileName)  {   
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
		 * �����ļ�����ѹ���ļ�
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


		      ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip));
		    
		     out.setEncoding("gbk");

		      for (int i = 0; i < paths.size(); i++) {
		    	  
		    	  File f=new File(SAVEPATH+"//"+paths.get(i));
		    	  if(f.exists())
		    	  {
				        FileInputStream in = new FileInputStream(SAVEPATH+"//"+paths.get(i));

				        out.putNextEntry(new ZipEntry(paths.get(i)));
			
				        int len;
				        while ( (len = in.read(buf)) > 0) {
				          out.write(buf, 0, len);
				        }
				        

				        out.closeEntry();
				        
				        in.close();
		    	  }
		    	  else
		    	  {
		    		System.out.println("�ļ�������");  
		    	  }

	
		   
		      }
		      
		      out.close();
		      
		      for(int i=0;i<paths.size();i++)
		      {
		    	     delFile(paths.get(i));
		      }
		   
		      System.out.println("ѹ�����.");


     

			
			return zipName;
		}
		
		
		/**
		 * ȥ��list���ظ���Ԫ��
		 * @param list
		 * @return
		 */
		 public   static   List  removeDuplicate(List list)   { 
			    HashSet h  =   new  HashSet(list); 
			    list.clear(); 
			    list.addAll(h); 
			    //System.out.println(list); 
			    
			    return list;
			} 
		 
		 /**
		  * ��Ӧһ��list
		  * @param list
		  */
		 public static void printfList(List list)
		 {
			 
			 for(int i=0;i<list.size();i++)
			 {
				 System.out.println("the key is "+list.get(i).toString());
				 
			 }
		 }
		 public static User getCurrentUser()
		 {
		
			  User u=(User) ActionContext.getContext().getSession().get("user");
			 
			 if(u==null)
			 {
				 u=new User();
			 }else
			 {
				 System.out.println("the name is "+u.getName());
			 }
				 
			 return u;
		 }
}
