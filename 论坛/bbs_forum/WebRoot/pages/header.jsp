<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
  <div class="row">
      <div>
         <img src="${pageContext.request.contextPath }/imgs/logo.jpg" class="img-responsive">
      </div>
  </div>
</div>
<div class="container">
  <div class="row">
    <div>
      <ul class="nav nav-pills">
        <p:choose>
          <p:when test="${sessionScope.users !=null}">
            <li class="disabled"><a href="javascript:void(0)">欢迎您：
              <span>
              <p:out value="${sessionScope.users.loginname }"></p:out>
              </span></a></li>
            
            <li><a href="">站内短消息</a></li>
           	
            <li><a href="top.do">置顶帖子</a></li>
             <li><a href="good.do">精华帖子</a></li>
              <li><a href="adpages/index.jsp">进入后台管理</a></li>
             <li><a href="./exit.do">退出</a></li>
          </p:when>
          <p:otherwise>
            <li class="disabled"><a href="javascript:void(0)">您尚未</a></li>
            <li><a href="./pages/login.jsp">登录</a></li>
            <li><a href="./pages/regist.jsp">注册</a></li>
          </p:otherwise>
        </p:choose>
      </ul>
    </div>
  </div>
</div>
