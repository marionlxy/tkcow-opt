<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@ include file="/include.jsp"%>
<title></title>
</head>
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
	<div  data-options="region:'north',height:80,border : false">
		<form id="dataForm1" method="post">
		<div class="container_12" style="position: relative;">
		        <br/>
                <div class="grid_1 lbl">开始时间:</div>
                <div class="grid_2 val">
                    <input name="startTime" class="easyui-datetimebox" >
                </div>
                <div class="grid_1 lbl">结束时间:</div>
                <div class="grid_2 val">
                    <input name="endTime"  class="easyui-datetimebox" >
                </div>

                <div class="grid_1 lbl">日志类型:</div>
                <div class="grid_2 val">
                    <select class="easyui-combobox" name="logType" style="width:80px" panelHeight="60px" >
                            <option value=""></option>                      
                            <option value="1">成功日志</option>
                            <option value="0">异常日志</option>
                    </select>
                </div>
                <div class="grid_1 lbl">操作类型:</div>
                <div class="grid_2 val">
                    <select class="easyui-combobox" name="operType" style="width:80px" panelHeight="60px" >
                            <option value=""></option>                      
                            <option value="C">新增</option>
                            <option value="R">读取</option>
                            <option value="U">更新</option>
                            <option value="D">删除</option>
                    </select>
                </div>
                <div class="grid_1 lbl">操作名称:</div>
                <div class="grid_2 val">
                    <input name="operName" class="easyui-textbox" >
                </div>

                <div class="grid_1 lbl">日志信息:</div>
                <div class="grid_2 val">
                    <input name="message" class="easyui-textbox" >
                </div>
                <div class="grid_1 lbl"></div>
                <div class="grid_2 val"">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchInfo()" style="width:90px">查询</a>
                </div>
                <div class="grid_1 lbl">
                     <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-empty" onclick="clearForm()" style="width:90px">清空</a>
                </div>
                <div class="grid_2 val">
                </div>
        </div>
        <div class="container_13" style="position: relative;">
        </div>
        <div class="container_13" style="position: relative;">
        </div>
		</form>
    </div>
    <div data-options="region:'center',border:false"   style="overflow: hidden;">
         <table id="dataTable">
         </table> 
     </div>
       
    <div id="divDialog"> </div>
</div>
<script type="text/javascript">
	
	jQuery(function(){	  
   	//初始化列表
   	$('#dataTable').datagrid({
   		iconCls:'icon-tip',
   		singleSelect : true,
   		rownumbers:true,
   		fit:true,
   		pagination:true,
   		fitColumns:true,
   		method : 'post',		
		idField : 'Log_ID',//此处根据实际情况进行填写
		columns:[[
                            {field:'ck',checkbox:true},
							{field:'Log_ID',title:'日志编码',width:80},
							{field:'Log_Type',title:'日志类型',width:80,
		                        formatter: function(value,row,index) {
		                            if(value=='1'){
		                                return '成功日志';
		                            }else if(value=='2'){
		                                return '异常日志';
		                            }
		                        }},
							{field:'Oper_Type',title:'操作类型',width:80,
	                                formatter: function(value,row,index) {
	                                    if(value=='C'){
	                                        return '新增';
	                                    }else if(value=='R'){
	                                        return '读取';
	                                    }else if(value=='U'){
                                            return '更新';
                                        }else if(value=='D'){
                                            return '删除';
                                        }
	                                }},
							{field:'Oper_Name',title:'操作名称',width:80},
							{field:'Message',title:'日志内容',width:200},
							/* {field:'logLevel',title:'logLevel',width:80},
							{field:'userId',title:'userId',width:80},*/
							{field:'User_Name',title:'操作人',width:80},
							{field:'Create_Time',title:'操作时间',width:80},
							/*{field:'clientIp',title:'clientIp',width:80},
							{field:'reqUrl',title:'reqUrl',width:80},
							{field:'reqMethod',title:'reqMethod',width:80},
							{field:'reqParam',title:'reqParam',width:80},
							{field:'backup1',title:'backup1',width:80},
							{field:'backup2',title:'backup2',width:80}, */
						//注：最后一行后面的逗号要去掉
		]],
		toolbar : [{
			id : 'btnadd',
			text : '查看详细',
			iconCls : 'icon-arrow_nsew',
			handler : function() {
				showdetail();
			}
		}],
		onLoadSuccess : function(data) {
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
   	
   	//初始化下拉框-示例，请根据需要自定义实现
   	/*
   	 $('#combo1').combobox({    
  	        url:'${rootPath}/getDictlist?dicttypeid=userstatus',    
  	        valueField:'dictid',    
  	        textField:'dictname',
  	     	panelHeight:'auto'
  	    }); 
  	  */ 
   });
   
	//查看详细信息
	function showdetail() {
	    var row = $('#dataTable').datagrid('getSelected');
	    if (row) {
	        url = '${rootPath}/log/showDetailPage?rowId=' + row.Log_ID;
	        $("#divDialog").dialog({
	            title : "日志详细信息",
	            width : 450,
	            height : 550,
	            href : url,
	            cache : false,
	            closed : false,
	            modal : true
	        });
	        // openWin(url);
	    } else {
	        $.messager.alert('提示', "请选择你要查看的记录", 'info');
	        return;
	    }
	}
   //表格查询
	function searchInfo() {
		//查询参数直接添加在queryParams中
		var queryParams = $('#dataTable').datagrid('options').queryParams;
		var fields = $('#dataForm1').serializeArray(); //自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
			queryParams[field.name] = field.value; //设置查询参数
	
		});
		var url = '${rootPath}/log/getBycomLogList';
		$('#dataTable').datagrid('reload',url); //设置好查询参数 reload 一下就可以了
	}
	
	//清空查询条件
	function clearForm() {
		$('#dataForm1').form('clear');
	
	}

   //点击增加弹出增加窗口
	function openWin(url) {
		$('#iframeDialogSelect').attr("src", url);
		$('#divDialog').window('open');

	}
	
	//关闭弹出窗口，返回父页面,根据标记决定是否执行查询操作
	function returnParent(flag) {
		if(flag==1) {
			searchInfo();
		}
		$("#divDialog").window('close');
	}
		
</script>
</body>
</html>