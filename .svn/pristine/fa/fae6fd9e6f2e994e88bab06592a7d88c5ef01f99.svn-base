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
	<div  data-options="region:'north'" style="height:8%">
		<form id="dataHosForm" method="post">
			<div id="condition" class="container_12" style="position: relative;">
			    <br/>
				<div class="grid_1 lbl">用户编码</div>
				<div class="grid_2 val">
					<input class="easyui-textbox" style="width:100%;" name="usercode" id="usercode" />
				</div>
				<div class="grid_1 lbl">用户姓名</div>
				<div class="grid_2 val">
					<input class="easyui-textbox" style="width:100%;" name="username" id="username"/>
				</div>

				<div class="grid_2 val">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="quertUsrBtn"  style="width:50%;">查询</a>
				</div>
				<div class="grid_1 lbl">
                        <a href="javascript:void(0)" class="easyui-linkbutton"
                            iconCls="icon-empty" onclick="clearForm()" style="width:100%;">清空</a>
                </div>
			</div>
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

function setOrganize(userId,userName){
      url='${rootPath}/user/setOrgaize?userId='+userId+"&userName="+userName;
      $("#divDialog").dialog({
	        title: "设置机构",
	        width: 620,
	        height: 500,
	        href:url,
			cache: false,
			closed: false,    
			modal:true
	    });
    }
function setRole(userId,userName){
	 url='${rootPath}/user/setUserRole?userCode='+userId+"&userName="+userName;
	 $("#divDialog").dialog({
		        title: "设置角色",
		        width: 650,
		        height: 500,
		        href:url,
				cache: false,
				closed: false,    
				modal:true
		    });
  }
