<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%@ include file="/include.jsp"%>
	<script type="text/javascript" src="<%=basePath%>/static/js/fileuploder/ajaxfileupload.js"></script>
    <title></title>
</head>
<body>
<div class="easyui-panel"  style="height:200px;border:none;">
	<form id="dataForm1"  name="dataForm1"
			method="post" enctype="multipart/form-data">
			
			<!-- <input  name="banImgId" id="hidSId" type="hidden"> -->
			<input  name="modId" id="modId" type="hidden" value="${modId}">
			<div class="fitem" style="text-align: center;margin-top:20px;">
		    <label>Logo名称:</label>
                <select panelHeight="auto" name="cooperLogoId" style="width: 204px" id="cooperLogoId" class="easyui-combobox" data-options="required:true" >
						<option value=""></option>
						<c:forEach items="${logoNameList}" var="logoInfo" varStatus="s">
							<option value="${logoInfo.COOPER_LOGO_ID}">${logoInfo.COOPER_LOGO_NAME}</option>
						</c:forEach>
			  </select>
			</div>
			 <div class="fitem" style="text-align: center;margin-top:20px;">
		    	<label>排序号:</label>
		       <input name="logoSquence" id="logoSquence"  value="${modSquence }" 
					missingMessage='必须填1-99之间的数字' class="easyui-numberbox"
					validType="length[0,2]" data-options="required:true" />
			</div>
		
			</form>
    <div id="dlg-buttons" align="center" style="  margin-top: 40px;">
       <!-- 页面按钮有无权限控制 -->
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()" style="width:90px">提交</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="goBack(1)" style="width:90px">取消</a>
   </div>
</div>
    
    <script type="text/javascript">

	//提交记录
	function saveOrUpdate()
	{
		var r = $('#dataForm1').form('validate');
		if(!r) {
			return false;
		}
		else
		{		
			$('#save').linkbutton('disable');
			$.post("${rootPath}/logoModule/saveLogoInfo",$("#dataForm1").serializeArray(),
			function(data)
			{			
				if(data.result == 'true' || data.result == true)
				{
				 	/*  $.messager.show({title:'提示',msg:data.msg,showType:'show'});
					goBack(1);  */
					
					$.messager.alert('提示',data.msg);
				 	$('#divDialog').dialog('close');
				 	searchInfo();
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
		$("#divDialog").window('close');
		parent.returnParent(flag);
		
	}
</script>

</body>
</html>
