<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%@ include file="/include.jsp"%>
    <title></title>
    <script type="text/javascript" src="<%=basePath%>/static/hightchart/highstock.js"></script>
    <script type="text/javascript" src="<%=basePath%>/static/hightchart/highcharts.js"></script>
    <script type="text/javascript" src="<%=basePath%>/static/hightchart/highcharts-more.js"></script>
    <script type="text/javascript" src="<%=basePath%>/static/hightchart/exporting.js"></script>
</head>
<body>
<div class="easyui-layout" fit="true" data-options="border:false">
	<div class="easyui-panel" title="查询条件" data-options="region:'north',height:124,border : false">
		<form id="dataForm" >
			<table style="width:100%; height:1%; overflow: hidden;" border="10" 
				cellpadding="0" cellspacing="0"  class="kTable" >
			  <tr>
				 <td class="kTableLabel lbl" style="height:70px;line-height: 70px;">统计开始日期:
				 <input id="createdTime" name="createdTime" class="easyui-datebox" style="width:200px" ></td>
			 	<td valign="middle" align="center" colspan="6" >
			 		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="select_id" style="width:90px">查询</a>              
			 	</td>
			 
			 </tr>
			 <tr>
			 	<td class="kTableLabel lbl" style="padding-left:72px;">说明:此处可以统计两周内订单预订的情况</td>
			 </tr>
			</table>
   </form>
    </div>
    <div style="border:1px solid #95B8E7;border-left:none;border-right:none;width:100%;height:320px;text-align:center;margin-top:130px;">
        <div style="width:20%;float:left;height:310px;margin-left:10%;background:#f5f5f5;border:1px solid #ccc;margin-top:5px;">
        	<ul style="margin-top:20px;list-style:none;">
        		<li style="height:30px;line-height:45px;font-size:18px;color:rgb(6, 6, 153);"><b>用户预定</b></li>
        		<li style="height:50px;line-height:55px;font-size:30px;color:orange;"><b id="today_id"></b></li>
        		<li style="height:50px;line-height:55px;font-size:18px;color:orange;"><b>今天预订用户</b></li>
        		<li style="height:50px;line-height:55px;font-size:30px;color:rgb(201, 179, 138);"><b id="yestoday_id"></b></li>
        		<li style="height:50px;line-height:55px;font-size:18px;color:rgb(201, 179, 138);"><b>昨天预订用户</b></li>
        		<li style="height:30px;line-height:45px;font-size:16px;color:rgb(6, 6, 153);border-top:1px solid #ccc"><b>目前预订用户共计<span id="total_id"></span>人</b></li>
        	</ul>
        </div>
        <div style="width:60%;height:310px;text-align:center;float:left;border:1px solid #ccc;margin-left:1%;margin-top:5px;">
        	<div id="containerPeople" ></div>
        	<div style="font-size:16px;border-top:1px solid #ccc;">
		        <span id="startTime"></span>&nbsp;&nbsp;到&nbsp;&nbsp;<span id="endTime"></span>&nbsp;&nbsp;&nbsp;&nbsp;共<span id="intTotal"></span>个用户预订
        	</div>
        </div>
    </div>

</div>




<script type="text/javascript">
	jQuery(function(){
		var createdTime = $("#createdTime").next("span").find(".textbox-value").val();
		searchInfo(createdTime);
		$("#select_id").click(function(){
			createdTime = $("#createdTime").next("span").find(".textbox-value").val();
			searchInfo(createdTime);
			
		});
	});		    
   //表格查询
	function searchInfo(createdTime) {
		var urlstr;
		if(createdTime!=""&&createdTime!==null&&createdTime!=undefined){
			urlstr="${rootPath}/orderVisit/getTime?dateTime=" + createdTime;
		}else{
			urlstr="${rootPath}/orderVisit/getTime?dateTime=";
		}
			$.ajax({
				url:urlstr,
				type:"post",
				success:function(data){
					var list=new Array();
					for(var i=0;i<data.num.listTimeT.length;i++){
						list.push("");
						list.push(data.num.listTimeT[i]);
					}
					$("#yestoday_id").html(data.yesterday);
					$("#today_id").html(data.today);
					$("#total_id").html(data.all);
					
					$("#startTime").html(data.num.startTime);
					$("#endTime").html(data.num.endTime);
					$("#intTotal").html(data.num.intTotal);
					
					$('#containerPeople').highcharts({
				        chart: {
				            type: 'line',
				            options3d: {
				                enabled: false,
				                alpha: 15,
				                beta: 15,
				                viewDistance: 25,
				                depth: 40
				            },
				            marginTop: 40,
				            marginRight: 40,
				            height:280
				        },

				        title: {
				            text: '预订人数趋势图'
				        },

				        xAxis: {
				            categories:eval(list,"{0:#.00}"),
				            gridLineWidth:1
				        },

				        yAxis: [{
				            allowDecimals: false,
				            min: 0,
				            stackLabels: {   
				            	enabled: false //显示总和标签
				            	},
				            title:{
				                text :'预订人数趋势图'
				            },
					            lineWidth : 1,
					            opposite:false
				        	}],
				        legend: {
					    	enabled: false
					    	},
				    	exporting: {
				            enabled:false
						},
				        tooltip: {
				            headerFormat: '<b>{point.key}</b><br>',
				            pointFormat: '<span style="color:{series.color}">\u25CF</span> {series.name}: {point.y} / {point.stackTotal}'
				            	
				        },

				        plotOptions: {
				            line: {
				                stacking: 'normal',
				                depth: 40,
				                dataLabels:{
				                	enabled:true //是否显示数据标签
				                	},
				        		enableMouseTracking:false
				            }
				        },

				        series: [{
				            name:'',
				            data:eval('['+data.num.listTime+']'),
				            stack: 'sessions',
				            color:'blue',
				            yAxis:0
				        }]
				    });
				}
			});
	}
	
	
	
</script>
</body>
</html>