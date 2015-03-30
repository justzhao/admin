		$(function(){

			
			
	   $("#tt").datagrid({
		 url:'getPagePic'
		 });
		


         

			$('#ff').form({
				url:'saveScroll',
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
			//	alert(data);
				     $.messager.progress('close');
				     
				     
				     if(data==true||data=='true')
				     {
					$.messager.alert('消息', "操作成功", 'info',function(){
					
				
					$(".panel-tool-close").click();
					$('#ff').form('clear');
					 document.getElementById("count").innerHTML=0;
				  $('#tt').datagrid('reload');
				  
					});
				}else{
					  $.messager.alert("消息", "操作失败！","error");
				}
				     
				}
			});
			
			
						$('#editff').form({
				url:'updateScroll',
				onSubmit:function(){
					 if($(this).form('validate'))
					 {
					  $.messager.progress({
						 title: '稍等',
						 msg: '正在操作中...',
						text: '操作中'
						 });
					   return true;
					 }
				},
				success:function(data){
				   $.messager.progress('close');
				   if(data==true||data=='true')
				    	 {
							$.messager.alert('消息', "操作成功", 'info',function(){
								
								
								$(".panel-tool-close").click();
					
							  $('#tt').datagrid('reload');
							  
								});
				    	 
				    	 
				    	 }else
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
		
		function formatUrls(value)
		{
		
		var a='<a href=\"'+'http://7x00ae.com1.z0.glb.clouddn.com/'+value+ '\" target=\"_blank\">'+'点击查看'+'</a>';
		return   a;
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
            document.getElementById("editowner").value=row.owner;
            
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
	                  $.messager.progress({
									 title: '稍等',
									 msg: '正在操作中...',
									text: '操作中'
									 });
               
                $.post("deScrollPic",{'pic.id':row.id},function(d){
                

                  $.messager.progress('close');
                  if(d==true||d=='true')
                   {
           
                      $('#tt').datagrid('reload');
                   }else{
                	   
               
                	   $.messager.alert("消息", "操作失败！","error");
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