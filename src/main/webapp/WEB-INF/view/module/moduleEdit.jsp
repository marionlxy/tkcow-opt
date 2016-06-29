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
	
	<div class="" title="栏目介绍·修改页面" style="padding: 10px 60px 20px 60px">
		<form id="dataFormEdit" method="post" enctype="multipart/form-data">
			<input type="hidden" name="modId" value="${modId }">
			<input type="hidden" name="modParentName" value="${modParentName }">
			<input type="hidden" name="modParentId" value="${modParentId }">
			<input type="hidden" name="menuId" value="${menuId }"> 
			<input type="hidden" name="menuName" value="${menuName }">
			<input type="hidden" name="parentId"  >
			<input type="hidden" name="version" >
			<div class="fitem">
				<label>模块名称:</label> <input name="modName" class="easyui-textbox" value="${modName }" 
					data-options="required:true"  />
			</div>
			<div class="fitem">
				<label>模块别名:</label> <input name="modByname" class="easyui-textbox" value="${modByname }"
					data-options=""  />
			</div>
			<div class="fitem">
				<label>模块排序号:</label> <input name="modSquence" value="${modSquence }"
					 
					missingMessage='必须填1-99之间的数字' class="easyui-numberbox"
					validType="length[0,2]" data-options="required:true" />
			</div>
			<div class="fitem">
				<label>链接名称(站内):</label> <input name="modUrl" class="easyui-textbox" data-options="required:true" value="${modUrl }"
					 />
			</div>
			<div class="fitem">
				<label>SEO标题:</label> <input name="modSeo" class="easyui-textbox" 
					data-options="" value="${modSeo }" />
			</div>
			<div class="fitem">
				<label>SEO描述:</label> <input name="seoDes" class="easyui-textbox" value="${seoDes }"
					 />
			</div>
			<div class="fitem">
				<label>描述:</label>
				<textarea name="modDes"  style=" border:1px solid #99bbe8;" validType="length[0,1000]"  rows="4" cols="33" class="easyui-validatebox" value="${modDes }" ></textarea>
			<!-- 	<textarea name="modDes" class="easyui-textbox"
					style="width: 316px; border: 1px solid #99bbe8; height: 200px"
					rows="4" cols="33" ></textarea> -->
			</div>

			<div class="fitem">
				<label>banner图片:</label>
				<input name="modNewBanner"
					class="easyui-validatebox" type="file" validType="img_upload"
					data-options="prompt:'Choose a image...'"
					style="width: 25%" >
				<!-- <input name="modNewBanner" class="easyui-textbox"> 
				<input name="modBanner"
					class="easyui-validatebox" type='file' validType="img_upload"
					data-options="prompt:'Choose a image...'"
					style="width: 25%"  /> -->
				<img src="<%=basePath%>/${modBannerUrl }" style="width:50px;height:50px;" />
			</div>
			<div class="fitem">
				<label>图标:</label> 
				<input name="imgIcon"	class="easyui-validatebox" type="file" validType="img_upload"
					data-options="prompt:'Choose a image...'"	style="width: 25%" >
				<img src="<%=basePath%>/${modIco }" style="width:50px;height:50px;"/>
			</div>
		<%-- 	<div class="fitem">
				<label>图标:</label> 
				<input name="imgIcon"	class="easyui-filebox validatebox" validType="img_upload"
					data-options="prompt:'Choose a image...'" style="width: 25%">
				<img src="<%=basePath%>/${modBannerUrl }" style="width: 50px; height: 50px;" />留空默认使用父栏目banner
				<img src="<%=basePath%>/${modIco }" style="width:50px;height:50px;" />
			</div> --%>
			<div class="fitem">
				<label>小图片:</label> 
				<input name="modNewImg"
					class="easyui-validatebox" type="file" validType="img_upload"
					data-options="prompt:'Choose a image...'"
					style="width: 25%" >
				<img src="<%=basePath%>/${modImgUrl }" style="width:50px;height:50px;"/>
			</div>
		</form>
		<div id="dlg-buttons" align="center">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" id="save" onclick="saveOrUpdate()"
				style="width: 90px">提交</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.parent.wrapper.rightMenuClick({ id: 'close' })"
				style="width: 90px">取消</a>
		</div>
	</div>

	<script type="text/javascript">

	jQuery(function() {
		// 注意：不要读取缓存
		jQuery.ajaxSetup({
			cache : false
		});

		var modId = '${modId}';

		if (modId != null && modId != "" && modId != 0) {
			var url = '${rootPath}/module/getOne?modId=' + modId;
			$('#dataFormEdit').form('load', url);
		}
	});

		//提交记录
		function saveOrUpdate() {
			
				var r = $('#dataFormEdit').form('validate');
				if (!r) {
					return false;
				} else {
					$('#dataFormEdit').form(
							'submit',
							{
								url : '${rootPath}/column/editFormCol',
								onSubmit : function() {
									$("#dataFormEdit").serializeArray();
								} ,
								success : function(data) {
									var ajaxobj = eval("(" + data + ")");
									if (ajaxobj.result == 'true'
											|| ajaxobj.result == true) {
										$.messager.alert('提示', ajaxobj.msg, function(){
											url = '/module/subList?reqMenuId=' + ajaxobj.reqMenuId + '&backUp2='
											+ ajaxobj.backUp2;
											/* openWin(url); */
											var tabName = window.parent.wrapper.getCurrentTabTitle();
											window.parent.wrapper.addTabCloseOld(ajaxobj.menuName, url, '', tabName);
									});
									$('#save').linkbutton('disable');
									} else {
										$.messager
												.alert("提示", ajaxobj.msg, 'error');
									}
								} 
							});
				}
			}
		//返回父页面  
		function goBack(flag) {
			parent.returnParent(flag);
		}

		
	</script>
</body>
</html>
