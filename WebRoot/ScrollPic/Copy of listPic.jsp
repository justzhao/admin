<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addPic.jsp' starting page</title>
    
    <link rel="stylesheet" href="css/default.css" type="text/css"></link>
   
    <link rel="stylesheet" href="js/themes/default/easyui.css" type="text/css"></link>

    <link rel="stylesheet" href="js/themes/icon.css" type="text/css"></link>
    
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="js/jQuery.easyui.js"></script>

  </head>
  
  <body>
<table id="tt" class="easyui-datagrid" style="width:800px;height:auto;" data-options="">
<thead>
<tr>
<th field="id" width="50">编号</th>
<th field="name" width="100">名字</th>
<th field="info" width="300">备注</th>
<th field="urls" width="200">图片</th>
<th field="effective" width="50">是否有效</th>

</tr>
</thead>
<tbody>
<tr>
<td>Data 1</td>
<td>Data 2</td>
<td>Data 3</td>
<td>Data 4</td>
<td>Data 5</td>

</tr>
<tr>
<td>Data 1</td>
<td>Data 2</td>
<td>Data 3</td>
<td>Data 4</td>
<td>Data 5</td>

</tr>
<tr>
<td>Data 1</td>
<td>Data 2</td>
<td>Data 3</td>
<td>Data 4</td>
<td>Data 5</td>

</tr>
<tr>
<td>Data 1</td>
<td>Data 2</td>
<td>Data 3</td>
<td>Data 4</td>
<td>Data 5</td>
<td>Data 6</td>
</tr>
</tbody>
</table>


<div id="tb">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:alert('Add')">Add</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="javascript:alert('Cut')">Cut</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:alert('Save')">Save</a>
</div>
  </body>
</html>
