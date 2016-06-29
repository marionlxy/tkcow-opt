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
		    	<label>用户名:</label>
		        <input name="username" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>密码:</label>
		        <input id="password" name="password" class="easyui-textbox" data-options="required:true" type="password" validType="length[4,32]">
			</div>
			<div class="fitem">
		    	<label>确认密码:</label>
		        <input class="easyui-textbox" data-options="required:true" type="password" validType="equalTo['#password']" invalidMessage="两次输入密码不匹配">
			</div>
			<div class="fitem">
		    	<label>电子邮箱:</label>
		        <input name="email" class="easyui-textbox">
			</div>
			<div class="fitem">
		    	<label>电话号码:</label>
		        <input name="telephone" class="easyui-textbox" , validType: ['phoneAndMobile']>
			</div>
			<div class="fitem">
		    	<label>角色:</label>
		        <input name="userrole" class="easyui-combobox"
								panelHeight="auto"
								panelHeight="auto" 
								url="${rootPath}/role/getAllRole" valueField="roleCode" textField="roleName" editable="false" data-options="required:true">
			</div>
			</form>
    <div id="dlg-buttons" align="center">
       <!-- 页面按钮有无权限控制 -->
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()" style="width:90px">提交</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="goBackFromAdd()" style="width:90px">取消</a>
   </div>
</div>
    
    
<script type="text/javascript">
//重复密码验证
$.extend($.fn.validatebox.defaults.rules, {  
    /*必须和某个字段相等*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    }
});
	/**
	 * 保存记录新增用户<br/>
	 */
	function saveOrUpdate() {
		var r = $('#dataForm').form('validate');
		if (!r) {
			return false;
		} else {
			//$('#save').linkbutton('disable');
			$.post("${rootPath}/user/saveUserInfo", $("#dataForm")
					.serializeArray(), function(data) {
				if (data.result == 'true' || data.result == true) {
					$.messager.alert("success", data.msg);
					goBack(1);
				} else {
					$.messager.alert("error", data.msg);
				}
			});
		}
	}

	function goBackFromAdd() {
		$("#divDialog").window('close');
	}
</script>

</body>
</html>
