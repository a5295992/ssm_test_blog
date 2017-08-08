<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zking.constants.PermissionAndRoleConstant" %>

<%@ include file="../../global/global.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>后台管理首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="后台管理首页">
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/demo/demo.css">
	<link rel="stylesheet" type="text/css" href="${statics }/awesome/css/font-awesome.css">
	<script type="text/javascript" src="${statics }/easyUI/jquery.easyui.min.js"></script>
  </head>
  <body class="easyui-layout">
   <div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">
       				<a href="${headpath }/loginOut" > 退出登录</a> &nbsp; &nbsp;随心博客管理
        </div>
		<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">今天是 2017年7月30日</div>
		<div data-options="region:'east',split:true" title="消息管理" style="width:180px;">
			<ul class="easyui-tree" data-options="url:'${headpath}/a/roless',method:'get',animate:true,dnd:true"></ul>
		</div>
		<div data-options="region:'west',split:true" title="功能" style="width:200px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<div title="用户管理" style="padding:10px;">
	                
					<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search"
	                onclick="addTab('用户列表','${headpath}/a/users')"> 查看用户信息</a><br>
	                <a href="#" class="easyui-linkbutton" plain="true" iconCls="fa fa-lock"
	                onclick="addTab('google','${headpath}/a/roles')"> 角色管理</a><br>
	                <a href="#" class="easyui-linkbutton" plain="true" iconCls="fa fa-commenting"
	                onclick="addTab('权限管理','${headpath}/a/permissions')"> 权限管理</a><br>
	                <a href="#" class="easyui-linkbutton" plain="true" iconCls="fa fa-hourglass-1"
	                onclick="addTab('google','http://www.google.com')">用户申请待批</a><br>
                </div>
				<div title="博客管理"  style="padding:10px;">
					发表博客
                    删除博客
                    查看评论
				</div>
                <div title="个人中心" style="padding:10px" data-options="selected:true">
					
                    <a href="#" class="easyui-linkbutton" plain="true" iconCls="fa fa-pencil-square-o">写博客</a><br />
                  	<a href="#" class="easyui-linkbutton" plain="true" iconCls="fa fa-weibo">我的博客</a><br />
                    <a href="#" class="easyui-linkbutton" plain="true" iconCls="fa fa-commenting-o">我的评论</a><br />
                    <a href="#" class="easyui-linkbutton" plain="true" iconCls="fa fa-comments">我的消息</a><br />
                    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-edit">修改密码</a><br />
                   	<a href="#" class="easyui-linkbutton" plain="true" iconCls="fa fa-cog">个人信息设置</a><br />
				</div>
				<div title="评论管理" style="padding:10px">
					删除评论
                    已经回复
                    清空历史
				</div>
                <div title="系统管理" style="padding:10px">
					删除评论
                    已经回复
                    清空历史
				</div>
             
                <div title="高级设置" style="padding:10px">
					删除评论
                    已经回复
                    清空历史
				</div>
			</div>
		</div>
		<div data-options="region:'center',title:'待办事项',iconCls:'icon-ok'">
			<div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="tt">
				<div title="欢迎页" data-options="href:'${headpath }/a/a/welecom'" style="padding:10px"></div>
									
			</div>
		</div>
  </body>
  <script type="text/javascript" src="${statics_views_modules }/a/index/index.js"></script>
</html>
