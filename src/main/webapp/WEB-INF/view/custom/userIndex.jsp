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
<input type="hidden" name="reqMenuId">
<div class="easyui-layout" data-options="fit : true,border : false">
	<div  class="easyui-panel" title="查询条件" data-options="region:'north',height:124,border : false">
		<form id="dataHosForm" method="post">
			<table style="width:100%; height:1%; overflow: hidden;" border="10" 
				cellpadding="0" cellspacing="0"  class="kTable" >
			 <tr>
				 <td class="kTableLabel lbl">会员编号:</td>
				 <td><input name="id" class="easyui-textbox" ></td>
				 <td class="kTableLabel lbl">用户名:</td>
				 <td><input name="username" class="easyui-textbox" ></td>
			 </tr>
			  <tr>
			  	 <td class="kTableLabel lbl">姓名:</td>
				 <td><input name="truename" class="easyui-textbox" ></td>
				 <td class="kTableLabel lbl" style="height:29px;line-height: 29px;">注册时间:</td>
				 <td><input name="startTime" class="easyui-datetimebox" style="width:100px" >-<input name="endTime" class="easyui-datetimebox" style="width:100px"></td>
			 </tr>
			 <tr>
			 	<td valign="middle" align="center" colspan="6" >
			 		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchInfo()" style="width:90px">查询</a>              
                	&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-empty" onclick="clearForm()" style="width:90px">清空</a>
			 	</td>
			 </tr>
			</table>
		</form>
		</div>
     <div data-options="region:'center',border:false,fit : true"   style="overflow: hidden;">
             <table id="dataTable" >
             </table> 
     </div>
   <div id="divDialog"></div>
   <div id="MyPopWindow"></div>
   
</div>
<script type="text/javascript">
 $(function() {
	//初始化列表
   	$('#dataTable').datagrid({
   		iconCls:'icon-tip',
   		singleSelect : true,
   		url:"${rootPath}/custom/getCutomerList",
   		//fit:true,
   		rownumbers:true,
        multiSort: true,
        pagination: true,
   		method : 'post',
   		idField : 'id',//此处根据实际情况进行填写
		columns:[[
            		{field:'id',title:'会员编码',width:150,sortable: true},
					{field:'userName',title:'用户名',width:140,sortable: true},
					{field:'trueName',title:'姓名',width:100},
					{field:'addTime',title:'注册时间',width:200},
					{field:'address',title:'城市',width:100},
					{field:'aa',title:'操作', width:280,
						formatter: function(value,row,index){
							return "<a href='#' onclick=editUserrow('"+row.id+"');><span class='icon icon-wrench'>&nbsp</span>编辑</a> "
								+ "<a href='#' onclick=deleUserrow('"+row.id+"') style='margin-left:10px'>&nbsp<span class='icon icon-delete'>&nbsp;</span>删除</a>";
						}
			    }
					//注：最后一行后面的逗号要去掉
				]],
		toolbar : [{
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function() {
				addUser();
			}
		}],
		onLoadSuccess : function(data) {
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
        
	}).datagrid("columnMoving");
});
 
function queryUserList(sort,order) {
	var usercode = $("#usercode").val();
	var username = $("#username").val();
	var queryParams = $('#dataTable').datagrid('options').queryParams; 
	queryParams.user_code = usercode;
	queryParams.user_name = username;
	queryParams.sort = sort;
	queryParams.order = order;
	var url='${rootPath}/user/getUserByConList';
	$('#dataTable').datagrid('reload',url);
	
}

/**
 * 增加用户<br/>
 */
function addUser() {
	 var tabName = window.parent.wrapper.getCurrentTabTitle();
	 url = '/custom/addCutomer';
	 window.parent.wrapper.addTab('注册用户添加',url,'');
}

/**
 * 修改用户<br/>
 */
function editUserrow(id) {
	if (id) {
		var tabName = window.parent.wrapper.getCurrentTabTitle();
		url = '/custom/editCutomer?id=' + id;
		window.parent.wrapper.addTab('编辑客户信息',url,'');
	} else {
       	$.messager.alert('提示', "请选择你要更新的记录", 'info');
		return;
	}
}
//重置密码
function rSetPsw(){
	   var row = $('#dataTable').datagrid('getSelected');
     if (row){
    	 com.message('confirm','确定要重置密码为'+row.User_Code,'提示',function(r){
  		    if (r){
		         $.ajax({
		 		    type: "POST",
		 		    url: "${rootPath}/user/reSetPwd",
		 		    data: {userId:row.User_Id,userCode:row.User_Code},
		 		    dataType: "json",
		 		    success: function (data) {
		 		    	if(data.result == 'true' || data.result == true)
		 				{
		 		    		$.messager.alert("success", "密码重置密码成功！");
		 					goBack(1);
		 				}
		 				else
		 				{
		 					$.messager.alert("error", "密码重置密码失败 ！");
		 				}
		 		    }
		 		  });
  		   }
  	});
  } else {
		$.messager.alert("warning","请选择你要操作的记录!");
		return;
  }
  	
}

/**
 * 删除用户<br/>
 */
function deleUserrow(id) {
	var row = $('#dataTable').datagrid('getSelected');
	if (row) {
		com.message('confirm', '确定删除该用户吗？请谨慎操作！', '提示', function(r) {
			if (r) {
				$.post('${rootPath}/custom/delCustomInfo', {
					id : id
				}, function(data) {
					var ajaxobj = eval("(" + data + ")");
					if (ajaxobj.result == 'true' || ajaxobj.result == true) {
						$.messager.alert("success", "用户删除成功！");
						$('#dataTable').datagrid('reload');
					} else {
						$.messager.alert("error", "用户删除失败 ！");
					}
				});
			}
		});
	} else {
        $.messager.alert("warning","请选择你要操作的记录!");
        return;
  }
}
 
function searchInfo () {
	var queryParams = $('#dataTable').datagrid('options').queryParams;
	var fields = $('#dataHosForm').serializeArray(); //自动序列化表单元素为JSON对象
	$.each(fields, function(i, field) {
		queryParams[field.name] = field.value; //设置查询参数

	});
	var url = '${rootPath}/custom/searchCutomerList';
	$('#dataTable').datagrid('reload', url); //设置好查询参数 reload 一下就可以了
}

//清空查询条件
function clearForm() {
    $('#dataHosForm').form('clear');

}

</script>

</body>
</html>