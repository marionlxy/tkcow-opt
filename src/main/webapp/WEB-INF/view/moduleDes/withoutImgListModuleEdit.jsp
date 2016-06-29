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
<div class="" title="不带图模板修改·修改页面" style="width:1000px">
	<input type="hidden" id="modId" value='${modId}'>
	<form id="dataForm" name="dataForm"
			action="${rootPath}/moduleDes/uploadBySpringGrpModuleDes"
			method="post" enctype="multipart/form-data">
			
			<input type="hidden" name="modId" id="modId" value='${modId}'>
			<input type="hidden" name="modParentId" value='${modParentId}'>
			<input type="hidden" name="modIsleaf" value='${modIsleaf}'>
			<input type="hidden" name="menuName" value='${menuName}'>
			<input type="hidden" name="menuId" id="menuId" value='${menuId}'>
			<input type="hidden" name="modParentName" id="modParentName" value='${modParentName}'>
			<input type="hidden" name="bannerId" id="bannerId" value=''>
			<input type="hidden" name="banImgId" id="banImgId" value=''>
			
			<div class="fitem">
		    	<label>模块名称:</label>
		        <input name="title" id="title" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>排序号:</label>
		        <!-- <input name="weight" id="weight" class="easyui-textbox" data-options="required:true"> -->
		        <input name="weight" value="${modSquence }"
					 
					missingMessage='必须填1-99之间的数字' class="easyui-numberbox"
					validType="length[0,2]" data-options="required:true" />
			</div>
			<div class="fitem">
		    	<label>链接名称（站内）:</label>
		        <input name="modUrl" id="modUrl" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>SEO标题:</label>
		        <input name="modSeo"  id="modSeo" class="easyui-textbox">
			</div>
 			<div class="fitem">
 		    	<label>关键词:</label>
 		        <input name="key" id="key" class="easyui-textbox" >
 			</div>

		    <div class="fitem">
				<label>描述:</label>
				<textarea name="modDes" id="modDes" class="easyui-validatebox " validType="length[0,2000]"
					style="width: 316px; border: 1px solid #99bbe8; height: 200px"
					rows="4" cols="33"></textarea>
			</div>

		    <div class="fitem">
				<label>banner替换:</label> <input name="banImgUrl"
					class="easyui-filebox validatebox" validType="img_upload"
					data-options="prompt:'Choose a image...'"
					style="width: 25%" >
				<img id="ban" src="" style="width:50px;height:50px;" />
			</div>
			<div class="fitem">
				<label>图标:</label> <input name="imgIcon"
					class="easyui-filebox validatebox" validType="img_upload"
					data-options="prompt:'Choose a image...'" style="width: 25%">
				<%-- <img src="<%=basePath%>/${modBannerUrl }" style="width: 50px; height: 50px;" />留空默认使用父栏目banner --%>
				<img src="<%=basePath%>/${modIco }" style="width:50px;height:50px;" />
			</div>
			<div class="fitem">
				<label>大图:</label> <input name="coverImg"
					class="easyui-filebox validatebox" validType="img_upload"
					data-options="prompt:'Choose a image...'"
					style="width: 25%" >
				<img id="img" src="" style="width:50px;height:50px;" />
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
var modId;
var basePath='<%=basePath%>';
jQuery(function(){
	modId ='${modId}';
	var content;
	$.post("${rootPath}/imgListmodule/getModInfo",
			{"modId" : modId},
			function(data)
			{
				if (data.code == '0' || data.code == 0) {
					/* var mod = JSON.parse(data.mod);
					var modDes = JSON.parse(data.modDes);
					var modContent = JSON.parse(data.modContent);
					var ban = JSON.parse(data.banner); */
					$("#title").textbox('setValue',data.mod.modName);
					$("#weight").textbox('setValue', data.mod.modSquence);
					$("#modUrl").textbox('setValue', data.mod.modUrl);
					$("#modSeo").textbox('setValue', data.modDes.modSeo);
					$("#key").textbox('setValue', data.modDes.keyWord);
					//$("#modDes").textbox('setValue', modDes.modDes);
					$("#modDes").val(data.modDes.modDes);
					$("#img").attr('src',basePath+'/'+data.modDes.modImg); 
					$("#ban").attr('src',basePath+'/'+data.banner.banImgUrl); 
					$("#bannerId").val(data.banner.banId); 
					$("#banImgId").val(data.banner.banImgId); 
					content = Base64.decode(data.modContent.content);
					CKEDITOR.instances.editor.setData(content);
				}
			});
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
					url : '${rootPath}/imgListmodule/updataModuleInfo',
					onSubmit : function() {
						$("#dataForm").serializeArray();
					},
					success : function(data) {
						ajaxobj = eval("(" + data + ")");
						if (ajaxobj.result == 'true'
							|| ajaxobj.result == true) {
							$.messager.alert('提示', ajaxobj.msg, function() {
								url = '/module/subList?reqMenuId=' + ajaxobj.reqMenuId + '&backUp2='
								+ ajaxobj.backUp2;
								var tabName = window.parent.wrapper.getCurrentTabTitle();
								window.parent.wrapper.addTabCloseOld(ajaxobj.menuName, url, '', tabName);
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
