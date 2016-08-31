<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>修改发布帖子页面</title>
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
    <li> <a href="index.do">论坛首页</a></li>
    <li>>> <a href="topic.do?boardId=${requestScope.secboard.boardId}">
      <p:out value="${requestScope.secboard.boardName}"></p:out>
      </a>
    <li>>> <a href="replay.do?topicId=${requestScope.topic.topicId}">
      <p:out value="${requestScope.replys.rtitle}"></p:out>
      </a>
  </ul>
</div>
<h2>${requestScope.kong}</h2>
<form action="modify.do?replyId=${param.replyId}" method="POST">
  <table class="tab2">
    <thead>
      <tr class="h30">
        <th colspan="2">修改回复帖子</th>
      </tr>
    </thead>
    <tbody>
      <tr  class="h30">
        <td>标题</td>
       <td><input type="text" name="title" value="${requestScope.replys.rtitle }"></td>
      </tr>
      <tr class="text">
        <td>内容</td>
        <td><textarea rows="16" clos="50" name="contents" >${requestScope.replys.rcontents }</textarea>
          <span>(不能大于：1000字)</span></td> 
      </tr>
    </tbody>
  </table>
  
  	<input type="hidden" name="replyId" value="${param.replyId}">
	<input type="hidden" name="topicId" value="${requestScope.topic.topicId}">
  
 <div class="tijiao">
    <input type="submit" value="提交" class="w50" />
    <input type="reset" value="重置" class="w50" />
  </div>
</form>
<!--footer开始  -->
<%@ include file="footer.jsp"%>
</body></html>
