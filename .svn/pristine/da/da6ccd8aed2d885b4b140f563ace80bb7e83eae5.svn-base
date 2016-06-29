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
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',height:60,border : false">
			<form id="dataForm1" method="post">
				<div class="container_12" style="position: relative;">
				<br/>
					<div class="grid_1 lbl">角色编码:</div>
					<div class="grid_2 val">
						<input name="roleCode" class="easyui-textbox">
					</div>
					<div class="grid_1 lbl">角色名称:</div>
					<div class="grid_2 val">
						<input name="roleName" class="easyui-textbox">
					</div>
					<div class="grid_1 lbl"></div>
					<div class="grid_2 val"">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" onclick="searchInfo()" style="width: 90px">查询</a>
					</div>
					<div class="grid_1 lbl">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-empty" onclick="clearForm()" style="width: 90px">清空</a>
					</div>
					<div class="grid_2 val"></div>
				</div>
			</form>
		</div>
		<div data-options="region:'center',border:false"
			style="overflow: hidden;">
			<table id="dataTable">
			</table>
		</div>
		<div id="divDialog"></div>
	</div>
	<script type="text/javascript">
		jQuery(function() {
			//初始化列表
			$('#dataTable')
					.datagrid(
							{
								iconCls : 'icon-tip',
								singleSelect : true,
								rownumbers : true,
								pagination : true,
								fit : true,
								url : '${rootPath}/role/getRoleList',
								method : 'post',
								idField : 'Role_Id',//此处根据实际情况进行填写
								columns : [ [
										{
											field : 'ck',
											checkbox : true
										},
										{
											field : 'Role_Code',
											title : '角色编码',
											width : 140
										},
										{
											field : 'Role_Name',
											title : '角色名称',
											width : 140
										},
										{
											field : 'Role_Nature',
											title : '角色性质',
											width : 140,
											formatter : function(value, row,
													index) {
												if (value == 'H') {
													return '总公司';
												} else if (value == 'B') {
													return '分公司';
												}
											}
										},
										{
											field : 'Role_Status',
											title : '状态',
											width : 100,
											formatter : function(value, row,
													index) {
												if (value == '1') {
													return '正常';
												} else if (value == '0') {
													return '失效';
												}
											}
										},
										{
											field : 'CREATOR',
											title : '创建人',
											width : 140
										},
										{
											field : 'CREATE_TIME',
											title : '创建时间',
											width : 140
										},
										{
											field : 'aa',
											title : '页面按钮',
											width : 250,
											formatter : function(value, row,
													index) {
												return "<a href='#' onclick=setAuth('"
														+ row.Role_Name
														+ "','"
														+ row.Role_Id
														+ "','"
														+ row.Role_Code
														+ "') style='margin-left:20px'><span class='icon icon-user_b'>&nbsp</span>[权限设置]</a>"
												//return "<a href='#' onclick=setRole('"+row.roleName+"','"+row.roleCode+"') style='margin-left:15px'><span class='icon icon-wrench_orange'>&nbsp</span>[管理成员]</a><a href='#' onclick=setAuth('"+row.Role_Name+"','"+row.Role_Id+"','"+row.Role_Code+"') style='margin-left:20px'><span class='icon icon-user_b'>&nbsp</span>[权限设置]</a>"
											}
										} ] ],
								toolbar : [ {
									id : 'btnadd',
									text : '刷新',
									iconCls : 'icon-arrow_refresh',
									handler : function() {
										searchInfo();
									}
								}, '-', {
									id : 'btnadd',
									text : '新增',
									iconCls : 'icon-add',
									handler : function() {
										addrow();
									}
								}, '-', {
									id : 'btnedit',
									text : '编辑',
									iconCls : 'icon-edit',
									handler : function() {
										editrow();
									}
								}, '-', {
									id : 'btndel',
									text : '删除',
									iconCls : 'icon-remove',
									handler : function() {
										delerow();
									}
								} ],
								onLoadSuccess : function(data) {
									$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
								}
							});
		});

		//新增
		function addrow() {
			url = '${rootPath}/role/showRoleAddPage';
			$("#divDialog").dialog({
				title : "新增角色",
				width : 450,
				height : 300,
				href : url,
				cache : false,
				closed : false,
				modal : true
			});
		}

		//修改
		function editrow() {
			var row = $('#dataTable').datagrid('getSelected');

			if (row) {
				url = '${rootPath}/role/showRoleEditPage?roleId=' + row.Role_Id;
				$("#divDialog").dialog({
					title : "编辑角色",
					width : 450,
					height : 300,
					href : url,
					cache : false,
					closed : false,
					modal : true
				});
				//openWin(url);
			} else {
				$.messager.alert("warning", "请选择你要更新的记录!");
				return;
			}
		}

		//删除
		function delerow() {
			var row = $('#dataTable').datagrid('getSelected');
			if (row) {
				com.message('confirm', '删除后不可恢复,确定要删除该角色吗？', '提示', function(r) {
					if (r) {
						$.post('${rootPath}/role/deleteRole', {
							roleId : row.Role_Id
						}, function(data) {

							if (data.result == 'true' || data.result == true) {
								$.messager.alert("success", data.msg);
								goBack(1);
							} else {
								$.messager.alert("error", data.msg);
							}
						});
					}
				});
			} else {
				$.messager.alert("warning", "请选择你要操作的记录!");
				return;
			}
		}

		function setRole(roleName, value) {
			url = '${rootPath}/role/showSetRoleUserPage?roleName=' + roleName
					+ '&roleCode=' + value;
			$("#divDialog").dialog({
				title : "设置用户",
				width : 500,
				height : 430,
				href : url,
				cache : false,
				closed : false,
				modal : true
			});
		}
		function setAuth(roleName, value, code) {
			var roleNametmp = escape(encodeURIComponent(roleName));
			//alert(roleNametmp);
			url = '${rootPath}/role/showSetAuthPage?roleName=' + roleNametmp
					+ '&roleId=' + value + '&roleCode=' + code;
			$("#divDialog").dialog({
				title : "角色授限",
				fixPosition: true, //随滚动条滚动
				width : 550,
				height : 600,
				href : url,
				cache : false,
				closed : false,
				modal : true
			});
		}
		function goBack(flag) {
			if (flag == 1) {
				searchInfo();
			}
			$("#divDialog").window('close');
		}
		//清空查询条件
	    function clearForm() {
	        $('#dataForm1').form('clear');
	    
	    }
	    //表格查询
	    function searchInfo() {
	        //查询参数直接添加在queryParams中
	        var queryParams = $('#dataTable').datagrid('options').queryParams;
	        var fields = $('#dataForm1').serializeArray(); //自动序列化表单元素为JSON对象
	        $.each(fields, function(i, field) {
	            queryParams[field.name] = field.value; //设置查询参数
	    
	        });
	        var url = '${rootPath}/role/getRoleList';
	        $('#dataTable').datagrid('reload',url); //设置好查询参数 reload 一下就可以了
	    }
	</script>
</body>
</html>