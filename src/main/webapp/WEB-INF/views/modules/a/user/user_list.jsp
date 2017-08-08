
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../global/global.jsp"%>
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${statics }/awesome/css/font-awesome.css">
	<script type="text/javascript" src="${statics }/easyUI/jquery.easyui.min.js"></script>
<div title="DataGrid" style="padding:5px;width: 100%;height: 100%" >
					
<table class="easyui-datagrid"
		style="width:100%;height:100%"
		url="${headpath }/a/userss"
		title="用户信息" iconCls="icon-search"
		rownumbers="true" pagination="true">
			<thead>
				<tr>
						<th data-options="field:'userName'" width="80">用户姓名</th>
						<th data-options="field:'flag'" width="100">可用</th>
						<th data-options="field:'userCname',align:'right'" width="80">姓名</th>
						<th data-options="field:'userId',align:'right'" width="80">ID</th>
						<th data-options="field:'email'" width="150">邮箱</th>
						<th data-options="field:'phone',align:'center'" width="150">手机</th>
				</tr>
			</thead>
</table>

</div>


