$(document).ready(function() {
	init();

});
function init() {
	$(".name,.pwd,#valid").keyup(function(event) {
		if (event.keyCode == 13) {
			$('#login').click();
		}
	});
	
	$("#login").click(function(){
		//采用 ajax 提交模拟提交表单
		//1.获取 表单对象
		var loginForm = $("#login_form");
		var loginUrl = $ajaxLogin;
		//2.ajax提交
		var result= submitAjaxForm(loginForm,loginUrl).split(":");
		console.log("login_01.js line 19执行返回的结果为 :"+result);
		//登录成功
		if(result[0]=="0"){
			window.confirm("登录成功");
			setTimeout(toAIndex( $aIdex), 2000);
		}else if(result[0]=="1"){
			$("#err").css("display", "inline-block");
			$("#err").text(result[1]);
			changePic();
		}else{
			window.confirm("别瞎搞！");
		}
	});
	
	//定时 跳转
	function toAIndex(url){
		window.location.href=url;
	}
	
	
	var changePic = function changePic(){
		$('#verifyCodePic')[0].src = headpath+'/getValidateImage?name=' + Math.random();
		return false;
	};
	
	$('#changeimg').click(changePic);
}
















