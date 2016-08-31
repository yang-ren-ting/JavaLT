<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>show allht</title>
<meta charset="utf-8">

<script type="text/javascript" src="easyui/js/jquery.min.js"></script>
<script type="text/javascript" src="easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/js/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/base.css">


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/styles.css">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body {
	width: 100%;
	text-align: center;
	font-size: 20px;
}

.panel-tool {
	right: 12px;
	width: auto;
}

.ul {
	width: 100%;
	text-align: center;
}

.ul li {
	width: 100%;
}

.accordion .accordion-header-selected {
	text-align: center;
}
</style>
<script type="text/javascript">
	
$(document).ready(function () {

	$("#sel").combobox({
	
	onChange: function (n,o) {
		var s = $("#sel").combobox("getValue");
		var currtab=$('#tt').tabs('getSelected');
		var title=currtab.panel('options').title;
		var hre1;
		var hre2;
	
		if(title=="发帖信息"){
			hre1="finallsend.do";
			hre2="sendBybdname.do?boardname=";
		}else if(title=="回帖信息"){
			hre1="findallget.do";
			hre2="getBybdname.do?boardname=";
		}
		if(n == "all"){
				$('#tt').tabs('update',{
					tab:currtab,
					options:{
						href:hre1,
					}
				});
				currtab.panel('refresh');
			}else{
				$('#tt').tabs('update',{
					tab:currtab,
					options:{
						href:hre2+s,
					}
				});
				currtab.panel('refresh');
			}
		
	}

	})
})

	
//上一页  所有贴 
	function dolasting(page,maxPage,hrefing){
		var s = $("#sel").combobox("getValue");
		var currtab=$('#tt').tabs('getSelected');
		var title=currtab.panel('options').title;
		var hrepl;
		if(page == 1){
			return;
		}
		if(title=='发帖信息'){
			hrepl="finallsend.do?pageNo=";
		}else{
			hrepl="findallget.do?pageNo="
		}
		
		if(s=='all'){
			var currtab=$('#tt').tabs('getSelected');
			var url=currtab.panel('options').href;
			$('#tt').tabs('update',{
				tab:currtab,
				options:{
					href:hrepl+(page-1),
				}
			});
			currtab.panel('refresh');
		
		}else{
			var currtab=$('#tt').tabs('getSelected');
			var url=currtab.panel('options').href;
			$('#tt').tabs('update',{
				tab:currtab,
				options:{
					href:hrefing+(page-1)+"&boardname="+s,
				}
			});
			currtab.panel('refresh');
		}
			
	}

//下一页  所有贴
	
	function donexting(page,maxPage,hrefing){
		var currtab=$('#tt').tabs('getSelected');
		var title=currtab.panel('options').title;
		var s = $("#sel").combobox("getValue");
		var hrepn;
		if(page >=maxPage){
			return;
		}
		if(title=='发帖信息'){
			hrepn="finallsend.do?pageNo=";
		}else{
			hrepn="findallget.do?pageNo="
		}
			if(s=='all'){
				var currtab=$('#tt').tabs('getSelected');
				var url=currtab.panel('options').href;
				$('#tt').tabs('update',{
					tab:currtab,
					options:{
						href:hrepn+(page+1),
					}
				});
				currtab.panel('refresh');
			}else{
				var currtab=$('#tt').tabs('getSelected');
				var url=currtab.panel('options').href;
				$('#tt').tabs('update',{
					tab:currtab,
					options:{
						href:hrefing+(page+1)+"&boardname="+s,
					}
				});
				currtab.panel('refresh');
			
			}
		
		
		
	}
			
        function addsend(){
        	var exit= $('#tt').tabs('exists', "发帖信息");
        	if(exit){
        		$('#tt').tabs('select',"发帖信息");
        	}else{
        		 $('#tt').tabs('add',{
              	 title: '发帖信息',
                 plain:false,
               	 href:'finallsend.do',
                closable: true
            });
        	}
           
        }
	
		 function addget(){
		 	var exit= $('#tt').tabs('exists', "回帖信息");
		 	if(exit){
		 		$('#tt').tabs('select',"回帖信息");
		 	}else{
		 		$('#tt').tabs('add',{
                title: '回帖信息',
                 plain:false,
              	  href:'findallget.do',
               	 closable: true
            });
		 	}
            
        }
         function searchUser(){
		 	var exit= $('#tt').tabs('exists', "所有用户");
		 	if(exit){
		 		$('#tt').tabs('select',"所有用户");
		 	}else{
		 		$('#tt').tabs('add',{
                title: '所有用户',
                 plain:false,
               href:'findallusers.do', 
               	 closable: true
            });
		 	}
            
        }
	
	function uselasting(page,maxPage){
	if(page==1){
		return;
	}
		var currtab=$('#tt').tabs('getSelected');
				var url=currtab.panel('options').href;
				$('#tt').tabs('update',{
					tab:currtab,
					options:{
						href:"findallusers.do?pageNo="+(page-1)
					}
				});
				currtab.panel('refresh');
	
	}
	function usernexting(page,maxPage){
	if(page==maxPage){
		return;
	}
		var currtab=$('#tt').tabs('getSelected');
				var url=currtab.panel('options').href;
				$('#tt').tabs('update',{
					tab:currtab,
					options:{
						href:"findallusers.do?pageNo="+(page+1)
					}
				});
				currtab.panel('refresh');
	
	}

	
