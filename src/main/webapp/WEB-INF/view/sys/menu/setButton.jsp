<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  
 <div style="margin:5px;overflow:auto;">
        <div style="border-bottom:1px solid #CCC; margin-bottom:5px;">
            <span class="icon32 icon-settings32" style="padding-left:48px;font-weight:bold; font-size:14px;color:#666;">请选择页面按钮</span> 
        </div>
        <div class="metrouicss">
            <label class="input-control checkbox" style="margin-top:6px;margin-left:3px;">
                <input type="checkbox" id="chkBox"><span class="helper">全选</span>
            </label>
            <button  id="editButton" class="image-button standart fg-color-white" style="height:32px;float:right">
            <i class="icon-grid-view bg-color-green"></i>
                                          管理按钮库
            </button>
        </div>
        <div id="buttDialog"></div>
         <div class="metrouicss">
	        <form id="dataForm">
				<ul class="listview">
					<c:forEach var="button" varStatus="varStatus" items="${buttonList}">
						<li button="${button.buttoncode}"><img alt="" src="${basePath}/script/css/icon/<c:out value="${button.buttonicon }"/>.png"> ${button.buttonname }</li>
						<c:forEach var="currentButton" items="${menuButList}">
							<c:if test="${currentButton.buttoncode==button.buttoncode}">
								<script type="text/javascript">
									$("li[button=${button.buttoncode}]").addClass('selected');
								</script>
							</c:if>
						</c:forEach>
					</c:forEach>
				</ul>
			</form>
        </div>
        </div>     
 	<div style="text-align:center;">
        <a  href="javascript:void(0)"  class="easyui-linkbutton" id="save" data-options="iconCls:'icon-ok'" >确定</a>  
        <a  href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"  onclick="goBack(0)">取消</a> 
    </div>
    
<script type="text/javascript">
var menuId;
jQuery(function(){ 
	menuId='${menuId}';	
	$('#save').click(function(){
				var buttons = [];
				$(".listview").find("li.selected").each(function () {
					buttons.push({"buttoncode": $(this).attr('button')});
				  });
		        if (buttons.length==0){
		        	   com.message('confirm','尚未分配菜单，确定离开？','提示',function(r){
		               if (r){
		                 $.ajax({
		     				 type: "POST",
		     		            url: "${rootPath}/menu/saveMenuButton?menuId="+menuId,
		     		            contentType:"application/json",
		     		            data: JSON.stringify(buttons),
		     		            dataType: "json",
		     		            success: function (data) {
		     		            	if(data.result == 'true' || data.result == true)
		     		   			{
		     		   				com.message("success", "按钮添加成功！");
		     		   				goBack(1);
		     		   			}
		     		   			else
		     		   			{
		     		   				com.message("error", '按钮添加失败 ！');
		     		   			}
		     		            }
		           	           });
		               }
		           });
		       }else {
			    	   $.ajax({
							 type: "POST",
					            url: "${rootPath}/menu/saveMenuButton?menuId="+menuId,
					            contentType:"application/json",
					            data: JSON.stringify(buttons),
					            dataType: "json",
					            success: function (data) {
					            	if(data.result == 'true' || data.result == true)
			     		   			{
			     		   				com.message("success", "按钮添加成功！");
			     		   				goBack(1);
			     		   			}
			     		   			else
			     		   			{
			     		   				com.message("error", '按钮添加失败 ！');
			     		   			}
					            }	
			     	           });
		       }
		
	});
	$('#chkBox').click(function(){
	  $(".listview").find("li").addClass('selected');
	});
   });

$(function(){
	$(".listview").find("li").click(function () {
         if ($(this).hasClass('selected'))
             $(this).removeClass('selected');
         else
             $(this).addClass('selected');
	});
});


$('#editButton').click(function() {
	 url='${rootPath}/menu/toButtonLibrary';
     $("#buttDialog").dialog({
	        title: "管理按钮库",
	        width: 480,
	        height: 380,
	        href:url,
			cache: false,
			closed: false,    
			modal:true
	    });
});
function Back() {
		$("#buttDialog").window('close');
}
    
</script>

</body>
</html>