<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
</head>
<body class=" theme-blue">
	<div class="content_other">
		<div id="delete_alert" class="alert alert-success" style="display: none;">
			<button type="button" class="close" data-dismiss="alert">×</button>
			删除成功!
		</div>
		<div class="main-content">
			<div class="btn-toolbar list-toolbar">
				<button class="btn btn-primary" onclick="openpage('addGoods')">
					<i class="fa fa-plus"></i> 添加商品
				</button>
				<!-- <button class="btn btn-default">Import</button>
				<button class="btn btn-default">Export</button> -->
				<div class="btn-group"></div>
			</div>

			<div class="panel-body">
				<div class="dataTable_wrapper">
					<table class="table table-striped table-bordered table-hover" id="goods">
						<thead>
							<tr>
								<th>商品编号</th>
								<th>分类</th>
								<th>名称</th>
								<th>原价(元)</th>
								<th>平台价(元)</th>
								<th>抵用券?</th>
								<th>券</th>
								<th>是否有效</th>
								<th>图片</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			tableRender();
		});

		function deleteRow(number) {
			console.log("row number: ", number);
			$.ajax({
				type : "post",
				url : "../d/deleteGoods.action",
				dataType : "json",
				data : {
					number : number
				},
				success : function() {
					console.log("delete success");
					$("#delete_alert").show();
					tableRender();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		}

		function tableRender() {
			var storeNumber = '${sessionScope.store.number}';
			//alert(storeNumber);
			$('#goods').dataTable({
				destroy : true,
				"ajax" : "../d/getAllGoodsByStoreNumber.action?storeNum=" + storeNumber,
				"columns" : [ {
					"data" : "number"
				}, {
					"data" : "category"
				}, {
					"data" : "name"
				}, {
					"data" : "priceori"
				}, {
					"data" : "pricehere"
				}, {
					"data" : "isticket"
				}, {
					"data" : "ticket"
				}, {
					"data" : null,
					render : function(data, type, row){
						if(data.status == "active"){
							return "有效";
						} else if(data.status == "storeDown"){
							return "被商家下架";
						} else if(data.status == "platformDown"){
							return "被平台下架";
						} else {

						}
					}
				}, {
					"data" : "imageName"
				}, {
					"data" : null,
					render : function(data, type, row) {
						return "<a href='javascript:void(0);' onclick=deleteRow('" + data.number + "')><i class='fa fa-pencil'></i></a>" + "&nbsp;&nbsp;" + "<a href='javascript:void(0);' onclick=deleteRow('" + data.number + "')><i class='fa fa-trash-o'></i></a>";
					}
				} ]
			});
		}
	</script>
</body>
</html>