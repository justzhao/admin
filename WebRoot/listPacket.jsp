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
<script type="text/javascript" src="js/packet.js"></script>
<style>

</style>
</head>
<body>
<table id="tt"   style="width:100%;height:450px" title="数据包列表" iconCls="icon-save"   singleSelect="true"  pagination="true"  pageList="[5,10,15]" toolbar="#tb"  >
<thead>
<tr>
<th field="id" width="50">编号</th>
<th field="name" width="150">名字</th>
<th field="info" width="200">备注</th>
<th field="device" width="70"  formatter="formatDevice">针对设备</th>
<th field="owner"  width="100">上传用户</th>
<th field="url" width="100"  formatter="formatUrl">数据包</th>
<!-- 
<th field="character" width="100"  formatter="formatUrl">人物角色形象图</th>
<th field="background" width="100"  formatter="formatUrl">人物背景图</th> -->
<th field="xml" width="200">描述文件路径</th>
<th field="testPacket"   formatter="formatEffective"  width="100">是否测试包</th>
<th field="effective"   formatter="formatEffective"  width="100">是否有效</th>
<th field="createDate" width="100">上传日期</th>
<th field="theme" formatter="formatTheme"  width="100">主题组名字</th>

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
 <div  style="margin-top: 10px;margin-bottom: 10px">
 
   <span>上传开始时间</span>
	  			   <input   id="start"   style="width:100px"  class="easyui-datebox"  editable=false>
	  		<span>上传结束时间</span>
	  		
	  			   <input  id="end"    style="width:100px"  class="easyui-datebox"  editable=false >
	
	  		<span>名  &nbsp;&nbsp;            字</span>
        <input id="name"  type="text"  style="line-height:20px;border:1px solid #ccc">

    	</div>
  <div  style="margin-top: 10px;margin-bottom: 10px">
          		<span>下载数量大于</span>	   
	  			   <input id="startcount"  class="easyui-numberbox" style="width:100px"/> 
	     	<span>下载数量小于</span>	   
	  			   <input id="endcount"  class="easyui-numberbox"  style="width:100px"/> 
	  			<span>上传用户</span>
        <input id="owner"  type="text"  style="line-height:20px;border:1px solid #ccc">
        
        </div>
  <div  style="margin-top: 10px;margin-bottom: 10px">
  
  <span>针对 &nbsp;&nbsp;    设备</span>

         <select id="searchdevice" class="easyui-combobox"   style="width:100px;">
        <option value="-1">全部</option> 
    <option value="0">所有设备</option>
    <option value="1">苹果</option>
    <option value="2">安卓</option>

    </select>
  
  
  
          		<span style="margin-left:10px">是否测试数据包</span>

	  <select id="isTest" class="easyui-combobox"   style="width:60px;">
	    <option value="2">全部</option>
        <option value="0">否</option> 
    <option value="1">是</option>

  </select>   
			
			
			        <span style="margin-left:50px">是否有效</span>

 <select id="isEffective" class="easyui-combobox"   style="width:60px;">
	    <option value="2">全部</option>
        <option value="0">否</option> 
    <option value="1">是</option>

  </select>	
			
	  			   
		<a href="#" class="easyui-linkbutton"  iconCls="icon-search" plain="true" onclick="doSearch()">搜索</a>
 
 
 </div>

	</div>
	


		<div id="edit" class="opear easyui-dialog" 
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

	   <!--  
	     <input type="hidden"   id="editthumb"  name="packet.thumbPic"/>
	      <input type="hidden"   id="editthumbUp"  name="packet.thumbUp"/>
	       <input type="hidden"   id="editthumbFooter"  name="packet.thumbFooter"/>
	        <input type="hidden"   id="editthumbWord"  name="packet.thumbWord"/>
	        -->
	        
	     <input type="hidden"  id="editcount" name="packet.count"/>
	       <input type="hidden"  id="editdesc" name="packet.descPic"/>
	       
	       <input   type="hidden"   id="editCreate"  name="packet.createDate"  />
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
	   是否有效
	   </td>
	   <td>
      <s:checkbox  id="editeff" name="packet.effective"></s:checkbox>
    </td>
	   </tr>
	   
	      	   	   <tr>
     <td class="td1">
	   是否测试
	   </td>
	   <td>
      <s:checkbox  id="edittest" name="packet.testPacket"></s:checkbox>
    </td>
	   </tr>
	   
	   <tr>
	   <td class="td1">
	     主题组
	    </td>
	    
	     <td>
	      
	 
	  <input id="theme" style="width:150px"  name =" packet.theme.id" valueField="id"  textField="name">
	 
	   
	    
	    </td>
	   </tr>
	   
	   
<!-- 
	           <tr>
                        <td class="td1">
                           主题缩略图上传：
                        </td>
                        <td>                            
                           <a href="javascript:void(0)" class="a-upload">
                                   	    <input type="file" name="thumb"  accept="image/gif, image/jpeg"  /> 点击这里上传文件
                              </a>
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
     </td>
     </tr>
     
     	    <tr>
      <td class="td1">
                主题大图分割图文字部分：
    </td>
     <td>   
      <a href="javascript:void(0)" class="a-upload">  
      <input type="file" name="thumbWord"  id="thumbWord"  accept="image/gif, image/jpeg"   />  点击这里上传文件
      </a>
     </td>
     </tr>  
                    
           -->          
                    
	   	 	   	   	   <tr>
	   <td class="td1">
	 版本号
	   </td>
	   <td>
	   <input id="editversion"  type="text"  class=" textbox"    name="packet.version" ></td>
	   </tr>
	   
	   	   	   	   
	   
	   <tr>
	   <td style="text-align:right" colspan=2> 
	   <input  type="button" onclick="cancel()"  class="subbtn" value="取消">
	   <input  type="submit"  class="subbtn"  value="提交">
	   
	   </td>
	   </tr>
	   
	   </table></form>
</div>


		   
		   
</body>
</html>