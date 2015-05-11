<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript">
	$(document).ready(function() {
		$('#cates').dataTable({
			"ajax": "data/getAllCates.action",
			"columns" : [ {
				"data" : "num"
			}, {
				"data" : "name"
			}, {
				"data" : "time"
			}, {
				"data" : "is_valid"
			}]
		});
	});
</script>
</head>
<body class=" theme-blue">
	<div class="content_other">
		<div class="main-content">
			<div class="btn-toolbar list-toolbar">
				<button class="btn btn-primary" onclick="openpage('addcate')">
					<i class="fa fa-plus"></i> 添加分类
				</button>
				<!-- <button class="btn btn-default">Import</button>
				<button class="btn btn-default">Export</button> -->
				<div class="btn-group"></div>
			</div>
			
			<div class="panel-body">
				<div class="dataTable_wrapper">
					<table class="table table-striped table-bordered table-hover" id="cates">
						<thead>
							<tr>
								<th>分类编号</th>
								<th>名称</th>
								<th>日期</th>
								<th>是否有效</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>