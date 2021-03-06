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
<div>
	<form id="dataFormLogo" enctype="multipart/form-data" method="post">
			<div class="fitem">
		    	<label>字典类型:</label>
		        <input name="dictTypeId" class="easyui-textbox" value='${dictTypeId}' readonly="true">
			</div>
			<div class="fitem">
		    	<label>字典代码:</label>
		        <input name="dictId" class="easyui-textbox"  data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>字典描述:</label>
		        <input name="dictName" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
		    	<label>状态:</label>
				 <select class="easyui-combobox" name="status" panelHeight="60px" style="width:80px" required="required">	
				            <option value=""></option>			        
							<option value="1">有效</option>
							<option value="0">无效</option>
				</select>
			</div>
			<div class="fitem">
		    	<label>排序(整数):</label>
		        <input name="sortNo" class="easyui-numberbox" >
			</div>
		   <div class="fitem">
		    	<label>Logo图片:</label>
		        <!-- <input name="logoFile" class="easyui-filebox" required="required"> -->
		        <input name="logoFile" id="logoFile" type="file" onchange="javascript:setImagePreview();">
			</div>
			<div class="fitem" id="localImag">
		        <img id="logoImg" style="display: block; width: 80px; height: 80px; padding-left: 100px;" />
			</div>
			<input id="mod_img_name" name="mod_img_name" type=hidden value="">
			<input id="mod_img_rename" name="mod_img_rename" type=hidden value="">
			<input id="mod_img_url" name="mod_img_url" type=hidden value="">
	</form>
    <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()" style="width:90px">提交</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="goBack(0)" style="width:90px">取消</a>
   </div>
</div>
    
<script type="text/javascript">

//提交记录
function saveOrUpdate() {
	//$("input[name='logoFile']").attr('id', 'logoFile');
	var r = $('#dataForm').form('validate');
	if(!r) {
		return false;
	} else {
		
		$.ajaxFileUpload({
            url: "${rootPath}/imgUpload/uploadLogo", 
            type: 'post',
            secureuri: false, //一般设置为false
            complete:null,
            fileElementId: 'logoFile', // 上传文件的id、name属性名
            dataType: 'text', //返回值类型，必须text
            success: function(data, status,msg){
            	if (data == null || data == '') {
            		com.message("error", "Logo图片上传失败！");
            	} else {
            		var daObj = eval('(' + data + ')');
            		if (daObj.code == '0') {
                		$("#mod_img_name").val(daObj.originName);
                		$("#mod_img_rename").val(daObj.trueName);
                		$("#mod_img_url").val(daObj.storePath);
            			var jsonData = $("#dataFormLogo").serializeArray();
    					$.post("${rootPath}/sys/dictEntry/saveLogoEntry",
    							$("#dataFormLogo").serializeArray(),
    							function(data) {
    								if(data.result == 'true' || data.result == true) {
    									com.message("success", "数据字典添加成功！");
    									goBack(1);
    								} else {
    									com.message("error", "数据字典添加失败 ！");
    								}
    							});
            		} else {
            			com.message("error", "Logo图片上传失败！");
            		}
            	}
            }
        });
	}
}

//下面用于图片上传预览功能
function setImagePreview(avalue) {
	var docObj = document.getElementById("logoFile");

	var imgObjPreview = document.getElementById("logoImg");
	if (docObj.files && docObj.files[0]) {
		//火狐下，直接设img属性
		imgObjPreview.style.display = 'block';
		imgObjPreview.style.width = '80px';
		imgObjPreview.style.height = '80px';
		//imgObjPreview.src = docObj.files[0].getAsDataURL();

		//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
		imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
	} else {
		//IE下，使用滤镜
		docObj.select();
		var imgSrc = document.selection.createRange().text;
		var localImagId = document.getElementById("localImag");
		//必须设置初始大小
		localImagId.style.width = "80px";
		localImagId.style.height = "80px";
		//图片异常的捕捉，防止用户修改后缀来伪造图片
		try {
			localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			localImagId.filters
					.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
		} catch (e) {
			alert("您上传的图片格式不正确，请重新选择!");
			return false;
		}
		imgObjPreview.style.display = 'none';
		document.selection.empty();
	}
	return true;
}

</script>

</body>
</html>
