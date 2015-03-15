
  <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%

String path = request.getContextPath();

String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="jquery,ui,easy,easyui,web">
	<meta name="description" content="easyui help you build your web page easily!">
	<title>jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/icon.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="http://www.jeasyui.net/Public/js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript">

		$(function(){
			$('#cc').combobox({
	        onLoadSuccess: function (data) {
				            if (data) {
				               $('#cc').combobox('setValue',data[0].id);
				            }
				            }
			});
		});
	</script>
</head>
<body>
	<table id="tt" class="easyui-datagrid" style="width:800px;height:250px"
			url="getPicList"
			title="轮播图列表" iconCls="icon-save"
			toolbar="#tb">
		<thead>
			<tr>
<th field="id" width="50">编号</th>
<th field="name" width="100">名字</th>
<th field="info" width="300">备注</th>
<th field="urls" width="200">图片</th>
<th field="effective" width="100">是否有效</th>
			</tr>
		</thead>
	</table>
		<div id="tb">
	    
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dd').dialog('open')">添加</a>

		<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="javascript:alert('Cut')">Cut</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:alert('Save')">Save</a>
	
	</div>
	
	
	<div id="dd"  class="easyui-dialog"   style="padding:5px;width:600px;height:300px;"
			title="添加轮播图" iconCls="icon-ok"
			 closed="true" modal="true">
			 
			 
	 <table   style="width:100%; font-size: 12px;font-weight: normal">
	   <tr>
	   <td  class="td1" > 名字:</td><td style="width:70%"><input  class=""   type="text"   name ="pic.name"  required="true"></td>
	   </tr>
	   	  
  <tr>
  <td class="td1">曾多次:</td>
  
  <td>
  <form name="ff" id="ff"  method="post" action="saveModel">
  	<input id="cc" style="width:100px"  name ="model.code.id"  url="getCodeList" valueField="id" textField="name">
  <input type="submit"  value="submit">
  </form>
  </td>
  </tr>
	 
	   	  
	

	
	   
	   <tr>
	   <td style="text-align:right" colspan=2> <input   type="submit" value="提交"></td>
	   </tr>    
	   </table>
			 </div>
</body>
</html>