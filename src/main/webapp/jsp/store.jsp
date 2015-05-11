<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#storeDetails").validate({
			rules : {
				storeName : {
					required : true,
					minlength : 2,
					maxlength : 100
				},
				brief : {
					required : true,
					minlength : 2,
					maxlength : 200
				},
				address : {
					required : true,
					minlength : 2,
					maxlength : 100
				},
				tel : {
					required : true,
					digital : true,
					minlength : 7,
					maxlength : 15
				}
			},
			messages : {
				name : {
					required : "请输入店铺名字",
					minlength : "名称最小为2个字",
					maxlength : "名称长度不能超过100个字"
				},
				brief : {
					required : "请输入店铺简介",
					minlength : "简介最小为2个字",
					maxlength : "简介长度不能超过200个字"
				},
				address : {
					required : "请输入店铺地址",
					minlength : "地址最小为2个字",
					maxlength : "地址长度不能超过100个字"
				},
				tel : {
					required : "请输入店铺电话",
					digital : "电话必须是数字",
					minlength : "电话最小为7个字",
					maxlength : "电话长度不能超过15位数"
				}
			},
			submitHandler : function(form) {
				console.log("111111");
				if (checkImages) {
					console.log("22222");
					var names = getImages();
					var action = "../d/storeUpdate.action?storeName=" + $("#storeName").val() + "&brief=" + $("#brief").val() + "&address=" + $("#address").val() + "&tel=" + $("#tel").val() + "&names=" + names;
					console.log("action: ", action);
					form.action = action;
					form.submit();
				} else {
					alert("请上传店铺图片");
				}
			}
		});
	});

	function checkImages() {
		var cookie = document.cookie;
		if (cookie && cookie != '') {
			var cookies = cookie.split(';');
			for (var i = 0; i < cookies.length; i++) {
				var cookieItem = jQuery.trim(cookies[i]);
				if (cookieItem.substring(0, 2) == "ST") {
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	function getImages() {
		var cookie = document.cookie;
		if (cookie && cookie != '') {
			var cookies = cookie.split(';');
			var nameString = "";
			for (var i = 0; i < cookies.length; i++) {
				var cookieItem = jQuery.trim(cookies[i]);
				if (cookieItem.substring(0, 2) == "ST") {
					var data = cookieItem.split("=");
					nameString += data[0] + ",";
					
				}
			}
			return nameString;;
		} else {
			return "";
		}
	}

	function load(param) {
		if (param == "open") {
			$("#imagesUpload").load("upload.html");
			$("#imagesUpload").show();
		}
		if (param == "close") {
			$("#imagesUpload").hide();
		}
	}
	function clearCookie() {
		var cookie = document.cookie;
		if (cookie && cookie != '') {
			var cookies = cookie.split(';');
			for (var i = 0; i < cookies.length; i++) {
				var cookieItem = jQuery.trim(cookies[i]);
				if (cookieItem.substring(0, 2) == "ST") {
					$.cookie(cookieItem.split("=")[0], null, {
						expires : -1
					});
				}
			}
		}
	}
	function update() {
		var storeName = $("#storeName").val();
		var brief = $("#brief").val();
		var address = $("#address").val();
		var tel = $("#tel").val();
		var names = getImages();
		if (storeName == "" || brief == "" || address == "" || tel == "") {
			alert("请填写完整信息");
		} else if (names == "") {
			alert("请上传图片");
		} else {
			$.ajax({
				type : "post",
				url : "../d/storeUpdate.action?storeNum=${sessionScope.store.number}",
				dataType : "json",
				data : {
					storeName : storeName,
					brief : brief,
					address : address,
					tel : tel,
					names : names,
				},
				success : function(data) {
					var result = data.data;
					if (result == "success") {
						$("#alert").show();
					} else {
						$("#alert_error").show();
					}
					clearCookie();
					$("#storeName").val('');
					$("#brief").val('');
					$("#address").val('');
					$("#tel").val('');
					names = "";

				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		}
	}
</script>
</head>
<body class="theme-blue">
	<div class="panel panel-default">
		<p class="panel-heading no-collapse">店铺信息</p>
		<div class="panel-body">
			<form id="storeDetails">
				<div class="form-group">
					<label>店名</label> <input id="storeName" name="storeName" type="text" class="form-control span12" value="${sessionScope.store.name}">
				</div>
				<div class="form-group">
					<label>简介</label>
					<textarea id="brief" name="brief" class="form-control span12 form-control" rows="" cols="">${sessionScope.store.brief}</textarea>
				</div>
				<div class="form-group">
					<label>地址</label> <input id="address" name="address" type="text" class="form-control span12" value="${sessionScope.store.address}">
				</div>
				<div class="form-group">
					<label>电话</label> <input id="tel" name="tel" type="text" class="form-control span12" value="${sessionScope.store.tel}">
				</div>
				<div class="form-group">
					<a href="javascript:void(0);" class="btn btn-default" onclick="load('open')">上传图片</a> <a href="javascript:void(0);" class="btn btn-default" onclick="load('close')">关闭图片</a>
					<div id="imagesUpload"></div>
				</div>
				<div id="alert" class="alert alert-success" style="display: none;">
					<button type="button" class="close" data-dismiss="alert">×</button>
					店铺信息更新成功!
				</div>
				<div id="alert_error" class="alert alert-danger" style="display: none;">
					<button type="button" class="close" data-dismiss="alert">×</button>
					店铺信息更新失败!
				</div>
				<a href="javascript:void(0);" onclick="update()" class="btn btn-primary pull-left">更新店铺</a>
				<div class="clearfix"></div>
			</form>

		</div>
	</div>
</body>
</html>
