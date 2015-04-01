		$(function(){

			
			
	   $("#tt").datagrid({
		 url:'getPageTheme'
		 });
		


         

			$('#ff').form({
				url:'saveTheme',
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
					$.messager.alert('消息', "操作成功", 'info',function(){
					
				
					$(".panel-tool-close").click();
					$('#ff').form('clear');
					
				  $('#tt').datagrid('reload');
				  
					});
				}else{
					  $.messager.alert("消息", "操作失败！","error");
				}
				     
				}
			});
			
			
						$('#editff').form({
				url:'updateTheme',
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
				    		 $(".panel-tool-close").click();
				    		  $.messager.alert("消息", "操作失败！","error");
				    		 }
				   

				}
			});
			
			
			

 
  
		});

		
		
		function formatEffective(value)
		{
		 return value!=0?"是":"否";
		}
		
		function formatPacket(value)
		{
			if(value !=undefined)
				{
                if(value.name==undefined)
                	{
                	return '无';
                	}
				return value.name;
		}

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
          document.getElementById("editth").value=row.thumb;
          document.getElementById("editup").value=row.up;
          document.getElementById("editfo").value=row.footer;
            document.getElementById("editwo").value=row.word;
            document.getElementById("editowner").value=row.owner;
            document.getElementById("editname").value=row.name;
            document.getElementById("editpacket").value=row.packet.id;

          


       $('#edit').dialog('open');
         }
   }

	function removeRow(){
		   var row = $('#tt').datagrid('getSelected');
		   var msg='您确定要执行操作吗？';
		  // alert(row.packet.name);
		   if(row.packet.name!=undefined)
			   {
			   msg='此主题组已经和数据包关联，删除后，会自动删除对应数据包，您确定?'
			   }
		   
		   
		    if (row){
         	     $.messager.confirm("操作提示", msg, function (data) {
		            if (data) {
	                  $.messager.progress({
									 title: '稍等',
									 msg: '正在操作中...',
									text: '操作中'
									 });
               
                $.post("delTheme",{'theme.id':row.id},function(d){
                

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

				
				'theme.owner':$('#operator').val()
				
			});
				
		
		}
		
	function cancelTheme()
		{
			$(".panel-tool-close").click();


			
			
			$('#packet').form('clear');
}