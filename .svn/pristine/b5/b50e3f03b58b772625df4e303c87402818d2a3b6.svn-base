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
	<input type="hidden" name="isShow" value="1">
	         <div class="fitem">
		    	<label>菜单ID(整数):</label>
		        <input name="menuId" class="easyui-numberbox" data-options="required:true">
			</div>	
	    	<div class="fitem">
		    	<label>菜单名称:</label>
		        <input name="menuName" class="easyui-textbox" data-options="required:true">
			</div>
	    	<c:if test="${isLeaf =='1'}"><!-- 如果是叶子节点，则需要选择上级菜单 -->
				<div class="fitem">
					<label>上级菜单:</label>
				  	<!-- <input class="easyui-combobox" id="combo2" name="parentId" data-options="required:true" value=""> -->
				  	<input name="parentId" class="easyui-textbox" value='${parentId}' data-options="required:true" readonly>
				</div>
				<div class="fitem">
			    	<label>访问路径:</label>
			        <input name="menuUrl" class="easyui-textbox" >
			         <input type="hidden" name="isLeaf" value="1">
				</div>
			</c:if>
				<c:if test="${isLeaf =='0'}"><!-- 如果不是叶子节点，则需要初始化parentId和isLeaf -->
				  	<input type="hidden"  name="parentId" value="0">
			        <input type="hidden" name="isLeaf" value="0">
			</c:if>
			<div class="fitem">
		    	<label>菜单顺序（整数）:</label>
		        <input name="menuOrder" class="easyui-numberbox" data-options="required:true">
			</div>
			<div class="fitem">
	        	<label>菜单状态:</label>
	        	<select class="easyui-combobox" name="menuStatus" panelHeight="60px" style="width:80px" required="required">
	        	            <option value=""></option>
							<option value="1">有效</option>
							<option value="0">无效</option>
				</select>
	    	</div>
		</form>
</div>
 <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()" style="width:90px">保存</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="goBack(0)" style="width:90px">取消</a>
  </div>
    
<script type="text/javascript">
jQuery(function() { 
	var iLeaf = '${isLeaf}';
	var parentId = '${parentId}';
	if("1"==iLeaf) {
		//如果是叶子节点
	    $('#iLeaf').textbox({value:'是'});
		 //初始化上级菜单
		 
		 /* $('#combo2').combobox({
		        url:'${rootPath}/menu/getParentMenu',    
		        valueField:'Menu_Id',    
		        textField:'Menu_Name',
		     	panelHeight:'auto'
		    });  */
	} else {
		$('#iLeaf').textbox({value:'否'});
		$('#parentId').val("0");
	}
});

//保存记录
function saveOrUpdate() {
	var r = $('#dataForm').form('validate');
	if(!r) {
		return false;
	} else {		
		$.post("${rootPath}/menu/saveMenuInfo",$("#dataForm").serializeArray(),
		function(data) {			
			if(data.result == 'true' || data.result == true) {
				$.messager.alert("success","菜单添加成功！");
				goBack(1);
			} else {
				$.messager.alert("error", '菜单添加失败 ！');
			}
		});
	}
}
 
</script>

</body>
</html>
