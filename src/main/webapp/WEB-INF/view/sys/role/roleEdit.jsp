<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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
	        <input type="hidden" name="roleId">
			<div class="fitem">
		    	<label>角色编码:</label>
		        <input name="roleCode" class="easyui-textbox" readonly="true" data-options="required:true">
			</div>
					<div class="fitem">
		    	<label>角色名称:</label>
		        <input name="roleName" class="easyui-textbox" data-options="required:true">
			</div>
			         <!-- <div class="fitem">
		    	<label>角色性质:</label>
				  <select name="roleNature" class="easyui-combobox" panelHeight="50px" style="width:80px" required="required">
						<option value="H" <c:if  test="${roleNature =='H'}"> selected</c:if> >总公司</option>
						<option value="B" <c:if test="${roleNature=='B'}"> selected</c:if> >分公司</option>
				 </select>
			</div> -->
			        <div class="fitem">
		    	<label>状态:</label>
				  <select name="roleStatus" class="easyui-combobox" panelHeight="50px" style="width:80px" required="required">
						<option value="1" <c:if  test="${roleStatus =='1'}"> selected</c:if> >有效</option>
						<option value="0" <c:if test="${roleStatus=='0'}"> selected</c:if> >失效</option>
				 </select>
			</div>
			<!-- <div class="fitem">
		    	<label>分属部门:</label>
		        <input name="roleDep" class="easyui-textbox">
			</div> -->
			</form>
    <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()" style="width:90px">提交</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="goBack(0)" style="width:90px">取消</a>
   </div>
</div>
    
<script type="text/javascript">

	var roleId;
	jQuery(function() {
		roleId = '${roleId}';
		if (roleId != null && roleId != "" && roleId != 0) {
			var url = '${rootPath}/role/getRoleById?roleId=' + roleId;
			$('#dataForm').form('load', url);
		}
	});

	//提交记录
	function saveOrUpdate() {
		var r = $('#dataForm').form('validate');
		if (!r) {
			return false;
		} else {
			$.post("${rootPath}/role/saveRoleInfo", $("#dataForm").serializeArray(),
					function(data) {
						if (data.result == 'true' || data.result == true) {
							$.messager.alert("success","角色编辑成功！");
							goBack(1);
							//com.message("success", "角色编辑成功！");
						} else {
		                    $.messager.alert("error","角色编辑失败 ！");
							//com.message("error", "角色编辑失败 ！");
						}
					});
		}
	}
</script>

</body>
</html>
