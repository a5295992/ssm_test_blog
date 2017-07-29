$(document).ready(function() {
	init();

});
function init() {
	$(".name,.pwd,#valid").keyup(function(event) {
		if (event.keyCode == 13) {
			$('#login').click();
		}
	});
	$("form").submit(function(event) {
		var account = $(".name").val();
		var pwd = $(".pwd").val();
		console.log(account+" "+pwd);
		if (!account || !pwd) {
			$("#err").css("display", "inline-block");
			$("#err").text("请输入账号密码");
			return false;
		}
		var code = $("#valid").val().toLocaleLowerCase();
		code = $.trim(code);
		if (code.length < 5) {
			$("#err").css("display", "inline-block");
			$("#err").text("验证码不足5位");
			return false;
		}
	});
	$("#login").click(function(){
		//采用 ajax 提交模拟提交表单
		//1.获取 表单对象
		var loginForm = $("#login_form");
		var loginUrl = $ajaxLogin;
		//2.ajax提交
		var result= submitAjaxForm(loginForm,loginUrl);
		//登录成功
		if(result[0]=="0"){
			window.confirm("登录成功");
			setTimeout(toAIndex( $aIdex), 2000);
		}else if(result[0]=="1"){
			$("#err").css("display", "inline-block");
			$("#err").text(result[1]);
		}else{
			window.confirm(result[1]);
		}
	});
	
	//定时 跳转
	function toAIndex(url){
		window.location.href=url;
	}
	
	
	$('#changeimg').click(function() {
		$('#verifyCodePic')[0].src = headpath+'/getValidateImage?name=' + Math.random();
		return false;
	});
}