<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="bf" uri="http://www.itany.com/bbs/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>主题帖与回复</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styles.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js" ></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">

<script>
	
	if(${empty topic}){
		location.href="topic.do?topicId="+${param.topicId};
	}

	function doPublish(id){
			if(${empty sessionScope.users}){
		alert("请登录后 在进行操作");
		window.location.href="./pages/login.jsp";
	}else{
		window.location.href="publish.do?boardId="+id;
	}
	}
	
	function doReply(id){
			if(${empty sessionScope.users}){
		alert("请登录后 在进行操作");
		window.location.href="./pages/login.jsp";
	}else{
		window.location.href="./pages/post.jsp?topicId="+id;
	}
	}
	
	
	//上一页
		function dopre(page){
			if(page == 1){
				return;
			}
			window.location.href="replay.do?topicId=${requestScope.topic.topicId}&pageNo="+(page-1);
		}
		//下一页
		function donext(page,maxPage){
			if(page == maxPage ){
				return;
			}
			window.location.href="replay.do?topicId=${requestScope.topic.topicId}&pageNo="+(page+1); 
		}
		/*function modify(id){
				window.location.href="remodify.do?topicId="+id; 
		}*/
	
	//是否删除主题帖
		var replyid = null;
		var userid = null;
		function del(ryid,usid){
			
			/**if(confirm("确认删除？")){
				return true;
			}
			return false;*/
			
			$(".del").fadeIn(500);
			replyid = ryid;
			userid = usid;
			console.log(replyid);
		}
		
		function delCancel(){
			$(".del").fadeOut(500);
			replyid = null;
			console.log(replyid);
		}
		
		function delRight(){
		console.log(replyid+"huhui");
			window.location.href="delete.do?topicId=${requestScope.topic.topicId}&replyId="+replyid+"&userid="+userid;
		}
		function dotop(id){
	
		window.location.href="totop.do?totop="+id;
	}
	function dogood(id){
	
		window.location.href="togood.do?togood="+id;
	}
		
		
</script>

<!--header开始 -->
<%@ include file="header.jsp"%>

<!-- 删除框 -->
<div class="del">
	<div> 确认删除吗</div>
	<div>
		<button onclick="delRight()">确认</button>
		<button onclick="delCancel()">取消</button>
	</div>
</div>
<!--nav导航栏 -->
<div class="indexnav">
  <ul>
    <li>>><a href="index.do">论坛首页</a></li>
    <li>>><a href="topic.do?boardId=${sessionScope.secboard.boardId}">
      <p:out value="${sessionScope.secboard.boardName}"></p:out>
      </a>
    <li>>>
      <p:out value="${requestScope.topic.title}"></p:out>
      </a>
  </ul>
</div>
<section class="post">
  <button onclick="doReply(${requestScope.topic.topicId})"><img src="imgs/reply.gif"></button>
  <button onclick="doPublish(${sessionScope.secboard.boardId})"><img src="imgs/post.gif"></button>
</section>
<div class="location"> 帖子的主题标题：
  <p:out value="${requestScope.topic.title }"></p:out>
</div>
<style type="text/css">
  .replaytab{
    width:1140px;
    margin:10px auto ;
	overflow: hidden;
    /*border:1px solid red;*/
  }
  .tableft{
    width: 300px;
    text-align: center;
    background-color: #ccc;
    float: left;
  }
  .tableright{
  	width: 800px;
  	height: 210px;
  	float: left;
  }
   .tableft div{
   	width: 90px;
   	height: 90px;
   	vertical-align: middle;
   	text-align: center;
   	background-color: white;
   	margin:auto;
  
   
   }
.tableft div img{
		margin-top: 10px;
	}
 .replaytab p{
    height: 40px;
    line-height: 50px;
    padding-left:20px;
  }
	.tabcont{
	
		height: 90px;
		border-top: 1px dashed #ccc;
   		border-bottom:1px dashed #ccc;
	}
</style>


   <div class="replaytab">
   
      <div class="tableft">
      	<p style="color:blue;">
      		<p:out value="${bf:findUserNameById(requestScope.topic.userid).loginname}"></p:out>
     	 </p>
            
            <div><img src="${bf:findUserNameById(requestScope.topic.userid).head}"></div>
            <p style="color:green;">注册时间：
                  <fmt:formatDate value="${bf:findUserNameById(requestScope.topic.userid).regtime}" pattern="yyyy-MM-dd"/>
            </p>
      </div>
      <div class="tableright">
      		<p>
      			<span><img src="imgs/user.gif" alt="">发表于:</span>   <fmt:formatDate value="${requestScope.topic.postdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
      		</p>
      		<div class="tabcont">
      			<p>标题：${requestScope.topic.title} </p>
      			<p> 内容：${requestScope.topic.tcontents}</p>
      		</div>
      	 	<p>
      			<!-- <span><img src="imgs/top.gif"><a href="">置顶帖子</a></span>
      			<span><img src="imgs/top.gif"><a href="">设为精华帖子</a></span> -->
      			
      		<%-- 	${reply.topicid} --%>
      		<p:choose>
            <p:when test="${sessionScope.users.state > 1}"> 
            
         	 <a href="javascript:void(0)" onclick="javascript:return dotop(${requestScope.topic.topicId })"> <span><img src="imgs/top.gif">置顶帖子</span> </a> 
           	<a href="javascript:void(0)" onclick="javascript:return dogood(${requestScope.topic.topicId })"><span><img src="imgs/top.gif">设为精华帖子</span></a> 
             </p:when>
          	</p:choose>
      		</p>
      </div>
    </div>
      <p:forEach items="${requestScope.replys }" var="reply" >
      <div class="replaytab">
   
      <div class="tableft">
      	<p style="color:blue;">
      		<p:out value="${bf:findUserNameById(reply.userid).loginname}"></p:out>
     	 </p>
            
            <div><img src="${bf:findUserNameById(reply.userid).head}"></div>
            <p style="color:green;">注册时间：
                  <fmt:formatDate value="${bf:findUserNameById(requestScope.topic.userid).regtime}" pattern="yyyy-MM-dd"/>
            </p>
      </div>
      <div class="tableright">
      		<p>
      			<span><img src="imgs/user.gif" alt="">发表于:</span> <fmt:formatDate value="${reply.postdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
      		</p>
      		<div class="tabcont">
      			<p>回复标题：${reply.rtitle} </p>
      			<p> 回复内容：${reply.rcontents}</p>
      		</div>
      		<p>
      			<!-- <span><img src="imgs/top.gif"><a href="">置顶帖子</a></span>
      			<span><img src="imgs/top.gif"><a href="">设为精华帖子</a></span> -->

      		<p:choose>
            <p:when test="${sessionScope.users.userid == reply.userid}"> 
            <a href="javascript:void(0)" onclick="javascript:return del(${reply.replyId},${reply.userid})"> 【 删除 】 </a> 
           	<a href="remodify.do?replyId=${reply.replyId}&topicId=${requestScope.topic.topicId}">[修改]</a>
             </p:when>
          	</p:choose>
      		</p>
      </div>
     </div>
    </p:forEach>
<div class="page">
  <button class="pre" onclick="dopre(${requestScope.page.pageNo})">上一页</button>
  <button class="next" onclick="donext(${requestScope.page.pageNo},${requestScope.page.maxPageNo })">下一页</button>
</div>
<!--footer开始  -->
<%@ include file="footer.jsp"%>
</body>
</html>
