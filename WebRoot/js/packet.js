		$(function(){
			
			/**
            				$('#model').combobox({
            				multiple:true,
            				editable:false,
                           onLoadSuccess: function (data) {
				            if (data) {
				               $('#model').combobox('setValue',data[0].id);
				            }
				            },
				            onChange:function(newValue,oldValue)
				            {
			
				    
				             document.getElementById("modelsid").value=$('#model').combobox('getValues');
				            },
				            onSelect:function(data)
				            {
				 
				            }edittheme
                }); */
            	
			$('#theme').combobox({

				
				url:'getThemeList'
	
				});


            				
            				
            				
            $("#tt").datagrid({
       		 
       		 	url:'getPagePacket'
       		 	
          });
          
          //	  $("#tt").datagrid('hideColumn', 'urls'); 
          	  $("#tt").datagrid('hideColumn', 'xml'); 
; 
          	  $("#tt").datagrid('hideColumn', 'descPic'); 
			$('#editff').form({
				url:'updatePacket',
				onSubmit:function(){
				     if($(this).form('validate')==true)
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
					
					
					  $('#theme').combobox('reload');
					 $.messager.progress('close');
					 if(data==true||data=='true')
						 {
							$.messager.alert('消息', "操作成功", 'info',function(){
								
							  
								   $('#tt').datagrid('reload');
								$(".panel-tool-close").click();
							
								});
						 }else
							 {
							 $.messager.alert("消息", "操作失败！","error");
							 $(".panel-tool-close").click();
							 }
					

				}
			});
			


  
		});

		function editRow()
		{
		 var row = $('#tt').datagrid('getSelected');
		 
	
		 if(row)
		 {
			 
	
		  document.getElementById("editid").value=row.id;
		  document.getElementById("editname").value=row.name;
		  document.getElementById("editinfo").value=row.info;
		  document.getElementById("editversion").value=row.version;
		  document.getElementById("editurl").value=row.url;
		  document.getElementById("editowner").value=row.owner;
	      document.getElementById("editCreate").value=row.createDate;
		  document.getElementById("editcount").value=row.count;
		 document.getElementById("editdesc").value=row.descPic;
		 /**
		 document.getElementById("editthumb").value=row.thumbPic;
		 document.getElementById("editthumbUp").value=row.thumbUp;
		 document.getElementById("editthumbFooter").value=row.thumbFooter;
		 document.getElementById("editthumbWord").value=row.thumbWord;
		 */

		 //getData
		 
		 var dd= $('#theme').combobox('getData');
		 dd.push({ "name": row.theme.name, "id": row.theme.id });
     // alert(dd.length);
		 $("#theme").combobox("loadData", dd); 
		 $("#theme").combobox("setValue",row.theme.id);
		 if(row.device==0)
		 {
		 document.getElementById("all").checked=true;
		 }else if(row.device==1)
		 {
		 document.getElementById("apple").checked=true;
		 }else if(row.device==2)
		 {
		 document.getElementById("android").checked=true;
		 }
		   
		 if(row.effective==false)
		 {
		 document.getElementById("editeff").checked=false;
		 }else
		 {
		 	 document.getElementById("editeff").checked=true;
		 }
		 
		 if(row.testPacket==false)
		 {
		 document.getElementById("edittest").checked=false;
		 }else
		 {
		 	 document.getElementById("edittest").checked=true;
		 }
		 
	
		  $('#edit').dialog('open');
		 }else
		 {
			 $.messager.alert("消息", "没有选择数据！","error");
		 }
		
		
		}
		function formatTheme(value)
		{
			if(value !=undefined)
		  {
				if(value.name==undefined)
					{
					  return '无';
					}
				return value.name
		  }
				
		}
		function formatEffective(value)
		{
		 return value!=0?"是":"否";
		}
        function formatDevice(value)
        {
           if(value==0)
           {
           return '所有设备';
           }else if(value==1)
           {
            return '苹果';
           }else if(value==2)
           {
            return '安卓';
           }else
           {
            return '';
           }
        }
        
        function formatUrl(value)
        {
        	var a='<a href=\"'+'http://7x00ae.com1.z0.glb.clouddn.com/'+value+ '\" target=\"_blank\">'+'点击查看'+'</a>';
		return   a;
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
               
                $.post("delPacket",{'packet.id':row.id},function(d){
                	   $.messager.progress('close');
                	   if(data==true||data=='true')
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



	$('#tt').datagrid('load',{
				'packet.name': $('#name').val(),
				'packet.createDate': $('#start').datebox('getValue'),
				'packet.endDate': $('#end').datebox('getValue'),
				'packet.owner':$('#owner').val(),
				'packet.count':($('#startcount').val()==''?0:$('#startcount').val()),
				'packet.endcount':($('#endcount').val()==''?0:$('#endcount').val()),
             	'packet.searchFlag': $('#isTest').combobox('getValue'),
             	'packet.effectiveFlag':$('#isEffective').combobox('getValue'),
             	'packet.device':$('#searchdevice').combobox('getValue')
	});

	}
function cancel()
{
	$(".panel-tool-close").click();
	$('#editff').form('clear');

}
		

