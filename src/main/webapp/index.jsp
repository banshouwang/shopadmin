<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.banshou.app.utils.auth.QRCodeUtil"%>
<%@page import="com.banshou.app.utils.common.RandomStrUtil"%>
<%@page import="com.banshou.app.utils.common.Constants"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>车帮手商户管理系统</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="lib/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="lib/jquery.cookie.js"></script>
<script type="text/javascript" src="lib/bootstrap/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="lib/font-awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="css/theme.css">
<style type="text/css">
.navbar-default .navbar-brand,.navbar-default .navbar-brand:hover {
	color: #fff;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#login_user_pwd").hide();
		$("#resetPass").hide();
		$("#validCode").hide();
		$("#newPass").hide();

		$.ajax({
			type : "post",
			url : "d/authQr.action",
			dataType : "json",
			data : {},
			success : function(data) {
				var code = data.data;
				if (code != "" && code != "error") {
					$("#qrcode").attr("src", "images/qr/" + code + ".jpg");
					var timeout = "";
					var count = 0;
					timeout = setInterval(function() {
						$.ajax({
							type : "post",
							url : "d/authQrReload.action",
							dataType : "json",
							data : {
								authCode : code
							},
							success : function(data) {
								var result = data.data;
								if (count < 12) {
									if (result != "error") {
										if (result == "notshop") {
											$("#alert_status").html("您不是商家,请先申请成为商家");
										} else if (result == "yes") {
											$("#alert_status").html("认证通过，请确认登录");
										} else if (result == "confirm") {
											location.href = "mobile/login.action?authCode=" + data.code;
										} else if (result == "unreg") {
											$("#alert_status").html("您不是车帮手用户!");
										}
									} else {
										$("#alert_error").show();
									}
								} else {
									clearInterval(timeout);
									$("#alert_status").html("认证过期，请刷新页面重新认证!");
									setExpried(code);
								}

								count++;
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								alert(errorThrown);
							}
						});
					}, 5000);
				} else {
					$("#alert_error").show();
				}

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	});

	function setExpried(code) {
		$.ajax({
			type : "post",
			url : "d/authExpired.action",
			dataType : "json",
			data : {
				authCode : code
			},
			success : function(data) {
				var status = data.data;
				if (status != "error") {
					return status;
				} else {
					return "error";
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}

	function loginMode(flag) {
		if (flag == "forgetPass") {
			$("#login_qrcode").hide();
			$("#login_user_pwd").hide();
			$("#resetPass").show();
			$("#validCode").hide();
			$("#newPass").hide();
		} else if (flag == "user") {
			$("#login_qrcode").hide();
			$("#login_user_pwd").show();
			$("#resetPass").hide();
			$("#validCode").hide();
			$("#newPass").hide();
		} else {
			$("#login_user_pwd").hide();
			$("#login_qrcode").show();
			$("#resetPass").hide();
			$("#validCode").hide();
			$("#newPass").hide();
		}
	}

	function login() {
		$.ajax({
			type : "post",
			url : "d/login.action",
			dataType : "json",
			data : {
				mobile : function() {
					return $("#mobile").val();
				},
				password : function() {
					return $("#password").val();
				}
			},
			success : function(data) {
				var status = data.data;
				if (status != "error") {
					location.href = "jsp/index.jsp";
				} else {
					$("#alert_fail").show();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}

	function addDataToCookie(key, value) {
		var cookie = document.cookie;
		$.cookie(key, value, {
			expires : 1
		});
	}

	function getDataFromCookie(key) {
		var cookie = document.cookie;

		if (cookie && cookie != '') {
			var cookies = cookie.split(';');
			for (var i = 0; i < cookies.length; i++) {
				var cookieItem = jQuery.trim(cookies[i]);
				var items = cookieItem.split("=");
				if (items[0] == key) {
					return items[1];
				}
			}
		}
	}

	function send() {
		var mobile = $("#mobileValid").val();
		if (mobile != "") {
			$.ajax({
				type : "post",
				url : "d/valid.action",
				dataType : "json",
				data : {
					mobile : function() {
						return $("#mobileValid").val();
					}
				},
				success : function(data) {
					var status = data.data;
					if (status != "error") {
						var validCode = data.validCode;
						addDataToCookie("mobile", mobile);
						$("#vcode").val(validCode);
						$("#resetPass").hide();
						$("#validCode").show();
					} else {
						$("#alert_fail_reset").show();
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		} else {
			alert("请输入手机号");
		}
	}

	function valid() {
		var validCode = $("#code").val();
		if (validCode != "") {
			var validCode2 = $("#vcode").val();;
			if (validCode == validCode2) {
				$("#validCode").hide();
				$("#newPass").show();
			} else {
				alert("验证出错，请重试");
			}
		} else {
			alert("请输入验证码");
		}
	}

	function reset() {
		var newpass = $("#newpass").val();
		var newpass2 = $("#newpass2").val();
		var mobile = getDataFromCookie("mobile");
		
		if(newpass == "" || newpass2 == ""){
			alert("请输入新密码");
		} else {
			if (newpass == newpass2) {
				$.ajax({
					type : "post",
					url : "d/reset.action",
					dataType : "json",
					data : {
						newpass : newpass,
						mobile : mobile
					},
					success : function(data) {
						var status = data.data;
						if (status == "success") {
							alert("修改成功");
							$("#newpass").val('');
							$("#newpass2").val('');
							$("#newPass").hide();
							$("#alert_fail").hide();
							$("#login_user_pwd").show();
						} else {
							$("#alert_fail_newpass").show();
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(errorThrown);
					}
				});
			} else {
				alert("两次密码输入不一致");
			}
		}
	}
</script>
</head>
<body class=" theme-blue">
	<div class="navbar navbar-default">
		<div class="navbar-header">
			<a class="" href="index.html"><span class="navbar-brand"><span class="fa fa-paper-plane"></span> 车帮手商户后台管理系统</span></a>
		</div>

	</div>
	<div class="dialog" id="login_qrcode">
		<div id="alert_error" class="alert alert-success" style="display: none;">
			<button type="button" class="close" data-dismiss="alert">×</button>
			系统错误，请刷新页面重试!
		</div>
		<div id="alert_timeout" class="alert alert-success" style="display: none;">
			<button type="button" class="close" data-dismiss="alert">×</button>
			认证过期，请刷新页面重试!
		</div>

		<div id="alert_status" class="alert alert-success">认证中，请在1分钟内用微信扫描登录...</div>

		<div class="panel panel-default">

			<p class="panel-heading no-collapse">请用微信扫描下面二维码</p>
			<div class="panel-body">
				<img id="qrcode" src="" style="margin-left: 35px;">
			</div>
		</div>
		<p>
			<a href="javascript:void(0);" onclick="loginMode('user')">使用用户名密码登录</a>
		</p>
	</div>
	<div class="dialog" id="login_user_pwd">
		<div id="alert_fail" class="alert alert-danger" style="display: none;">
			<button type="button" class="close" data-dismiss="alert">×</button>
			登录失败，请重试!
		</div>
		<div class="panel panel-default">
			<p class="panel-heading no-collapse">登录</p>
			<div class="panel-body">
				<form>
					<div class="form-group">
						<label>手机号</label> <input id="mobile" name="mobile" type="text" class="form-control span12">
					</div>
					<div class="form-group">
						<label>密码</label> <input id="password" name="password" type="password" class="form-controlspan12 form-control">
					</div>
					<a href="javascript:void(0)" onclick="login()" class="btn btn-primary pull-right">登录</a>
					<!-- <label class="remember-me"><input type="checkbox"> Remember me</label> -->
					<div class="clearfix"></div>
				</form>
			</div>
		</div>
		<p>
			<a href="javascript:void(0);" onclick="loginMode('forgetPass')">忘记密码</a>
		</p>
		<p>
			<a href="javascript:void(0);" onclick="loginMode('qrcodes')">扫描二维码登录</a>
		</p>
	</div>

	<div class="dialog" id="resetPass">
		<div id="alert_fail_reset" class="alert alert-danger" style="display: none;">
			<button type="button" class="close" data-dismiss="alert">×</button>
			发送失败，请重试!
		</div>
		<div class="panel panel-default">
			<p class="panel-heading no-collapse">通过验证手机重置密码</p>
			<div class="panel-body">
				<form>
					<div class="form-group">
						<label>手机号</label> <input id="mobileValid" name="mobileValid" type="text" class="form-control span12">
					</div>
					<a href="javascript:void(0)" onclick="send()" class="btn btn-primary pull-right">发送</a>
					<div class="clearfix"></div>
				</form>
			</div>
		</div>
	</div>

	<div class="dialog" id="validCode">
		<div id="alert_fail_reset_valid" class="alert alert-danger" style="display: none;">
			<button type="button" class="close" data-dismiss="alert">×</button>
			验证失败，请重试!
		</div>
		<div class="panel panel-default">
			<p class="panel-heading no-collapse">输入手机验证码</p>
			<div class="panel-body">
				<form>
					<div class="form-group">
						<label>验证码</label> <input id="code" name="code" type="text" class="form-control span12">
						<input id="vcode" name="vcode" type="hidden">
					</div>
					<a href="javascript:void(0)" onclick="valid()" class="btn btn-primary pull-right">验证</a>
					<div class="clearfix"></div>
				</form>
			</div>
		</div>
	</div>

	<div class="dialog" id="newPass">
		<div id="alert_fail_newpass" class="alert alert-danger" style="display: none;">
			<button type="button" class="close" data-dismiss="alert">×</button>
			修改失败，请重试!
		</div>
		<div class="panel panel-default">
			<p class="panel-heading no-collapse">输入新密码</p>
			<div class="panel-body">
				<form>
					<div class="form-group">
						<label>新密码</label> <input id="newpass" name="newpass" type="password" class="form-control span12">
					</div>
					<div class="form-group">
						<label>再次输入新密码</label> <input id="newpass2" name="newpass2" type="password" class="form-control span12">
					</div>
					<a href="javascript:void(0)" onclick="reset()" class="btn btn-primary pull-right">提交</a>
					<div class="clearfix"></div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
