
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
	<!-- 
	<script type="text/javascript" src="http://www.jeasyui.net/Public/js/easyui/jquery.easyui.min.js"></script>
	-->
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>


	<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script> 
	<script type="text/javascript" src="./js/uploadify/jquery.uploadify.min.js"></script>
	
	
	<script type="text/javascript">
		$(function(){
		 $("#tt").datagrid({
		 url:'getPagePic'
		 });
		
		 $("#tt").datagrid('hideColumn', 'urls'); 

         

			$('#ff').form({
				url:'saveScroll',
				onSubmit:function(){
					return $(this).form('validate');
				},
				success:function(data){
					$.messager.alert('消息', "操作成功", 'info',function(){
					
					
					$(".panel-tool-close").click();
					$('#ff').form('clear')
				  $('#tt').datagrid('reload');
				  
					});
				}
			});
			
			
						$('#editff').form({
				url:'updateScroll',
				onSubmit:function(){
					return $(this).form('validate');
				},
				success:function(data){
					$.messager.alert('消息', "操作成功", 'info',function(){
					
					
					$(".panel-tool-close").click();
					//$('#ff').form('clear')
				  $('#tt').datagrid('reload');
				  
					});
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
         //   alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
        },
        //检测FLASH失败调用
        'onFallback':function(){
            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
        },
        //上传到服务器，服务器返回相应信息到data里
        'onUploadSuccess':function(file, data, response){
        // data 就是需要保存的 变量；

 
           document.getElementById("urls").value=data;
           count=document.getElementById("count").innerHTML;
           document.getElementById("count").innerHTML=Number(count)+1;
        }
            });
            
            
                              $('#edit_file_upload').uploadify({
                'swf': './js/uploadify/uploadify.swf',  //FLash文件路径
                'buttonText': '浏  览',                                 //按钮文本
                'debug' : false,
                'uploader': 'addPic',                       //处理文件上传Action
                 'fileObjName':'file',
               ' cancelImg' : './js/uploadify/uploadify-cancel.png',
                'queueID': 'editfileQueue',                        //队列的ID
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
           // alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
        },
        //检测FLASH失败调用
        'onFallback':function(){
            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
        },
        //上传到服务器，服务器返回相应信息到data里
        'onUploadSuccess':function(file, data, response){
        // data 就是需要保存的 变量；

 
           document.getElementById("editurls").value=data;
           count=document.getElementById("editcount").innerHTML;
           document.getElementById("editcount").innerHTML=Number(count)+1;
        }
            });
  
		});

		
		
		function formatEffective(value)
		{
		 return value!=0?"是":"否";
		}

   function editRow()
   {
   
   $('#editff').form('clear')
         var row = $('#tt').datagrid('getSelected');
           if (row){
          document.getElementById("editid").value=row.id;
          document.getElementById("editurls").value=row.urls;
          document.getElementById("editname").value=row.name;
          document.getElementById("editinfo").value=row.info;//
            document.getElementById("editdate").value=row.createDate;
          if(row.effective==true)
          {
            document.getElementById("editeff").checked=true;
          }
          if(row.testPage==true)
          {
            document.getElementById("edittest").checked=true;
          }
          


       $('#edit').dialog('open');
         }
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
		function doSearch()
		{
				$('#tt').datagrid('load',{
				'pic.name': $('#name').val(),
				'pic.createDate': $('#start').datebox('getValue'),
				'pic.endDate': $('#end').datebox('getValue'),//operator
				'pic.owner':$('#operator').val()
				
			});
				
		
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

	<table id="tt" style="width:100%;height:400px"	title="首页图列表" iconCls="icon-save"   singleSelect="true"  pagination="true"  pageList="[5,10,15]"
			toolbar="#tb"  >
		<thead>
			<tr>
<th field="id" width="50">编号</th>
<th field="name" width="100">名字</th>
<th field="info" width="300">备注</th>

<th field="urls" width="200">图片</th>
<th field="effective"   formatter="formatEffective"  width="100">是否有效</th>
<th field="testPage"   formatter="formatEffective"  width="100">是否测试页</th>
<th field="createDate"   width="100"  >上传时间</th>
<th field="owner"   width="100"  >上传用户</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
	    <div>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dd').dialog('open')">添加</a>
		
        <a href="#" class="easyui-linkbutton" iconcls="icon-edit" plain="true"   onclick="editRow()" >修改</a>
	         <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:removeRow()">删除</a>
	  </div>
	  <div>
	  <span>上传开始时间</span>
	  			   <input   id="start"   style="width:100px"  class="easyui-datebox" >
	  		<span>结束时间</span>
	  		
	  			   <input  id="end"    style="width:100px"  class="easyui-datebox"  >
	  		<span>名字</span>
      <input id="name"  type="text"  style="line-height:20px;border:1px solid #ccc">
			  		<span>上传用户</span>
     <input id="operator"  type="text"  style="line-height:20px;border:1px solid #ccc">
		<a href="#" class="easyui-linkbutton"  iconCls="icon-search" plain="true" onclick="doSearch()">搜索</a>
	  </div>
	</div>
	


		<div id="dd" class="easyui-dialog" style="top:100px;padding:5px;width:600px;height:300px;"
			title="添加首页图" iconCls="icon-ok"
			 closed="true" modal="true">
			  <form id="ff"    name="ff"  enctype= "multipart/form-data"  method="post">
	   <table   style="width:100%; font-size: 12px;font-weight: normal">

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
	   <td><input type="text"   name="pic.info" ></td>
	   </tr>
	   	   <tr>
	   <td class="td1">
	   是否有效
	   </td>
	   <td>
	   
	   <s:checkbox name="pic.effective"></s:checkbox>


	 
	   
	   </td>
	   </tr>
	   
	   	   	   <tr>
	   <td class="td1">
	   是否为测试页
	   </td>
	   <td>
	   
	   <s:checkbox name="pic.testPage"></s:checkbox>


	 
	   
	   </td>
	   </tr>
	   
	   
	   	  
	
	    <tr>
                        <td class="td1">
                            <label for="Attachment_GUID">图片上传：</label>
                        </td>
                        <td>                            
                            <div>
                     
                            
       
                  
           
                               <input type="hidden"  id="urls" name="pic.urls" />
                                <input id="file_upload"  name="file"   type="file" multiple="multiple">
       
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnUpload" data-options="plain:true,iconCls:'icon-save'"
                                    onclick="javascript: $('#file_upload').uploadify('upload', '*')">上传</a>
                                    
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnCancelUpload" data-options="plain:true,iconCls:'icon-cancel'"
                                    onclick="javascript: $('#file_upload').uploadify('cancel', '*')">取消</a>
                                
                                      当前有图片 <label id="count">0 </label>张
                                <div id="fileQueue" class="fileQueue"></div>
                                    
                              
                              
                          
                                <br />
                            </div>
                  
                        </td>
                    </tr>
	   
	   
	   <tr>
	   <td style="text-align:right" colspan=2> <input  type="submit" value="提交"></td>
	   </tr>
	
	   </table>   
	   
	   
	   </form>
</div>




		<div id="edit" class="easyui-dialog" style="top:100px;padding:5px;width:600px;height:300px;"
			title="编辑首页图" iconCls="icon-ok"
			 closed="true" modal="true">
			  <form id="editff"    name="ff"  enctype= "multipart/form-data"  method="post">
	   <table   style="width:100%; font-size: 12px;font-weight: normal">
      
        <input id="editid"  type="hidden"  name="pic.id" />
        <input id="editdate" type="hidden" name="pic.createDate"/>

	   <tr>
	   <td  class="td1" >
	   名字:
	   </td>
	   <td style="width:70%">
	   
	   <input  id="editname"  class="easyui-validatebox textbox"  type="text"   name ="pic.name"  required="true"></td>
	   </tr>
	   	   <tr>
	   <td class="td1">
	   备注
	   </td>
	   <td><input id="editinfo" type="text"   name="pic.info" ></td>
	   </tr>
	   	   <tr>
	   <td class="td1">
	   是否有效
	   </td>
	   <td>
	   
	   <s:checkbox id="editeff" name="pic.effective"></s:checkbox>


	 
	   
	   </td>
	   </tr>
	   
	   	   	   <tr>
	   <td class="td1">
	   是否为测试页
	   </td>
	   <td>
	   
	   <s:checkbox  id="edittest" name="pic.testPage"></s:checkbox>


	 
	   
	   </td>
	   </tr>
	   
	   
	   	  
	
	    <tr>
                        <td class="td1">
                            <label for="Attachment_GUID">图片上传：</label>
                        </td>
                        <td>                            
                            <div>
                     
                            
       
                  
           
                               <input type="hidden"  id="editurls" name="pic.urls" />
                                <input id="edit_file_upload"  name="file"   type="file" multiple="multiple">
       
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnUpload" data-options="plain:true,iconCls:'icon-save'"
                                    onclick="javascript: $('#edit_file_upload').uploadify('upload', '*')">上传</a>
                                    
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnCancelUpload" data-options="plain:true,iconCls:'icon-cancel'"
                                    onclick="javascript: $('#edit_file_upload').uploadify('cancel', '*')">取消</a>
                                
                                      当前有图片 <label id="editcount">0 </label>张
                                <div id="editfileQueue" class="fileQueue"></div>
                                    
                              
                              
                          
                                <br />
                            </div>
                  
                        </td>
                    </tr>
	   
	   
	   <tr>
	   <td style="text-align:right" colspan=2> <input  type="submit" value="提交"></td>
	   </tr>
	
	   </table>   
	   
	   
	   </form>
</div>



		   
		   
</body>
</html>