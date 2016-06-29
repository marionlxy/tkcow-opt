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
		    	<label>字典类型:</label>
		        <input name="dictTypeId" class="easyui-textbox" value='weblink' readonly="true">
			</div>
			<div class="fitem">
		    	<label>标题:</label>
		        <input name="dictName" class="easyui-textbox"  data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>描述:</label>
		        <input name="backup2" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>排序(整数):</label>
		        <input name="sortNo" class="easyui-numberbox" >
			</div>
			<div class="fitem">
		    	<label>链接地址:</label>
		        <input name="seqNo" class="easyui-textbox" data-options="required:true">
			</div>
			<input name="backup1" type="hidden" id="time">
			<input name="dictId" type="hidden" id="dicId">
    <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()" style="width:90px">提交</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.parent.wrapper.rightMenuClick({ id: 'close' })" style="width:90px">取消</a>
   </div>
</div>
    
<script type="text/javascript">

var dictTypeId;
var dictId;
jQuery(function(){ 
  	dictTypeId ='${dictTypeId}';
  	dictId ='${dictId}';
	
	if (dictTypeId != null && dictTypeId != "" && dictTypeId!=0){
		var url="${rootPath}/sys/dictEntry/getDictEntryById?dictTypeId=" + dictTypeId+"&dictId="+dictId;
		$('#dataForm').form('load', url);
	}
});

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
		$.post("${rootPath}/sys/dictEntry/saveDictEntryEdit",$("#dataForm").serializeArray(),
		function(data)
		{			
			//var data = eval("(" + data + ")");
			if(data.result == 'true' || data.result == true)
			{
				$.messager.alert("提示", data.msg, function () {
					url = '/module/weblink';
					var tabName = window.parent.wrapper.getCurrentTabTitle();
					window.parent.wrapper.addTabCloseOld("相关网站链接", url, '', tabName);
				});
			}
			else
			{
				$.messager.alert("提示", data.msg,"error");
			}
		});
	}
}
 
</script>

</body>
</html>
