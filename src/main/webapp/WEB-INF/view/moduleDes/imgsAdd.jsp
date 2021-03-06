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
<div class="easyui-panel" style="height: 212px;">
	<form id="dataForm1"  name="dataForm1"
			method="post" enctype="multipart/form-data" style="  margin: 30px 22px;">
			
			<!-- <input  name="banImgId" id="hidSId" type="hidden"> -->
			<input  name="banImgId" id="banImgId" type="hidden" value="">
			<input  name="banId" id="banId" type="hidden">
			
			<div class="fitem">
		    	<label style=" text-align: right;">排序权重:</label>
		        <input name="banImgNum" id="banImgNum" 
					missingMessage='必须填1-99之间的数字' class="easyui-numberbox"
					validType="length[0,2]"  data-options="required:true">
			</div>
			 <div class="fitem">
		    	<label style=" text-align: right;">图片:</label>
		        <input name="imgFile" id="imgFile" type="file" class="easyui-validatebox" validType="img_upload" data-options="required:true" accept=".jpg,.gif,.ico,.png, bmp">
			</div>
			<div class="fitem">
		    	<label style=" text-align: right;">标题:</label>
		        <input name="banImgTitle" id="banImgTitle" 
					 class="easyui-validatebox"
					validType="length[0,100]">
			</div>
			<div class="fitem">
		    	<label style=" text-align: right;">链接:</label>
		        <input name="banImgOutSide" id="banImgOutSide" 
					 class="easyui-validatebox"
					validType="length[0,1024]">
			</div>
			</form>
    <div id="dlg-buttons" align="center">
       <!-- 页面按钮有无权限控制 -->
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()" style="width:90px">提交</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="goBack()" style="width:90px">取消</a>
   </div>
</div>
    
    <script type="text/javascript">

//提交记录
function saveOrUpdate() {
	var banImgNum=$("#banImgNum").val();
	var banImgTitle=$("#banImgTitle").val();
	var banImgOutSide=$("#banImgOutSide").val();
// 	var params='{"banImgNum":"'+banImgNum+'"}'
// 	$("input[name='imgFile']").attr('id', 'imgFile');
	var r = $('#dataForm1').form('validate');
	if(!r) {
		return false;
	} else {
		$.ajaxFileUpload({
            url: "${rootPath}/imgUpload/uploadImgBanner", 
            type: 'post',
            secureuri: false, //一般设置为false
            complete:null,
            fileElementId: 'imgFile', // 上传文件的id、name属性名
            dataType: 'text', //返回值类型，一般设置为json、application/json
            data: { 'banImgNum': banImgNum,'banImgTitle': banImgTitle,'banImgOutSide': banImgOutSide},
            success: function(data, status,msg){
            	//window.location.href='${rootPath}/moduleDes/add';
            	//alert(document.getElementById('dataTable').html());
            	if (data == null || data == '') {
            		com.message("error", "图片上传失败！");
            	} else {
            		goBack(data);
            		var daObj = eval('(' + data + ')');
            	}
            }
        });
	}
}



	var rowId;
	jQuery(function(){ 
		rowId ='${rowId}';
		
		if (rowId != null && rowId != "" && rowId!=0){
			var url='${rootPath}/banner/getOne?rowId=' + rowId;
			$('#dataForm1').form('load', url);
		}
	});


	/**
	 * 提交记录新增用户<br/>
	 */
	function saveOrUpdate_0() {
		 
		 var banImgNum = document.getElementById('banImgNum').value;
// 		 alert(banImgNum);
// 		 alert(banImgNum.length);
		 
		 if(banImgNum.length>2){
			 alert("banImgNum最大为两位数");
			 return;
		 }
		 
		var r = $('#dataForm1').form('validate');
		if (!r) {
			return false;
		} else {
			$('#save').linkbutton('disable');
// 			$.post("${rootPath}/banner/saveBannerImg",
			$.post("${rootPath}/imgUpload/uploadLogo",
					
			$("#dataForm1").serializeArray(), 
				function(data) {
				if (data.result == 'true' || data.result == true) {
					
// 					alert("data+++=:  "+data.sId);
					$("#banImgId").val(data.sId);
// 					alert("banImgId:  "+$("#banImgId").val());
					document.dataForm1.submit();
					
					$.messager.alert("success", data.msg);
					//goBack(1);
				} else {
					$.messager.alert("error", data.msg);
				}
			});
		}
	}


	 function goBack(data){
		returnParent(data);
		}
</script>

</body>
</html>
