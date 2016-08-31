<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bf" uri="http://www.itany.com/bbs/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
	 <meta charset="utf-8">
    <title>show All</title>

	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styles.css">
	<script src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="easyui/js/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/js/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css"href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/base.css">
  </head>
  <body>
	 <table id="dg" title="用户" class="easyui-datagrid" style="width:800px;height:200px"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="userid" width="50">用户ID</th>
                <th field="loginname" width="50">用户名</th>
                <th field="email" width="50">邮箱</th>
                <th field="head" width="150">头像</th>
                <th field="regtime" >注册时间</th>
                <th field="state"  width="150">权限</th>
                <th field="point" width="60">发帖数</th>
            </tr>
        </thead>
         <p:forEach items="${requestScope.users}" var="one">
      <tr>
        <td>${one.userid }</td>
        <td>${one.loginname}</td>
        <td>${one.email}</td>
        <td>${one.head}</td>
        <td>${one.regtime}</td>
        <td>${one.state}</td>
        <td>${one.point}</td>
       <td >  
       		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()"></a>
       		 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()"></a>
        </td>
      </tr>
    </p:forEach>
  </tbody>
  </table>
    <!-- 	<div id="dlg" class="easyui-dialog" style="width:400px;" data-options="closed:true," >
        <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px"  >
            <div style="margin-bottom:20px;font-size:14px;border-bottom:1px solid #ccc">回帖信息</div>
           
            <div style="margin-bottom:10px">
                <input name="replyId" class="easyui-textbox" required="true" label="回帖ID" style="width:100%" readonly="readonly">
            </div>
            <div style="margin-bottom:10px">
                <input name="userid" class="easyui-textbox" required="true" label="userid" style="width:100%" readonly="readonly">
            </div>
            <div style="margin-bottom:10px">
                <input name="title" class="easyui-textbox" required="true" label="标题" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="contents" class="easyui-textbox" required="true"  label="内容" style="width:100%">
            </div>
             <div style="margin-bottom:10px">
                <input name="replyname" class="easyui-textbox" required="true"  label="回帖人" style="width:100%" readonly="readonly">
            </div>
             <div style="margin-bottom:10px">
                <input name="postdate" class="easyui-datetimebox" required="true"  label="回帖时间" style="width:100%" readonly="readonly">
            </div>
            <div style="margin-bottom:10px">
                <input name="modifydate" class="easyui-datetimebox" required="true"  label="修改时间" style="width:100%">
            </div>
             <div style="margin-bottom:10px">
             <a onclick="subs()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width:90px">确定
				</a>
              <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
            </div>
        </form>
    </div> -->
      
<div class="page" style="text-align:center;width:800px;">
  <button class="pre" onclick="uselasting(${requestScope.page.pageNo},${requestScope.page.maxPageNo})">上一页</button>
  
  <button class="next" onclick="usernexting(${requestScope.page.pageNo},${requestScope.page.maxPageNo})">下一页</button>
</div>
<script type="text/javascript">
		  function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改帖子信息');
                $('#fm').form('load',row);
              
            } 
           
        }
     function subs() {
			$('#fm').form('submit', {
				url : "xiugairepliys.do", // 
				onSubmit : function() {
					return true;
				},
				success : function() {
					$('#dlg').dialog('close');
					var currtab=$('#tt').tabs('getSelected');
					currtab.panel('refresh');
				}
			});
		} 
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
                    if (r){
                    var replyId=row.replyId;
                    var userid=row.userid;
                    alert(replyId +" "+userid)
                        $.post("deletereplys.do?replyId="+replyId+"&userid="+userid,{id:row.id},function(result){
                            if (result.success){
                            	alert("222")
                              // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.errorMsg
                                });
                            }
                        },'json');
                         var currtab=$('#tt').tabs('getSelected');
						currtab.panel('refresh');   
                    }
                });
            }
            
        }
	</script>
  </body>
</html>
