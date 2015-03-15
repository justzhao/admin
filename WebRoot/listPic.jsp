
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
	<script type="text/javascript" src="http://www.jeasyui.net/Public/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="./js/uploadify/jquery.uploadify.min.js"></script>
	
	
	<script type="text/javascript">
		$(function(){

			$('#ff').form({
				url:'saveScroll',
				onSubmit:function(){
					return $(this).form('validate');
				},
				success:function(data){
					$.messager.alert('Info', "操作成功", 'info');
				}
			});
			

 var urls='';
 var count=0;
            
            
                  $('#file_upload').uploadify({
                'swf': './js/uploadify/uploadify.swf',  //FLash文件路径
                'buttonText': '浏  览',                                 //按钮文本
                'debug' : false,
                'uploader': 'addPic',                       //处理文件上传Action
                 'fileObjName':'file',
               ' cancelImg' : './js/uploadify/uploadify-cancel.png',
                'queueID': 'fileQueue',                        //队列的ID
                'queueSizeLimit': 10,                          //队列最多可上传文件数量，默认为999
                'auto': false,                                 //选择文件后是否自动上传，默认为true
                'multi': true,                                 //是否为多选，默认为true
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
        // data 就是需要保存的 变量；
           //alert(file+"dd"+data+":"+response);
           
            urls=document.getElementById("urls").value;
           //alert(urls);
           if(urls=='')
           {
             urls=data;
           }else
           {
           urls=urls+","+data;
           }
           document.getElementById("urls").value=urls;
           count=document.getElementById("count").innerHTML;
           document.getElementById("count").innerHTML=Number(count)+1;
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
		<!--  
		<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="javascript:alert('Cut')">Cut</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:alert('Save')">Save</a>
		-->
	</div>
	


		<div id="dd" class="easyui-dialog" style="padding:5px;width:600px;height:300px;"
			title="添加轮播图" iconCls="icon-ok"
			 closed="true" modal="true">
		
	   <table   style="width:100%; font-size: 12px;font-weight: normal">
	   		<form id="ff"  name="ff"  enctype= "multipart/form-data"  method="post">
	   <tr>
	   <td  class="td1" >
	   名字:
	   </td>
	   <td style="width:70%"><input   class="easyui-validatebox textbox"  type="text"   name ="pic.name"  required="true"></td>
	   </tr>
	   	   <tr>
	   <td class="td1">
	   备注
	   </td>
	   <td><input type="text"   name="pic.info" style="width:200px;height:40px"></td>
	   </tr>
	   	   <tr>
	   <td class="td1">
	   是否立即生效
	   </td>
	   <td>
	   
	 是   <input type="radio"  value="1" name="pic.effective">
	 否   <input type="radio"  value="0" name="pic.effective">
	 
	   
	   </td>
	   </tr>
	   
	   
	   	  
	
	    <tr>
                        <td class="td1">
                            <label for="Attachment_GUID">图片上传：</label>
                        </td>
                        <td>                            
                            <div>
                     
                            
       
                  
                                <input class="easyui-validatebox" type="hidden" id="Attachment_GUID" name="Attachment_GUID" />
                               <input type="hidden"  id="urls" name="pic.urls" />
                                <input id="file_upload"  name="file"   type="file" multiple="multiple">
       
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
	   </form>
	   </table>
</div>


		   
		   
</body>
</html>