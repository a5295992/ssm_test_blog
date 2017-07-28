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
		if (!account || !pwd) {
			$("#err").css("display", "inline-block");
			$("#err").text("�������û��������");
			return false;
		}
		var code = $("#valid").val().toLocaleLowerCase();
		code = $.trim(code);
		if (code.length < 4) {
			$("#err").css("display", "inline-block");
			$("#err").text("��֤�����");
			return false;
		}
	});
	$('#changeimg').click(function() {
		$('#verifyCodePic')[0].src = pat+'/jcaptcha.jpg?' + Math.random();
		return false;
	});
}