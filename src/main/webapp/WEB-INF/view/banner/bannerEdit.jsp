<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%@ include file="/include.jsp"%>
    <script src="<%=basePath%>/static/js/imageValidate.js"></script>
<title></title>
<style>
input{height:20px;}
</style>
</head>
<body>
<div class="" title="banner图·修改页面" style="padding: 10px 60px 20px 60px">
	<form id="dataFormEdit" enctype="multipart/form-data"  method="post">
			<input type="hidden" name="banImgId">
			<input  name="modId" id="modId" type="hidden" value="${modId}">
			<div class="fitem">
			<label>类别:</label>
			<input  class="easyui-textbox" data-options="required:true" style="border:1px solid #ccc;font-style:#C0C0C0; " value="indexBanner" readonly="readonly">
			<input name="banType" value="0" type="hidden">
			</div>
		<div class="fitem">
			<label>标题:</label> <input name="banImgName" class="easyui-textbox"
				data-options="required:true" value="${banImgName }">
		</div>
		<!-- <div class="fitem">
			<label>类别:</label> <input name="banType" class="easyui-textbox"
				data-options="required:true">
		</div> -->

		<div class="fitem">
			<label>排序号:</label> <input name="banImgNum" class="easyui-numberbox"
				 missingMessage='必须填1-99之间的数字'
				class="easyui-numberbox" validType="length[0,2]"
				data-options="required:true" value="${banImgNum }">
		</div>
		<div class="fitem">
			<label>描述:</label>
			<textarea name="banImgDes" rows="5"   class="easyui-validatebox " validType="length[0,2000]"
				style="width: 316px; border: 1px solid #99bbe8; height: 200px"
				rows="4" cols="33">${banImgDes }</textarea>
		</div>
		<div class="fitem">
			<label>链接地址:</label> <input name="banImgOutside"
				class="easyui-textbox" data-options="required:true" value="${banImgOutside }"> 如:http://www.abc.com
		</div>
		<div class="fitem">
			<label>图片:</label> <input name="modBanner"
					class="easyui-validatebox" type='file' validType="img_upload"
					data-options="prompt:'Choose a image...'"
					style="width: 25%" />
					<img src="<%=basePath%>/${banImgUrl }" style="width:50px;height:50px;" />
		</div>

			</form>
    <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()" style="width:90px">提交</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.parent.wrapper.rightMenuClick({ id: 'close' })" style="width:90px">取消</a>
   </div>
</div>
    
<script type="text/javascript">

var banImgId;
jQuery(function(){ 

	//初始化下拉框-示例，请根据需要自定义实现
   	/*
   	 $('#combo1').combobox({    
  	        url:'${rootPath}/getDictlist?dicttypeid=userstatus',    
  	        valueField:'dictid',    
  	        textField:'dictname',
  	     	panelHeight:'auto'
  	    }); 
  	  */ 
	
  	banImgId ='${banImgId}';
	
	if (banImgId != null && banImgId != "" && banImgId!=0){
		var url='${rootPath}/banner/getOne?banImgId=' + banImgId;
		$('#dataFormEdit').form('load', url);
	}
});

//提交记录
	//提交记录
		function saveOrUpdate() {
			var r = $('#dataFormEdit').form('validate');
			if (!r) {
				return false;
			} else {
				$('#dataFormEdit').form(
						'submit',
						{
							url : '${rootPath}/banner/save',
							onSubmit : function() {
								$("#dataFormEdit").serializeArray();
							},
							success : function(data) {
								var ajaxobj = eval("(" + data + ")");
								if (ajaxobj.result == 'true'
										|| ajaxobj.result == true) {
									$.messager.alert('提示', ajaxobj.msg, function(){
									url = '/banner';
										//openWin(url); 
									var tabName = window.parent.wrapper.getCurrentTabTitle();
									window.parent.wrapper.addTabCloseOld("banner图", url, '', tabName);
								});
								} else {
									$.messager.alert("提示", ajaxobj.msg, 'error');
									$('#save').linkbutton('enable');
								}
							}
						});
			}
		}
 
//返回父页面  
function goBack(flag){
	parent.returnParent(flag);
}
</script>

</body>
</html>
