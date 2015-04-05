<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>口袋家居后台管理系统</title>	
	<META http-equiv="X-UA-Compatible" content="IE=8" > </META>
  <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>

	<link href="css/main.css" rel="stylesheet" type="text/css" />
	   
</head>
<body>
<div id="login_center">
	<div id="login_area">
		<div id="login_box">
			<div id="login_form">
				<form id="loginForm"  method="post" action="checkUser" class="form-horizontal" role="form">
					<div class="form-group">
				      <label for="name" class="col-sm-4 control-label">用户名:</label>
				      <div class="col-sm-6">
				         <input type="text"  id="name" name="user.name"  class="form-control" >
				      </div>
				    </div>
				   <div class="form-group">
				      <label for="pass" class="col-sm-4 control-label">密&nbsp;&nbsp;&nbsp;码:</label>
				      <div class="col-sm-6">
				      <input type="password"  id="pass"  name="user.pass"   class="form-control">
				      </div>
				   </div>
				   <div class="form-group" id="c1">
				      <div  class="col-sm-offset-4 col-sm-10">
				         <button type="submit" class="btn btn-primary ">登录</button>
				        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				         <button type="reset" class="btn btn-primary ">重置</button>        
				     </div>
				     </div>
					</form>				
				</div>				
			</div>
		</div>
	</div>
	<script src="js/jquery-1.11.2.min.js" type="text/javascript" charset="utf-8"></script> 
	 
	<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/jquery.validate.js" type="text/javascript" charset="utf-8" ></script>
	<script src="js/form.js" type="text/javascript"  charset="utf-8"></script> 
	<script type="text/javascript" charset="utf-8" >
		 MyValidator.init();
     /* $(function(){
      	$(".form-horizontal").validate({
      		rules:{
      			"user.name":{
      				required:true
      				},
      			"user.pass":{
      				required:true
      				}
      		},
      		messages : {  
                "user.name" : {  
                    required: "必填字段"  
                },  
                "user.pass" : {  
                    required: "必填字段"  
                }               
            }
      	});
      
      });*/
	</script>
</body>
</html>
