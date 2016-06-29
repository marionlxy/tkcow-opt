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
		    	<label>id:</label>
		        <input name="id" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>addtime:</label>
		        <input name="addtime" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>modifytime:</label>
		        <input name="modifytime" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>modifier:</label>
		        <input name="modifier" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>deletestatus:</label>
		        <input name="deletestatus" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>msn:</label>
		        <input name="msn" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>qq:</label>
		        <input name="qq" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>ww:</label>
		        <input name="ww" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>address:</label>
		        <input name="address" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>availablebalance:</label>
		        <input name="availablebalance" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>birthday:</label>
		        <input name="birthday" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>email:</label>
		        <input name="email" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>freezeblance:</label>
		        <input name="freezeblance" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>gold:</label>
		        <input name="gold" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>integral:</label>
		        <input name="integral" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>mobile:</label>
		        <input name="mobile" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>password:</label>
		        <input name="password" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>sex:</label>
		        <input name="sex" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>telephone:</label>
		        <input name="telephone" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>truename:</label>
		        <input name="truename" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>username:</label>
		        <input name="username" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>userrole:</label>
		        <input name="userrole" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>userCredit:</label>
		        <input name="userCredit" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>qqOpenid:</label>
		        <input name="qqOpenid" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>sinaOpenid:</label>
		        <input name="sinaOpenid" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>years:</label>
		        <input name="years" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>areaId:</label>
		        <input name="areaId" class="easyui-textbox" data-options="required:true">
			</div>
			
			</form>
    <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()" style="width:90px">提交</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="goBack(0)" style="width:90px">取消</a>
   </div>
</div>
    
<script type="text/javascript">
	var userId;
	jQuery(function() {
		userId = '${userId}';
		if (userId != null && userId != "" && userId != 0) {
			var url = '${rootPath}/user/getUserById?rowId=' + userId;
			$('#dataForm').form('load', url);
		}
	});

	//保存记录
	function saveOrUpdate() {
		var r = $('#dataForm').form('validate');
		if (!r) {
			return false;
		} else {
			$('#save').linkbutton('disable');
			$.post("${rootPath}/user/saveUserInfo", $("#dataForm").serializeArray(),
					function(data) {
						if (data.result == 'true' || data.result == true) {
							$.messager.alert("success",data.msg);
							/* $.messager.show({
								title : '提示',
								msg : data.msg,
								showType : 'show'
							}); */
							goBack(1);
						} else {
							$.messager.alert('提示', data.msg, 'error');
							$('#save').linkbutton('enable');
						}
					});
		}
	}
</script>

</body>
</html>
