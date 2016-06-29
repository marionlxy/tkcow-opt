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
	<input type="hidden" name="isLeaf" >	
	        <div class="fitem">
		        <label>菜单ID:</label>		
			    <input name="menuId" readonly="true"  class="easyui-numberbox" data-options="required:true"/>
		    </div>
	    	<div class="fitem">
		    	<label>菜单名称:</label>
		        <input name="menuName" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
	        	<label>菜单状态:</label>
	        	<select name="menuStatus" class="easyui-combobox" panelHeight="50px" required="required" style="width:80px">
						<option value="1" <c:if  test="${menuStatus =='1'}"> selected</c:if> >有效</option>
						<option value="0" <c:if test="${menuStatus=='0'}"> selected</c:if> >无效</option>
				 </select>
	    	</div>
	    	<c:if test="${isLeaf =='1'}"><!-- 如果是叶子节点，则需要选择上级菜单 -->
				<div class="fitem">
					<label>上级菜单:</label>
				  	<input name="parentId" class="easyui-textbox" data-options="required:true" >
				</div>
				<div class="fitem">
			    	<label>访问路径:</label>
			        <input name="menuUrl" class="easyui-textbox" >
				</div>
			</c:if>
			<div class="fitem">
		    	<label>菜单顺序（整数）:</label>
		        <input name="menuOrder" class="easyui-numberbox" data-options="required:true">
			</div>
		</form>
    <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()" style="width:90px">保存</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="goBack(0)" style="width:90px">取消</a>
   </div>
</div>
    
<script type="text/javascript">

	var menuId;
	jQuery(function() {
		menuId = '${menuId}';
		if (menuId != null && menuId != "") {
			var url = '${rootPath}/menu/getMenuById?menuId=' + menuId;
			$('#dataForm').form('load', url);
		}
		var iLeaf = '${isLeaf}';
		if ("1" == iLeaf) {
			//如果是叶子节点
			$('#iLeaf').textbox({
				value : '是'
			});
			//初始化上级菜单
			/* $('#combo2').combobox({
				url : '${rootPath}/menu/getParentMenu',
				valueField : 'Menu_Id',
				textField : 'Menu_Name',
				panelHeight : 'auto'
			}); */
		} else {
			$('#iLeaf').textbox({
				value : '否'
			});
		}
	});

	//保存记录
	function saveOrUpdate() {
		var r = $('#dataForm').form('validate');
		if (!r) {
			return false;
		} else {
			$.post("${rootPath}/menu/editMenuInfo",
					$("#dataForm").serializeArray(), function(data) {
						if (data.result == 'true' || data.result == true) {
							$.messager.alert("success", "菜单编辑成功！");
							goBack(1);
						} else {
							$.messager.alert("error", "菜单编辑失败 ！");
						}
					});
		}
	}
</script>

</body>
</html>
