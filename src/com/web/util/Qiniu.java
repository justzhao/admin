package com.web.util;

import org.json.JSONException;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

public class Qiniu {
	private final static   String QINIUURL="7x00ae.com1.z0.glb.clouddn.com ";
	private final static  String   BUCKETNAME="ljh123";
	
	
	public static  void  uploadFile(String key,String path) throws Exception
	{
		
		  Config.ACCESS_KEY = "NkeUoq9b-b6Qh4dcn2XTZ6nxDJFP_2q5qBoUH8RM";
	       Config.SECRET_KEY = "Wu9PjR1XaK-9tsccKiMdstSJuvbyv7Kes5UQSrNR";
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);

        
        PutPolicy putPolicy = new PutPolicy(BUCKETNAME);
        String uptoken = putPolicy.token(mac);
        PutExtra extra = new PutExtra();
     

        PutRet ret = IoApi.putFile(uptoken, key, path, extra);
		
	}

}
