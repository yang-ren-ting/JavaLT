
function doCheck(){
	return doCheckPass() && validUserName && validVcode;
}

function doCheckPass(){
	return true;
}
var validUserName=false;
function doCheckName(){
	var xhr = new XMLHttpRequest();
	var uname = $("#uname").val();
	uname = encodeURI(uname);
	uname = encodeURI(uname);

	xhr.open("get", "validname.do?op=validname&uname=" + uname);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var str = xhr.responseText;
			if(str=="true"){
				validUserName=true;
				$("#spname").addClass("glyphicon glyphicon-ok form-control-feedback").removeClass(" glyphicon-remove").css("color","green");
			}
			else{
				validUserName=false;
				$("#spname").addClass("glyphicon glyphicon-remove form-control-feedback").removeClass("glyphicon-ok").css("color","red");
			}
		}
	};
	xhr.send(null);
}
var validVcode=false;
function doCheckCode(){
	var xhr = new XMLHttpRequest();
	var code = $("#code").val();

	xhr.open("get", "validname.do?op=validvcode&vcode="+code);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var str = xhr.responseText;
			if(str=="true"){
				validVcode=true;
				$("#spcode").addClass("glyphicon glyphicon-ok ").css("color","green");;
			}
			else{
				
				validVcode=false;
				$("#spcode").addClass("glyphicon glyphicon-remove ").css("color","red")
			}
		}
	};
	xhr.send(null);
}