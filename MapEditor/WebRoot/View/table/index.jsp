<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>地图小家</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<link rel="stylesheet" type="text/css" href="/MapEditor/CSS/Main.css">
<link rel="stylesheet" type="text/css"
	href="/MapEditor/CSS/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/MapEditor/CSS/dashboard.css">
<script>
	if (!navigator.onLine) {
		//无网络
		alert("抱歉，请先联网！否则不能正常使用");
		window.close();
	//执行离线状态时的任务
	}
</script>
<script type="text/javascript" src="/MapEditor/JS/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/MapEditor/JS/bootstrap.min.js"></script>
<script type="text/javascript" src="/MapEditor/JS/Table.js"></script>

</head>

<body>
	<!--这是位于最上方的可以伸缩的菜单栏，宽度充满屏幕，悬浮在最顶上-->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<!--navbar-inverse 表示带反色（黑色背景，白色文字）的导航栏，navbar-fixed-top 表示 一直停留在顶部  添加 role="navigation"，有助于增加可访问性-->
	<div class="container-fluid">
		<!--流动的容器-->
		<div class="navbar-header">

			<!--这是按钮  缩放按钮-->
			<button id="button_show_right" type="button"
				class="navbar-toggle collapsed" data-toggle="collapse"
				data-target="#navbar-right" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"> <!--<img src="img/th.jpg" style="height: 100%;width: 100%;"/>-->
				这是文字/图片logo <!--条件：图片要控制宽度，文字也要控制长度，不然会溢出-->
			</a>
			<!--这是logo标志-->
		</div>

		<!--这是向右对齐的列表-->
		<div id="navbar-right" class="navbar-collapse collapse">
			<!--这是一个汉堡按钮-->
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/MapEditor/Map/Index">首页</a></li>	
				<li><a href="/MapEditor/Table/Index">列表</a></li>
				<li><a href="#">登陆</a></li>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search...">
			</form>
		</div>

	</div>
	</nav>


	<!--大界面-->
	<div class="container-fluid " style="height: 100%;">
		<!--流动的容器-->
		<div class="row" style="height: 100%;">
			<!--这是CSS的自定义样式-->
			<div id="navbar-left" class="col-sm-3 col-md-2 sidebar">
				<!--sidebar  边栏-->
				<ul class="nav nav-sidebar">
					<!--这是列表组-->
					<li><a href="/MapEditor/Map/Index">展示中心</a></li>
					<li class="active"><a href="/MapEditor/Table/Index">信息列表</a></li>

				</ul>
				<!-- 				<ul class="nav nav-sidebar"> -->
				<!-- 					<li> -->
				<!-- 						<div id="r-result"> -->
				<!-- 							请输入:<input type="text" id="suggestId" size="20" -->
				<!-- 								style="width:auto ;" /> -->
				<!-- 						</div> -->
				<!-- 						<div id="searchResultPanel" -->
				<!-- 							style="border: 1px solid #C0C0C0; width: 150px; height: auto; display: none; "></div> -->
				<!-- 					</li> -->
				<!-- 				</ul> -->
			</div>
			<!--列表组到这里完成-->


			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="height: 100%;">
				<!--这是表单-->
				<table class="table">
					<thead>
						<tr>
							<th>编号</th>
							<th>地区</th>
							<th>公建</th>
							<th>医务</th>
							<th>肄业</th>
							<th>社区</th>
							<th>报名</th>
							<th>上门</th>
							<th>编辑</th>
							<th>删除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="list" varStatus="xh">
							<tr>
								<td><input class="txtAutoWidth txtstyle" readonly="readonly" type="number" value="${xh.count}"  /></td>
								<td class="td_name"><input class="txtAutoWidth txtstyle" readonly="readonly"  type="text" value="${list.getName()}"  /></td>
								<td class="td_gongjian"><input class="txtAutoWidth editInput" type="text" value="${list.getGongjian()}"  /></td>
								<td class="td_yiwu"><input class="txtAutoWidth editInput" type="text" value="${list.getYiwu()}"  /></td>
								<td class="td_yiye"><input class="txtAutoWidth editInput" type="text" value="${list.getYiye()}"  /></td>
								<td class="td_shequ"><input class="txtAutoWidth editInput" type="text" value="${list.getShequ()}"  /></td>
								<td class="td_home"><input class="txtAutoWidth editInput" type="number" value="${list.getGoHomeNums()}"  /></td>
								<td class="td_school"><input class="txtAutoWidth editInput" type="number" value="${list.getGoSchoolNums()}"  /></td>
								<td><a href="javascript:;" class="Edit">开始编辑</a></td>
								<td><a href="javascript:;" class="Delete">删除</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>
