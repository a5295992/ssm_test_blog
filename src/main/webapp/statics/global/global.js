//###########全局变量 定义##############//
var $aIdex = headpath+"/a/index";		//后台管理首页
var $aLogin  ="/a/login"; 				//后台登录
var $ajaxLogin =headpath+ "/ajaxLogin";	//后台 登录 地址(表单)


var $addRole =headpath+"/a/role/add";
var $deleRole =headpath+"/a/role/dele";
var $updateRole =headpath+"/a/role/update";
var $updateRoleajx =headpath+"/a/role/updatea";
             




//用于表单      AJax 提交 
//@param  loginForm 表单名
//@param  loginUrl 表单 提交地址
//@Author Along 2017-7-29
function submitAjaxForm(loginForm,loginUrl){
	var result="";
	$.ajax({
        cache: true,
        type: "POST",
        url:loginUrl,
        data:loginForm.serialize(),// 你的formid
        async:false,
        error: function(request) {
            window.confirm("抱歉！ 服务器繁忙");
        },
        success: function(data) {
        	result =result+data;
        }
    });
	return result;
};

/**用于表单  AJax 删除 指定的 资源 
//@param  itemId 资源id
//@param  url  请求的地址
 */
function deleById(url,itemId){
	var result="";
	$.ajax({
        cache: true,
        type: "GET",
        url:url,
        data:itemId,// 你的formid
        async:false,
        error: function(request) {
            window.confirm("抱歉！ 服务器繁忙");
        },
        success: function(data) {
        	result =result+data;
        }
    });
	return result;
}

/**
 * 刷新 指定 window
 */

function flush(window){
	
	window.reload();
}

function flush(){
	setTimeout(function(){
		window.location.reload();
	}, 500);
}
