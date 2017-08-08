<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../global/global.jsp"%>
<%@ include file ="../default/commom.jsp" %>
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${statics }/awesome/css/font-awesome.css">
	<script type="text/javascript" src="${statics }/easyUI/jquery.easyui.min.js"></script>
<div title="角色列表" style="padding:5px;width: 100%;height: 100%" >
					
<table id="t_permission" class="easyui-datagrid"
		style="width:100%;height:100%"
		url="${headpath }/a/permission/search"
		toolbar="#tb"
		title="权限信息" iconCls="icon-search"
		rownumbers="true" pagination="true">
			<thead>
				<tr>
						<th data-options="field:'perId'" width="15%">权限ID</th>
						<th data-options="field:'perName'" width="25%">权限名</th>
						<th data-options="field:'roleName'" width="15%">所属角色</th>
						<th data-options="field:'perMs'" width="15%">权限别名</th>
						<th data-options="field:'deleFlag',align:'right'" width="15%">删除标注</th>
				</tr>
			</thead>
</table>

	<!--工具栏  -->
	<div id="tb">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="do_permission_add_()">添加</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="do_permission_update_view()">修改</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveRoles()">保存</a>
	<a href="#" class="easyui-linkbutton" iconCls="fa fa-trash" plain="true" onclick="do_permission_delete()">删除</a>
	<span>角色ID:</span>
	<input id="roleId" style="line-height:26px;border:1px solid #ccc">
	<span>权限名:</span>
	<input id="perName" style="line-height:26px;border:1px solid #ccc">
	<a href="#" class="easyui-linkbutton" plain="true" onclick="do_permission_search()">查询</a>
    </div>
    
    <!-- 添加 -->
    <div id="addPermissionWin" class="easyui-window" title="创建新角色" closed="true" style="width:700px;height:250px;">
	<form style="padding:10px 20px 10px 40px;" id="form_add_permission" method="post" action="${headpath }/a/permission/add">
	<table>
				<tr>
					<td>权限名:</td>
					<td><input  id = "roleName_id" type="text" name="perName" style="width:350px;" required="required"/></td>
				</tr>
				<tr>
					<td>权限别名:</td>
					<td><input type="text" name="perMs" style="width:350px;" /></td>
				</tr>
				
				<tr>
					<td>所属角色:</td>
					<td><select class="easyui-combotree" url="${headpath }/a/roless_list" name="roleId" style="width:156px;"required="required"  id="roleId_id"/></td>
				</tr>
				<tr>
					<td> </td>
				   <td><div id="dlg-buttons">
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="do_permission_add()">添加</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
				</div>
				</tr>
			</table>
	</form>
</div>

 <div id="update_permission" class="easyui-window" 
  title="更新权限"
  closed="true" style="width:550px;height:250px;">
 <form style="padding:10px 20px 10px 40px;" id="form_update_permission" method="post" action="${headpath }/a/permission/add">
	<table>
				<tr>
					<td>权限名:</td>
					<td><input   type="text" name="perName" style="width:350px;" required="required" id="perName_od"/></td>
				</tr>
				<tr>
					<td>权限别名:</td>
					<td><input type="text" name="perMs" style="width:350px;" id="percName_od"/></td>
				</tr>
				
				<tr>
					<td>所属角色:</td>
					<td><select class="easyui-combotree" url="${headpath }/a/roless_list" name="roleId" style="width:156px;"required="required" /><span id="showRoleId" ></span></td>
				</tr>
				<tr>
					<td> </td>
				   <td><div id="dlg-buttons">
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="do_permission_update()">修改</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#update_permission').dialog('close')">取消</a>
				</div>
				</tr>
			</table>
			<input type="hidden" name="perId">
	</form>
 
 </div>
 <!--删除  -->
</div>


