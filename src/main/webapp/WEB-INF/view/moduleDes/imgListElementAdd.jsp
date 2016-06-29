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
<div class="" title="带图列表模板·新增页面" style="width:1000px">
	<form id="dataForm" name="dataForm"
			action="${rootPath}/moduleDes/uploadBySpringGrpModuleDes"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="parent_modid" id="parentId" value='${parentId}'>
			<input type="hidden" name="modLevel" id="modLevel" value='${modLevel}'>
			<input type="hidden" name="modParentId" id="modParentId" value='${modParentId}'>
			
			<div class="fitem">
		    	<label>标题:</label>
		        <input name="title" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>排序号:</label>
		        <input name="weight" value="${modSquence }"
					 
					missingMessage='必须填1-99之间的数字' class="easyui-numberbox"
					validType="length[0,2]" data-options="required:true" />
			</div>
			<div class="fitem">
		    	<label>链接名称（站内）:</label>
		        <input name="modUrl" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>SEO标题:</label>
		        <input name="modSeo" class="easyui-textbox">
			</div>
 			<div class="fitem">
 		    	<label>关键词:</label>
 		        <input name="key" class="easyui-textbox" >
 			</div>

		    <div class="fitem">
				<label>描述:</label>
				<textarea name="modDes" class="easyui-validatebox " validType="length[0,2000]"
					style="width: 316px; border: 1px solid #99bbe8; height: 200px"
					rows="4" cols="33"></textarea>
			</div>
			<div class="fitem">
				<label>大图:</label> <input name="coverImg"
					class="easyui-filebox validatebox" validType="img_upload"
					data-options="prompt:'Choose a image...'"
					style="width: 25%" >
			</div>
			<span style="display:block;float:left;margin-right:40px;margin-left:0px;">内容：</span>
		    <div class="adjoined-bottom" style="display:block;float:left;">
				<div class="grid-container">
					<div class="grid-width-100">
						<textarea id="editor" style="height: 40px;width: 200px"
							 data-options="required:true">
		            	</textarea>
					</div>
				</div>
			</div>
			<input type="hidden" id="editInput" name="editor" value=''>
			</form>
			 
   

    <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate1()" style="width:90px">提交</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.parent.wrapper.rightMenuClick({ id: 'close' })" style="width:90px">取消</a>
   </div>
   
    <div id="divDialog"></div>
   
</div>
    
<script type="text/javascript">
	initSample();
	var banImgId;

var rowId;
jQuery(function(){
});

//提交记录
function saveOrUpdate1() {
	var editorText=CKEDITOR.instances.editor.getData();
	$("#editInput").val(Base64.encode(editorText));
	var r = $('#dataForm').form('validate');
	if (!r) {
		return false;
	} else {
		$('#dataForm').form(
				'submit',
				{
					url : '${rootPath}/moduleDes/saveImgListElementInfo?pmodid=${parent_modid}',
					onSubmit : function() {
						$("#dataForm").serializeArray();
					},
					success : function(data) {
						ajaxobj = eval("(" + data + ")");
						if (ajaxobj.result == 'true'
							|| ajaxobj.result == true) {
							$.messager.alert('提示', ajaxobj.msg, function() {
								//window.parent. wrapper.rightMenuClick({ id: 'close' });
								url = '/moduleDes/showImgModList?backUp2='
								+ ajaxobj.backUp2;
								//window.parent.wrapper.addTab('${modName}', url, '');
								var tabName = window.parent.wrapper.getCurrentTabTitle();
								window.parent.wrapper.addTabCloseOld('${modName}', url, '', tabName);
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
