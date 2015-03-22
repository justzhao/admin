
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
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<!--<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
	-->
	<script type="text/javascript" src="http://www.jeasyui.net/Public/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="./js/uploadify/jquery.uploadify.min.js"></script>
	
	<script type="text/javascript" src="./js/easyui-lang-zh_CN.js"></script>

	<script type="text/javascript">
		$(function(){
			
			$('#ff').form({
				url:'saveCode',
				onSubmit:function(){
					if($(this).form('validate'))
					{
					   $.messager.progress({
						 title: '稍等',
						 msg: '正在操作中...',
						text: '操作中'
						 });
					 return true;
					}else
					{
					
					return false;
					}
				},
				success:function(data){
				
				    $.messager.progress('close');
		             $.messager.alert('消息', "操作成功", 'info',function(){
					
					
					   $('#tt').datagrid('reload');
					$(".panel-tool-close").click();
				
					});
				}
			});
			

		 var urls='';
		 var count=0;
		 
            
            
         $('#file_upload').uploadify({
	         'swf': './js/uploadify/uploadify.swf',  //FLash文件路径
	         'buttonText': '浏  览',                                 //按钮文本
	         'debug' : false,
	         'uploader': 'addCode',                       //处理文件上传Action
	         'fileObjName':'file',
	         'cancelImg' : './js/uploadify/uploadify-cancel.png',
	         'queueID': 'fileQueue',                        //队列的ID
	         'queueSizeLimit': 1,                          //队列最多可上传文件数量，默认为999
	         'auto': false,                                 //选择文件后是否自动上传，默认为true
	         'multi': false,                                 //是否为多选，默认为true
	         'removeCompleted': true,                       //是否完成后移除序列，默认为true
	         'fileSizeLimit': '1000MB',                       //单个文件大小，0为无限制，可接受KB,MB,GB等单位的字符串值
	         'fileTypeDesc': 'Image Files',                 //文件描述
	         'fileTypeExts': '*.gif; *.jpg; *.png; *.bmp',  //上传的文件后缀过滤器
	         
	         'onSelect' : function(file) {
	                  
        	 },
        	 
	         'onUploadError' : function(file, errorCode, errorMsg, errorString) {
	            alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
	         },
	         
	         //检测FLASH失败调用
	         'onFallback':function(){
	            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
	         },
	         
        	//上传到服务器，服务器返回相应信息到data里
	         'onUploadSuccess':function(file, data, response){

	           
	             document.getElementById("urls").value=data; //
	             count=document.getElementById("count").innerHTML;
	             document.getElementById("count").innerHTML=Number(count)+1;
   			 }
            });
            
        var url='';   
        var up_count=0;    
  		$('#up_file_upload').uploadify({
	         'swf': './js/uploadify/uploadify.swf',  //FLash文件路径
	         'buttonText': '浏  览',                                 //按钮文本
	         'debug' : false,
	         'uploader': 'addCode',                       //处理文件上传Action
	         'fileObjName':'file',
	         'cancelImg' : './js/uploadify/uploadify-cancel.png',
	         'queueID': 'up_fileQueue',                        //队列的ID
	         'queueSizeLimit': 1,                          //队列最多可上传文件数量，默认为999
	         'auto': false,                                 //选择文件后是否自动上传，默认为true
	         'multi': false,                                 //是否为多选，默认为true
	         'removeCompleted': true,                       //是否完成后移除序列，默认为true
	         'fileSizeLimit': '1000MB',                       //单个文件大小，0为无限制，可接受KB,MB,GB等单位的字符串值
	         'fileTypeDesc': 'Image Files',                 //文件描述
	         'fileTypeExts': '*.gif; *.jpg; *.png; *.bmp',  //上传的文件后缀过滤器
	         
	         'onSelect' : function(file) {
	                  
        	 },
        	 
	         'onUploadError' : function(file, errorCode, errorMsg, errorString) {
	            //alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
	         },
	         
	         //检测FLASH失败调用
	         'onFallback':function(){
	            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
	         },
	         
        	//上传到服务器，服务器返回相应信息到data里
	         'onUploadSuccess':function(file, data, response){

	           
	             document.getElementById("up_url").value=data; //
	             count=document.getElementById("up_count").innerHTML;
	             document.getElementById("up_count").innerHTML=Number(up_count)+1;
   			 }
            });
  		
		});
	    function doSearch(){
		   /*  $('#tt').datagrid('load',{
		    itemid: $('#itemid').val(),
		    productid: $('#productid').val()
		    }); */
		    var checked = document.getElementById('packed').checked;
		    //alert(checked); true false
		    $('#tt').datagrid('load',{
				'code.name': $('#name').val(),
				'code.createDate': $('#start').datebox('getValue'),
				'code.endDate': $('#end').datebox('getValue'),
				'code.owner': $('#owner').val(),
				'code.packed': checked
				
			});
	    }
	    
		function formatPacked(value)
		{
			return value!=0?"是":"否";
		}
		
		function newCode() {
			$('#ff').form('clear');
			document.getElementById("count").innerHTML=0;
			$('#dd').dialog('open').dialog('setTitle','添加识别码');
		}
		
		function deltCode() {
            var row = $('#tt').datagrid('getSelected');
            //alert('row id  '+row.id);
            if (row) {
                $.messager.confirm('操作提示', '是否要删除识别码?', function (r) {
                    if (r) {
                        $.post('deleteCode.action', { id: row.id }, function (result) {
                        
                            if (result=='删除成功') {
                                $('#tt').datagrid('reload');    // reload the user data 
                                $.messager.alert("操作提示", "操作成功","info");
                                
                            } else {
                                $.messager.show({   // show error message 
                                    title: 'info',
                                    msg: '删除失败'
                                });
                            }
                        });
                    } 
                });
            } else{
   
                $.messager.alert("操作提示", "请选择要删除的识别码","error");
            }
            
        }  
        
        function editCode() {
	    	var row = $('#tt').datagrid('getSelected');
		    if (row){
			    $('#dU').dialog('open').dialog('setTitle','编辑识别码');
			    $('#fU').form('load',row);

			   

			    
		    } else{
            	
                $.messager.alert("操作提示", "请选择要修改的识别码","error");
            }
        }
        
        $(function(){
	        $('#fU').form({
					url:'updateCode',
					onSubmit:function(){
						 if($(this).form('validate'))
						 {
						  $.messager.progress({
							 title: '稍等',
							 msg: '正在操作中...',
							text: '操作中'
							 });
						 
						 return true;
						 }else
						 {
						 return false;
						 }
					},
					success:function(data){
					
				
						$.messager.alert('Info', "操作成功", 'info',function(){
							 $.messager.progress('close');
							 	$(".panel-tool-close").click();
					      	$('#tt').datagrid('reload');
						});
						
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

	<table id="tt" class="easyui-datagrid" style="width:100%;height:400px"
			url="showContent"
			title="识别码列表" iconCls="icon-save" pagination="true" pageList="[5,10,15]" 
			singleSelect ="true"
			toolbar="#tb">
		<thead>
			<tr>
<th field="id" width="50">编号</th>
<!-- <th field="code" width="50">识别码</th> -->
<th field="name" width="150">名字</th>
<!-- <th field="info" width="300">备注</th> -->

<th field="url" width="250">图片</th>

<!-- <th field="effective"   formatter="formatEffective"  width="100">是否有效</th> -->
<th field="owner" width="100">上传用户</th>

<th field="packed"   formatter="formatPacked"  width="100">是否被打包</th>

<th field="createDate" width="100">创建日期</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
	    <div>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCode();">添加</a>
		
		<a href="#" class="easyui-linkbutton" iconcls="icon-edit" plain="true"
                onclick="editCode()" >修改</a>
		
		<a href="#" class="easyui-linkbutton" iconcls="icon-remove" plain="true" onclick="deltCode();">删除</a>
		</div>
		
		<div>
			<span>开始时间</span>
			<input id="start" class="easyui-datebox" style="line-height:26px;border:1px solid #ccc" editable=false>
			<span>结束时间</span>
			<input id="end" class="easyui-datebox" style="line-height:26px;border:1px solid #ccc" editable=false>
			<span>名字</span>
			<input id="name"  type="text" style="line-height:20px;border:1px solid #ccc">
			<span>上传用户</span>
			<input id="owner"  type="text"  style="line-height:20px;border:1px solid #ccc">
			<span>是否被打包</span>
			<input id="packed"  type="checkbox"  style="line-height:20px;border:1px solid #ccc">
			
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch()">搜索</a>
			
		</div>
	</div>
	

		<div id="dd" class="easyui-dialog"  style="top:100px;padding:5px;width:600px;height:300px;"
			title="添加识别码" iconCls="icon-ok"
			 closed="true" modal="true">
			 
		<form id="ff"  name="ff"  enctype= "multipart/form-data"  method="post">
	   <table   style="width:100%; font-size: 12px;font-weight: normal">
	   		
	   <tr>
	   <td  class="td1" >
	         名字:
	   </td>
	   <td style="width:70%"><input class="easyui-validatebox textbox" type="text" name ="code.name" required="true" validtype="remote['checkName','codeName']" invalidMessage="识别码名已存在"></td>
	   </tr>
	   
	   <%-- <tr>
	   <td class="td1">
	         是否有效
	   </td>
	   <td>
	   <s:checkbox name="code.effective"></s:checkbox>
	   </td>
	   </tr> --%>
	   
	   
	   
	  <tr>
      	<td class="td1">
               <label for="Attachment_GUID">图片上传：</label>
           </td>
           <td>                            
               <div>
        
                    
                  <input class="easyui-validatebox" type="hidden" id="Attachment_GUID" name="Attachment_GUID" />
                  <input type="hidden"  id="urls" name="code.url" />
                   <input id="file_upload" name="file" type="file" multiple="multiple">

                   <a href="javascript:void(0)" class="easyui-linkbutton" id="btnUpload" data-options="plain:true,iconCls:'icon-save'"
                       onclick="javascript: $('#file_upload').uploadify('upload', '*')">上传</a>
                       
                   <a href="javascript:void(0)" class="easyui-linkbutton" id="btnCancelUpload" data-options="plain:true,iconCls:'icon-cancel'"
                       onclick="javascript: $('#file_upload').uploadify('cancel', '*')">取消</a>
                   
                         当前有图片 <label id="count">0 </label>张
                   <div id="fileQueue" class="fileQueue"></div>
                       
                 
                   <div id="div_files"></div>
             
                   <br />
               </div>
     
           </td>
       </tr>


	   <tr>
	   <td style="text-align:right" colspan=2> <input  type="submit" value="提交"></td>
	   </tr>
	  
	   </table> </form>
	   </div>

<div id="dU" class="easyui-dialog" style="padding:5px;width:600px;height:300px;"
			title="编辑识别码" iconCls="icon-ok"
			 closed="true" modal="true">
			 
	<form id="fU"  name="fU"  enctype= "multipart/form-data"  method="post">
	   <table   style="width:100%; font-size: 12px;font-weight: normal">
	   
	   <input type="hidden"  id="id" name="id" />	
	   <input type="hidden"  id="id" name="owner" />	
	   <tr>
	   <td  class="td1" >
	         名字:
	   </td>
	   <td style="width:70%"><input id="editname" class="easyui-validatebox textbox" type="text" name ="name" required="true" ></td>
	   </tr>
	
	  <tr>
      	<td class="td1">
               <label for="Attachment_GUID">图片上传：</label>
           </td>
           <td>                            
               <div>
        
                    
                  <input class="easyui-validatebox" type="hidden" id="Attachment_GUID" name="Attachment_GUID" />
                  <input type="hidden"  id="up_url" name="url" />
                   <input id="up_file_upload" name="file" type="file" multiple="multiple">

                   <a href="javascript:void(0)" class="easyui-linkbutton" id="btnUpload" data-options="plain:true,iconCls:'icon-save'"
                       onclick="javascript: $('#up_file_upload').uploadify('upload', '*')">上传</a>
                       
                   <a href="javascript:void(0)" class="easyui-linkbutton" id="btnCancelUpload" data-options="plain:true,iconCls:'icon-cancel'"
                       onclick="javascript: $('#up_file_upload').uploadify('cancel', '*')">取消</a>
                   
                         当前有图片 <label id="up_count">0 </label>张
                   <div id="up_fileQueue" class="fileQueue"></div>
                       
                 
                   <div id="div_files"></div>
             
                   <br />
               </div>
     
           </td>
       </tr>


	   <tr>
	   <td style="text-align:right" colspan=2> <input  type="submit" value="提交"></td>
	   </tr>
	  
	   </table> </form>
  	</div>
</body>
</html>
