package com.web.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.EncoderException;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.GetPolicy;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;
import com.qiniu.api.rs.URLUtils;

public class Qiniu {
	                                                               //http://7x00ae.com1.z0.glb.clouddn.com
	private final static   String QINIUURL="7x00ae.com1.z0.glb.clouddn.com";
	private final static  String   BUCKETNAME="ljh123";
	private final static String ACCESSKEY="NkeUoq9b-b6Qh4dcn2XTZ6nxDJFP_2q5qBoUH8RM";
	private final static String SECRETKEY="Wu9PjR1XaK-9tsccKiMdstSJuvbyv7Kes5UQSrNR";
	
   private final static  String SAVEPATH=ServletActionContext.getServletContext().getRealPath("/upload");
	
	public static void main(String[] args) {
		try {
			downloadFile("1366_768_5111_2015031609432163612.jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static  void  uploadFile(String key) throws Exception
	{
		System.out.println("七牛云开始上传");
		
		  Config.ACCESS_KEY = ACCESSKEY;
	       Config.SECRET_KEY = SECRETKEY;
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);

        
        PutPolicy putPolicy = new PutPolicy(BUCKETNAME);
        String uptoken = putPolicy.token(mac);
        PutExtra extra = new PutExtra();
     

        PutRet ret = IoApi.putFile(uptoken, key, SAVEPATH+"\\"+key, extra);
    	System.out.println("七牛云结束上传");
    	//删除本地文件
    	Tools.delFile(key);
	}
	public static void deleteFile(String key)
	{
		System.out.println("七牛云开始删除");
		  Config.ACCESS_KEY = ACCESSKEY;
	       Config.SECRET_KEY = SECRETKEY;
		 Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
	        RSClient client = new RSClient(mac);
	        client.delete(BUCKETNAME, key);
	    	System.out.println("七牛云结束删除");
	}
	public static void downloadFile(String key) throws Exception 
	{
		System.out.println("七牛云开始下载");
		Config.ACCESS_KEY = ACCESSKEY;
        Config.SECRET_KEY = SECRETKEY;
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);

        String baseUrl = URLUtils.makeBaseUrl(QINIUURL,key);
        GetPolicy getPolicy = new GetPolicy();
        String downloadUrl = getPolicy.makeRequest(baseUrl, mac);
  

 
 

         
         int bytesum = 0;
         int byteread = 0;
         URL url = new URL(downloadUrl);
         
         URLConnection conn = url.openConnection();
         InputStream inStream = conn.getInputStream();
         FileOutputStream fs = new FileOutputStream(SAVEPATH+"//"+key);

         byte[] buffer = new byte[1204];
     
         while ((byteread = inStream.read(buffer)) != -1) {
             bytesum += byteread;
        
             fs.write(buffer, 0, byteread);
         }
          inStream.close();
          fs.close();
         

/**
         try {
             URLConnection conn = url.openConnection();
             InputStream inStream = conn.getInputStream();
             FileOutputStream fs = new FileOutputStream(SAVEPATH+"//"+key);

             byte[] buffer = new byte[1204];
         
             while ((byteread = inStream.read(buffer)) != -1) {
                 bytesum += byteread;
            
                 fs.write(buffer, 0, byteread);
             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
            
         } catch (IOException e) {
             e.printStackTrace();
      
         }
*/
     	System.out.println("七牛云结束下载");
	}

}
