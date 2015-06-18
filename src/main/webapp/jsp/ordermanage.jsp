<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String flag = request.getParameter("flag").toString();
	System.out.println("flag: " + flag);
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript">
	$(document).ready(function() {
		var flag = "<%= flag%>";
		console.log("flag: ", flag);
		tableRender(flag);
	});

	function tableRender(flag) {
		var url = "";
		if(flag == "today"){
			url = "../d/getTodayOrder.action";
		} else {
			url = "../d/getAllOrder.action";
		}
		
		$('#order').dataTable({
			destroy : true,
			"ajax" : url,
			"columns" : [ {
				"data" : "number"
			}, {
				"data" : null,
				render : function(data, type, row) {
					return Number(data.total) + Number(data.ticket);
				}
			}, {
				"data" : "total"
			}, {
				"data" : null,
				render : function(data, type, row) {
					if (data.total != 0) {
						return "是";
					} else {
						return "否";
					}
				}
			}, {
				"data" : "ticket"
			}, {
				"data" : "status"
			}, {
				"data" : "creatTime"
			}, {
				"data" : "payTime"
			}, {
				"data" : null,
				render : function(data, type, row) {
					return "<a href='javascript:void(0);' onclick=deleteRow('" + data.number + "','" + flag +"')><i class='fa fa-pencil'></i></a>" + "&nbsp;&nbsp;" + "<a href='javascript:void(0);' onclick=deleteRow('" + data.number + "','" + flag +"')><i class='fa fa-trash-o'></i></a>";
				}
			} ]
		});
	}

	function deleteRow(number, flag) {
		console.log("row number: ", number);
		$.ajax({
			type : "post",
			url : "../d/deleteOrder.action",
			dataType : "json",
			data : {
				number : number
			},
			success : function(data) {
				console.log("data: ", data.data);
				if(data.data == "success"){
					$("#delete_alert").show();
					tableRender(flag);
				} else {
					$("#delete_alert").html("删除失败，请重试");
					$("#delete_alert").show();
				}
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}
</script>

</head>
<body class="theme-blue">
	<div class="content_other">
		<div id="delete_alert" class="alert alert-success" style="display: none;">
			<button type="button" class="close" data-dismiss="alert">×</button>
			删除成功!
		</div>
		<div class="main-content">
			<div class="btn-toolbar list-toolbar">
				<button class="btn btn-primary" onclick="openpage('addgoods')">
					<i class="fa fa-plus"></i> 添加商品
				</button>
				<!-- <button class="btn btn-default">Import</button>
				<button class="btn btn-default">Export</button> -->
				<div class="btn-group"></div>
			</div>

			<div class="panel-body">
				<div class="dataTable_wrapper">
					<table class="table table-striped table-bordered table-hover" id="order">
						<thead>
							<tr>
								<th>订单编号</th>
								<th>原价(元)</th>
								<th>最终价(元)</th>
								<th>是否折扣</th>
								<th>折扣(元)</th>
								<th>订单状态</th>
								<th>创建时间</th>
								<th>支付时间</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>