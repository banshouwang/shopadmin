<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>

</head>
<body class=" theme-blue">
	<div class="content_other">
		<!-- error, info, success -->
		<div id="alert" class="alert alert-success" style="display: none;">
			<button type="button" class="close" data-dismiss="alert">×</button>
			添加成功!
		</div>
		<div class="main-content">
			<form id="changePass">
				<div class="form-group">
					<label>原密码</label> <input id="oripass" name="oripass" type="password" class="form-control">
				</div>
				<div class="form-group">
					<label>新密码</label> <input id="newpass" name="newpass" type="password" class="form-control">
				</div>
				<div class="form-group">
					<label>再次输入新密码</label> <input id="newpassagain" name="newpassagain" type="password" class="form-control">
				</div>
				<button class="btn btn-primary">
					<i class="fa fa-save"></i> Save
				</button>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#changePass").validate({
				rules : {
					oripass : {
						required : true,
						minlength : 2,
						maxlength : 10
					},
					newpass : {
						required : true,
						minlength : 2,
						maxlength : 10
					},
					newpassagain : {
						required : true,
						minlength : 2,
						maxlength : 10,
						equalTo : "#newpass"
					}
				},
				messages : {
					oripass : {
						required : "请输入原密码",
						minlength : "原密码最小为2个字符",
						maxlength : "原密码长度不能超过10个字符"
					},
					newpass : {
						required : "请输入新密码",
						minlength : "新密码最小为2个字",
						maxlength : "新密码长度不能超过10个字"
					},
					newpassagain : {
						required : "请再次输入新密码",
						minlength : "新密码最小为2个字",
						maxlength : "新密码长度不能超过10个字",
						equalTo : "两次密码输入不一样"
					}
				},
				submitHandler : function(form) {
					changePassword();
				}
			});
		});

		function changePassword() {
			var oripass = $("#oripass").val();
			var newpass = $("#newpass").val();
			var mobile = "${sessionScope.user.mobile}";

			$.ajax({
				type : "post",
				url : "../d/changePassword.action",
				dataType : "json",
				data : {
					oripass : oripass,
					newpass : newpass,
					mobile : mobile
				},
				success : function(data) {
					var result = data.data;
					if (result == "oriPassWrong") {
						alert("原密码错误");
					} else if (result == "success") {
						alert("密码修改成功");
					} else {
						alert("网络发生未知异常");
					}
					clearField();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("抱歉，系统发生未知错误");
				}
			});
		}

		function clearField(){
			$("#oripass").val('');
			$("#newpass").val('');
			$("#newpassagain").val('');
		}
	</script>
</body>
</html>
