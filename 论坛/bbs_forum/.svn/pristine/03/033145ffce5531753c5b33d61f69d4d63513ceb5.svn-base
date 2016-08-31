<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户注册界面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styles.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/js.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js" ></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">

<script type="text/javascript">
	 $(function(){
		var back = ${requestScope.checkname};
		if(back>0){
		$("#sp1").html("用户已存在").css("color","red");
		}
	})
</script>
</head>
<body>
<!-- 头部 -->
<%@ include file="header.jsp" %>
<%-- <p>
 id: <p:out value="${requestScope.checkname}"></p:out>
</p> --%>
<!-- 注册页面开始 -->
<div class="indexnav">
  <ul>
    <li><a href="index.do"> 论坛首页 </a></li>
    <li> &gt;&gt; <a href="javascript:void(0)">注册界面</a></li>
  </ul>
</div>
<div class="regist">
	<%-- <c:choose>
		<c:when test="${requestScope.userid!=1 }">
			<p>用户名已存在存在，请重新注册！！</p>
		</c:when>
		<c:otherwise><p>欢迎注册，请登录</p></c:otherwise>
	</c:choose> --%>
	<form action="./regist.do" method="post" id="doregist">
		<table class="tb1">
			<tr>
				<td><label for="loginname">用户名</label></td>
				<td><input type="text" name="loginname" value="" id="name">
					<span id="sp1"></span>
				</td>
			</tr>
			<tr>
				<td><label for="loginpwd">密码</label></td>
				<td><input type="password" name="loginpwd" id="loginpwd">
				<span id="sp2"></span>
				</td>
			</tr>
			<tr>
				<td><label for="password1">重复密码</label></td>
				<td><input type="password" name="password1" id="password1">
				<span id="sp3"></span>
				</td>
			</tr>
			<tr>
				<td><label for="email">邮箱</label></td>
				<td><input type="email" name="email" value="" id="eml" required>
				<span id="sp4"></span>
				</td>
			</tr>
			<tr>
				<td><label for="sex">性别</label></td>
				<td><input type="radio" name="sex" value="1" checked>男 
				<input type="radio" name="sex" value="2">女</td>
			</tr>
		</table>
		<table class="tb2">
			<tbody>
				<tr>
					<td colspan="5" class="sex"><label for="head">请选择头像</label></td>
				<tr>
				<% for(int i=1;i<=5;i++) {%>
				<td>
					<img src="imgs/head/<%=i %>.gif"><input type="radio"
						value="imgs/head/<%=i %>.gif" name="head" >
				</td>
				<%} %>
				

				</tr>
				<tr>
					<td><img src="imgs/head/6.gif"><input type="radio"
						value="imgs/head/6.gif" name="head"></td>
					<td><img src="imgs/head/7.gif"><input type="radio"
						value="imgs/head/7.gif" name="head"></td>
					<td><img src="imgs/head/8.gif"><input type="radio"
						value="imgs/head/8.gif" name="head"></td>
					<td><img src="imgs/head/9.gif"><input type="radio"
						value="imgs/head/9.gif" name="head"></td>
					<td><img src="imgs/head/10.gif"><input type="radio"
						value="imgs/head/10.gif" name="head"></td>
				</tr>
				<tr>
					<td><img src="imgs/head/11.gif"><input type="radio"
						value="11" name="head"></td>
					<td><img src="imgs/head/12.gif"><input type="radio"
						value="12" name="head"></td>
					<td><img src="imgs/head/13.gif"><input type="radio"
						value="13" name="head"></td>
					<td><img src="imgs/head/14.gif"><input type="radio"
						value="14" name="head"></td>
					<td><img src="imgs/head/15.gif"><input type="radio"
						value="15" name="head"></td>
				</tr>
				<tr>
					<td colspan="5" class="sex"><button>注册</button></td>
				</tr>
		</table>

	</form>
</div>
<!--footer开始  -->
<%@ include file="footer.jsp"%>
</body>
</html>
