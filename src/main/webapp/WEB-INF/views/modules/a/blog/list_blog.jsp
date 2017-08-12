<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file ="../default/commom.jsp" %>
<table id="dg" style="width:100%;height:100%"
			url="${headpath }/a/blog/query"
			pagination="true" sortName="blogId" sortOrder="desc"
			toolbar="#tb"
			title="我的博客信息"
			singleSelect="true" fitColumns="true">
		<thead>
			<tr>
				<th field="blogId"     width="60"               >ID</th>
				<th field="title"      width="25%"               >标题</th>
				<th field="createTime" align="right"   width="15%">创建时间</th>
				<th field="updateTime" align="right"   width="15%">更新时间</th>
				<th field="blogDesc"   align="center"  width="25%">描述</th>
				<th field="categoryId" align="center"width="100">分类</th>
				<th field="deleFlag"    align="right"  width="50">删除标记</th>
			</tr>
		</thead>
</table>
<!--工具栏  -->
	<div id="tb">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="to_blog_edit_update()">编辑博客</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateRole()">修改</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveRoles()">保存</a>
	<a href="#" class="easyui-linkbutton" iconCls="fa fa-trash" plain="true" onclick="do_blog_edit_delete()">删除</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="allPermissions()">权限列表</a>
	</div>
<script type="text/javascript">


	$('#dg').datagrid({
		view: detailview,
		detailFormatter:function(index,row){
			return '<div class="ddv" style="padding:5px 0"></div>';
		},
		onExpandRow: function(index,row){
			var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
			ddv.panel({
				border:false,
				cache:false,
				href:headpath+'/a/blog/query/view?blogId='+row.blogId,
				onLoad:function(){
					$('#dg').datagrid('fixDetailRowHeight',index);
				}
			});
			$('#dg').datagrid('fixDetailRowHeight',index);
		}
	});
</script>
