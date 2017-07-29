//###########全局变量 定义##############//
var $aIdex = headpath+"/a/index";		//后台管理首页
var $aLogin  ="/a/login"; 				//后台登录
var $ajaxLogin =headpath+ "/ajaxLogin";	//后台 登录 地址(表单)




//用于表单      AJax 提交 
//@param  loginForm 表单名
//@param  loginUrl 表单 提交地址
//@Author Along 2017-7-29
function submitAjaxForm(loginForm,loginUrl){
	$.ajax({
        cache: true,
        type: 	"POST",
        url:	loginUrl,
        data:	loginForm.serialize(),// 你的formid
        async: false,
        error: function(request) {
            window.confirm("抱歉！ 服务器繁忙");
        },
        success: function(data) {
        	alert(data);
        	return data.split(":");
        }
    });
	return "3:no respone";
};