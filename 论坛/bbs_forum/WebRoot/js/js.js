
$(function(){
	var ok1=false;
    var ok2=false;
    var ok3=false;
    var ok4=false;
	//验证姓名
	
	$("input[name='loginname']").focus(function() {
		$(this).text("用户名在2-20位 之间").addClass("state2");
	}).blur(function() {
		if($(this).val().length>=2 && $(this).val().length<=20 && $(this).val().length!=0){
			$(this).next().text('输入成功').addClass("state4");
			ok1=true;
		}else{
			$(this).next().text('用户名在2-20位 之间').addClass("state3");
		}
	})
	
   

		
		


	//验证密码
	$("input[name='loginpwd']").focus(function(){
		$(this).next().text("密码在3-10位之间").addClass("state2");
	}).blur(function(){
		if($(this).val().length>=3 && $(this).val().length<=10 && $(this).val().length!=0){
			$(this).next().text('输入成功').addClass("state4");
			ok2=true;
			console.log($("#loginpwd").val());
		}else{
			$(this).next().text('密码在3-10位之间').addClass("state3");
		}
	});
	//验证确认密码
	$("input[name='password1']").focus(function(){
		$(this).next().text("密码要输入一致").addClass("state2");
	}).blur(function() {
		if($(this).val().length>=3 && $(this).val().length<=10 && $(this).val().length!=0 && $(this).val().trim()== $("#loginpwd").val().trim()){
			$(this).next().text('输入成功').addClass("state4");
			ok3 = true;
			console.log($("#password1").val());
		}else{
			$(this).next().text("密码不一致，请重新输入").addClass("state3");
			console.log($("#password1").val());
		}
	});
	//验证邮箱
	$("input[name='email']").focus(function() {
		$(this).next().text("输入正确的email 格式").addClass("state2");
	}).blur(function() {
		var reg =  /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if(reg.test($(this).val())){
			$(this).next().text('输入成功').addClass("state4");
			ok4 = true;
		}else{
			$(this).next().text("输入正确的email 格式").addClass("state3");
		}
	})


	$("button").click(function() {
		if(ok1 && ok2  && ok4){
			$("#doregist").submit();
		}else{
			return false;
		}
	});
})