var reqMenuId;
 $(function() {
	 reqMenuId = '${reqMenuId}';
	//初始化列表
   	$('#dataTable').datagrid({
   		iconCls:'icon-tip',
   		singleSelect : true,
   		url:"${rootPath}/user/getUserList",
   		//fit:true,
   		rownumbers:true,
   		remoteSort:true, // true 本地排序js排序  false远程排序 
   		remoteSort: false,
        multiSort: true,
        pagination: true,
        enableHeaderClickMenu: true,        //此属性开启表头列名称右侧那个箭头形状的鼠标左键点击菜单
        enableHeaderContextMenu: true,      //此属性开启表头列名称右键点击菜单
        enableRowContextMenu: true,
        moveMenu: { submenu: false, top: true, up: true, down: true, bottom: true },//开始行右键菜单的移动列功能，此属性可丰富配置，详情见 API 文档
        selectOnRowContextMenu: true,       //此属性开启当右键点击行时自动选择该行的功能
   		onSortColumn:onSortColumnClick, // 远程排序时要调用的方法名称
   		pagination:true,
   		method : 'post',
   		idField : 'id',//此处根据实际情况进行填写
		columns:[[
	                {field:'ck',checkbox:true},
	                {field:'userName',title:'用户名',width:140,sortable: true},
            		{field:'email',title:'电子邮箱',width:150,sortable: true},
            		{field:'telephone',title:'电话号码',width:100},
					{field:'userRole',title:'用户角色',width:100},
					{field:'aa',title:'操作', width:280,
						formatter: function(value,row,index){
							return "<a href='#' onclick=setRole('"+row.id+"','"+row.userName+"') style='margin-left:10px'>&nbsp<span class='icon icon-group'>&nbsp;</span>[设置角色]</a>";
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
		}, '-', {
			id : 'btnrset',
			text : '密码重置',
			iconCls : 'icon-key',
			handler : function() {
				rSetPsw();
			}
		}, '-', {
			id : 'btndel',
			text : '删除',
			iconCls : 'icon-cross',
			handler : function() {
				deleUserrow();
			}
		}],
		onLoadSuccess : function(data) {
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
           	var toolbar = $('#dataTable').datagrid('options').toolbar;
			// 设置按钮是否可用
			var btnurl = "${rootPath}/menu/getbtnAccess?reqMenuId=" + reqMenuId;
           	btnCheck(btnurl,toolbar);
		}
        
	}).datagrid("columnMoving");
    
   	// 查询按钮事件
	$('#quertUsrBtn').click( function () {
		queryUserList();
	});
	// 查询按钮事件
	$('#exportUserBtn').click( function () {
		var usercode = $("#usercode").val();
		var username = $("#username").val();
		exportExcelList("${rootPath}/user/exportExcel?usercode="+ usercode + "&username=" + username);
	});
});

function exportExcelList(url) {  
	var elemIF = document.createElement("iframe");  
	elemIF.src = url;
    elemIF.style.display = "none";  
	document.body.appendChild(elemIF);  
}  

function onSortColumnClick(sort,order){
	queryUserList(sort+'-'+order);  
}
 
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
	url = '${rootPath}/user/showUserAddPage';
	$("#divDialog").dialog({
        title: "新增系统用户",
        width: 450,
        height: 300,
        href:url,
		cache: false,
		closed: false,    
	    modal:true
    });
}

/**
 * 修改用户<br/>
 */
function editUserrow() {
	var row = $('#dataTable').datagrid('getSelected');

	if (row) {
		url = '${rootPath}/user/showUserEditPage?rowId=' + row.User_Id;
		$("#divDialog").dialog({
			title : "编辑用户信息",
			width : 450,
			height : 550,
			href : url,
			cache : false,
			closed : false,
			modal : true
		});
		// openWin(url);
	} else {
       	$.messager.alert('提示', "请选择你要更新的记录", 'info');
		return;
	}
}
function downloadInfo(userid) {
	var url="${rootPath}/user/exportExcelByTemplate?rowId=" + userid; 
    var elemIF = document.createElement("iframe");  
	elemIF.src = url;
	elemIF.style.display = "none";
	document.body.appendChild(elemIF);
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
function deleUserrow() {
	var row = $('#dataTable').datagrid('getSelected');
	if (row) {
		com.message('confirm', '确定删除该用户吗？请谨慎操作！', '提示', function(r) {
			if (r) {
				$.post('${rootPath}/user/deleteUser', {
					rowId : row.User_Id
				}, function(data) {
					if (data.result == 'true' || data.result == true) {
						$.messager.alert("success", "用户删除成功！");
						goBack(2);
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

function goBack(flag) {
	if (flag == 1) {
	    $("#divDialog").window('close');
		queryUserList();
	} else if (flag == 2) {
        queryUserList();
    }
}
//清空查询条件
function clearForm() {
    $('#dataHosForm').form('clear');

}


function configGrid(grid, startIndex) {
	var columns = grid.datagrid('options').columns[0];
	//return;  
	var jsonStr = "[";
	$.each(columns, function(i, col) {

		if (startIndex) {
			if (i >= startIndex) {
				jsonStr += '{"field":"' + col.field + '","title":"'
						+ col.title + '","hidden":' + (col.hidden == true)
						+ '},';
			}
		} else {
			jsonStr += '{"field":"' + col.field + '","title":"' + col.title
					+ '","hidden":' + (col.hidden == true) + '},';
		}
	});
	//grid.datagrid('hideColumn',[4].field);  
	if (jsonStr.length > 1) {
		jsonStr = jsonStr.substring(0, jsonStr.length - 1);
	}
	jsonStr += "]";
	var tmpStr = escape(encodeURIComponent(jsonStr));
	$( "#MyPopWindow").window({
		title : '设置列表显示列',
		href : '${rootPath}/user/showConfig?columnStr=' + tmpStr,
		width : 400,
		height : 200,
		onLoad : function() {

		},
		onClose : function() {

			var cks = getConfiged();
			var ckobj = null;
			$.each(cks, function(i, ck) {
				ckobj = $(ck);
				if (ck.checked) {
					grid.datagrid("showColumn", ckobj.val());
				} else {
					grid.datagrid("hideColumn", ckobj.val());
				}
			});
		}
	});
}

</script>

</body>
</html>