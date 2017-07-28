<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  		
  <body>
  	${role.roleName }
  		<table>
	  			<tr>
	  				<td>角色名</td>
	  				<td>用户Id</td>
	  				<td>用户名</td>
	  				<td>密码</td>
	  			</tr>
  			<c:forEach items="${role.list }" var="user">
	  			<tr>
	  				<td>${role.roleName }</td>
	  				<td>${user.id }</td>
	  				<td>${user.username }</td>
	  				<td>***</td>
	  			</tr>
  			</c:forEach>
  		</table>
  </body>
</html>
