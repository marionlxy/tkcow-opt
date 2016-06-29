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
<style>
input{height:20px;}
</style>
</head>
<body>
	
	<div class="" title="栏目介绍·新增页面" style="padding: 10px 60px 20px 60px">
		<form id="dataFormCloIntrAdd" method="post"
			enctype="multipart/form-data">
			<%--<div class="fitem">
	    	<label>投诉编号:</label>
	        <input name="complain_num" class="easyui-textbox" disabled>
		</div> --%>
		
			<input type="hidden" name="modParentId" value='${modParentId }'>
			<input type="hidden" name="modIsleaf" value='${modIsleaf }'>
			<input type="hidden" name="menuType" value='${menuType }'>
			<input type="hidden" name="modParentName" value='${modParentName }'>
			<input type="hidden" name="modLevel" value='${modLevel }'>
			<input type="hidden" name="menuId" id="menuId" value='${menuId}'>
			<input type="hidden" name="menuName" id="menuName" value='${modParentName}'>
			<input type="hidden" name="parentId" id="parentId" value='${parentId}'>
			<div class="fitem">
				<label>模块名称:</label> <input name="modName" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
				<label>模块别名:</label> <input name="modByname" class="easyui-textbox" data-options="">
			</div>
			<div class="fitem">
				<label>模块排序号:</label> <input name="modSquence"  missingMessage='必须填1-99之间的数字' class="easyui-numberbox" validType="length[0,2]" data-options="required:true">
			</div>
			<div class="fitem">
				<label>链接名称(站内):</label> <input name="modUrl" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>SEO标题:</label> <input name="modSeo" class="easyui-textbox" data-options="">
			</div>
			<div class="fitem">
				<label>SEO描述:</label> <input name="seoDes" class="easyui-textbox" data-options="">
			</div>
			<div class="fitem">
				<label>描述:</label>
				<!-- <input name="complainMsg"  validType="length[0,30]" required="required"> -->
				<textarea name="modDes" class="easyui-validatebox " validType="length[0,2000]"
					style="width: 316px; border: 1px solid #99bbe8; height: 200px"
					rows="4" cols="33"></textarea>
			</div>

			<div class="fitem">
				<label>banner图片:</label> <input name="modBanner"
					class="easyui-validatebox" type='file' validType="img_upload"
					data-options="prompt:'Choose a image...'"
					style="width: 25%" />
			</div>
			<div class="fitem">
				<label>图标:</label> <input name="imgIcon"
					class="easyui-validatebox" type='file' validType="img_upload"
					data-options="prompt:'Choose a image...'"
					style="width: 25%" />
			</div>
			<div class="fitem">
				<label>小图片:</label> <input name="modImg" type='file' class="easyui-validatebox"
					validType="img_upload" data-options="prompt:'Choose a image...'"
					style="width: 25%" />
			</div>
			
		</form>
		<div id="dlg-buttons" align="center">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" id="save" onclick="saveOrUpdate()"
				style="width: 90px">确定</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="window.parent.wrapper.rightMenuClick({ id: 'close' })" style="width: 90px">取消</a>

		</div>
	</div>

	<script type="text/javascript">
		
		jQuery(function() {
			jQuery.ajaxSetup({
				cache : false
			});
		});

		//保存记录
		function saveOrUpdate() {
		
			var r = $('#dataFormCloIntrAdd').form('validate');
			if (!r) {
				return false;
			} else {
				$('#dataFormCloIntrAdd').form(
						'submit',
						{
							url : '${rootPath}/column/addFormCol',
							onSubmit : function() {
								$("#dataFormCloIntrAdd").serializeArray();
// 								alert('a');
							} ,
							success : function(data) {
								var ajaxobj = eval("(" + data + ")");
								if (ajaxobj.result == 'true'
										|| ajaxobj.result == true) {
									$.messager.alert('提示', ajaxobj.msg, function(r){
											url = '/module/subList?reqMenuId=' + ajaxobj.reqMenuId + '&backUp2='
											+ ajaxobj.backUp2;
											/* openWin(url); */
											var tabName = window.parent.wrapper.getCurrentTabTitle();
											window.parent.wrapper.addTabCloseOld(ajaxobj.menuName, url, '', tabName);
									});
									
									$('#save').linkbutton('disable');
									
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
