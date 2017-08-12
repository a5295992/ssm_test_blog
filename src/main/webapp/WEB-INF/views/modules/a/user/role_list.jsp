<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file ="../default/commom.jsp" %>
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${statics }/awesome/css/font-awesome.css">
	<script type="text/javascript" src="${statics }/easyUI/jquery.easyui.min.js"></script>
<div title="角色列表" style="padding:5px;width: 100%;height: 100%" >
					
<table id="tttt" title="角色" class="easyui-treegrid" style="width:100%;height:100%"
			url="${headpath }/a/roless"
			rownumbers="true"
			idField="id" treeField="text"
			toolbar="#tb"
			
			pagination= "true"
			
			>
		<thead>
			<tr>
				<th field="text" width="250" >角色名</th>
				<th field="id" width="150" align="right">ID</th>
				<th field="deleFlag" width="150" align="right">待删标记</th>
			</tr>
		</thead>
	</table>
	<!--工具栏  -->
	<div id="tb">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRole()">添加</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateRole()">修改</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveRoles()">保存</a>
	<a href="#" class="easyui-linkbutton" iconCls="fa fa-trash" plain="true" onclick="deleRole()">删除</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="allPermissions()">权限列表</a>
    </div>
    
    
    <!-- 添加 -->
    <div id="addRoleWin" class="easyui-window" title="创建新角色" closed="true" style="width:500px;height:250px;">
	<form style="padding:10px 20px 10px 40px;" id="form_add_role">
	<table>
				<tr>
					<td>角色名:</td>
					<td><input type="text" name="roleName" style="width:350px;"/></td>
				</tr>
				
				<tr>
					<td>父级角色:</td>
					<td><select class="easyui-combotree" url="${headpath }/a/roless_list" name="proleName" style="width:156px;"/></td>
				</tr>
				<tr>
					<td> </td>
				   <td><div id="dlg-buttons">
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save_()">创建</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
				</div>
				</tr>
			</table>
	</form>
</div>

 <div id="updateRole" class="easyui-window" title="创建新角色" closed="true" style="width:550px;height:250px;">
 
 
 </div>
 
 
 <!--删除  -->
 
</div>


