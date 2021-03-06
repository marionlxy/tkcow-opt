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
<div class="easyui-panel" title="索取资料信息编辑" style="width:600px">
	<form id="dataForm">
			<div class="fitem">
		    	<label>资料名称:</label>
		    	 <input name="meansMethod" class="easyui-combobox"
								panelHeight="auto"
								panelHeight="auto" 
								url="${rootPath}/getDictCombox?dictType=means_name" valueField="dictId" textField="dictName" editable="false" data-options="required:true" disabled="disabled"">
			</div>
			<div class="fitem">
		    	<label>索取方式:</label>
		    	  <input name="meansMethod" class="easyui-combobox"
								panelHeight="auto"
								panelHeight="auto" 
								url="${rootPath}/getDictCombox?dictType=means_method" valueField="dictId" textField="dictName" editable="false" data-options="required:true" disabled="disabled">
			</div>
			<div class="fitem">
		    	<label>提交时间:</label>
		        <input name="createdTime" class="easyui-textbox" data-options="required:true" disabled="disabled"">
			</div>
			<div class="fitem">
		    	<label>邮箱:</label>
		        <input name="meansEmail" class="easyui-textbox" data-options="required:true" disabled="disabled"">
			</div>
			<div class="fitem">
		    	<label>备注:</label>
		        <input name="meansBak" class="easyui-textbox" data-options="required:true" disabled="disabled"">
			</div>
			<div class="fitem">
		    	<label>Ip:</label>
		        <input name="userIp" class="easyui-textbox" data-options="required:true" disabled="disabled"">
			</div>
			</form>
    <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="goBack(0)" style="width:90px">取消</a>
   </div>
</div>
    
<script type="text/javascript">

var rowId;
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
	
  	claimId ='${claimId}';
	
	if (claimId != null && claimId != "" && claimId!=0){
		var url='${rootPath}/askforClaim/getOne?claimId=' + claimId;
		$('#dataForm').form('load', url);
	}
});

//提交记录
function saveOrUpdate()
{
	var r = $('#dataForm').form('validate');
	if(!r) {
		return false;
	}
	else
	{		
		$('#save').linkbutton('disable');
		$.post("${rootPath}/askforClaim/save",$("#dataForm").serializeArray(),
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
