//###########全局变量 定义##############//
var $aIdex = headpath+"/a/index";		//后台管理首页
var $aLogin  ="/a/login"; 				//后台登录
var $ajaxLogin =headpath+ "/ajaxLogin";	//后台 登录 地址(表单)


var $addRole =headpath+"/a/role/add";
var $deleRole =headpath+"/a/role/dele";
var $updateRole =headpath+"/a/role/update";
var $updateRoleajx =headpath+"/a/role/updatea";

//permission
var  $A_PERMISSION_SEARCH =headpath+ "/a/permission/search";
var  $A_PERMISSION_ADD =   headpath+ "/a/permission/add";
var  $A_PERMISSION_UPDATE =headpath+"/a/permission/update";
var  $A_PERMISSION_DELETE =headpath+ "/a/permission/delete";
var  $A_PERMISSION_UPDATE_VIEW = headpath+"/a/permission/update_view";




//****
//刷新页面常用方法
//**刷新 数据表
function flush(window){
	
	window.reload();
}

function flush(){
	setTimeout(function(){
		window.location.reload();
	}, 500);
}

function flushDataGrid($id,time){
	setTimeout(function(){
		$id.datagrid('reload');
	}, time);
}
//用于表单      AJax 提交  |Ajax提交返回结果
//@param  loginForm 表单名
//@param  loginUrl 表单 提交地址
//@Author Along 2017-7-29
function submitAjaxForm(loginForm,loginUrl){
	var result=null;
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
        	result =data;
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
 * 
 * @param url 请求 地址
 * @param itemIds
 * @returns {String}
 */
function global_Ajax_delete(url,itemId){
	var result="";
	$.ajax({
        cache: true,
        type: "POST",
        url:url,
        data:{"data":itemId},// 你的formid
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
function global_AjaxGetView(url,data){
	var result =null;
	$.ajax({
        cache: true,
        type: "GET",
        url:url,
        data:data,
        async:false,
        error: function(request) {
            window.confirm("抱歉！ 服务器繁忙");
        },
        success: function(data) {
        	result = data;
        }
    });
	return result;
}

//获取选中 的数据表 字段 并返回单个结果
function global_getDatagridSelected($id){
	var row = $id.datagrid('getSelected');
	if (row){
		return row;
	}else{
		return null;
	}
}
//获取 所有选中内容
function global_getDatagridSelections($id){
	var rows = $id.datagrid('getSelections');
	//返回 数组
	
	return rows;
}
