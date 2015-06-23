<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<script type="text/javascript" src="../lib/jcrop/js/jquery.uploadPreview.min.js"></script>
<script type="text/javascript" src="../lib/jcrop/js/ajaxfileupload.js"></script>
</head>
<body class="theme-blue">
	<div class="panel panel-default">
		<p class="panel-heading no-collapse">店铺信息</p>
		<div class="panel-body">
			<form id="storeDetails">
				<div class="form-group">
					<label>店标</label>
					<table>
						<tr>
							<td>
								<img id="storeIcon" src="" style="width: 60px; height: 60px;" />
							</td>
							<td>
								<div id="image-preview" style="width: 60px; height: 60px;">
									<label id="image-label"></label><br>
								</div>
							</td>
							<td style="padding-left: 10px;"><input type="file" id="imageUpload" name="upload" value="选择图片" /> <a href="javascript:void(0);" class="btn btn-success" onclick="upload()">上传图片</a></td>
						</tr>
					</table>
				</div>
				<div class="form-group">
					<label>店铺图片</label>
					<table id="storeImages"></table>
				</div>
				<div class="form-group">
					<a href="javascript:void(0);" class="btn btn-default" onclick="load('open')">上传图片</a> <a href="javascript:void(0);" class="btn btn-default" onclick="load('close')">关闭图片</a>
					<div id="imagesUpload"></div>
				</div>
				<div class="form-group">
					<label>店名</label> <input id="storeName" name="storeName" type="text" class="form-control span12" value="${sessionScope.store.name}">
				</div>
				<div class="form-group">
					<label>简介</label>
					<textarea id="brief" name="brief" class="form-control span12 form-control" rows="" cols="">${sessionScope.store.brief}</textarea>
				</div>

				<div class="form-group">
					<label>地址</label><br> <label for="select-choice-a" class="select">选择市区:</label> <select name="select-choice-a" id="select-choice-a" data-native-menu="false">

					</select> <select name="select-choice-b" id="select-choice-b" data-native-menu="false">

					</select> <small>（下面请填写详细地址）</small> <input id="address" name="address" type="text" class="form-control span12" value="${sessionScope.store.address}">
				</div>
				<div class="form-group">
					<label>经度</label> <input id="longitude" name="longitude" type="text" class="form-control span12" value="${sessionScope.store.longitude}">
				</div>
				<div class="form-group">
					<label>纬度</label> <input id="latitude" name="latitude" type="text" class="form-control span12" value="${sessionScope.store.latitude}">
				</div>
				<div class="form-group">
					<label>电话</label> <input id="tel" name="tel" type="text" class="form-control span12" value="${sessionScope.store.tel}">
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
	<script type="text/javascript">
	function setStoreImages(){
		var imagePrefix = "http://banshou.oss-cn-hangzhou.aliyuncs.com/images/store/";
		var htmlString = "";//$("#storeImages").html();
		var tmp = "";
		var image = "${sessionScope.store.image}";
		if(image != ""){
			var images = image.split(",");
			$.each(images, function(i, value) {
				//alert(value);
				htmlString = htmlString + "<td><img src='" + imagePrefix + value + "' style='width: 200px; height: 160px;' /></td>"; 
				
			});
			htmlString = "<tr>" + htmlString + "</tr>" ;
			$.each(images, function(i, value) {
				tmp = tmp + "<td><a href='javascript:void(0);' class='btn btn-success' onclick=deleteImage('" + value + "')>删除</a></td>"; 
				
			});

			tmp = "<tr>" + tmp + "</tr>";
			$("#storeImages").html(htmlString + tmp);
		} else {
			$("#storeImages").html("");
		}
		
	}

	function deleteImage(image){
		var storeNumber = "${sessionScope.store.number}";
		$.ajax({
			type : "post",
			url : "../d/deleteStoreImage.action",
			dataType : "json",
			data : {
				storeNum : storeNumber,
				imageName : image
			},
			success : function(data) {
				if (data.data == "success") {
					alert("删除成功");
				} else {
					alert("网络错误，请重试");
				}

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}
		$(document).ready(function() {
			setStoreImages();
			getAllCity();
			getAllDistrict();
			var imagePrefix = "http://banshou.oss-cn-hangzhou.aliyuncs.com/images/storeIcon/";
			var storeIcon = "${sessionScope.store.icon}";
			if(storeIcon != ""){
				$('#storeIcon').attr('src', imagePrefix + storeIcon);
			} else {
				$('#storeIcon').html("");
			}
			
			$.uploadPreview({
				input_field : "#imageUpload",
				preview_box : "#image-preview",
				label_field : "#image-label",
				no_label : true
			});

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

				}
			});
		});
		
		
		/*function checkImages() {
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
		}*/

		/*function getImages() {
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
				return nameString;
				;
			} else {
				return "";
			}
		}*/

		function load(param) {
			if (param == "open") {
				$("#imagesUpload").show();
				$("#imagesUpload").load("upload.jsp");
			}
			if (param == "close") {
				$("#imagesUpload").hide();
			}
		}
		/*function clearCookie() {
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
		}*/
		function update() {
			var storeName = $("#storeName").val();
			var brief = $("#brief").val();
			var address = $("#select-choice-a").val() + $("#select-choice-b").val() + $("#address").val();
			var tel = $("#tel").val();
			var longitude = $("#longitude").val();
			var latitude = $("#latitude").val();

			//var names = getImages();
			if (storeName == "" || brief == "" || address == "" || tel == "" || longitude == "" || latitude == "") {
				alert("请填写完整信息");
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
						longitude : longitude,
						latitude : latitude
					},
					success : function(data) {
						var result = data.data;
						if (result == "success") {
							$("#alert").show();
						} else {
							$("#alert_error").show();
						}
						//clearCookie();
						$("#storeName").val('');
						$("#brief").val('');
						$("#address").val('');
						$("#tel").val('');
						$("#longitude").val('');
						$("#latitude").val('');
						//names = "";

					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(errorThrown);
					}
				});
			}
		}

		function getAllCity() {
			$.ajax({
				type : "post",
				url : "../d/getAllCity.action",
				dataType : "json",
				success : function(data) {
					var result = data.data;
					if (result != null) {
						setOptionsCity(result);
					} else {
						alert("获取商店列表失败，请重试");
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		}

		function getAllDistrict() {
			$.ajax({
				type : "post",
				url : "../d/getAllDistrict.action",
				dataType : "json",
				success : function(data) {
					var result = data.data;
					if (result != null) {
						setOptions(result);
					} else {
						alert("获取商店列表失败，请重试");
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		}

		function setOptionsCity(data) {
			var htmlString = "";
			$.each(data, function(i, value) {
				htmlString += "<option value='" + value.name +"'>" + value.name + "</option>";
			});

			$("#select-choice-a").append(htmlString);
		}

		function setOptions(data) {
			var htmlString = "";
			$.each(data, function(i, value) {
				htmlString += "<option value='" + value.name +"'>" + value.name + "</option>";
			});

			$("#select-choice-b").append(htmlString);
		}

		function upload() {
			Date.prototype.Format = function(fmt) {
				var o = {
					"M+" : this.getMonth() + 1,
					"d+" : this.getDate(),
					"h+" : this.getHours(),
					"m+" : this.getMinutes(),
					"s+" : this.getSeconds(),
					"q+" : Math.floor((this.getMonth() + 3) / 3),
					"S" : this.getMilliseconds()

				};
				if (/(y+)/.test(fmt))
					fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
				for ( var k in o)
					if (new RegExp("(" + k + ")").test(fmt))
						fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
				return fmt;
			};

			//document.getElementById("imageName").value = fileName;
			var files = $("#imageUpload").val();
			//alert(files);
			if (files == null || files == "") {
				alert('请选择图片');
				return;
			}

			var fileName = new Date().Format("yyyyMMddhhmmss");
			$("#imageName").val(fileName);

			$.ajaxFileUpload({
				url : '../d/uploadImage.action?fileName=' + fileName + '&storeNum=${sessionScope.store.number}',
				type : 'post',
				dataType : 'json',
				secureuri : false,
				fileElementId : 'imageUpload',
				timeout : 100000,
				success : function(data, status) {

				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});

			alert("上传成功");
		}
	</script>
</body>
</html>
