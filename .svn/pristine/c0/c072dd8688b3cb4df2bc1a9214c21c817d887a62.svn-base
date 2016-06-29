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
<div class="easyui-panel" title="预约参观详细" style="width:600px">
	<form id="dataForm">
	<input type="hidden" name="visitId">
			<div class="fitem">
		    	<label>联系人:</label>
		        <input name="visitName" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>联系人:</label>
		        <input name="visitTel" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>提交日期:</label>
		        <input name="createdTime" class="easyui-textbox" data-options="required:true">
			</div>
				<div class="fitem">
		    	<label>参观人数:</label>
		        <input name="visitCount" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>预约日期:</label>
		        <input name="visitTime" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>体验馆:</label>
		        <input name="visitSite" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>年龄:</label>
		        <input name="visitAge" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>性别:</label>
		        <input name="visitSex" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>需要什么样养老服务:</label>
		        <input name="visitService" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>备注:</label>
		        <input name="visitBak" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>来源:</label>
		        <input name="visitFrom" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>Ip:</label>
		        <input name="userIp" class="easyui-textbox" data-options="required:true">
			</div>
			</form>
    <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="goBack(0)" style="width:90px">取消</a>
   </div>
</div>
    
<script type="text/javascript">

var visitId;
jQuery(function(){ 

	//初始化下拉框-示例，请根据需要自定义实现
   	/*
   	 $('#combo1').combobox({    
  	        url:'${rootPath}/getDictlist?dicttypeid=userstatus',    
  	        valueField:'dictid',    
  	        textField:'dictname',
  	     	panelHeight:'auto'
  	    }); 
  	  */ 
	
  	visitId ='${visitId}';
	alert(visitId)
	if (visitId != null && visitId != "" && visitId!=0){
		var url='${rootPath}/orderVisit/getOne?visitId=' + visitId;
		$('#dataForm').form('load', url);
	}
});

//保存记录
function saveOrUpdate()
{
	var r = $('#dataForm').form('validate');
	if(!r) {
		return false;
	}
	else
	{		
		$('#save').linkbutton('disable');
		$.post("${rootPath}/orderVisit/save",$("#dataForm").serializeArray(),
		function(data)
		{			
			if(data.result == 'true' || data.result == true)
			{
			 	$.messager.show({title:'提示',msg:data.msg,showType:'show'});
				goBack(1);
			}
			else
			{
				$.messager.alert('提示',data.msg,'error');
				$('#save').linkbutton('enable');
			}
		});
	}
}
 
//返回父页面  
function goBack(flag){
	window.parent.wrapper.tabCloseOne();
}
</script>

</body>
</html>
