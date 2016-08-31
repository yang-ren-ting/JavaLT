<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>信息提示界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styles.css">
	<script src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js" ></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/login.js" ></script>
	<script type="text/javascript">
		setTimeout(function(){
				location.href="./pages/login.jsp";
				
		},3000);
	</script>
  </head>
  
  <body>
  <!-- 头部 -->
<%@ include file="header.jsp" %>
<div class="container">
  <div class="row">
    ${requestScope.error } 
   <a href="./pages/login.jsp">点击跳转</a> 或者 3s 跳转至登录界面
   </div></div>
  <!-- 头部 -->
<%@ include file="footer.jsp" %>
  </body>
  
</html>
