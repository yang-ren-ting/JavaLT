<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>登录界面</title>

<link rel="stylesheet" type="text/css" href="css/styles.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js" ></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/base.css">

<script type="text/javascript" src="easyui/js/jquery.min.js"></script>
<script type="text/javascript" src="easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/js/locale/easyui-lang-zh_CN.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>
<!--nav导航栏 -->
<div class="indexnav">
  <ul>
    <li><a href="index.do"> 论坛首页 </a></li>
    <li> &gt;&gt; <a href="javascript:void(0)">登录界面</a></li>
  </ul>
</div>
 <div class="login">
  <form action="./login.do" method="post">
    <h2>${requestScope.loginrr }</h2>
    <table>
      <tr>
        <td><label for="loginname">用户名：</label></td>
        <td><input class="easyui-textbox" name="loginname"
					style="width: 100%; height: 40px; padding: 12px"
					data-options="prompt:'填写用户名',iconCls:'icon-man',iconWidth:38, required:true"></td>
      </tr>
      <tr>
        <td><label for="loginpwd">密码：</label></td>
        <td class="pass"><input class="easyui-textbox" type="password" name="loginpwd"
					style="width: 100%; height: 40px; padding: 12px"
					data-options="prompt:'填写密码',iconCls:'icon-lock',iconWidth:38, validType:'length[4,16]', required:true"></td>
      </tr>
      <tr>
        <td>验证码</td>
        <td><input class="easyui-textbox" name="vcode"
					style="width: 50%; height: 40px; padding: 12px; vertical-align: middle;"
					data-options="prompt:'填写验证码', required:true, validType:'length[4,4]'">
          <img  src="validate.jpg" id="vcode1" title="看不清，请点击刷新"></td>
      </tr>
      <tr>
        <td colspan="2" class="btn">
          <input type="submit" value="提交" class="w50" />
          <input type="reset" value="重置"  class="w50"/>
          <a href="pages/findpwd.jsp">忘记密码</a>
          </td>
      </tr>
    </table>
  </form>
</div>
<script>
    	$("#vcode1").click(function(){
    		this.src="validate.jpg?"+new Date().getTime();
    	
    	})
    	
</script> 
<%@ include file="footer.jsp" %>
</body>
</html>
