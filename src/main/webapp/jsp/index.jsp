<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>车帮手商家管理系统</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../lib/font-awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="../lib/datatable/dataTables.bootstrap.css">
<link rel="stylesheet" type="text/css" href="../css/theme.css">
<link rel="stylesheet" type="text/css" href="../css/premium.css">


<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.navbar-default .navbar-brand,.navbar-default .navbar-brand:hover {
	color: #fff;
}
</style>
</head>
<body class=" theme-blue">

	<div class="navbar navbar-default">
		<div class="navbar-header">
			<a class="" href="#"><span class="navbar-brand"><span class="fa fa-paper-plane"></span> 车帮手商家管理系统</span></a>
		</div>

		<div class="navbar-collapse collapse" style="height: 1px;">
			<ul id="main-menu" class="nav navbar-nav navbar-right">
				<li class="dropdown hidden-xs"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="glyphicon glyphicon-user padding-right-small" style="position: relative; top: 3px;"></span> ${sessionScope.user.name } <i class="fa fa-caret-down"></i>
				</a>

					<ul class="dropdown-menu">
						<li><a tabindex="-1" href="../mobile/logout.action">注销</a></li>
						<li><a tabindex="-1" onclick="openpage('changePass')">修改密码</a></li>
					</ul></li>
			</ul>
		</div>
	</div>

	<div class="sidebar-nav">
		<ul>
			<li><a href="#" data-target=".dashboard-menu" class="nav-header" data-toggle="collapse"><i class="fa fa-fw fa-dashboard"></i> 控制面版<i class="fa fa-collapse"></i></a></li>
			<li>
				<ul class="dashboard-menu nav nav-list collapse in">
					<li><a href="javascript:void(0);" onclick="openpage('consume')"><span class="fa fa-caret-right"></span> 快速消费</a></li>
					<li><a href="javascript:void(0);" onclick="openpage('todayOrder')"><span class="fa fa-caret-right"></span> 今日订单</a></li>
				</ul>
			</li>
			<li data-popover="true" data-placement="right"><a href="#" data-target=".premium-menu" class="nav-header collapsed" data-toggle="collapse"> <i class="fa fa-fw fa-fighter-jet"></i> 业务管理<i class="fa fa-collapse"></i></a></li>
			<li>
				<ul class="premium-menu nav nav-list collapse">
					<li><a href="javascript:void(0);" onclick="openpage('goodsManage')"><span class="fa fa-caret-right"></span> 商品管理</a></li>
					<li><a href="javascript:void(0);" onclick="openpage('orderManage')"><span class="fa fa-caret-right"></span> 订单管理</a></li>
				</ul>
			</li>

			<li><a href="#" data-target=".accounts-menu" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-fw fa-list-alt"></i> 报表系统 <i class="fa fa-collapse"></i></a></li>
			<li>
				<ul class="accounts-menu nav nav-list collapse">
					<li><a href="javascript:void(0);" onclick="openpage('goods')"><span class="fa fa-caret-right"></span> 商品</a></li>
					<li><a href="javascript:void(0);" onclick="alert('该功能正在开发中，敬请期待！')"><span class="fa fa-caret-right"></span> 订单</a></li>
				</ul>
			</li>
			<li><a href="#" data-target=".shop-settings" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-fw fa-home"></i> 商家设置 <i class="fa fa-collapse"></i></a></li>
			<li>
				<ul class="shop-settings nav nav-list collapse">
					<li><a href="javascript:void(0);" onclick="openpage('store_basic')"><span class="fa fa-caret-right"></span> 基本信息</a></li>
					<li><a href="javascript:void(0);" onclick="openpage('store_icon')"><span class="fa fa-caret-right"></span> 店标设置</a></li>
					<li><a href="javascript:void(0);" onclick="openpage('store_images')"><span class="fa fa-caret-right"></span> 图片设置</a></li>
				</ul>
			</li>
			<li><a href="#" data-target=".tools" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-fw fa-gear"></i> 商家工具 <i class="fa fa-collapse"></i></a></li>
			<li>
				<ul class="tools nav nav-list collapse">
					<li><a href="javascript:void(0);" onclick="alert('该功能正在开发中，敬请期待！')"><span class="fa fa-caret-right"></span> 图片剪切</a></li>
					<li><a href="javascript:void(0);" onclick="alert('该功能正在开发中，敬请期待！')"><span class="fa fa-caret-right"></span> 计算器</a></li>
				</ul>
			</li>
		</ul>
	</div>

	<div class="content">
		<div id="maincontent">
			<div class="main-content">
				<div class="panel panel-default">
					<p class="panel-heading">平台寄语</p>
					<div class="panel-body">
						<h2>欢迎使用车帮手商家管理系统</h2>
						<p>在这里您可以：</p>
						<ul>
							<li>设置店铺信息</li>
							<li>管理订单</li>
							<li>管理商品</li>
							<li>查看报表</li>
						</ul>

						<p>如需帮助，请随时联系我们：</p>

						<small>公司地址：宁波市鄞州区四明东路111号 </small><br> <small>联系电话：0574-87195560</small>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer -->
	<footer>
		<hr>
		<p class="pull-right">
			后台管理系统 by <a href="http://weibo.com/u/5506812360" target="_blank">扳手科技</a>
		</p>
		<p>
			© 2014 <a href="http://weibo.com/u/5506812360" target="_blank">扳手科技</a>
		</p>
	</footer>
	<script type="text/javascript" src="../lib/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="../lib/jquery.cookie.js"></script>
	<script type="text/javascript" src="../lib/datatable/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="../lib/datatable/dataTables.bootstrap.js"></script>
	<script type="text/javascript" src="../lib/jquery.validate.min.js"></script>
	<script type="text/javascript" src="../lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="../lib/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="../lib/highcharts/exporting.js" charset="utf-8"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$('#example').DataTable();
	});
	function openpage(command) {
		if (command == "todayOrder") {
			$("#maincontent").load("orderManage.jsp", {"flag" : "today"});
		} else if (command == "orderManage") {
			$("#maincontent").load("orderManage.jsp", {"flag" : "all"});
		} else if(command == "store_basic"){
			$("#maincontent").load("store/basic.jsp");
		} else if(command == "store_icon"){
			$("#maincontent").load("store/storeIcon.jsp");
		} else if(command == "store_images"){
			$("#maincontent").load("store/storeImages.jsp");
		} else {
			$("#maincontent").load(command + ".jsp");
		}
	}
</script>
</body>
</html>
