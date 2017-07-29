<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../global/global.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>后台管理登录中心</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="登录,后台,keyword3">
<meta http-equiv="description" content="简单登录">
<link rel="stylesheet" type="text/css" href="${statics_views_modules }/login/login_01/css/login_01.css">
<script type="text/javascript" src="${statics_views_modules }/login/login_01/js/login_01.js"></script>
</head>
</head>
<body>
	<div style="width: 100px">
		<ul >
			<li style="float: left; width: 25px">首页</li>
			<li style="float: left; width: 25px"">发现</li>
			<li style="float: left; width: 25px"">关于</li>
			<li style="float: left; width: 25px"">反馈</li>
		</ul>
	</div>
	<div id="sky"></div>
	<div id="head"></div>
	<div id="middle">
		<form action="ajaxLogin" method="post" id="login_form">
			<ul style="text-align: center;">
				<li style="font-size: 48px">随心博客管理</li>

				<li><input class="name" name="userName" placeholder="请输入用户名"></li>
				<li><input type="password" name="password" class="pwd"
					placeholder="请输入密码"></li>
				<li id="verifyCode" style="display: block;"><input id="valid"
					name="rand" placeholder="请输入验证码" maxlength="4"> <span
					id="validcode"> <img id="verifyCodePic" id="img_captcha"
						src="${headpath}/getValidateImage">
				</span> <span id="changeimg"> 换一张 </span></li>
				<li><button id="login">立即登录</button></li>
				<li><span id="err" style="display: inline-block;">密码错误</span></li>
			</ul>
		</form>
	</div>
	<div id="footer">
		<a>关于我们@qq50330690 &nbsp &nbsp|&nbsp &nbsp</a> <a>Copyright © 2015
			宋安伟 保留所有权利 沪ICP备110号-1</a>
	</div>
	<div id="cloud"></div>
</body>
<script type="text/javascript">
	$("#login").click(function(){
		$("#login_form").submit();
	});
</script>
</html>
