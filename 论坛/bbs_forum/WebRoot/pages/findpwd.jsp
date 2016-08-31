<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<!--html打开强制为极速模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!--避免IE使用兼容模式-->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>

<title>找回密码</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js" ></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styles.css">

<style type="text/css">
	section div{
		text-align:center;
		margin:10px auto;
	}

</style>
</head>
<body>
<%@ include file="header.jsp"%>
		
	<article>
		<section>
		<div class="login">
			<form action="findpwd.do" method="POST">
			<table>
				<tr>
		        	<td><label for="loginname">登录名：</label></td>
		        	<td><input type="text" name="loginname" ></td>
		      	</tr>
		      	<tr>
		        	<td><label for="loginname">邮箱：</label></td>
		       		<td><input type="text" name="email" ></td>
		      	</tr>
		      	 <tr>
			        <td  >
			         <input type="submit" value="提交" class="w50" />
			         </td>
			         <td  >
			         <input type="reset" value="重置"  class="w50"/>
			         </td>
			      </tr>
      		</table>
			</form>
		</div>
		</section>
	
	</article>
<%@ include file="footer.jsp"%>
		
	
	
</body>
</html>

