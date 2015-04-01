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
		                 required:true,
				        onLoadSuccess: function (data) {
				            if (data) {
				               $('#code').combobox('setValue',data[0].id);
				            }
				            }
					}); 
				
				
				
			//	searchcode 
				$('#searchcode').combobox({
					
	                 data:data
	       
			
				});
				
				//theme
				
				
				$('#theme').combobox({
					
	                 url:'getThemeList',
	                 required:true,
				      onLoadSuccess: function (data) {
				            if (data) {
				               $('#theme').combobox('setValue',data[0].id);
				            }
				            }
	       
			
				});
		  		});

               
			

      
      

			$('#ff').form({
				url:'saveModel',
				onSubmit:function(){

					
					
					if($(this).form('validate'))
					{
					   
					  // $('#code').combobox('getValue'),
					   
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
							$('#ff').form('clear');
							
							
							$(".panel-tool-close").click();
						     $('#tt').datagrid('reload'); 
							});
			    	 }else
			    		 {
			    		  $.messager.alert("消息", "操作失败！","error");
			    		 }
			     

			}
			});
			
			//themeff
			
			$('#themeff').form({
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
				     $('#theme').combobox('reload'); 
				     
				     if(data==true||data=='true')
				     {
					$.messager.alert('消息', "操作成功", 'info',function(){
					
			
			
						  $('#themediv').dialog('close');
					$('#themeff').form('clear');
					

				  
					});
				}else{
					  $.messager.alert("消息", "操作失败！","error");
				}
				     
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
					  if(data==true||data=='true')
						  {
							$('#packet').form('clear');
							
							$('#theme').combobox('reload'); 
							$.messager.alert('消息', "操作成功", 'info',function(){
							$('#tt').datagrid('reload'); 
							
							$(".panel-tool-close").click();
						
							});
						  
						  }else
							  {
							  $.messager.alert("消息", "操作失败！","error");
							  }
					

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
    var msg='';
    if(row.packaged==false)
     {
       msg='您确定要执行删除吗？';
     }
     else
     {
      msg='此模型已经被打包，您确定要删除吗？';
     }
     $.messager.confirm("操作提示", msg, function (data) {
            if (data) {
            			   $.messager.progress({
						 title: '稍等',
						 msg: '正在操作中...',
						text: '操作中'
						 });
               
                $.post("delModel",{'ids':parm},function(d){
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
    	  $.messager.alert("消息", "没有选择数据！","error");
    }

}

function doSearch()
{



  var codeid=$('#searchcode').combobox('getValue')!=''?$('#searchcode').combobox('getValue'):0;
  
	$('#tt').datagrid('load',{
				'model.name': $('#name').val(),
				'model.createDate': $('#start').datebox('getValue'),
				'model.endDate': $('#end').datebox('getValue'),
				'model.owner':$('#owner').val(),
				'model.searchFlag':$('#packed').combobox('getValue'),
				'model.code.id': codeid
			
			});

}
function addTheme()
{
	//alert('fasdfasdf');
  $('#themediv').dialog('open');

}
function editRow()
{
   var row = $('#tt').datagrid('getSelected');
    if (row){
    
       
      document.getElementById("editid").value=row.id;
    
     document.getElementById("editurl").value=row.url;
     document.getElementById("editname").value=row.name;
     document.getElementById("editcreateDate").value=row.createDate;

//	 $('#editcreateDate').datebox('setValue',row.createDate);
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

    
    }else
    {
    
    	  $.messager.alert("消息", "没有选择数据！","error");
    }

}
function addZip()
{


  var rows = $('#tt').datagrid('getSelections'); 
   var row = $('#tt').datagrid('getSelected');
 
  if(row)
  {
  
			  $.messager.progress({
			        title: '稍等',
			        msg: '正在生成数据包...',
			        text: '操作中'
			    }); 
  
  
      
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
            	   $.messager.progress('close');
                   if(d!='')
                   {
                     // alert(rows.length);
                	   document.getElementById("modelcount").innerHTML='当前选择了'+rows.length+'个模型包'; 
                        document.getElementById("packetname").value=d;
                        document.getElementById("packeturl").value=d;
                        document.getElementById("modelids").value=parm;
                       
                          $('#tt').datagrid('reload'); 
                        $('#addPacket').dialog('open');
               
                   }else{
                   
                	   $.messager.alert("消息", "操作失败,可能有不存在的数据包！","error");
                   }
                });

  }else
  {
	  $.messager.alert("消息", "没有选择数据！","error");
  
  
  
  }
}

function cancel()
{
	$(".panel-tool-close").click();
	$('#ff').form('clear');
	$('#editform').form('clear');
}
function cancelPacket()
{
	$(".panel-tool-close").click();
	//需要删除掉已经下载的包.
	var ids=document.getElementById("modelids").value;
	$.post("cancelZip",{'ids':ids},function(d){
		
		
		
		
	});
	
	
	$('#packet').form('clear');
}

	