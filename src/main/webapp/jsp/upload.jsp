<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript" src="../lib/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../lib/upload/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="../lib/upload/diyUpload/css/diyUpload.css">
<script type="text/javascript" src="../lib/upload/diyUpload/js/webuploader.html5only.min.js"></script>
<script type="text/javascript" src="../lib/upload/diyUpload/js/diyUpload.js"></script>
<script type="text/javascript" src="../lib/jquery.cookie.js"></script>
</head>
<style>
* {
	margin: 0;
	padding: 0;
}

#box {
	width: 540px;
	min-height: 400px;
	border: 2px solid #dedede;
	-moz-border-radius: 15px;
	-webkit-border-radius: 15px;
	border-radius: 15px;
}
</style>
<body>
	<div id="box">
		<div id="test"></div>
	</div>
</body>
<script type="text/javascript">

$('#test').diyUpload({
	url:"../d/uploadBatch.action?storeNum=${sessionScope.store.number}",
	chunked:true,
	chunkSize:512 * 1024,
	fileNumLimit:5,
	fileSizeLimit:500000 * 1024,
	fileSingleSizeLimit:50000 * 1024,
	accept: {},
	success:function( data ) {
		
	},
	error:function( err ) {
		console.info( err );	
	}
});
</script>
</html>
