package com.web.util;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.web.entity.User;



public class CheckLoginInterceptor extends MethodFilterInterceptor {

@Override
public void setIncludeMethods(String includeMethods) {
	// TODO Auto-generated method stub
	System.err.println("the include is "+includeMethods);
	super.setIncludeMethods(includeMethods);
}

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// TODO Auto-generated method stub
           // 定义方法拦截器


		 User user = (User) ActionContext.getContext().getSession().get("user");
	 //   User user = SecurityManager.getLoggedOnUser();
	//     System.out.println(user.getName()+"sdfsadfasdf");
	    if (user != null) {
	        // 存在的情况下进行后续操作。
	        System.out.println("already login!");
	        
	        return actionInvocation.invoke();
	    } else {
	        // 否则终止后续操作，返回LOGIN
	        System.out.println("no login, forward login page!");
	        
	        return "homeLogin";
	
        }
	}
	
	

}
