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

//blog

//A_BLOG
var  $A_BLOG = headpath +"/a/blog";
var  $A_BLOG_NEW_EDIT = headpath +"/a/blog/new/edit";
var  $A_BLOG_EDIT_ADD = headpath +"/a/blog/new/add";
var  $A_BLOG_EDIT_UPDATE =headpath+"/a/blog/edit";
var  $A_BLOG_EDIT_DELETE =headpath+"/a/blog/delete";
var  $A_BLOG_EDIT_QUERY_LIST =headpath+"/a/blog/query_list";


//AA_BLOG_CATEGORY_MANAGE
var  $A_BLOG_CATEGORY_MANAGE_QUERY="/a/blog/category/query";





//用语
var SUCCESS = "提示";
var WARN    ="警告";
var ERROR   ="error";





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
//用于表单      AJax 提交  |Ajax提交返回结果
//@param  param1    请求地址
//@param  param2    请求数据 json
//@param  param3    提交方式 POST|GET|PUT|DELET|UPDATE
//@return result    请求返回结果集 通常是json 数据格式 |常用结果 |:0|1|other
//@Author Along     2017-8-9
function global_AjaxGetView(param1,param2,param3){
	var result =null;
	$.ajax({
        cache: true,
        type: param3,
        url:param1,
        data:param2,
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


//
//对结果集的 几种处理方式
//1.弹窗提示结果并刷新 页面 |global_windowShowResultAndReloadView
//@param1 结果集 result |
//@param2 String    | 提示内容
function global_windowShowResultComfirm(param1,param2){
	if(param1[0]=='0'){
		$.messager.confirm(SUCCESS,param2,function(comfirm){
		});
	} else if(param1[0]=='1') {
		$.messager.alert(WARN,param1[1]);
	} else{
		$.messager.alert(ERROR+param1[0],param1[1]);
	}
}