</script>
</head>

<body>

	<p:choose>
		<p:when test="${sessionScope.users.state==3}">
			<h2>BBS论坛 简易后台管理系统</h2>
			<p style="text-align:right;width:1000px;margin:0px auto; "><a href="pages/index.jsp" style="color:blue">返回</a></p>
			<div style="margin:20px 0;"></div>
			<div class="easyui-layout"
				style="width:1200px;height:600px; margin:0px auto;text-align:center">
				<div id="p" data-options="region:'west',split:true" title="后台功能区"
					style="width:20%;">
					<div class="easyui-accordion" data-options="fit:false,border:false"
						style="width:100%;">
						<div title="帖子管理" style="padding:10px;"
							data-options="iconCls:'icon-tip'">
							<ul>
								<li style="text-align:center;margin:10px auto;"><a
									class="easyui-linkbutton" data-options="iconCls:'icon-redo' ,selected:true"
									onclick="addsend()">发帖信息</a></li>
								<li style="text-align:center"><a class="easyui-linkbutton"
									data-options="iconCls:'icon-undo'" onclick="addget()">回帖信息</a></li>
							</ul>
						</div>
						<div title="版块管理" 
							style="padding:5px;">
							content2
						</div>
						<div title="用户管理" style="padding:10px" data-options="iconCls:'icon-lock'">
							<ul>
								<li style="text-align:center;margin:10px auto;"><a
									class="easyui-linkbutton" data-options="iconCls:'icon-man' ,selected:true"
									onclick="searchUser()">所有用户</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div data-options="region:'center'" title="详细信息"
					style="overflow:hidden;">
						<div style="float:right;margin-right:10px;margin-top:20px;">
						<label>选择版块信息</label>
						 <select class="easyui-combobox"
							name="bordername" id="sel"  >
							<option value="all" borderid='1'>所有</option>
							<option value=".Net技术">.Net技术</option>
							<option value="Jave技术" borderid='1'>Jave技术</option>
							<option value="数据技术">数据库技术</option>
							<option value="娱乐">娱乐</option>
							<option value="c#语言">c#语言</option>
							<option value="Winform">Winform</option>
							<option value="java基础">java基础</option>
							<option value="jsp技术">jsp基础</option>
							<option value="SQL基础">SQL基础</option>
							<option value="SQl server高级">SQL serber高级</option>
							<option value="灌水乐园">灌水乐园</option>
							<option value="ios">ios</option>
							<option value="javac">favac</option>
						</select>
					</div>
					<div id="tt" class="easyui-tabs" data-options="tools:'#tab-tools'"
						plain="true" style="width:100%;height:580px;margin-top:20px;" >
						<div title="首页" style="padding:10px">
							<p style="font-size:14px">ssss</p>
						</div>
					</div>

				</div>
			</div>

		</p:when>
		<p:otherwise>
  		你没有权限 
  	<p style="text-align:right;width:1000px;margin:0px auto; "><a href="pages/index.jsp" style="color:blue">返回</a></p>
  </p:otherwise>
	</p:choose>

</body>
</html>
