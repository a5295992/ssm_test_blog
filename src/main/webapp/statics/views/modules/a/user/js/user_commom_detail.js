
//用户 
//增删改查







//用户角色
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
            window.confirm("抱歉！ 服务器繁忙");
        },
        success: function(data) {
        	updateRole.html(data);
        	updateRole.window('open');
        }
    });
}

function saveRoles(){
	
	
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
			flush();
		}
	}else{
		window.confirm("请选中一项");
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
function allPermissions(){
	
	
}

// 提交表单
function save_(){
	
	var form = $("#form_add_role");
	var url  = $addRole;
	var result = submitAjaxForm(form,url).split(":");
	if(result[0]=="0"){
		closeWind($("#addRoleWin"));
		
		setTimeout(function(){
			window.location.reload();
		}, 200);
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
		
		setTimeout(function(){
			window.location.reload();
		}, 200);
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
//关闭窗口
function closeWind(win){
	win.window('close');
}

/**
 * 刷新页面
 */
var flushPage = function flushPage(){
	//刷新当前页面
	window.location.reload(true);
};


//用户权限
//增删改查