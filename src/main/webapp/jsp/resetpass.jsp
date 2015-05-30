<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#addcate").validate({
			rules : {
				name : {
					required : true,
					minlength : 2,
					maxlength : 10
				}
			},
			messages : {
				name : {
					required : "请输入名称",
					minlength : "名称最小为2个字",
					maxlength : "名称长度不能超过10个字"
				}
			},
			submitHandler : function(form) {
				var name = $("#name").val();
				addCate(name);
				//form.submit();
			}
		});
	});

	function resetPassword(){
		$.ajax({
			type : "post",
			url : "../d/resetPassword.action",
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
</script>
</head>
<body class=" theme-blue">
	<div class="content_other">
	<!-- error, info, success -->
	<div id="alert" class="alert alert-success" style="display: none;">
        <button type="button" class="close" data-dismiss="alert">×</button>
        	 添加成功!
    </div>
		<div class="main-content">
			<form id="addcate">
				<div class="form-group">
					<label>分类名称</label> <input id="name" name="name" type="text"
						class="form-control">
				</div>
				<button class="btn btn-primary">
					<i class="fa fa-save"></i> Save
				</button>
			</form>
		</div>
	</div>
</body>
</html>
