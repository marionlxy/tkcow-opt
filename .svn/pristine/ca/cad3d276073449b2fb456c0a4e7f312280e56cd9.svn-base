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
		        <input name="dictTypeId" class="easyui-textbox" readonly="true">
			</div>
			<div class="fitem">
		    	<label>字典类型名称:</label>
		        <input name="dictTypeName" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>状态:</label>
		        <select name="status" class="easyui-combobox" panelHeight="50px" style="width:80px" required="required">
						<option value="1" <c:if  test="${dictStatus =='1'}"> selected</c:if> >有效</option>
						<option value="0" <c:if  test="${dictStatus=='0'}"> selected</c:if> >失效</option>
				 </select>
			</div>
			<div class="fitem">
		    	<label>排序(数字):</label>
		        <input name="sortNo" class="easyui-numberbox">
			</div>
			<div class="fitem">
		    	<label>父类型:</label>
		        <input name="parentId" class="easyui-textbox">
			</div>
			<div class="fitem">
		    	<label>级联关系描述:</label>
		        <input name="seqNo" class="easyui-textbox">
			</div>
			</form>
    <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()" style="width:90px">保存</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="goTypeBack(0)" style="width:90px">取消</a>
   </div>
</div>
    
<script type="text/javascript">

var dictTypeId;
jQuery(function(){ 
  	dictTypeId ='${dictTypeId}';
	
	if (dictTypeId != null && dictTypeId != "" && dictTypeId!=0){
		var url='${rootPath}/sys/dictType/getDictTypeById?dictTypeId=' + dictTypeId;
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
		$.post("${rootPath}/sys/dictType/saveEditDictTypeInfo",$("#dataForm").serializeArray(),
		function(data)
		{			
			if(data.result == 'true' || data.result == true)
			{
				com.message("success", "字典类别编辑成功！");
				goTypeBack(1);
			}
			else
			{
				com.message("error", "字典类别编辑失败 ！");
			}
		});
	}
}
 
</script>

</body>
</html>
