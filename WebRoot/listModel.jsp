<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%

String path = request.getContextPath();

String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE html>
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

	<script type="text/javascript" src="js/model.js"></script>



</head>
<body>
 
	<table id="tt"  style="width:100%;height:450px"	title="模型列表"   iconCls="icon-save"     pagination="true"  pageList="[5,10,15]"  
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
	  <div  style="margin-top: 10px;margin-bottom: 10px">
	  		<span>上传开始时间</span>
	  			   <input   id="start"   style="width:100px"  class="easyui-datebox"  editable=false >
	  		<span>结束时间</span>
	  		
	  			   <input  id="end"    style="width:100px"  class="easyui-datebox"  editable=false>
	  		<span>名字</span>
        <input id="name"  type="text"  style="line-height:20px;border:1px solid #ccc">

    		<span>上传用户</span>
        <input id="owner"  type="text"  style="line-height:20px;border:1px solid #ccc">

</div>
<div style="margin-top: 10px;margin-bottom: 10px"">

          		<span>是否被打包</span>
        	<input type="radio" name="packed" value="0" > 否
			<input type="radio" name="packed" value="1"> 是
			<input type="radio" name="packed" value="2" checked> 全部 


<span>识别码</span>
		<input id="searchcode" style="width:150px"  name ="model.code.id"  valueField="id" textField="name"   >
		<a href="#" class="easyui-linkbutton"  iconCls="icon-search" plain="true" onclick="doSearch()">搜索</a>
	  </div>
	</div>
	


		<div id="dd"  class="opear easyui-dialog"
			title="添加模型" iconCls="icon-ok"
			 closed="true" modal="true">
			 
	 		<form id="ff"  name="ff"  enctype= "multipart/form-data"  method="post">	
	   <table   style="width:100%; font-size: 14px;font-weight: normal">
	  
	   <tr>
	     <td  class="td1" >名字:</td><td style="width:70%">
	     <input  class="easyui-validatebox textbox"   type="text"   name ="model.name"  required="true"   validtype="remote['checkModelName','model.name']" invalidMessage="用户名已存在">
	     
	    </td>
	   </tr>
	   	  
	   	   

	   	   <tr>
	   	   <td class="td1">  识别码：</td>
	   <td>

  	<input id="code" style="width:150px"  name ="model.code.id"  valueField="id" textField="name"   >
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
	   <!-- class="easyui-validatebox "  required="true"   -->
	    <a href="javascript:void(0)" class="a-upload">
	    <input type="file" name="file"   id="modelfile"  class="easyui-validatebox "  required="true"  />点击这里上传文件
	    </a>
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
	   
	  
	   	
	   <tr><td style="text-align:right" colspan=2> 
	   
	   <input  type="button" onclick="cancel()"  class="subbtn" value="取消">
	   <input   type="submit"  class="subbtn"   value="提交">
	   
	   </td> 
	   </tr>  
	  
	   </table> </form>
</div>

<!-- 编辑模型 -->
<div id="edit" class="opear easyui-dialog" 
			title="编辑模型" iconCls="icon-ok"
			 closed="true" modal="true">
			 
	 		<form id="editform"  name="edit"  enctype= "multipart/form-data"  method="post">	
	   <table   style="width:100%; font-size: 12px;font-weight: normal">
	  
	   <tr>
	   <input id="editid" type="hidden"  name="model.id" />
	   <input id="editurl" type="hidden" name="model.url" />
	    <input id="editowner" type="hidden" name="model.owner" />
	    <input id="editcreateDate"  type="hidden" name="model.createDate" />
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
	   
	    <a href="javascript:void(0)" class="a-upload"> 
	    <input id=editfile  type="file" name="file"  class="easyui-validatebox "   /> 点击这里上传文件
	    </a>
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
	   
	  
	   	
	   <tr><td style="text-align:right" colspan=2> 
	   <input  type="button" onclick="cancel()"  class="subbtn" value="取消">
	   <input   type="submit"  class="subbtn"   value="提交"></td> 
	   </tr>  
	  
	   </table> </form>
</div>


<div id="addPacket" class="opear easyui-dialog"  
			title="添加数据包" iconCls="icon-ok"
			 closed="true" modal="true">
<form id="packet"  name="packet"  enctype= "multipart/form-data"  method="post">
	   <table   style="width:100%; font-size: 12px;font-weight: normal">
	
	   <tr>
	   <td  class="td1" >
	   名字:
	   </td>
	   <td style="width:70%">
	   
	   <input id="packetname"   class="easyui-validatebox textbox"  type="text"  style="width:250px"  name ="packet.name"  required="true">
	    <span id="modelcount" style="color:red"> </span>
	   </td>
	   
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
                  主题缩略图：
    </td>
     <td>  
                   <a href="javascript:void(0)" class="a-upload">
       <input type="file" name="thumb"   accept="image/gif, image/jpeg"  class="easyui-validatebox "  required="true"   /> 点击这里上传文件
        </a>
     </td>
     </tr>
     
     	    <tr>
      <td class="td1">
                  主题大图分割图上半部：
    </td>
     <td>   
     
             <a href="javascript:void(0)" class="a-upload">
      <input type="file" name="thumbUp"   id="thumbUp"  accept="image/gif, image/jpeg"  class="easyui-validatebox "  required="true"  /> 点击这里上传文件 
      
      </a>
     </td>
     </tr>
     
     
     	    <tr>
      <td class="td1">
                主题大图分割图下半部：
    </td>
     <td>    <a href="javascript:void(0)" class="a-upload">
       <input type="file" name="thumbFooter"  id="thumbFooter" accept="image/gif, image/jpeg" class="easyui-validatebox "  required="true"    /> 
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
      <input type="file" name="thumbWord"  id="thumbWord"  accept="image/gif, image/jpeg"   class="easyui-validatebox "  required="true"   />  点击这里上传文件
      </a>
     </td>
     </tr>
	   	 
	   	      	    <tr>
      <td class="td1">
                人物：
    </td>
     <td>   
      <a href="javascript:void(0)" class="a-upload">  
      <input type="file" name="character"  id="character"  accept="image/gif, image/jpeg" class="easyui-validatebox "  required="true"     />  点击这里上传文件
      </a>
     </td>
     </tr>
	   	 	   	      
	  	    <tr>
		      <td class="td1">
		                人物相关背景图：
		    </td>
		     <td>   
		      <a href="javascript:void(0)" class="a-upload">  
		      <input type="file" name="background"  id="background"   accept="image/gif, image/jpeg"   class="easyui-validatebox "  required="true" />  点击这里上传文件
		      </a>
		     </td>
     </tr>
	   	 
	   	 	   	   	  
	   	 	   	   	  
	   	 <tr>
	   <td class="td1">
	 版本号
	   </td>
	   <td><input type="text"  class=" easyui-numberbox textbox"   style="width:250px"  name="packet.version"  required="true"  precision="2"   ></td>
	   </tr>
	   
	   	   	   	   <tr>
	   <td class="td1">
	  说明文件
	   </td>
	   <td> 
	   
	        <a href="javascript:void(0)" class="a-upload"> 
	    <input   type="file" name="desc"  id="desc" class="easyui-validatebox "  required="true"  /> 
	   点击这里上传文件</a>
	   
	   </td>
	   </tr>
	   
	   <tr>
	   <td style="text-align:right" colspan=2> 
	   
	   <input  type="button" onclick="cancelPacket()"  class="subbtn" value="取消">
	   <input  type="submit"  class="subbtn"   value="提交"></td>
	   </tr>
	   
	   </table></form>
</div>

		   
		   
</body>
</html>