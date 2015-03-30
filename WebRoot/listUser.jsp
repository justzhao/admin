<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="js/jquery-easyui-1.4.2/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="js/jquery-easyui-1.4.2/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="css/mycss.css" type="text/css"></link>
<link rel="stylesheet" href="js/uploadify/uploadify.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/user.js"></script>


	

	


  </head>
  
  <body>
    <table id="tt" class="easyui-datagrid" style="width:100%;height:450px"
			url="getPageUser"
			title="用户列表" iconCls="icon-save"  pagination="true" pageList="[2,5,10,15]"
			singleSelect ="true"
			toolbar="#tb">
			
		<thead>
		<tr>
			<th field="id" width="50">编号</th>
			
			<th field="name" width="150">名字</th>
			
			
			 <th field="pass" width="250">密码</th> 
			
			
			<th field="role" width="100" formatter="formatRole">角色</th>
			
			<th field="createDate" width="100" >创建时间</th>
		</tr>
		</thead>
		
		<div id="tb">
			<div>
			
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
						
			<a  class="easyui-linkbutton" iconcls="icon-edit" plain="true" onclick="editUser()">修改</a>
		
			<a  class="easyui-linkbutton" iconcls="icon-remove" plain="true" onclick="deltUser();">删除</a>
   </div>


<div  style="margin-top: 10px;margin-bottom: 10px">

				<span>名字</span>
				<input id="name"  type="text" style="line-height:20px;border:1px solid #ccc">
				 <span>角色</span>
				   
				   <input id="searchrole" style="width:150px"  name ="user.role.id"  valueField="id" textField="name">
				<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch()">搜索</a>
			</div>
		</div>
	</table>
		<!-- 添加用户 -->
		                     
		<div id="dd"  class=" easyui-dialog" style="top:40px;padding:5px;width:700px;height:450px;"
		 title="添加用户"   iconCls="icon-ok" 
		  closed="true" modal="true">
			 
 			<form id="ff"  name="ff"   method="post">
 				
	        <table style="width:100%; font-size: 12px;font-weight: normal">
	  
		    <tr>
		    <td class="td1">名字:</td> 
		    <td style="width:70%">
		    <input  class="easyui-validatebox textbox"  type="text" name ="user.name"  required="true" validtype="remote['checkUserName','user.name']"  invalidMessage="用户名已存在">
		    </td>
		    </tr>
		   	  
		    <tr>
		    <td class="td1">密码:</td>
		    <td style="width:70%">
		    <input  class="easyui-validatebox textbox"  type="password"  name ="user.pass"  required="true" placeholder="初始密码">
		    </td>
	   		</tr>	
	   		   

	   	   <tr>
	   	   <td class="td1">角色：</td>
	   	   <td>
  		   <input id="role" style="width:150px"  name ="user.role.id"  valueField="id" textField="name">
		   </td>
		   </tr>
		   <tr>
	   	   <td style="text-align:right" colspan=2> 
	   	   <input  type="button" onclick="cancel()"  class="subbtn" value="取消">
	   	   <input  type="submit"  class="subbtn"  value="提交"></td>
	   	   </tr>	  

	   </table>
	    </form>
	</div>
		
	<!-- 编辑用户 -->
    <div id="edit" class="opear easyui-dialog" 
			title="编辑用户" iconCls="icon-ok"
			 closed="true" modal="true">
			 
		<form id="editform"  name="edit" method="post">	
		
	    <table style="width:100%; font-size:12px; font-weight:normal">
	  
	    <tr>
	    <input id="editid" type="hidden"  name="user.id">
	      <input id="editcreateDate" type="hidden" name="user.createDate">
	    <td class="td1">名字:</td><td style="width:70%">
	    <input id="editname" class="easyui-validatebox textbox"   type="text" name ="user.name"  required="true" readonly="true">
	    </td>
	    </tr>
	   	 
	   	<tr>
	    <td class="td1">密码:</td><td style="width:70%">
	    <input id="editpass" class="easyui-validatebox textbox"   type="password" name ="user.pass"  > 没填写表示不修改
	    </td>
	    </tr> 
	   	   
   	    <tr>
   	    <td class="td1">角色:</td>
	    <td>
  		<input id="editrole" style="width:150px"  name =" user.role.id" valueField="id"  textField="name">
	    </td>
	    </tr>

	   	
	    <tr>
	    <td style="text-align:right" colspan=2> 
	    <input  type="button" onclick="cancel()"  class="subbtn" value="取消">
	    <input type="submit"  class="subbtn"  value="提交">
	    
	    </td> 
	    </tr>  
	  
	   </table> </form>
	</div>
	
  </body>
  
  
</html>
