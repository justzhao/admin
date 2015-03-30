	$(function(){
	
	  		$('#ff').form({
			url:'saveUser',
			onSubmit:function(){
			
			if($(this).form('validate')) {
			   
		 		$.messager.progress({
					title: '稍等',
					msg: '正在操作中...',
					text: '操作中'
				});
			
			    return true;
			    
			}else {
			
		    	return false;
			}
			
			},
			
			success:function(data){
	
	     		$.messager.progress('close');
	     		$('#ff').form('clear');
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
		
		 $("#tt").datagrid('hideColumn', 'pass');
	
	});
		function doSearch(){
		   
		 var roleid=$('#searchrole').combobox('getValue');
		 if(roleid=='')
		 {
		 roleid=0;
		 }
		    
		    $('#tt').datagrid('load',{
				'user.name': $('#name').val(),
				'user.role.id':roleid
				
			});
	    }
	    
		function formatRole(value){
		
			if(value!=null){  //
				return value.name;
			}
		}
		
		function newUser() {
			
			
			$('#dd').dialog('open').dialog('setTitle','添加用户');
			//$('#edit').dialog('open').dialog('setTitle','编辑用户');
			return true;
		}
		

       
        $.post("getRoleList",function(data){
		  		
	
     
			$('#role').combobox({
			
            	data:data,
            	required:true,
         		onLoadSuccess: function (data) {
         
	            	if (data) {
	           
	                	$('#role').combobox('setValue',data[0].id);
	            	}
            	}
			}); 
		  		
	  		$('#editrole').combobox({
		       required:true,
           		data:data,
           		onLoadSuccess: function (data) {
         
	            	if (data) {
	           
	                	$('#role').combobox('setValue',data[0].id);
	            	}
            	}
			}); 
			
			//searchrole
					  		
	  		$('#searchrole').combobox({

           		data:data,

			}); 
		  		
  		});
  		
		function editUser(){
			var row = $('#tt').datagrid('getSelected');
		    if (row){
		    
		    	$('#editrole').combobox('setValue',row.role.id);
		    	
		    	document.getElementById("editname").value=row.name;
		    //document.getElementById("editpass").value=row.pass
		    	document.getElementById("editid").value=row.id;
		    	document.getElementById("editcreateDate").value=row.createDate;
		    	
			    $('#edit').dialog('open').dialog('setTitle','编辑用户');
			    //$('#editform').form('load',row);
			    
		    } else{
            	
                $.messager.alert("操作提示", "请选择要修改的用户","error");
            }
		}
		$(function(){
		$('#editform').form({
			
			url:'updateUser',
			onSubmit:function(){
		 		if($(this).form('validate')) {
		 			
		 		
		 			$.messager.progress({
				        title: '稍等',
				        msg: '正在操作中...',
				        text: '操作中'
			    	});
			    
		 			return true;
		 		
		 		}else {
		 			return false;
		 		}
			},
			success:function(data){
				$.messager.progress('close');
				     if(data==true||data=='true'){
				    
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
       });
       
       function deltUser() {
           var row = $('#tt').datagrid('getSelected');
           
		   if (row) {
                $.messager.confirm('操作提示', '是否要删除用户?', function (r) {
                    if (r) {
                        $.post('deleteUser', { 'user.id': row.id }, function (data) {
                        
                   
                               if(data==true||data=='true'){
                               
                                $('#tt').datagrid('reload');  
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
            	
                $.messager.alert("操作提示", "请选择要删除的用户","error");
            }
       }
       
       
       function cancel()
       {
       	$(".panel-tool-close").click();
    	$('#ff').form('clear');
    	$('#editform').form('clear');
       }

       