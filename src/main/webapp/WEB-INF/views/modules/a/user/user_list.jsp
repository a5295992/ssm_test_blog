

<!--显示 所有用户数据信息  -->
<table class="easyui-datagrid"
	data-options="url:'${headpath }/a/users',method:'get',singleSelect:true,fit:true,fitColumns:true">
			<thead>
				<tr>
						<th data-options="field:'userName'" width="80">账号</th>
						<th data-options="field:'flag'" width="100">可用</th>
						<th data-options="field:'userCname',align:'right'" width="80">姓名</th>
						<th data-options="field:'userId',align:'right'" width="80">用户ID</th>
						<th data-options="field:'email'" width="150">邮箱</th>
						<th data-options="field:'phone',align:'center'" width="50">手机</th>
				</tr>
			</thead>
</table>


