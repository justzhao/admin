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


	<link href="css/login_css.css" rel="stylesheet" type="text/css" />
	
<link rel="stylesheet" href="js/jquery-easyui-1.4.2/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="js/jquery-easyui-1.4.2/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
	
	<%-- <script type="text/javascript" src="js/login.js"></script> --%>
	
	<script type="text/javascript">

	</script>

</head>

<body>
	<div id="login_center">
		<div id="login_area">
			<div id="login_box">
				<div id="login_form">
					<form id="loginForm"  method="post" action="checkUser">
						<table style="width:100%;margin-top:100px;font-size: 20px;font-weight: normal">
						
						<tr>
		    			<td class="td1">名字</td> 
					    <td style="width:70%">
					    <input  class="easyui-validatebox textbox"  type="text"  style="height:30px" name ="user.name"  required="true">
					    </td>
					    </tr>
					    
					    <tr>
					    <td class="td1">密码</td>
					    <td style="width:70%">
					    <input  class="easyui-validatebox textbox"  type="password"  style="height:30px" name ="user.pass"  required="true" placeholder="输入密码" >
					    </td>
				   		</tr>
				   		<tr style="height:30px">
				   		</tr>
						
						<tr>
						
				   	    <td style="text-align:right ">
				   	    <input  type="submit" value="登录"  style="border:none;background:url('images/login/btn.jpg') left top no-repeat;width:80px; height:29px;" > </td>
				   	    
				   	    <td style="padding：10px; width:50%" >
				   	    
				   	    <input  type="reset" value="重置"  style="border:none;background:url('images/login/btn.jpg') left top no-repeat;width:80px; height:29px;"></td>
				   	    </tr>
				   	   
						</table>
					</form>
				</div>

			</div>
		</div>
	</div>

</body>
</html>
