<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
${
demo
.css
}
</style>
<script type="text/javascript">
	$(function () {
		var storeName = "${sessionScope.store.name}";
	    $('#container').highcharts({
	        chart: {
	            type: 'line'
	        },
	        title: {
	            text: "宁波宝成4S店 4月份销量走势图"
	        },
	        subtitle: {
	            text: '来源: 车帮手服务平台'
	        },
	        xAxis: {
	            categories: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30']
	        },
	        yAxis: {
	            title: {
	                text: '销量(件)'
	            }
	        },
	        plotOptions: {
	            line: {
	                dataLabels: {
	                    enabled: true
	                },
	                enableMouseTracking: false
	            }
	        },
	        series: [{
	            name: '洗车',
	            data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6, 7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
	        }/*,{
            name: '保养',
            data: [2.0, 5.9, 7.5, 5.5, 12.4, 24.5, 22.2, 16.5, 21.3, 15.3, 3.9, 7.6, 17.0, 3.9, 5.5, 12.5, 14.4, 11.5, 22.2, 20.5, 18.3, 8.3, 23.9, 6.6, 15.2, 6.5, 16.3, 8.3, 1.9, 12.6]
        }*/]
	    });
	});
</script>
</head>
<body>
	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	<div>
		销售详情
	</div>
</body>
</html>
