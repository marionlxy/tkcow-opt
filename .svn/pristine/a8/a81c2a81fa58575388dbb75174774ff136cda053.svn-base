<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%@ include file="/include.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/css/icon.css">
    <title></title>
</head>
<body>

	<div style="margin: 5px; height: 40px; overflow: auto;">
		<div style="border-bottom: 1px solid #CCC; margin-bottom: 5px;">
			<span class="icon32 icon-group32"
				style="padding-left: 48px; font-weight: bold; font-size: 14px; color: #666;"
				id="sp"></span>
		</div>
	</div>
	<input type="hidden" id="roleCode"/>
	<div id="dataGrd" data-options="modal:true"></div>  
	
	<div id="newdivDialog">
	</div>
<script type="text/javascript">
var roleName;
var roleCode;
jQuery(function(){ 
	roleName='${roleName}';	
	roleCode='${roleCode}';
	$('#sp').text(roleName);
	$('#roleCode').val(roleCode);
//初始化列表
   	$('#dataGrd').datagrid({
   		iconCls:'icon-tip',
   		rownumbers:true,
   		pagination:true,
   		fitColumns:true,
   		url:'${rootPath}/role/findAllUser?roleCode='+roleCode,
   		method : 'post',		
		idField : 'userId',//此处根据实际情况进行填写
		columns:[[
		          {field:'ck',checkbox:true},
					{field:'userCode',title:'用户编码',width:30},
					{field:'userName',title:'用户名',width:30},
					{field:'userNature',title:'用户性质',width:30,
						formatter: function(value,row,index){
		                            if(value=='H'){
		                            	return '总公司';
		                            }else if(value=='B'){
		                            	return '分公司';
		                            }
						}	
					},
					{field:'workStatus',title:'工作状态',width:30,
						formatter: function(value,row,index){
		                            if(value=='1'){
		                            	return '在岗';
		                            }else if(value=='0'){
		                            	return '离岗';
		                            }
						}	
					},
					{field:'comCoy',title:'分公司',width:20},
					{field:'comBranch',title:'中支',width:20},
		 ]],
		toolbar : [{
			id : 'btnadd',
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				adduser();
			}
		}, '-', {
			id : 'btndel',
			text : '取消用户',
			iconCls : 'icon-remove',
			handler : function() {
				deleuser();
			}
		}],
		onLoadSuccess : function(data) {
			$('#dataGrd').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
   });
function adduser(){
	   url = '${rootPath}/role/toEditUserRole?&roleCode='+roleCode;
	   	$("#newdivDialog").dialog({
		        title: "新增用户",
		        width: 550,
		        height: 450,
		        href:url,
				cache: false,
				closed: false,    
			    cache: false,
			    modal:true
		    });
		}

function deleuser(){
    var rows = $('#dataGrd').datagrid('getSelections');
    if (rows.length>0){
     	   com.message('confirm','确定要取消该角色相关用户？','提示',function(r){
            	if (r){
                    $.ajax({
        				 type: "POST",
        		            url: "${rootPath}/userRole/del?roleCode="+roleCode,
        		            contentType:"application/json",
        		            data: JSON.stringify(rows),
        		            dataType: "json",
        		            success: function (data) {
        		            	if(data.result == 'true' || data.result == true)
        		    			{
        		    				com.message("success", "该角色用户取消成功！");
        		    				Back(1);
        		    			}
        		    			else
        		    			{
        		    				com.message("error", "该角色用户取消失败 ！");
        		    			}
        		                    }
              	           });
                  }
        });
    }else{
    	com.message('warning','还未选择用户！')
    }
}

function Back(flag) {
	if(flag==1)
	{
		$('#dataGrd').datagrid('reload');
	}
	$("#newdivDialog").window('close');
	
}

    
</script>

</body>
</html>