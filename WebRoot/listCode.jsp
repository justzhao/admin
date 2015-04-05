
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

<script type="text/javascript" src="js/code.js"></script>



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

	<table id="tt" class="easyui-datagrid" style="width:100%;height:450px"
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

<th field="url"   formatter="formatUrl	"  width="250">图片</th>

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
                onclick="editCode()" >编辑</a>
		
		<a href="#" class="easyui-linkbutton" iconcls="icon-remove" plain="true" onclick="deltCode();">删除</a>
		</div>
		
		<div  style="margin-top: 10px;margin-bottom: 10px">
		
			<span>开始时间</span>
			<input id="start" class="easyui-datebox" style="line-height:26px;border:1px solid #ccc" editable=false>
			<span>结束时间</span>
			<input id="end" class="easyui-datebox" style="line-height:26px;border:1px solid #ccc" editable=false>
			<span>名字</span>
			<input id="name"  type="text" style="line-height:20px;border:1px solid #ccc">
			<span>上传用户</span>
			<input id="owner"  type="text"  style="line-height:20px;border:1px solid #ccc">
			<span>是否被打包</span>
			<!--  
			<input type="radio" name="packed" value="0" > 否
			<input type="radio" name="packed" value="1"> 是
			<input type="radio" name="packed" value="2" checked> 全部 
-->
       <select id="packed" class="easyui-combobox"   style="width:60px;">
			    <option value="2">全部</option>
		         <option value="0">否</option> 
		         <option value="1">是</option>

      </select>


			<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch()">搜索</a>
			
		</div>
	</div>
	

		<div id="dd" class="easyui-dialog" style=" top:40px;padding:5px;width:700px;height:450px;"
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
	   <td style="text-align:right" colspan=2> 
	   
	   <input  type="button" onclick="cancel()"  class="subbtn" value="取消">
	   <input  type="submit"  class="subbtn" value="提交">
	   
	   
	   </td>
	   </tr>
	  
	   </table> </form>
	   </div>

<div id="dU" class=" easyui-dialog" style=" top:40px;padding:5px;width:700px;height:450px;"
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
                  <input type="hidden"  id="up_url" name="url"  class="easyui-validatebox textbox"  required="true" />
                   <input id="up_file_upload"    name="file" type="file" multiple="multiple"  >

                   <a href="javascript:void(0)" class="easyui-linkbutton" id="btnUpload" data-options="plain:true,iconCls:'icon-save'"
                       onclick="javascript: $('#up_file_upload').uploadify('upload', '*')">上传</a>
                       
                   <a href="javascript:void(0)" class="easyui-linkbutton" id="btnCancelUpload" data-options="plain:true,iconCls:'icon-cancel'"
                       onclick="javascript: $('#up_file_upload').uploadify('cancel', '*')">取消</a>
                   
                         当前有图片 <label id="up_count">0 </label>张
                         
                         
                         <a id="codeurl"   href=""  target="_blank"> 点击查看当前识别码</a>
                   <div id="up_fileQueue" class="fileQueue"></div>
                       
                 
                   <div id="div_files"></div>
             
                   <br />
               </div>
     
           </td>
       </tr>


	   <tr>
	   <td style="text-align:right" colspan=2> 
	   <input  type="button" onclick="cancel()"  class="subbtn" value="取消">
	   <input  type="submit"  class="subbtn"  value="提交"></td>
	   </tr>
	  
	   </table> </form>
  	</div>
</body>
</html>
