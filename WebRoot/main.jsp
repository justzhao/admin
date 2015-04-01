<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head id="Head1">
    <title>口袋家居后台管理系统</title>
<META http-equiv="X-UA-Compatible" content="IE=8" > </META>
  <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    
 <link rel="stylesheet" href="css/default.css" type="text/css"></link>
<link rel="stylesheet" href="js/jquery-easyui-1.4.2/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="js/jquery-easyui-1.4.2/themes/icon.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>


<script type="text/javascript" src="js/main.js"></script>



<script>





</script>


<style type="text/css">

a {
color: #385170; text-decoration:none  !important;
}
a:HOVER {
color:#385170;

}

.nav-item{

background: url("images/btn.jpg") no-repeat;
width:200px;
height:30px;
text-align: center;
padding-top:13px;
}
.nav-item:HOVER {
	background: url("images/btnon.jpg") no-repeat;
width:200px;
height:30px;
text-align: center;
padding-top:13px;
}


</style>
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div>

</noscript>




<div class="easyui-layout" data-options="fit:true" style="width:100%;height:450px;">


<div data-options="region:'north',split:true" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head"><s:property value="#request.user.name" /> <a href="#" style="color:white;" id="editpass">修改密码</a> <a href="#"  style="color:white;" id="loginOut">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; "><img src="images/blocks.gif" width="20" height="20" align="absmiddle" /> 欢迎使用口袋家居</span>
</div>

<div data-options="region:'west',split:true" title="导航菜单" style="width:208px; height:800px" id="west" >
 

  
  <div id="aa"    class="easyui-accordion"    fit="true" border="false">
	
			  			<s:iterator value="#request.Menus"  id="v">
				  			<div title="<s:property value="#v.text" />" data-options="iconCls:'icon-save'" style="overflow: auto;  font-size:20px">
		
				  			  <s:iterator value="#v.children"  id="vv">
			  	   	      
			  	   	      <div class="nav-item">
			  	   	      
                        <a href="javascript:addTab('<s:property value="#vv.text" />','<s:property value="#vv.id" />')">
          
                          <span style="font-size:16px;font-weight:bold"  ><s:property value="#vv.text" /></span>
                        </a>
                  
                    </div>
			  	   	      
			  	   	      	
			  	   	      	</s:iterator>
			
				  			     </div>
				  			</s:iterator>
		
		


				
				
			</div>
</div>
<div data-options="region:'south'" style="height: 30px; background: #D2E0F2; ">
<div class="footer">By 成都锐丽元科技科技有限公司 Email:17171146@qq.com</div>
</div>
<div   id="mainPanle" data-options="region:'center'" style="padding:10px">



<div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用,双击此处关闭所有选项" style="padding:20px;overflow:hidden;" id="home">
				
			<h1>欢迎使用口袋家居</h1>

			</div>
		</div>
</div>
</div>

    
    
    <!--修改密码窗口-->
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="Password"  /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="Password"  /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton"  onclick="javascript:changePass()" >
                    确定</a> <a class="easyui-linkbutton" href="javascript:void(0)"
                        onclick="closeLogin()">取消</a>
            </div>
        </div>
    </div>
<!--
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
  -->

</body>
</html>
