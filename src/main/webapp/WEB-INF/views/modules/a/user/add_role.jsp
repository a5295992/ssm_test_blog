<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<form style="padding:10px 20px 10px 40px;" id="form_update_role">
	<table>
				<tr>
					<td>角色名:</td>
					<td><input type="text" name="roleName"  value ="${role.roleName }" style="width:350px;"/></td>
				</tr>
				
				<tr>
					<td>父级角色:</td>
					<td>
						<td><select class="easyui-combotree" url="${headpath }/a/roless" name="proleName" style="width:156px;"/></td>
					</td>
				</tr>
				<tr>
					<td> </td>
				   <td><div id="dlg-buttons">
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="update_('${role.roleId}')">保存</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#updateRole').dialog('close')">取消</a>
				</div>
				</tr>
			</table>
			<input name="roleId" value="${role.roleId }" type="hidden"/>
	</form>
