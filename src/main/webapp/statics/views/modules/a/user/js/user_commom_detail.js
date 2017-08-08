//关闭窗口
function closeWind(win){
	win.window('close');
}
var flushPage = function flushPage(){
	//刷新当前页面
	window.location.reload(true);
};
//用户权限
//增删改查
function openWin($winName){
	$winName.window('open');
}
//增删改查
function addRole(){
	$('#addRoleWin').window('open');//打开 窗口
}
function updateRole(){
	var row = $('#tttt').datagrid('getSelected');
	var selectRoleId = "";
	if (row){
		selectRoleId = row.id;
		//获取 选中的 数据
		//获取 窗口节点
		showUpdateView(selectRoleId);
	}else{
		window.confirm("请选中一项");
	}
}
//添加修改的页面
function showUpdateView(selectRoleId){
	var updateRole = $("#updateRole");
	$.ajax({
        cache: true,
        type: "GET",
        url:$updateRole+"/"+selectRoleId,
        /*data:loginForm.serialize(),// 你的formid*/
        async:false,
        error: function(request) {
        	$.messager.alert("提示","服务器繁忙");
        },
        success: function(data) {
        	updateRole.html(data);
        	updateRole.window('open');
        }
    });
}

function deleRole(){
	//得到选中内容
	var row = $('#tttt').datagrid('getSelected');
	var selectRoleId=null;
	if(row){
		selectRoleId = row.id;
		var result = deleById($deleRole+"?roleId="+selectRoleId,"").split(":");
		if( result[0]=="0" ){
			//删除成功
			flushDataGrid($('#tttt'),200);
		}
	}else{
		$.messager.alert("提示","请选中一项");
	}
}

//获取选中内容
function selected(){
	var row = $('#tttt').datagrid('getSelected');
	var selectRoleId=null;
	if(row){
		selectRoleId = row.id;
	}
	return selectRoleId;
	
}
//获取选中内容 返回
function allPermissions(){
	
}

// 提交表单
function save_(){
	
	var form = $("#form_add_role");
	var url  = $addRole;
	var result = submitAjaxForm(form,url).split(":");
	if(result[0]=="0"){
		closeWind($("#addRoleWin"));
		
		flushDataGrid($('#tttt'),200);
	} else {
		if(result[1] !=""){
			window.confirm(result[1]);
		}
		else {
			window.confirm("错误");
		}
	}
}
//修改
function update_(){
	var form = $("#form_update_role");
	var url  = $updateRoleajx;
	var result = submitAjaxForm(form,url).split(":");
	if(result[0]=="0"){
		closeWind($("#updateRole"));
		
		flushDataGrid($('#tttt'),200);
	} else {
		if(result[1] !=""){
			window.confirm(result[1]);
		}
		else {
			window.confirm("错误");
		}
	}
}



function cancel_(){
	
}

/**--------------------------permission-------------------------------**/


var permissions={
	createNew:function(){
		var permisison ={};
		permisison.do_permission_search=function(){
			var perName = $('#perName').val();
			if(perName.trim()!=''){
				$('#t_permission').datagrid('load',{
					likeName:"perName",
					like:perName
				});
			}else{
				flushDataGrid($('#t_permission'),200);
			}
		};
		//前端数据验证
		//前端数据验证
		//perName 验证
		//父级id验证 必填
		//别名验证|
		permisison.validate = function(){
			var perName = $("input[name='perName']").val();
			if(perName==null||perName.trim()==''){
				return false;
			}
			return true;
		};
		//数据添加方法
		permisison.do_permission_add=function(){
			
			if(permisison.validate()){
				var result = submitAjaxForm($("#form_add_permission"),$A_PERMISSION_ADD).split(":");
				if(result[0]=='0'){
					closeWind($('#addPermissionWin'));
					flushDataGrid($('#t_permission'),200);
				}else if(result[0]=='1'){
					window.confirm(result[1]);
				}else{
					window.confirm("系统错误"+result[1]);
				}
			}else {
				$.messager.alert("提示","请按要求填入字段");
			}
		};
		/**
		 * 修改
		 */
		permisison.do_permission_update=function(){
			var perName = $("#perName_od").val();
			if(perName!=null&&perName.trim()!=''){
				var result = submitAjaxForm($("#form_update_permission"),$A_PERMISSION_UPDATE).split(":");
				if(result[0]=='0'){
					closeWind($('#update_permission'));
					flushDataGrid($('#t_permission'),200);
				}else if(result[0]=='1'){
					window.confirm(result[1]);
				}else{
					window.confirm("系统错误"+result[1]);
				}
			}else {
				$.messager.alert("提示","请按要求填入字段");
			}
		};
		/*********
		 * 删除方法
		 * 
		 * 
		 */
		permisison.deleteAllDataByIds = function(rows,$url){
			var data ={};
			for (var int = 0; int < rows.length; int++) {
				var row = rows[int];
				//取出 ID
				data[row.perId]=row.perId;
			}
			var jsonFy = JSON.stringify(data);
			
			//开始发送ajax请求
			var reault = global_Ajax_delete($url,jsonFy).split(":");
			if(reault[0]=="0"){
				flushDataGrid($("#t_permission"),200);
				$.messager.alert("操作成功","删除数据"+reault[1]+"个成功");
			}else{
				$.messager.alert("数据不存在,或已被删除");
			}
		};
		
		permisison.do_permission_delete = function(){
			//获取多个
			var rows = global_getDatagridSelections($("#t_permission"));
			//数组
			if(rows.length>0){
				$.messager.confirm("提醒","您一共选中改了"+rows.length+"条数据,确定删除吗?",function(data){
					if(data){
						permisison.deleteAllDataByIds(rows,$A_PERMISSION_DELETE);
					}
				});
			}else {
				$.messager.alert("错误","未选中内容");
			}
		};
		
		return permisison;
	}	
};

/**
 * 查询
 */
function do_permission_search(){
	
	var permisison = permissions.createNew();
	//执行  查找函数
	permisison.do_permission_search();
}

/**
 * 添加
 */
function do_permission_add(){
	var permisison = permissions.createNew();
	//执行  查找函数
	permisison.do_permission_add();
}

function do_permission_add_(){
	$('#addPermissionWin').window('open');//打开 窗口
}
/**
 * 进入修改页面
 */
function do_permission_update_view(){
	//获取    选中的  记录
	var row = $('#t_permission').datagrid('getSelected');
	var selectRoleId=null;
	if(row){
		selectRoleId = row.perId;
		//获取访问 页面
		//添加到 窗口 update_permission 
		var data = global_AjaxGetView($A_PERMISSION_UPDATE_VIEW+"?perId="+selectRoleId,'');
		if(data!=null&&data.trim()!=''){
			var json = JSON.parse(data); 
			$("#perName_od").val(json['perName']);//设置名字
			$("#percName_od").val(json['perMs']);
			$("#showRoleId").html("当前:"+json['roleId']);
			$("input[name='perId']").val(json['perId']);
			openWin($("#update_permission"));
		}
		
	}else {
		window.confirm("请选择一条记录");
	}
}
//更新
function do_permission_update(){
	//获取    选中的  记录
	var permisison = permissions.createNew();
	permisison.do_permission_update();
}

//删除
function do_permission_delete(){
//getDatagridSelected
	var permisison = permissions.createNew();
	permisison.do_permission_delete();
}

