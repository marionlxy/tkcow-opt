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
</head>
<body>
<div>
	<form id="dataForm">
			<div class="fitem">
		    	<label>类型:</label>
		        <input name="dictTypeId" class="easyui-textbox" value='${dictTypeId}' readonly="true">
			</div>
			<div class="fitem">
		    	<label>编码:</label>
		        <input name="dictId" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>标题:</label>
		        <input name="dictName" class="easyui-textbox"  data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>描述:</label>
		        <input name="backup2" class="easyui-textbox">
			</div>
			<!-- <div class="fitem">
		    	<label>排序(整数):</label>
		        <input name="sortNo" class="easyui-numberbox"  data-options="required:true">
			</div> -->
			<input name="backup1" type="hidden" id="time" value="">
			<input name="status" type="hidden" value="1">
    <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()" style="width:90px">提交</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.parent.wrapper.rightMenuClick({ id: 'close' })" style="width:90px">取消</a>
   </div>
</div>
    
<script type="text/javascript">
var type = '${dictTypeId}';
//提交记录
function saveOrUpdate()
{
	var date = new Date();
	var now = ""; 
	now = date.getFullYear()+"-"; //读英文就行了 
	now = now + (date.getMonth()+1)+"-"; //取月的时候取的是当前月-1如果想取当前月+1就可以了 
	now = now + date.getDate()+" "; 
	now = now + date.getHours()+":"; 
	now = now + date.getMinutes()+":"; 
	now = now + date.getSeconds(); 
	$("#time").val(now);
	var r = $('#dataForm').form('validate');
	if(!r) {
		return false;
	}
	else
	{		
		$.post("${rootPath}/sys/dictEntry/saveDictEntryAdd",$("#dataForm").serializeArray(),
		function(data)
		{			
			//var data = eval("(" + data + ")");
			if(data.result == 'true' || data.result == true)
			{
				$.messager.alert("提示", data.msg, function () {
					var tabName = window.parent.wrapper.getCurrentTabTitle();
					if (type == 'means_name') {
						url = '/dictype/askFor';
						window.parent.wrapper.addTabCloseOld("索取资料", url, '', tabName);
					} else if (type == 'means_method') {
						url = '/dictype/askForMethod';
						window.parent.wrapper.addTabCloseOld("索取方式", url, '', tabName);
					} else if (type == 'tiyan') {
						url = '/dictype/tiyan';
						window.parent.wrapper.addTabCloseOld("体验馆", url, '', tabName);
					} else if (type == 'nianl') {
						url = '/dictype/age';
						window.parent.wrapper.addTabCloseOld("年龄", url, '', tabName);
					} else if (type == 'timeYY') {
						url = '/dictype/time';
						window.parent.wrapper.addTabCloseOld("预约时间", url, '', tabName);
					} else if (type == 'service') {
						url = '/dictype/server';
						window.parent.wrapper.addTabCloseOld("需要什么样的养老服务", url, '', tabName);
					}
				});
			}
			else
			{
				$.messager.alert("提示",data.msg, 'error');
			}
		});
	}
}
 
</script>

</body>
</html>
