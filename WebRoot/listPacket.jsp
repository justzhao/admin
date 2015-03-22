`
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
			
				    
				             document.getElementById("modelsid").value=$('#model').combobox('getValues');
				            },
				            onSelect:function(data)
				            {
				           // alert(data.id);
				            }
                }); 
            $("#tt").datagrid({
       		 
       		 	url:'getPagePacket'
       		 	
          });
          
          	  $("#tt").datagrid('hideColumn', 'urls'); 
          	  $("#tt").datagrid('hideColumn', 'xml'); 
          	  $("#tt").datagrid('hideColumn', 'thumbPic'); 
          	  $("#tt").datagrid('hideColumn', 'descPic'); 
			$('#editff').form({
				url:'updatePacket',
				onSubmit:function(){
				     if($(this).form('validate')==true)
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
					$.messager.alert('消息', "操作成功", 'info',function(){
					
					 $.messager.progress('close');
					   $('#tt').datagrid('reload');
					$(".panel-tool-close").click();
				
					});
				}
			});
			


  
		});

		function editRow()
		{
		 var row = $('#tt').datagrid('getSelected');
		 
	
		 if(row)
		 {
		  document.getElementById("editid").value=row.id;
		  document.getElementById("editname").value=row.name;
		  document.getElementById("editinfo").value=row.info;
		  document.getElementById("editversion").value=row.version;
		  document.getElementById("editurl").value=row.url;
		  document.getElementById("editowner").value=row.owner;
	       $('#editCreate').datebox('setValue',row.createDate);
		  document.getElementById("editcount").value=row.count;
		 document.getElementById("editdesc").value=row.descPic;
		 document.getElementById("editthumb").value=row.thumbPic;
		 if(row.device==0)
		 {
		 document.getElementById("all").checked=true;
		 }else if(row.device==1)
		 {
		 document.getElementById("apple").checked=true;
		 }else if(row.device==2)
		 {
		 document.getElementById("android").checked=true;
		 }
		   
		 if(row.effective==false)
		 {
		 document.getElementById("editeff").checked=false;
		 }else
		 {
		 	 document.getElementById("editeff").checked=true;
		 }
		 
		 
		  $('#edit').dialog('open');
		 }else
		 {
		   alert('请先选择一个数据');
		 }
		
		
		}
		
		function formatEffective(value)
		{
		 return value!=0?"是":"否";
		}
        function formatDevice(value)
        {
           if(value==0)
           {
           return '所有设备';
           }else if(value==1)
           {
            return '苹果';
           }else if(value==2)
           {
            return '安卓';
           }else
           {
            return '';
           }
        }
       

	    	function removeRow(){

         var row = $('#tt').datagrid('getSelected');
    if (row){
    
    
     $.messager.confirm("操作提示", "您确定要执行操作吗？", function (data) {
            if (data) {
               
               			 $.messager.progress({
					        title: '稍等',
					        msg: '正在操作中...',
					        text: '操作中'
					    });
               
                $.post("delPacket",{'packet.id':row.id},function(d){
                   if(d)
                   {
              $.messager.progress('close');
                      $('#tt').datagrid('reload');
                   }else{
                      $.messager.progress('close');
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



  var ck= document.getElementById("isTest").checked;

	$('#tt').datagrid('load',{
				'packet.name': $('#name').val(),
				'packet.createDate': $('#start').datebox('getValue'),
				'packet.endDate': $('#end').datebox('getValue'),
				'packet.owner':$('#owner').val(),
				'packet.count':($('#startcount').val()==''?0:$('#startcount').val()),
				'packet.endcount':($('#endcount').val()==''?0:$('#endcount').val()),
				'packet.effective':ck
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

	<table id="tt"   style="width:100%;height:400px" title="轮播图列表" iconCls="icon-save"   singleSelect="true"  pagination="true"  pageList="[5,10,15]"
			toolbar="#tb"  >
		<thead>
			<tr>
<th field="id" width="50">编号</th>
<th field="name" width="150">名字</th>
<th field="info" width="200">备注</th>

<th field="device" width="70"  formatter="formatDevice">针对设备</th>


<th field="owner"  width="100">上传用户</th>
<th field="urls" width="200">包路径</th>
<th field="xml" width="200">描述文件路径</th>
<th field="effective"   formatter="formatEffective"  width="100">是否测试版本</th>
<th field="createDate" width="100">上传日期</th>
<th field="thumbPic" width="200">缩略图</th>
<th field="version" width="100">版本</th>
<th field="count" width="70">下载数量</th>
<th field="descPic" width="200">说明文件</th>

			</tr>
		</thead>
	</table>
	<div id="tb">
	    <div>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:editRow()">编辑</a>
	         <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:removeRow()">删除</a>
 </div>
 <div>
 
   <span>上传开始时间</span>
	  			   <input   id="start"   style="width:100px"  class="easyui-datebox"  editable=false>
	  		<span>上传结束时间</span>
	  		
	  			   <input  id="end"    style="width:100px"  class="easyui-datebox"  editable=false >
	
	  		<span>名  &nbsp;&nbsp;            字</span>
        <input id="name"  type="text"  style="line-height:20px;border:1px solid #ccc">

    	
        <br>
          		<span>下载数量大于</span>	   
	  			   <input id="startcount"  class="easyui-numberbox" style="width:100px"/> 
	     	<span>下载数量小于</span>	   
	  			   <input id="endcount"  class="easyui-numberbox"  style="width:100px"/> 
	  			<span>上传用户</span>
        <input id="owner"  type="text"  style="line-height:20px;border:1px solid #ccc">

          		<span>是否测试版本</span>
        
         <input id="isTest"  type="checkbox" />	   
	  			   
		<a href="#" class="easyui-linkbutton"  iconCls="icon-search" plain="true" onclick="doSearch()">搜索</a>
 
 
 </div>

	</div>
	


		<div id="edit" class="easyui-dialog" style="top:80px;padding:5px;width:600px;height:300px;"
			title="编辑数据包" iconCls="icon-ok"
			 closed="true" modal="true">
		   		<form id="editff"  name="ff"  enctype= "multipart/form-data"  method="post">
	   <table   style="width:100%; font-size: 12px;font-weight: normal">
	
	   <tr>
	   <td  class="td1" >
	   名字:
	   </td>
	   <td style="width:70%">
	   <input id="editid"  name="packet.id"  type="hidden"/>
	   <input id="editurl"  name="packet.url"  type="hidden"/>
	   <input id="editowner"  name="packet.owner" type="hidden" />
	     <input type="hidden"   id="editthumb"  name="packet.thumbPic"/>
	     <input type="hidden"  id="editcount" name="packet.count"/>
	       <input type="hidden"  id="editdesc" name="packet.descPic"/>
	   <input id="editname"   class="easyui-validatebox textbox"  type="text"   name ="packet.name"  required="true"></td>
	   </tr>
	   	   <tr>
	   <td class="td1">
	   备注
	   </td>
	   <td><input type="text"   id="editinfo"  class="easyui-validatebox textbox"  name="packet.info" ></td>
	   </tr>
	   

	   	   	 <tr>
	   <td class="td1">
	  针对设备
	   </td>
	   <td><input type="radio"  id="all"  name="packet.device" value="0">所有设备
	   <input type="radio"  id="apple" name="packet.device" value="1">苹果
	    <input type="radio"  id="android" name="packet.device" value="2">安卓
	   
	   </td>
	   </tr>
   	   	   <tr>
     <td class="td1">
	   是否测试版本
	   </td>
	   <td>
      <s:checkbox  id="editeff" name="packet.effective"></s:checkbox>
    </td>
	   </tr>
	   
	   	   	   <tr>
	   <td class="td1">
	  创建日期
	   </td>
	   <td><input  id="editCreate" type="text"  class="easyui-datebox"   name="packet.createDate"  editable=false></td>
	   </tr>
	   	  
	
	    <tr>
                        <td class="td1">
                            缩略图上传：
                        </td>
                        <td>                            
                                       
                                   	    <input type="file" name="thumb"  class=" easyui-validatebox textbox"  /> 
                              
                       
                          
                      
                
                  
                        </td>
                    </tr>
	   	 	   	   	   <tr>
	   <td class="td1">
	 版本号
	   </td>
	   <td>
	   <input id="editversion"  type="text"  class=" textbox"    name="packet.version" ></td>
	   </tr>
	   
	   	   	   	   
	   
	   <tr>
	   <td style="text-align:right" colspan=2> <input  type="submit" value="提交"></td>
	   </tr>
	   
	   </table></form>
</div>


		   
		   
</body>
</html>