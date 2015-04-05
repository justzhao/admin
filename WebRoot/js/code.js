	
var URL='http://7x00ae.com1.z0.glb.clouddn.com/';

$(function(){
			
			$('#ff').form({
				url:'saveCode',
				onSubmit:function(){
				
			
					if($(this).form('validate'))
					{
					
					if(document.getElementById("urls").value!='')
					
					{
					   $.messager.progress({
						 title: '稍等',
						 msg: '正在操作中...',
						text: '操作中'
						 });
					 return true;
					}else
					{
					  $.messager.alert("消息", "选择一张图片！","error");
					return false;
					}
					
					}else
					{
					
					return false;
					}
				},
				success:function(data){
				
				    $.messager.progress('close');
				    
				    if(data==true||data=='true')
				    {
				      $.messager.alert('消息', "操作成功", 'info',function(){
                  	   $('#tt').datagrid('reload');
					$(".panel-tool-close").click();
				
					});
				    }
				    else
				    {
				         $.messager.alert("消息", "操作失败！","error");
				    }

				}
			});
			

		 var urls='';
		 var count=0;
		 
            
            
         $('#file_upload').uploadify({
	         'swf': './js/uploadify/uploadify.swf',  //FLash文件路径
	         'buttonText': '浏  览',                                 //按钮文本
	         'debug' : false,
	         'uploader': 'addCode',                       //处理文件上传Action
	         'fileObjName':'file',
	         'cancelImg' : './js/uploadify/uploadify-cancel.png',
	         'queueID': 'fileQueue',                        //队列的ID
	         'queueSizeLimit': 1,                          //队列最多可上传文件数量，默认为999
	         'auto': false,                                 //选择文件后是否自动上传，默认为true
	         'multi': false,                                 //是否为多选，默认为true
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

	           
	             document.getElementById("urls").value=data; //
	             count=document.getElementById("count").innerHTML;
	             document.getElementById("count").innerHTML=Number(count)+1;
   			 }
            });
            
        var url='';   
        var up_count=0;    
  		$('#up_file_upload').uploadify({
	         'swf': './js/uploadify/uploadify.swf',  //FLash文件路径
	         'buttonText': '浏  览',                                 //按钮文本
	         'debug' : false,
	         'uploader': 'addCode',                       //处理文件上传Action
	         'fileObjName':'file',
	         'cancelImg' : './js/uploadify/uploadify-cancel.png',
	         'queueID': 'up_fileQueue',                        //队列的ID
	         'queueSizeLimit': 1,                          //队列最多可上传文件数量，默认为999
	         'auto': false,                                 //选择文件后是否自动上传，默认为true
	         'multi': false,                                 //是否为多选，默认为true
	         'removeCompleted': true,                       //是否完成后移除序列，默认为true
	         'fileSizeLimit': '1000MB',                       //单个文件大小，0为无限制，可接受KB,MB,GB等单位的字符串值
	         'fileTypeDesc': 'Image Files',                 //文件描述
	         'fileTypeExts': '*.gif; *.jpg; *.png; *.bmp',  //上传的文件后缀过滤器
	         
	         'onSelect' : function(file) {
	                  
        	 },
        	 
	         'onUploadError' : function(file, errorCode, errorMsg, errorString) {
	            //alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
	         },
	         
	         //检测FLASH失败调用
	         'onFallback':function(){
	            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
	         },
	         
        	//上传到服务器，服务器返回相应信息到data里
	         'onUploadSuccess':function(file, data, response){

	           
	             document.getElementById("up_url").value=data; //
	             count=document.getElementById("up_count").innerHTML;
	             document.getElementById("up_count").innerHTML=1;
   			 }
            });
  		
		});
	    function doSearch(){

 var temp = document.getElementsByName("packed");



		    $('#tt').datagrid('load',{
				'code.name': $('#name').val(),
				'code.createDate': $('#start').datebox('getValue'),
				'code.endDate': $('#end').datebox('getValue'),
				'code.owner': $('#owner').val(),
			   	'code.searchFlag': $('#packed').combobox('getValue')
				
			});
	    }
	    
		function formatPacked(value)
		{
			return value!=0?"是":"否";
		}
		
		//http://7x00ae.com1.z0.glb.clouddn.com/code_20150323232449.png
		function formatUrl	(value)
		{
		var a='<a href=\"'+URL+value+ '\" target=\"_blank\">'+'点击查看'+'</a>';
		return   a;
		}
		
		function newCode() {
			$('#ff').form('clear');
			document.getElementById("count").innerHTML=0;
			$('#dd').dialog('open').dialog('setTitle','添加识别码');
		}
		
		function deltCode() {
            var row = $('#tt').datagrid('getSelected');
            //alert('row id  '+row.id);
            if (row) {
         
                var msg='';
                if(row.packed==false)
                {
                 msg='是否要删除识别码?';
                }else
                {
                msg='该识别码已经被打包，是否要删除?';
                }
            
            
                $.messager.confirm('操作提示', msg, function (r) {
                    if (r) {
                    
                     $.messager.progress({
							 title: '稍等',
							 msg: '正在操作中...',
							text: '操作中'
							 });
                        $.post('deleteCode', { id: row.id }, function (result) {
                            $.messager.progress('close');
                            if (result==true||result=='true') {
                                $('#tt').datagrid('reload');    // reload the user data 
                                $.messager.alert("操作提示", "操作成功","info");
                                
                            } else {
                                $.messager.show({   // show error message 
                                    title: 'info',
                                    msg: '删除失败'
                                });
                            }
                        });
                    } 
                });
            } else{
   
                $.messager.alert("操作提示", "请选择要删除的识别码","error");
            }
            
        }  
        
        function editCode() {
	    	var row = $('#tt').datagrid('getSelected');
		    if (row){
			    $('#dU').dialog('open').dialog('setTitle','编辑识别码');
			    $('#fU').form('load',row);
                 document.getElementById("codeurl").href=URL+row.url;
			   

			    
		    } else{
            	
                $.messager.alert("操作提示", "请选择要修改的识别码","error");
            }
        }
        
        $(function(){
	        $('#fU').form({
					url:'updateCode',
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
				 
				 if(data==true||data=='true')
				 {
				 		$.messager.alert('Info', "操作成功", 'info',function(){
							
							 	$(".panel-tool-close").click();
					      	$('#tt').datagrid('reload');
						});
				 }
				 else
				 {
				   $.messager.alert("消息", "操作失败！","error");
				 }

						
					}
				});
		});
        
        function cancel()
        {
        	$('#ff').form('clear');
        	$('#fU').form('clear');
        	$(".panel-tool-close").click();
        }
