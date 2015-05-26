<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	
%>
<!doctype html>
<html>
<head>
<script type="text/javascript" src="../lib/jcrop/js/jquery.uploadPreview.min.js"></script>
<script type="text/javascript" src="../lib/jcrop/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	function hideTicket() {
		$("#ticket").hide();
	}
	function showTicket() {
		$("#ticket").show();
	}

	$(document).ready(function() {
		setOption();
		
		/*$.uploadPreview({
			input_field : "#imageUpload",
			preview_box : "#image-preview",
			label_field : "#image-label",
			no_label : true
		});*/

		$("#addGoods").validate({
			rules : {
				/*washcate : {
					maxlength : 2,
				},*/
				gname : {
					required : true,
					maxlength : 50,
				},
				priceori : {
					required : true,
					maxlength : 11,
					digits : true,
				},
				pricehere : {
					required : true,
					maxlength : 11,
					digits : true,
				},
				brief : {
					required : true,
					maxlength : 100,
				},
				imageName : {
					required : true,
				}
			},
			messages : {
				/*washcate : {
					maxlength : "请选择分类",
				},*/
				gname : {
					required : "请输入名称",
					maxlength : "名称不能超过50位"
				},
				priceori : {
					required : "请输入原价",
					maxlength : "不能超过11位",
					digits : "必须是数字"
				},
				pricehere : {
					required : "请输入平台价",
					maxlength : "不能超过11位",
					digits : "必须是数字"
				},
				brief : {
					required : "请输入简介",
					maxlength : "简介不能超过100位"
				},
				imageName : {
					required : "请上传文件"
				}
			},
			submitHandler : function(form) {
				var category = $("#category").val();
				if(category != "选择类别"){
					addGoods();
					/*var image = document.getElementById("imageName").value;
					if (image == "" || image == null) {
						alert("请上传图片");
					} else {
						addGoods();
					}*/
				} else {
					alert("请选择分类");
				}
			}
		});

	});

	function addGoods() {
		var address = "${sessionScope.store.address}";
		var storeNum = "${sessionScope.store.number}";
		var latitude = "${sessionScope.store.latitude}";
		var longitude = "${sessionScope.store.longitude}";
		var imageName = "${sessionScope.store.icon}";
		console.log("address: ", address);
		console.log("storeNum: ", storeNum);
		$.ajax({
			type : "post",
			url : "../d/addGoods.action",
			dataType : "json",
			data : {
				gname : function() {
					return $("#gname").val();
				},
				category : function() {
					return $("#category").val();
				},
				priceori : function() {
					return $("#priceori").val();
				},
				pricehere : function() {
					return $("#pricehere").val();
				},
				brief : function() {
					return $("#brief").val();
				},
				isticket : function() {
					return $('input[name="radio-choice-h-2"]:checked').val();
				},
				ticket : function() {
					return $("#select-choice-a").val();
				},
				imageName : imageName,
				address : address,
				storeNum : storeNum,
				latitude : latitude,
				longitude : longitude,
			},
			success : function() {
				$("#alert").show();
				$("#category").val('');
				$("#gname").val('');
				$("#priceori").val('');
				$("#pricehere").val('');
				$("#brief").val('');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}
	/*function upload() {
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
			url : '../d/uploadImage.action?fileName=' + fileName,
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
	}*/

	function setOption(){
		$.ajax({
			type : "post",
			url : "../d/getCategory.action",
			data : {
			},
			success : function(data) {
				if(data != null){
					var htmlData = data.data;
					console.log("htmlData: ", htmlData);
					
					var htmlString = "<option>选择类别</option>";
					$.each(htmlData, function(i, value) {
						htmlString += 
							"<option value='" + value.name +"'>" + value.name + "</option>";
					});
					
					$("#category").append(htmlString);
				} else {
					alert("网络错误，请重试");	
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}
</script>
<style type="text/css">
.formtable {
	padding-right: 10px;
}
</style>
</head>
<body class=" theme-blue">
	<div class="content_other">
		<div id="alert" class="alert alert-success" style="display: none;">
			<button type="button" class="close" data-dismiss="alert">×</button>
			添加成功!
		</div>
		<div class="main-content">
			<div id="crop"></div>
			<div class="row">
				<div class="col-md-4">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form id="addGoods">
								<table>
									<tr>
										<td>
											<div class="form-group formtable">
												<label>分类</label> 
												<select name="category" id="category" class="form-control">
													
												</select>
											</div>
										</td>
										<td>
											<div class="form-group">
												<label>名称</label> <input type="text" class="form-control" name="gname" id="gname">
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="form-group formtable">
												<label>原价</label> <input type="text" class="form-control" name="priceori" id="priceori">
											</div>
										</td>
										<td>
											<div class="form-group">
												<label>平台价</label> <input type="text" class="form-control" name="pricehere" id="pricehere">
											</div>
										</td>
									</tr>
								</table>
								<div class="form-group">
									<label>简介</label>
									<textarea rows="3" class="form-control" name="brief" id="brief"></textarea>
								</div>

								<!-- <div id="image-preview" style="width: 48px; height: 48px;">
									<label id="image-label"></label><br>
								</div>
								<br>
								<div class="form-group">
									<input type="file" id="imageUpload" name="upload" value="选择图片" /><br> <a href="javascript:void(0);" class="btn btn-success" onclick="upload()">上传图片</a>
								</div> -->
								<div class="form-group">
									<label>是否支持抵用券?</label> <input name="radio-choice-h-2" id="radio-choice-h-2a" value="on" checked="checked" type="radio" onclick="showTicket()"> <label for="radio-choice-h-2a">是</label> <input name="radio-choice-h-2" id="radio-choice-h-2b" value="off" type="radio" onclick="hideTicket()"> <label for="radio-choice-h-2b">否</label>
								</div>

								<div id="ticket">
									<label for="select-choice-a" class="select">选择一个抵用券:</label> <select name="select-choice-a" id="select-choice-a" data-native-menu="false">
										<option value="5">5元</option>
										<option value="10">10元</option>
										<option value="15">15元</option>
										<option value="20">20元</option>
									</select>
								</div>
								<input type="hidden" id="imageName" name="imageName" />
								<div class="btn-toolbar list-toolbar">
									<button class="btn btn-success">
										<i class="fa fa-save"></i> 保存
									</button>
									<a href="#myModal" data-toggle="modal" class="btn btn-danger">清空</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
