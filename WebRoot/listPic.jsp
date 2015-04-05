
  <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%

String path = request.getContextPath();

String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



<link rel="stylesheet" href="js/jquery-easyui-1.4.2/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="js/jquery-easyui-1.4.2/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="css/mycss.css" type="text/css"></link>
<link rel="stylesheet" href="js/uploadify/uploadify.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/uploadify/jquery.uploadify.min.js"></script>


<script type="text/javascript" src="js/pic.js"></script>




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

	<table id="tt" style="width:100%;height:450px"	title="首页图列表" iconCls="icon-save"   singleSelect="true"  pagination="true"  pageList="[5,10,15]"
			toolbar="#tb"  >
		<thead>
			<tr>
<th field="id" width="50">编号</th>
<th field="name" width="100">名字</th>
<th field="info" width="200">备注</th>

<th field="urls" width="100"  formatter="formatUrls" >图片</th>
<th field="effective"   formatter="formatEffective"  width="150">是否有效(客户端显示的)</th>
<th field="testPage"   formatter="formatEffective"  width="100">是否测试页</th>
<th field="createDate"   width="100"  >上传时间</th>
<th field="owner"   width="100"  >上传用户</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
	    <div>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dd').dialog('open')">添加</a>
		
        <a href="#" class="easyui-linkbutton" iconcls="icon-edit" plain="true"   onclick="editRow()" >编辑</a>
	         <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:removeRow()">删除</a>
	  </div>
	  <div style="margin-top: 10px;margin-bottom: 10px">
	  <span>上传开始时间</span>
	  			   <input   id="start"   style="width:100px"  class="easyui-datebox"  editable=false >
	  		<span>结束时间</span>
	  		
	  			   <input  id="end"    style="width:100px"  class="easyui-datebox"  editable=false>
	  		<span>名字</span>
      <input id="name"  type="text"  style="line-height:20px;border:1px solid #ccc">
			  		<span>上传用户</span>
     <input id="operator"  type="text"  style="line-height:20px;border:1px solid #ccc">
		<a href="#" class="easyui-linkbutton"  iconCls="icon-search" plain="true" onclick="doSearch()">搜索</a>
	  </div>
	</div>
	


		<div id="dd" class="easyui-dialog" style="top:40px;padding:5px;width:700px;height:450px;"
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
                                
                                      当前有图片 <label id="count" name="count" >0 </label>张
                                <div id="fileQueue" class="fileQueue"></div>
                                    
              
                              
                          
                                <br />
                            </div>
                  
                        </td>
                    </tr>
	   
	   
	   <tr>
	   <td style="text-align:right" colspan=2> <input  type="submit"   class="subbtn"    value="提交"></td>
	   </tr>
	
	   </table>   
	   
	   
	   </form>
</div>




		<div id="edit" class="easyui-dialog" style="top:40px;padding:5px;width:700px;height:450px;"
			title="编辑首页图" iconCls="icon-ok"
			 closed="true" modal="true">
			  <form id="editff"    name="ff"  enctype= "multipart/form-data"  method="post">
	   <table   style="width:100%; font-size: 12px;font-weight: normal">
      
        <input id="editid"  type="hidden"  name="pic.id" />
        <input id="editdate" type="hidden" name="pic.createDate"/>
      <input id="editowner"  type="hidden"  name="pic.owner" />
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
                                           <a href=""   id="picurl"  target="_blank">点击查看当前图片 </a>
                                <div id="editfileQueue" class="fileQueue"></div>
                                    
                 
                                         
            
                          
                                <br />
                            </div>
                  
                        </td>
                    </tr>
	   
	   
	   <tr>
	   <td style="text-align:right" colspan=2> <input  type="submit"   class="subbtn"   value="提交"></td>
	   </tr>
	
	   </table>   
	   
	   
	   </form>
</div>



		   
		   
</body>
</html>