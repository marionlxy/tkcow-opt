<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <% 
       String roleNametmp = java.net.URLDecoder.decode(request.getParameter("roleName"),"UTF-8");
       //System.out.println(roleNametmp);
    %>
</head>
<body>

	<div style="margin: 5px; height: 40px; overflow: auto;">
		<div style="border-bottom: 1px solid #CCC; margin-bottom: 5px;">
			<span class="icon32 icon-group32"
				style="padding-left: 48px; font-weight: bold; font-size: 14px; color: #666;"
				id="sp"></span>
		</div>
	</div>
	<input type="hidden" id="roleId"/>
	<input type="hidden" id="roleName"/>
	<input type="hidden" id="roleCode"/>
	<div id="tt" class="easyui-tabs" data-options="fit : true,border : true">   
	    <div title="菜单权限" >
	    <table id="menuTree"  style="padding:20px;border:false"></table>
	    </div>   
    </div>   
	
<script type="text/javascript">
var roleId;
var roleCode;
jQuery(function(){ 
	roleId='${roleId}';
	roleCode='${roleCode}';
	roleName='${roleName}';
	$('#sp').text('<%=roleNametmp%>');
	$('#roleCode').val(roleCode);
	//菜单权限初始化菜单列表
	$('#menuTree').treegrid({
   		url:'${rootPath}/menu/getMenuTreeList?roleId=' + roleId,
   		method : 'post',
   		rownumbers: true,
   		singleSelect : false,
		idField: 'Menu_Id',
		cascadeCheck:false,
		treeField: 'Menu_Name',
		columns:[[
					{field:'Menu_Id',title:'菜单ID',width:80},
                    /*{field:'ck',checkbox:true},*/
                    {field:'Menu_Name',title:'菜单名称',formatter:function(value,rowData,rowIndex){
                        return "<input TYPE='checkbox' name='" + rowData.Menu_Id + "' id='" + rowData.Menu_Id + "'></INPUT>" + value + "";
                    },width:340},
				]],
		toolbar : [{
	           id : 'btnadd',
	           text : '刷新',
	           iconCls : 'icon-arrow_refresh',
	           handler : function() {
	        	   /* var furl='${rootPath}/menu/getMenuTreeList?roleId=' + roleId;
	        	   alert(furl); */
	        	   //var target = $(this);
	        	   $('#menuTree').treegrid('clearSelections');
	               var loaddata = $('#menuTree').treegrid('getData');
	               var id = loaddata[0].Menu_Id;
	               $('#menuTree').treegrid('reload',id);
	           }
	       }, '-', {
	           id : 'btnadd',
	           text : '取消',
	           iconCls : 'icon-arrow_rotate_clockwise',
	           handler : function() {
	        	   goBackRole(2);
	           }
	       }],
	       onLoadSuccess : function(data) {
	           $('#menuTree').treegrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
	           
	    	   var target = $(this);
	           var loaddata = target.treegrid('getData');
	           var havedmune = loaddata[0]._havedMenuId;
	           var list = havedmune.split("_");
	           for (i=0; i<list.length; i++) {
		           // 全部选中
		           $('input:checkbox').each(function() {
		              // $(this).attr('checked', true);
		        	  $("[name = " + list[i] + "]:checkbox").attr("checked", true);
		        	  if (list[i] != 0) {
                          $("#menuTree").treegrid("select", list[i]);
                      }
		           });
	           }
	       }
   });
	
});
function goBackRole(flag) {
    if(flag==2) {
        $("#divDialogRole").window('close');
    }
}
</script>

</body>
</html>