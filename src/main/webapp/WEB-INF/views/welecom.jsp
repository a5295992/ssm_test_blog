<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="global/global.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>后台管理首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="欢迎页面">
  </head>
  <body >
  		<h3> 欢迎您，[${user.userName }]</h3>
  		<h4> 您上次登录的 IP是:[${user.host }] 如果不是您亲自登录 <a href="#"> 修改密码</a></h4>
  </body>
</html>
