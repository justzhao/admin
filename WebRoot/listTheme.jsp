
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


<script type="text/javascript" src="js/theme.js"></script>




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


<th field="thumb" width="100"  formatter="formatUrls" >缩略图</th>
<th field="up" width="100"  formatter="formatUrls" >大图分割图上</th>
<th field="footer" width="100"  formatter="formatUrls" >大图分割图下</th>
<th field="word" width="100"  formatter="formatUrls" >大图分割图文字部分</th>
<th field="packet" width="150"  formatter="formatPacket" > 当前对应数据包</th>


<th field="owner" width="100"  >上传用户</th>
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

			  		<span>上传用户</span>
     <input id="operator"  type="text"  style="line-height:20px;border:1px solid #ccc">
     

		<a href="#" class="easyui-linkbutton"  iconCls="icon-search" plain="true" onclick="doSearch()">搜索</a>
	  </div>
	</div>
	


		<div id="dd" class="easyui-dialog" style="top:100px;padding:5px;width:600px;height:300px;"
			title="添加主题图片组" iconCls="icon-ok"
			 closed="true" modal="true">
			  <form id="ff"    name="ff"  enctype= "multipart/form-data"  method="post">
	   <table   style="width:100%; font-size: 12px;font-weight: normal">

	   <tr>
	   <td  class="td1" >
	   名字:
	   </td>
	   <td style="width:70%"><input   class="easyui-validatebox textbox"  type="text"   name ="theme.name"  required="true"></td>
	   </tr>
	   	
	   	   	           <tr>
                        <td class="td1">
                           主题缩略图上传：
                        </td>
                        <td>                            
                           <a href="javascript:void(0)" class="a-upload">
                                   	    <input type="file" name="thumb"  accept="image/gif, image/jpeg"  required="true" /> 点击这里上传文件
                              </a>
                 </td>
                    </tr>
                    
                       	    <tr>
      <td class="td1">
                  主题大图分割图上半部：
    </td>
     <td>   
     
             <a href="javascript:void(0)" class="a-upload">
      <input type="file" name="thumbUp"   id="thumbUp"  accept="image/gif, image/jpeg"  required="true" /> 点击这里上传文件 
      
      </a>
     </td>
     </tr>
     
     
     	    <tr>
      <td class="td1">
                主题大图分割图下半部：
    </td>
     <td>    <a href="javascript:void(0)" class="a-upload">
       <input type="file" name="thumbFooter"  id="thumbFooter" accept="image/gif, image/jpeg"  required="true"/> 
        点击这里上传文件 
      
      </a>
     </td>
     </tr>
     
     	    <tr>
      <td class="td1">
                主题大图分割图文字部分：
    </td>
     <td>   
      <a href="javascript:void(0)" class="a-upload">  
      <input type="file" name="thumbWord"  id="thumbWord"  accept="image/gif, image/jpeg"  required="true" />  点击这里上传文件
      </a>
     </td>
     </tr>
		   <tr>
	   <td style="text-align:right" colspan=2> 
	   
	      <input  type="button" onclick="cancelTheme()"  class="subbtn" value="取消">
	   <input  type="submit"   class="subbtn"   value="提交">
	   
	   
	   </td>
	   </tr>
	   </table>   
	   
	   
	   </form>
</div>




		<div id="edit" class="easyui-dialog" style="top:100px;padding:5px;width:600px;height:300px;"
			title="编辑主题组" iconCls="icon-ok"
			 closed="true" modal="true">
			  <form id="editff"    name="ff"  enctype= "multipart/form-data"  method="post">
	   <table   style="width:100%; font-size: 12px;font-weight: normal">
      
        <input id="editid"  type="hidden"  name="theme.id" />
        <input id="editth" type="hidden" name="theme.thumb"/>
          <input id="editup" type="hidden" name="theme.up"/>
          <input id="editfo" type="hidden" name="theme.footer"/>
        <input id="editwo" type="hidden" name="theme.word"/>
      <input id="editowner"  type="hidden"  name="theme.owner" />
       <input id="editpacket"  type="hidden"  name="theme.packet.id" />
	   <tr>
	   <td  class="td1" >
	   名字:
	   </td>
	   <td style="width:70%">
	   
	   <input  id="editname"  class="easyui-validatebox textbox"  type="text"   name ="theme.name"  required="true"></td>
	   </tr>

	   	
	   	   	           <tr>
                        <td class="td1">
                           主题缩略图上传：
                        </td>
                        <td>                            
                           <a href="javascript:void(0)" class="a-upload">
                                   	    <input type="file" name="thumb"  accept="image/gif, image/jpeg" /> 点击这里上传文件
                              </a>
                              
                              <a href="" id="thumburl" target="_blank" > 点击查看当前图片</a>
                              
                 </td>
                    </tr>
                    
                       	    <tr>
      <td class="td1">
                  主题大图分割图上半部：
    </td>
     <td>   
     
             <a href="javascript:void(0)" class="a-upload">
      <input type="file" name="thumbUp"   id="thumbUp"  accept="image/gif, image/jpeg"  /> 点击这里上传文件 
      
      </a>
           <a href="" id="upurl"  target="_blank" > 点击查看当前图片</a>
     </td>
     </tr>
     
     
     	    <tr>
      <td class="td1">
                主题大图分割图下半部：
    </td>
     <td>    <a href="javascript:void(0)" class="a-upload">
       <input type="file" name="thumbFooter"  id="thumbFooter" accept="image/gif, image/jpeg"  /> 
        点击这里上传文件 
      
      </a>
      
           <a href="" id="footerurl" target="_blank" > 点击查看当前图片</a>
     </td>
     </tr>
     
     	    <tr>
      <td class="td1">
                主题大图分割图文字部分：
    </td>
     <td>   
      <a href="javascript:void(0)" class="a-upload">  
      <input type="file" name="thumbWord"  id="thumbWord"  accept="image/gif, image/jpeg"  />  点击这里上传文件
      </a>
      
           <a href="" id="wordurl" target="_blank" > 点击查看当前图片</a>
     </td>
     </tr>
	   
	   
	   <tr>
	   <td style="text-align:right" colspan=2> 
	   <input  type="button" onclick="cancelTheme()"  class="subbtn" value="取消">
	   
	   <input  type="submit"   class="subbtn"   value="提交">
	   
	   </td>
	   </tr>
	
	   </table>   
	   
	   
	   </form>
</div>



		   
		   
</body>
</html>