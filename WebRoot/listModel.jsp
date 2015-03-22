
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
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="./js/uploadify/jquery.uploadify.min.js"></script>
	
	
	<script type="text/javascript">
		$(function(){
       		 $("#tt").datagrid({
       		 
       		 	url:'getPageModel'
       		 	
       
		
		 });
		  $("#tt").datagrid('hideColumn', 'url'); 
		  
		  
		  		$.post("getCodeList",function(data){
		  		
		  		 $('#editcode').combobox({
				  data:data
	
              }); 
     
				$('#code').combobox({
				
		                 data:data,
				        onLoadSuccess: function (data) {
				            if (data) {
				               $('#code').combobox('setValue',data[0].id);
				            }
				            }
					}); 
		  		
		  		});

               
			

      
      

			$('#ff').form({
				url:'saveModel',
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
					
					$(".panel-tool-close").click();
				     $('#tt').datagrid('reload'); 
					});
			}
			});
			
			
			
			$('#editform').form({
				url:'updateModel',
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
					$(".panel-tool-close").click();
				    $('#tt').datagrid('reload'); 
					});
				}
			});
			
			
						$('#packet').form({
				url:'savePacket',
				onSubmit:function(){
					 if($(this).form('validate'))
					 {
					 $.messager.progress({
					        title: '稍等',
					        msg: '正在操作中...',
					        text: '操作中'
					    });
					  return true;
					 }else{
					 return false;
					 }
				},
				success:function(data){
				 $.messager.progress('close');
					$.messager.alert('消息', "操作成功", 'info',function(){
					
					
					$(".panel-tool-close").click();
				
					});
				}
			});
			
		});
		
		
	
      function formatCode(value)
      {

	   if(value!=undefined)
	      return value.name;
	      
	        
 	    }
	   function  formatAnimation(value)
	   {
	
	        return value!=0?"是":"否";
	    
	    }
	    
	    
	    	function removeRow(){

         var row = $('#tt').datagrid('getSelected');
         var rows = $('#tt').datagrid('getSelections'); 
         

         
    if (row){
    
    
                 $.each(rows, function (i, n) {

			if (i == 0) {
			
			parm = n.id;
			
			} else {
			
			parm += "," + n.id;
			
			}

          }); 
    
    
    //alert(parm);
     
     $.messager.confirm("操作提示", "您确定要执行操作吗？", function (data) {
            if (data) {
               
                $.post("delModel",{'ids':parm},function(d){
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

  var ck= document.getElementById("packaged").checked;
  
	$('#tt').datagrid('load',{
				'model.name': $('#name').val(),
				'model.createDate': $('#start').datebox('getValue'),
				'model.endDate': $('#end').datebox('getValue'),
				'model.owner':$('#owner').val(),
				'model.packaged':ck
			
			});

}

function editRow()
{
   var row = $('#tt').datagrid('getSelected');
    if (row){
    
       
      document.getElementById("editid").value=row.id;
    
     document.getElementById("editurl").value=row.url;
     document.getElementById("editname").value=row.name;

	 $('#editcreateDate').datebox('setValue',row.createDate);
	 $('#editsize').numberbox('setValue',row.size);
	 document.getElementById("editinfo").value=row.info;
	 document.getElementById("editowner").value=row.owner;
	 document.getElementById("editrotate").value=row.rotate;//editsize
      if(row.animation==false)
      {
      document.getElementById("editanimation").checked=false;
      }else
      {
     document.getElementById("editanimation").checked=true;
      }
       $('#editcode').combobox('setValue',row.code.id);

	 document.getElementById("editoffset").value=row.offset;
    $('#edit').dialog('open');
    /**
    
    $.get("getModelById",{'model.id':row.id},function(data){
    
   
      document.getElementById("editid").value=data.id;
    
     document.getElementById("editurl").value=data.url;
     document.getElementById("editname").value=data.name;

	 $('#editcreateDate').datebox('setValue',data.createDate);
	 $('#editsize').numberbox('setValue',data.size);
	 document.getElementById("editinfo").value=data.info;
	 document.getElementById("editrotate").value=data.rotate;//editsize
      if(data.animation==false)
      {
      document.getElementById("editanimation").checked=false;
      }else
      {
     document.getElementById("editanimation").checked=true;
      }
       $('#editcode').combobox('setValue',data.code.id);

	 document.getElementById("editoffset").value=data.offset;
    $('#edit').dialog('open');
 
 },"json");
    */
    
    }else
    {
    
        alert('没有选择数据');
    }

}
function addZip()
{

$.messager.progress({
        title: '稍等',
        msg: '正在生成数据包...',
        text: '操作中'
    }); 
  var rows = $('#tt').datagrid('getSelections'); 
   var row = $('#tt').datagrid('getSelected');
 
  if(row)
  {
  
      
                 $.each(rows, function (i, n) {

			if (i == 0) {
			
			parm = n.id;
			urls=n.url;
			
			} else {
			
			parm += "," + n.id;
			urls+=","+n.url;
			
			}

          }); 
          
               $.post("zipModels",{'ids':parm},function(d){
                   if(d!='')
                   {
                      //  alert(d);
                        document.getElementById("packetname").value=d;
                        document.getElementById("packeturl").value=d;
                        document.getElementById("modelids").value=parm;
                        $.messager.progress('close');
                        $('#addPacket').dialog('open');
               
                   }else{
                   
                    alert('打包失败');
                   }
                });

  }else
  {
  alert('没有选择要打包的模型');
  
  
  
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
 
	<table id="tt"  style="width:100%;height:400px"	title="模型列表"   iconCls="icon-save"     pagination="true"  pageList="[5,10,15]"  
		toolbar="#tb">
		<thead>
			<tr>

<th field="ck"  checkbox ="true"></th>
<th field="id" width="50">编号</th>
<th field="name"     width="100">名字</th>
<th field="code"  formatter="formatCode"  width="100">识别码</th>

<th field="info" width="200">备注</th>
<th field="url" width="200">文件</th>
<th field="animation"   formatter="formatAnimation"  width="100">是否有动画</th>
<th field="size" width="100">模型尺寸</th>
<th field="offset" width="100">模型位移</th>
<th field="rotate" width="100">模型旋转</th>
<th field="packaged"   formatter="formatAnimation"  width="100">是否被打包</th>
<th field="createDate" width="100">发布日期</th>
<th field="owner" width="100">上传用户</th>


			</tr>
		</thead>
		

	</table>
	<div id="tb">
	    <div>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dd').dialog('open')">添加</a>
		         <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:editRow()">编辑</a>
         <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:removeRow()">删除</a>
         
          <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:addZip()">生成数据包</a>

	  </div>
	  <div>
	  		<span>上传开始时间</span>
	  			   <input   id="start"   style="width:100px"  class="easyui-datebox"  editable=false >
	  		<span>结束时间</span>
	  		
	  			   <input  id="end"    style="width:100px"  class="easyui-datebox"  editable=false>
	  		<span>名字</span>
        <input id="name"  type="text"  style="line-height:20px;border:1px solid #ccc">

    		<span>上传用户</span>
        <input id="owner"  type="text"  style="line-height:20px;border:1px solid #ccc">

          		<span>是否被打包</span>
        
         <input id="packaged"  type="checkbox" />

		<a href="#" class="easyui-linkbutton"  iconCls="icon-search" plain="true" onclick="doSearch()">搜索</a>
	  </div>
	</div>
	


		<div id="dd" class="easyui-dialog" style="top:100px;padding:5px;width:600px;height:300px;"
			title="添加模型" iconCls="icon-ok"
			 closed="true" modal="true">
			 
	 		<form id="ff"  name="ff"  enctype= "multipart/form-data"  method="post">	
	   <table   style="width:100%; font-size: 12px;font-weight: normal">
	  
	   <tr>
	     <td  class="td1" >名字:</td><td style="width:70%">
	     <input  class="easyui-validatebox textbox"   type="text"   name ="model.name"  required="true"   validtype="remote['checkModelName','model.name']" invalidMessage="用户名已存在">
	    
	    </td>
	   </tr>
	   	  
	   	   

	   	   <tr>
	   	   <td class="td1">  识别码：</td>
	   <td>

  	<input id="code" style="width:150px"  name ="model.code.id"  valueField="id" textField="name">
	   </td>
	   </tr>

	   <tr>
	   <td class="td1">
	    发布时间
	   </td>
	   
	   <td>
	   <input type="text"  class="easyui-datebox"  name="model.createDate"  editable=false>
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
	    <input type="file" name="file"     class="easyui-validatebox textbox"     required="true"  />
	    
	   </td>
	   </tr>
	   	   	   <tr>
	   <td class="td1">
	 是否有动画：
	   </td>
	   <td>
	     <s:checkbox name="model.animation"></s:checkbox>


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
	  
	   </table> </form>
</div>

<!-- 编辑模型 -->
<div id="edit" class="easyui-dialog" style="top:100px;padding:5px;width:600px;height:300px;"
			title="编辑模型" iconCls="icon-ok"
			 closed="true" modal="true">
			 
	 		<form id="editform"  name="edit"  enctype= "multipart/form-data"  method="post">	
	   <table   style="width:100%; font-size: 12px;font-weight: normal">
	  
	   <tr>
	   <input id="editid" type="hidden"  name="model.id">
	   <input id="editurl" type="hidden" name="model.url">
	    <input id="editowner" type="hidden" name="model.owner">
	     <td  class="td1" >名字:</td><td style="width:70%">
	     <input  id="editname" class="easyui-validatebox textbox"   type="text"   name ="model.name"  required="true"  >
	    
	    </td>
	   </tr>
	   	  
	   	   

	   	   <tr>
	   	   <td class="td1">  识别码：</td>
	   <td>

  	<input   id="editcode"  style="width:150px"  name ="model.code.id"    valueField="id"  textField="name">
	   </td>
	   </tr>

	   <tr>
	   <td class="td1">
	    发布时间
	   </td>
	   
	   <td>
	   <input id="editcreateDate"     type="text"  class="easyui-datebox"  name="model.createDate"  editable=false >
	   </td>
	   </tr>
	   <tr>
	   <td class="td1">
	    备注：
	   </td>
	   <td>
	    <input  id="editinfo"  type="text"   name ="model.info" >
	   </td>
	   </tr>	
	   	   	   <tr>
	   <td class="td1">
	    模型：
	   </td>
	   <td>
	    <input id=editfile  type="file" name="file"  class="easyui-validatebox textbox"   />
	    
	   </td>
	   </tr>
	   	   	   <tr>
	   <td class="td1">
	 是否有动画：
	   </td>
	   <td>
	   <s:checkbox  id="editanimation"  name="model.animation"></s:checkbox>

	   </td>
	   </tr>
	   	   	   <tr>
	   <td class="td1">
	   尺寸：
	   </td>
	   <td>
	    <input  id="editsize"  type="text"   class=" easyui-numberbox textbox"   name="model.size"   required="true"  />
	   </td>
	   </tr>
	   
	  <tr>
	   <td class="td1">
	   位移：
	   </td>
	   <td>
	   <input  id="editoffset" type="text" name="model.offset"/>
	   </td>
	   </tr>
	   
	   	 <tr> <td class="td1"> 旋转：</td><td><input id="editrotate" type="text" name="model.rotate"   /></td>
	   </tr>
	   
	  
	   	
	   <tr><td style="text-align:right" colspan=2> <input   type="submit" value="提交"></td> 
	   </tr>  
	  
	   </table> </form>
</div>


<div id="addPacket" class="easyui-dialog" style="top:80px;padding:5px;width:600px;height:300px;"
			title="添加数据包" iconCls="icon-ok"
			 closed="true" modal="true">
<form id="packet"  name="packet"  enctype= "multipart/form-data"  method="post">
	   <table   style="width:100%; font-size: 12px;font-weight: normal">
	
	   <tr>
	   <td  class="td1" >
	   名字:
	   </td>
	   <td style="width:70%">
	   
	   <input id="packetname"   class="easyui-validatebox textbox"  type="text"  style="width:250px"  name ="packet.name"  required="true"></td>
	   
	   <input id="packeturl"  type="hidden"  name="packet.url" />
	   <input id="modelids"  type="hidden"  name="ids" />
	   </tr>

	   	   <tr>  
	   	   
	   	   
	   <td class="td1">
	   备注
	   </td>
	   <td><input type="text"  style="width:250px"  class="easyui-validatebox textbox"  name="packet.info" ></td>
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
	   <td><input type="text"  class="easyui-datebox"  name="packet.createDate" editable=false ></td>
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
	   <td><input type="text"  class=" textbox"   style="width:250px"  name="packet.version" ></td>
	   </tr>
	   
	   	   	   	   <tr>
	   <td class="td1">
	  说明文件
	   </td>
	   <td>  <input   type="file" name="desc"  class="easyui-validatebox textbox"  required="true"  /> 
	   </td>
	   </tr>
	   
	   <tr>
	   <td style="text-align:right" colspan=2> <input  type="submit" value="提交"></td>
	   </tr>
	   
	   </table></form>
</div>

		   
		   
</body>
</html>