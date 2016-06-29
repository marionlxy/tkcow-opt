<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
  <head>      
    <title>密码修改</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<%@ include file="/include.jsp" %>
	<style>

	select
	{
			width:158px;
			border: 1px solid #447799!important;
			background-color: transparent;
			display:-moz-inline-box ! important;
			display:inline;
	}
</style>
	
	
</head>

<body>
<div> 
		<form id="dataForm" >
  			<div class="fitem">
		    	<label>当前系统用户:</label>
		    	<input type="hidden" name="userId" id="userId">
		    	<input class="easyui-textbox" name="userName" value="${userName}" readonly="readonly" >
		    </div>
		      <div class="fitem">
		    	<label>请输入新密码:</label>
		    	<input class="easyui-textbox" type="password" id="pwd" name="userPwd" data-options="required:true" />
		    </div>
		      <div class="fitem">
		    	<label>请重复输入新密码:</label>
		    	<input class="easyui-textbox" type="password" id="rpwd" name="rpwd" required="true" validType="equals['#pwd']" />
		    </div>
		</form>
		<div id="dlg-buttons" align="center">
	       <a href="javascript:void(0)" onclick="savePwd();" class="easyui-linkbutton" iconCls="icon-ok">确定</a>
	       <a href="javascript:void(0)" onclick="goBack();" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
         </div>
  	</div>


<script type="text/javascript">

$.extend($.fn.validatebox.defaults.rules, {    
    equals: {    
        validator: function(value,param){    
            return value == $(param[0]).val();    
        },    
        message: '两次输入的密码不一致, 请重新输入！'   
    }    
}); 
var userId;
jQuery(function(){ 
	userId='${userId}';
	$('#userId').val(userId);
   });
	
	//初始化页面元素
	jQuery(function($){
		$("input[required][class*='validate']").each(function(index,domEle)
			{
				$("#" + domEle.id).after("<font style='color:#FF0000' >*</font>");
			});
		
		
		$("input[class*='lookup']").each(function(index,domEle)
			{
			$("#" + domEle.id).after("<img src='${rootPath}/common/imgs/lookup/lookup_button.gif' onclick='openWin();' style='height: 20px;margin:0px;padding:0px; cursor: pointer;'>");
			});
	});
	
	
	function savePwd()
	{
		var r = $('#dataForm').form('validate');
		if(!r) {
			return false;
		}
		else
		{
			$.post("${rootPath}/user/pwdSave",$("#dataForm").serializeArray(),
					function(data)
					{			
						if(data.result == 'true' || data.result == true)
						{
							com.message("success", "密码修改成功！");
							goBack();
						}
						else
						{
							com.message("error", "密码修改失败 ！");
						}
					});	
		}
	}

</script>
</body>
</html>