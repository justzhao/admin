
	 var _menus = {"menus":[
						{"menuid":"1","icon":"icon-sys","menuname":"系统管理",
							"menus":[{"menuname":"菜单管理","icon":"icon-nav","url":"test.jsp"},
									{"menuname":"添加用户","icon":"icon-add","url":""},
									{"menuname":"用户管理","icon":"icon-users","url":""},
									{"menuname":"角色管理","icon":"icon-role","url":""},
									{"menuname":"权限设置","icon":"icon-set","url":""},
									{"menuname":"系统日志","icon":"icon-log","url":""}
								]
						},{"menuid":"2","icon":"icon-sys","menuname":"轮播图管理",
							"menus":[{"menuname":"查看轮播图","icon":"icon-nav","url":"listPic.jsp"},
									{"menuname":"添加轮播图","icon":"icon-add","url":"addPic.jsp"}
								]
						},{"menuid":"3","icon":"icon-sys","menuname":"识别码管理",
							"menus":[{"menuname":"查看识别码","icon":"icon-nav","url":"listCode.jsp"},
									{"menuname":"部门列表","icon":"icon-nav","url":""}
								]
						},{"menuid":"4","icon":"icon-sys","menuname":"模型管理",
							"menus":[{"menuname":"查看模型","icon":"icon-nav","url":"listModel.jsp"},
									{"menuname":"报表统计","icon":"icon-nav","url":"demo1.html"},
									{"menuname":"添加支出","icon":"icon-nav","url":"demo.html"}
								]
						},{"menuid":"5","icon":"icon-sys","menuname":"数据包管理",
							"menus":[{"menuname":"查看数据包","icon":"icon-nav","url":"/shop/productcatagory.aspx"},
									{"menuname":"商品列表","icon":"icon-nav","url":"/shop/product.aspx"},
									{"menuname":"商品订单","icon":"icon-nav","url":"/shop/orders.aspx"}
								]
						}
				]};
        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        }
        //关闭登录窗口
        function close() {
            $('#w').window('close');
        }

        

        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');

            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }

            $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
                msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
                $newpass.val('');
                $rePass.val('');
                close();
            })
            
        }

        $(function() {

            openPwd();
            //
            $('#editpass').click(function() {
                $('#w').window('open');
            });

            $('#btnEp').click(function() {
                serverLogin();
            })

           

            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

                    if (r) {
                        location.href = '/ajax/loginout.ashx';
                    }
                });

            })
			
			
			
        });
		
		

