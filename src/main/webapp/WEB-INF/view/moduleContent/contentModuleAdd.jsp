<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
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
	
<div class="easyui-panel" title="栏目名称" >
	<form id="dataForm" name="dataForm"
			action="${rootPath}/application/uploadBySpringGrpApplication"
			method="post" enctype="multipart/form-data">
			<br />
				 <input name="contentId" type="hidden" id="hidSId" />
				<div class="fitem">
		        	<label>标题:</label>
		        	<input name="title" class="easyui-textbox" >
		    	</div>
				<div class="fitem">
		        	<label>链接名称（站内）:</label>
		        	<input name="modUrl" class="easyui-textbox" >例如：aaa/bbb/ccc,留空即可自动生成拼音路径
		    	</div>
				<div class="fitem">
		        	<label>SEO标题:</label>
		        	<input name="modSeo" class="easyui-textbox" >
		    	</div>
				<div class="fitem">
		        	<label>关键词:</label>
		        	<input name="banType" class="easyui-textbox" >
		    	</div>
				<div class="fitem">
		        	<label>描述:</label>
		        	<textarea style="height:40px;width:200px;"  name="modDes" id="modDes"
		        	         class="easyui-validatebox " validType="length[0,2000]"
		        	          data-options="required:true">
		            </textarea>
		    	</div>
				<div class="fitem">
		        	<label>banner替换:</label>
		        	<input class="easyui-validatebox" validType="img_upload" type="file" name="banImgUrl" id="" >留空默认使用栏目banner
		    	</div>
				<div class="fitem">
		        	<label>内容:</label>
		        	<input name="content" class="easyui-textbox" >
		    	</div>
		</form>
		
	 <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()" style="width:90px">提交</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="goBack(0)" style="width:90px">取消</a>
  	 </div>
		
    <table id="dataTable" title="XX列表" style="width:800px;height:350px" >
    </table>    
 
</div>	
<script type="text/javascript">
//保存记录
function saveOrUpdate() {

	
	var r = $('#dataForm').form('validate');
	if (!r) {
		return false;
	} else 	{		
		$('#save').linkbutton('disable');
		$.post("${rootPath}/moduleContent/save",$("#dataForm").serializeArray(),
		function(data)
		{			
			if(data.result == 'true' || data.result == true)
			{
				
				$("#hidSId").val(data.sId);
				document.dataForm.submit();
				$.messager.alert("提示", data.msg);
			 	$.messager.show({title:'提示',msg:data.msg,showType:'show'});
				goBack(1);
			}
			else
			{
				$.messager.alert('提示',data.msg,'error');
				$('#save').linkbutton('enable');
			}
		});
	}

}
</script>

</body>
</html>
