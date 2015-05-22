<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#addcate").validate({
			rules : {
				password : {
					required : true,
					minlength : 15,
					maxlength : 15
				}
			},
			messages : {
				password : {
					required : "请输入消费密码",
					minlength : "消费密码为60开头的15位数字",
					maxlength : "消费密码为60开头的15位数字"
				}
			},
			submitHandler : function(form) {
				var pass = $("#password").val();
				consume(pass);
				//form.submit();
			}
		});
	});

	function consume(pass) {
		$.ajax({
			type : "post",
			url : "../d/consume.action",
			dataType : "json",
			data: {
            	password : pass
          	},
          	success: function (data){
              	var result = data.data;
              	alert(result);
              	if(result == "success"){
              		$("#alert").show();
    				$("#password").val('');
                } else if(result == "already"){
                	$("#alert_already").show();
    				$("#password").val('');
                } else {
                	$("#alert_error").show();
    				$("#password").val('');
                }
          		
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
        	 兑换成功!
    </div>
    <div id="alert_already" class="alert alert-danger" style="display: none;">
        <button type="button" class="close" data-dismiss="alert">×</button>
        	 兑换失败，该密码已使用过!
    </div>
    <div id="alert_error" class="alert alert-danger" style="display: none;">
        <button type="button" class="close" data-dismiss="alert">×</button>
        	 兑换失败，网络发生位置错误!
    </div>
		<div class="main-content">
			<form id="addcate">
				<div class="form-group">
					<label>请输入消费密码</label> <input id="password" name="password" type="text"
						class="form-control">
				</div>
				<button class="btn btn-primary">
					<i class="fa fa-save"></i> 兑换
				</button>
			</form>
		</div>
	</div>
</body>
</html>
