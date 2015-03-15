
  <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%

String path = request.getContextPath();

String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/icon.css">

<link rel="stylesheet" href="./js/uploadify/uploadify.css" type="text/css"></link>
<!-- 
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.4.min.js"></script>-->
		<!--<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>-->
		<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="http://www.jeasyui.net/Public/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="./js/uploadify/jquery.uploadify.min.js"></script>
	
	
	<script type="text/javascript">
		$(function(){

				$('#code').combobox({
				
		
				        onLoadSuccess: function (data) {
            if (data) {
                $('#code').combobox('setValue',data[0].id);
            }
   }
			
		
			});
      

			$('#ff').form({
				url:'saveModel',
				onSubmit:function(){
					return $(this).form('validate');
				},
				success:function(data){
					$.messager.alert('Info', "操作成功", 'info');
				}
			});
			

			


            

  
  
 
		});

		
		
		

		
		
	</script>


<style>
table   {
border-collapse:collapse;
} 
td {
border:1px   solid   #C0C0C0;
 text-align: left;
} 

.td1 {


 background-color:#FFF6F1;
 width:30%
}
</style>
</head>
<body>

	<table id="tt" class="easyui-datagrid" style="width:100%;height:250px"
			url=""
			title="模型列表" iconCls="icon-save"
			toolbar="#tb">
		<thead>
			<tr>
<th field="id" width="50">编号</th>
<th field="name" width="100">名字</th>
<th field="code" width="50">识别码</th>

<th field="info" width="300">备注</th>
<th field="url" width="200">图片</th>
<th field="animation" width="100">是否有动画</th>
<th field="size" width="100">模型尺寸</th>
<th field="offset" width="100">模型位移</th>
<th field="rotate" width="100">模型旋转</th>

<th field="createDate" width="100">创建日期</th>
			</tr>
		</thead>
		

	</table>
	<div id="tb">
	    
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dd').dialog('open')">添加</a>

	</div>
	


		<div id="dd" class="easyui-dialog" style="padding:5px;width:600px;height:300px;"
			title="添加模型" iconCls="icon-ok"
			 closed="true" modal="true">
			 
		
	   <table   style="width:100%; font-size: 12px;font-weight: normal">
	   		<form id="ff"  name="ff"  enctype= "multipart/form-data"  method="post">
	   <tr>
	     <td  class="td1" >名字:</td>
	   
	   
	    <td style="width:70%">
	    
	    <input  class="easyui-validatebox textbox"   type="text"   name ="model.name"  required="true">
	    
	    </td>
	   </tr>
	   	  
	   	   

	   	   <tr><td class="td1">  识别码：</td>
	   <td>
  
	    <input id="code"  style="width:150px"   url="getCodeList"    name="model.code.id"  valueField="id" textField="name" />
<!--
	   <input id="code" style="width:100px" url="data/combobox_data.json" valueField="id" textField="text">
	</input>-->
	   </td>
	   </tr>
	   	   	   <tr>
	   <td class="td1">
	    备注：
	   </td>
	   <td>
	    <input   type="text"   name ="model.info" >
	   </td>
	   </tr>	
	   	   	   <tr>
	   <td class="td1">
	    模型：
	   </td>
	   <td>
	    <input type="file" name="file"  class="easyui-validatebox textbox"   required="true"/>
	    
	   </td>
	   </tr>
	   	   	   <tr>
	   <td class="td1">
	 是否有动画：
	   </td>
	   <td>
	  	 是   <input type="radio"  value="1" name="model.animation">
	 否   <input type="radio"  value="0" name="model.animation">
	   </td>
	   </tr>
	   	   	   <tr>
	   <td class="td1">
	   尺寸：
	   </td>
	   <td>
	    <input type="text"   class="easyui-numberbox textbox"   name="model.size"   required="true"  />
	   </td>
	   </tr>
	   
	   	   	   	   <tr>
	   <td class="td1">
	   位移：
	   </td>
	   <td>
	   <input type="text" name="model.offset"/>
	   </td>
	   </tr>
	   
	   	 <tr> <td class="td1"> 旋转：</td><td><input type="text" name="model.rotate"   /></td>
	   </tr>
	   
	  
	   	
	   <tr><td style="text-align:right" colspan=2> <input   type="submit" value="提交"></td> 
	   </tr>  
	   </form>
	   </table>
</div>



		   
		   
</body>
</html>