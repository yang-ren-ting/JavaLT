<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="bf" uri="http://www.itany.com/bbs/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>发表回帖</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styles.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js" ></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">

<!--header开始 -->
<%@ include file="header.jsp"%>
<!--nav导航栏 -->
<div class="indexnav">
  <ul>
    <li><a href="index.do">论坛首页</a></li>
    <li>>>
      <p:out value="${sessionScope.secboard.boardName}"></p:out>
      </a></li>
    <li>>>
      <p:out value="${sessionScope.topic.title}"></p:out>
      </a></li>
  </ul>
</div>
<form action="post.do?topicId=${param.topicId}" method="POST">
  <table class="tab2">
    <thead>
      <tr>
        <th colspan="2">发表回帖</th>
      </tr>
    </thead>
    <tbody>
      <tr  class="h30">
        <td>标题</td>
        <td><input type="text" name="rtitle">
          </td>
      </tr>
      <tr class="text">
        <td>内容</td>
        <td><textarea cols="50" rows="16" id="textarea" onfocus="if(value=='限1000字'){value=''}"  
    onblur="if (value ==''){value='限1000字'}" name="rcontents">限1000字</textarea> 
    </td>
      </tr>
    </tbody>
  </table>
  <div class="tijiao">
    <input type="submit" value="提交" class="w50" />
    <input type="reset" value="重置" class="w50" />
  </div>
</form>
<!--footer开始  -->
<%@ include file="footer.jsp"%>
</body></html>
