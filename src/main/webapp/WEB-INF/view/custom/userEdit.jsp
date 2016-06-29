<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%@ include file="/include.jsp"%>
	<script src="<%=basePath%>/static/ckeditor/ckeditor.js"></script>
	<script src="<%=basePath%>/static/ckeditor/samples/js/sample.js"></script>
	<script src="<%=basePath%>/static/js/imageValidate.js"></script>
    <title></title>
</head>
<body>
			<input  name="modId" id="hidSId" type="hidden">
<div class="easyui-panel" title="注册用户添加" style="width:1000px">
	<form id="dataForm" name="dataForm"
			action=""
			method="post">
			<input name="tabName" type="hidden" value="${tabName}" id="tabName">
			<div class="fitem">
		    	<label>会员编号:</label>
		        <input name="id" class="easyui-textbox" readonly="readonly" value="${id}">
			</div>
			<div class="fitem">
		    	<label>用户名:</label>
		        <input name="username" class="easyui-textbox" data-options="required:true" value="${username}">
			</div>
			<div class="fitem">
		    	<label>姓名:</label>
		        <input name="truename" class="easyui-textbox" data-options="required:true" value="${truename}">
			</div>
			<div class="fitem">
		    	<label>电话:</label>
		        <input name="telephone" class="easyui-textbox" value="${telephone}">
			</div>
			<div class="fitem">
		    	<label>邮箱地址:</label>
		        <input name="email" class="easyui-textbox" value="${email}">
			</div>
 			<div class="fitem">
 		    	<label>城市:</label>
 		        <input name="address" class="easyui-textbox" value="${address}">
 			</div>
			<div class="fitem">
 		    	<label>注册时间:</label>
 		        <input name="addtime" class="easyui-datetimebox" value="${addtime}" data-options="required:true">
 			</div>
 			<div class="fitem">
 		    	<label>备注:</label>
 		        <textarea name="msn" class="easyui-textbox" style="width: 316px; border: 1px solid #99bbe8; height: 200px"
					rows="4" cols="33">${msn}</textarea>
 			</div>
			</form>
			 
   

    <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate1()" style="width:90px">提交</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.parent.wrapper.rightMenuClick({ id: 'close' })" style="width:90px">取消</a>
   </div>
   
    <div id="divDialog"></div>
   
</div>
    
<script type="text/javascript">

var rowId;
jQuery(function(){
});


//提交记录
function saveOrUpdate1() {
	var r = $('#dataForm').form('validate');
	if (!r) {
		return false;
	} else {
		$('#dataForm').form(
				'submit',
				{
					url : '${rootPath}/custom/updataCustomInfo',
					onSubmit : function() {
						$("#dataForm").serializeArray();
					},
					success : function(data) {
						var ajaxobj = eval("(" + data + ")");
						if (ajaxobj.result == 'true'
							|| ajaxobj.result == true) {
							$.messager.alert('提示', "修改成功", function() {
								url = '/custom/showCutomer';
								var tabName = window.parent.wrapper.getCurrentTabTitle();
								window.parent.wrapper.addTabCloseOld('注册用户添加', url, '', tabName);
							});
						} else {
							$.messager.alert("提示", ajaxobj.msg, 'error');
						}
					}
				});
	}
}
</script>

</body>
</html>
