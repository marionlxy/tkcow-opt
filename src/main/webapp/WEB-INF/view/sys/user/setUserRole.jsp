<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <%@ include file="/include.jsp"%>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/icon.css">
    <title></title>
</head>
<body>
<input type="hidden" name="userCode" >
<input type="hidden" name="userName" >
   <div class="easyui-layout" data-options="fit : true,border : true">
        <div data-options="region:'center',border:false"  style="overflow: hidden;">
             <table id="roledataTable" >
             </table> 
        </div>
        <div id="divDialogRole"> </div>
    </div>
<script type="text/javascript">
var userCode;
var userName;
    jQuery(function(){
    	userCode = '${userCode}';
    	userName = '${userName}';
       //初始化列表
       $('#roledataTable').datagrid({
           iconCls:'icon-tip',
           singleSelect : false,
           rownumbers:true,
           pagination:false,
           fit:true,
           url:'${rootPath}/role/getUsersRoleList?userCode=' + userCode,
           method : 'post',        
        idField : 'id',//此处根据实际情况进行填写
        columns:[[
            {field:'okflag',checkbox:true},
            {field:'roleCode',title:'角色编码',width:100},
            {field:'roleName',title:'角色名称',width:160},
            {field:'aa',title:'页面按钮', width:170,
                formatter: function(value,row,index){
                   return "<a href='#' onclick=setAuth('"+row.roleName+"','"+row.id+"','"+row.roleCode+"') style='margin-left:20px'><span class='icon icon-user_b'>&nbsp</span>[查看权限]</a>"
                }
        }
        ]],
        toolbar : [{
            id : 'btnedit',
            text : '确定',
            iconCls : 'icon-accept',
            handler : function() {
                editUserRole();
            }
           }, '-', {
            id : 'btndel',
            text : '取消',
            iconCls : 'icon-arrow_rotate_clockwise',
            handler : function() {
            	goBackUser(0);
            }
        }],
        onLoadSuccess : function(data) {
            //$('#roledataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
        	var rowData = data.rows;
            $.each(rowData,function(idx,val){//遍历JSON
                  if(val.okflag==1){
                    $("#roledataTable").datagrid("selectRow", idx);//如果数据行为已选中则选中改行
                  }
            });   
        }
    });
   });
    
    //编辑用户角色
   	function editUserRole() {
		var rows = $('#roledataTable').datagrid('getSelections');
		if (rows.length > 0) {
			com.message('confirm', '确定要设置角色？', '提示', function(r) {
				if (r) {
					$.ajax({
						type : "POST",
						url : "${rootPath}/userRole/saveUserRoles?userCode=" + userCode,
						contentType : "application/json",
						data : JSON.stringify(rows),
						dataType : "json",
						success : function(data) {
							if (data.result == 'true' || data.result == true) {
								$.messager.alert("success", "角色设置成功！");
								goBackUser(0);
							} else {
								$.messager.alert("error", "角色设置失败 ！");
							}
						}
					});
				}
			});
		} else {
			$.messager.alert('warning', '还未选择需要设置的角色！')
		}
	}

	function setAuth(roleName, value, code) {
		var roleNametmp=escape(encodeURIComponent(roleName));
		url = '${rootPath}/user/showAuthorithPage?roleName=' + roleNametmp
				+ '&roleId=' + value + '&roleCode=' + code;
		$("#divDialogRole").dialog({
			title : "查看权限",
			width : 550,
			height : 700,
			href : url,
			cache : false,
			closed : false,
			modal : true
		});
	}

	function goBackUser(flag) {
		if (flag == 0) {
			$("#divDialog").window('close');
		}
	}
</script>
</body>
</html>