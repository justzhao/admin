var MyValidator = function() {  
    var handleSubmit = function() {  
        $('.form-horizontal').validate({  
            errorElement : 'span',  
            errorClass : 'help-block',  
            focusInvalid : false,  
            rules : {  
            	"user.name" : {  
                    required : true  
                },  
               "user.pass" : {  
                    required : true  
                } 
            },  
            messages : {  
                "user.name" : {  
                    required: "必填字段"  
                },  
                "user.pass" : {  
                    required: "必填字段"  
                }               
            },  
  
            highlight : function(element) {  
                $(element).closest('.form-group').addClass('has-error');  
            },  
  
            success : function(label) {  
                label.closest('.form-group').removeClass('has-error');  
                label.remove();  
            },  
  
            errorPlacement : function(error, element) {  
                element.parent('div').append(error);  
            },  
  
            submitHandler : function(form) {  
                form.submit();  
            }  
        });  
  
        $('.form-horizontal input').keypress(function(e) {  
            if (e.which == 13) {  
                if ($('.form-horizontal').validate().form()) {  
                    $('.form-horizontal').submit();  
                }  
                return false;  
            }  
        });  
    };  
    return {  
        init : function() {  
            handleSubmit();  
        }  
    };  
  
}();  
