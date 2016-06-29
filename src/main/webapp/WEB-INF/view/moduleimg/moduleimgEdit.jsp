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
<div class="easyui-panel" title="XX详细信息编辑" style="width:600px">
	<form id="dataForm">
			<div class="fitem">
		    	<label>modImgId:</label>
		        <input name="modImgId" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>modImgName:</label>
		        <input name="modImgName" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>belongId:</label>
		        <input name="belongId" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>modImgUrl:</label>
		        <input name="modImgUrl" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>modImgNum:</label>
		        <input name="modImgNum" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>createTime:</label>
		        <input name="createTime" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>createdBy:</label>
		        <input name="createdBy" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>modifiedTime:</label>
		        <input name="modifiedTime" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>modifiedBy:</label>
		        <input name="modifiedBy" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>version:</label>
		        <input name="version" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>delflag:</label>
		        <input name="delflag" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>modImgRename:</label>
		        <input name="modImgRename" class="easyui-textbox" data-options="required:true">
			</div>
			</form>
    <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()" style="width:90px">提交</a>
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
	
	rowId ='${rowId}';
	
	if (rowId != null && rowId != "" && rowId!=0){
		var url='${rootPath}/moduleImg/getOne?rowId=' + rowId;
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
		$.post("${rootPath}/moduleImg/save",$("#dataForm").serializeArray(),
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
	parent.returnParent(flag);
}
</script>

</body>
</html>
