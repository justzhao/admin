
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
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
	-->
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="./js/uploadify/jquery.uploadify.min.js"></script>
	
	
	<script type="text/javascript">
		$(function(){
            				$('#model').combobox({
            				multiple:true,
            				editable:false,
                           onLoadSuccess: function (data) {
				            if (data) {
				               $('#model').combobox('setValue',data[0].id);
				            }
				            },
				            onChange:function(newValue,oldValue)
				            {
				          //  //alert('the new is '+newValue+'the old is '+oldValue);
				            
				            // $('modelsid').text=$('#model').combobox('getValues');
				    
				             document.getElementById("modelsid").value=$('#model').combobox('getValues');
				            },
				            onSelect:function(data)
				            {
				           // alert(data.id);
				            }
                }); 

			$('#ff').form({
				url:'savePacket',
				onSubmit:function(){
					return $(this).form('validate');
				},
				success:function(data){
					$.messager.alert('消息', "操作成功", 'info',function(){
					
					
					$(".panel-tool-close").click();
				//    window.location.href="listPacket.jsp";
					});
				}
			});
			


  
		});

		
		
		function formatEffective(value)
		{
		 return value!=0?"是":"否";
		}


	    	function removeRow(){

         var row = $('#tt').datagrid('getSelected');
    if (row){
    
    
     $.messager.confirm("操作提示", "您确定要执行操作吗？", function (data) {
            if (data) {
               
                $.post("deScrollPic",{'pic.id':row.id},function(d){
                   if(d)
                   {
           
                      $('#tt').datagrid('reload');
                   }else{
                   alert('删除失败');
                   }
                });
                
              
              
            }
         
        });

		    }else{
		      alert('没有选择数据');
		    }

}
		
		
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

	<table id="tt" class="easyui-datagrid" style="width:100%;height:400px"
			url="getPagePacket"
			title="轮播图列表" iconCls="icon-save"   singleSelect="true"  pagination="true"  pageList="[5,10,15]"
			toolbar="#tb"  >
		<thead>
			<tr>
<th field="id" width="50">编号</th>
<th field="name" width="100">名字</th>
<th field="info" width="300">备注</th>
<th field="urls" width="200">包路径</th>
<th field="xml" width="200">描述文件路径</th>
<th field="effective"   formatter="formatEffective"  width="100">是否测试版本</th>
<th field="createDate" width="200">创建日期</th>
<th field="thumbPic" width="200">缩略图</th>
<th field="version" width="200">版本</th>
<th field="count" width="200">下载数量</th>
<th field="descPic" width="200">说明文件</th>

			</tr>
		</thead>
	</table>
	<div id="tb">
	    
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dd').dialog('open')">添加</a>
	         <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:removeRow()">删除</a>
	</div>
	


		<div id="dd" class="easyui-dialog" style="top:80px;padding:5px;width:600px;height:300px;"
			title="添加数据包" iconCls="icon-ok"
			 closed="true" modal="true">
		   		<form id="ff"  name="ff"  enctype= "multipart/form-data"  method="post">
	   <table   style="width:100%; font-size: 12px;font-weight: normal">
	
	   <tr>
	   <td  class="td1" >
	   名字:
	   </td>
	   <td style="width:70%"><input   class="easyui-validatebox textbox"  type="text"   name ="packet.name"  required="true"></td>
	   </tr>
	   	   <tr>
	   <td class="td1">
	   备注
	   </td>
	   <td><input type="text"    class="easyui-validatebox textbox"  name="packet.info" style="width:200px;height:40px"></td>
	   </tr>
	   
	   	   	   <tr>
	   <td class="td1">
	   选择包
	   </td>
	   <td>	
	   <input id="modelsid"  name="modelsid"  type="hidden">
	   <input id="model" style="width:250px"  class="easyui-validatebox "   url="getModelList" valueField="id" textField="name"  ></td>
	   </tr>
	   	   	   	   <tr>
	   <td class="td1">
	  针对设备
	   </td>
	   <td><input type="radio" name="packet.device" value="0">所有设备
	   <input type="radio" name="packet.device" value="1">苹果
	    <input type="radio" name="packet.device" value="2">安卓
	   
	   </td>
	   </tr>
	   
	
	   	   
	   	   	   	   <tr>


	   	   
	   <td class="td1">
	   是否测试版本
	   </td>
	   <td>
	   
	   <s:checkbox name="packet.effective"></s:checkbox>


	 
	   
	   </td>
	   </tr>
	   
	   	   	   <tr>
	   <td class="td1">
	  创建日期
	   </td>
	   <td><input type="text"  class="easyui-datebox"  name="packet.createDate" ></td>
	   </tr>
	   	  
	
	    <tr>
                        <td class="td1">
                            缩略图上传：
                        </td>
                        <td>                            
             
                                   	    <input type="file" name="thumb"  class=" easyui-validatebox textbox"  required="true"  /> 
                              
                       
                          
                      
                
                  
                        </td>
                    </tr>
	   	 	   	   	   <tr>
	   <td class="td1">
	 版本号
	   </td>
	   <td><input type="text"  class=" textbox"    name="packet.version" ></td>
	   </tr>
	   
	   	   	   	   <tr>
	   <td class="td1">
	  说明文件
	   </td>
	   <td>  <input type="file" name="desc"  class="easyui-validatebox textbox"  required="true"  /> 
	   </td>
	   </tr>
	   
	   <tr>
	   <td style="text-align:right" colspan=2> <input  type="submit" value="提交"></td>
	   </tr>
	   
	   </table></form>
</div>


		   
		   
</body>
</html>