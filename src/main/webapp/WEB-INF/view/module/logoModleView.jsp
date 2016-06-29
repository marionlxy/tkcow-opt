<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8">
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
	<div style="padding: 10px 60px 20px 60px">
		<form id="dataFormLogoModelAdd" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="modParentId" value='${modParentId}'>
			<input type="hidden" name="modIsleaf" value='${modIsleaf}'>
			<input type="hidden" name="menuType" value='${menuType}'>
			<input type="hidden" name="modParentName" value='${modParentName}'>
			<input type="hidden" name="modLevel" value='${modLevel}'>
			<input type="hidden" name="menuId" id="menuId" value='${menuId}'>
			<input type="hidden" name="menuName" id="menuName" value='${modParentName}'>
			<input type="hidden" name="parentId" id="parentId" value='${parentId}'>
			<div class="fitem">
				<label>模块名称:</label> <input name="modName" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
				<label>模块别名:</label> <input name="modByname" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
				<label>模块排序号:</label> <input name="modSquence"  missingMessage='必须填1-99之间的数字' class="easyui-numberbox" validType="length[0,2]" data-options="required:true">
			</div>
			<div class="fitem">
				<label>链接名称(站内):</label> <input name="modUrl" class="easyui-textbox">例如：aaa/bbb/ccc,留空即可自动生成拼音路径
			</div>
			<div class="fitem">
				<label>SEO标题:</label> <input name="modSeo" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
				<label>SEO描述:</label> <input name="seoDes" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
				<label>描述:</label>
				<!-- <input name="complainMsg"  validType="length[0,30]" required="required"> -->
				<textarea name="modDes" class="easyui-textbox"
					style="width: 316px; border: 1px solid #99bbe8; height: 200px"
					rows="4" cols="33"></textarea>
			</div>

			<div class="fitem">
				<label>banner图片:</label> <input name="modBanner"
					class="easyui-validatebox" type='file' validType="img_upload"
					data-options="required:true,prompt:'Choose a image...'"
					style="width: 25%" />
			</div>
		</form>
		<div id="dlg-buttons" align="left">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" id="save" onclick="saveOrUpdate()"
				style="width: 90px">确定</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="returnParent(1)" style="width: 90px">取消</a>

		</div>
	</div>

	<script type="text/javascript">
		jQuery(function() {
			jQuery.ajaxSetup({
				cache : false
			});
		});
	
     var modelId;
	 jQuery(function(){ 
		    	
		    	modelId ='${modelId}';
		    	alert(modelId)
		    	if (modelId != null && modelId != "" && modelId!=0){
		    		var url='${rootPath}/logoModule/getLogoModuleOne?modelId=' + modelId;
		    		$('#dataForm1').form('load', url);
		    	}
		    });
		    
		//保存记录
		function saveOrUpdate() {
			var r = $('#dataFormLogoModelAdd').form('validate');
			if (!r) {
				return false;
			} else {
				$('#dataFormLogoModelAdd').form(
						'submit',
						{
							url : '${rootPath}/logoModule/saveLogoModel',
							onSubmit : function() {
								$("#dataFormLogoModelAdd").serializeArray();
// 								alert('a');
							} ,
							dataType:'text/html',
							success : function(data) {
								var ajaxobj = eval("(" + data + ")");
								if (ajaxobj.result == 'true'
										|| ajaxobj.result == true) {
									$.messager.alert('提示', ajaxobj.msg);
									$('#save').linkbutton('disable');
									url = '/module/subList?reqMenuId=' + ajaxobj.reqMenuId + '&ajaxobj='
									+ ajaxobj.backUp2;
									/* openWin(url); */
									window.parent.wrapper.addTab(ajaxobj.menuName, url, '');
								} else {
									//$.messager.alert('提示',data.msg,'error');
									$.messager
											.alert("提示", ajaxobj.msg, 'error');
								}
							} 
						});
			
				 }
		}
	</script>
</body>
</html>
