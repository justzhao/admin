package com.web.util;

import com.web.entity.User;



public class SecurityManager {
    private static ThreadLocal local = new ThreadLocal();    
    
   public static   void login(User u){  
    local.set(u);    
   }    
      
   public static  void logout(){    
       //local.set(null);
	   local.remove();
   }    
      
   public static  User getLoggedOnUser(){    
       return (User)local.get();    
   }  
}
