<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
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
	<div class="easyui-panel" title="查询条件" data-options="fit : true,border : false">
		<div   data-options="region:'north',height:80,border : false">
			<form id="dataForm" method="post">
				<div class="fitem">
					<label>模块名称:</label> <input name="modName" class="easyui-textbox">
				</div>
				<div class="fitem">
					<label>模块别名:</label> <input name="modByName" class="easyui-textbox">
				</div>
				<div class="fitem">
					<label>创建时间:</label> <input name="startTime"
						class="easyui-datetimebox" style="width: 100px">-<input
						name="endTime" class="easyui-datetimebox"
						style="width: 100px">
				</div>
			</form>
			<input name="reqMenuId" type="hidden" id="reqMenuId" value="${reqMenuId}">
			<input type="hidden" name="modId" id="modId" value='${modId}'>
			<input type="hidden" name="modIsleaf" id="modIsleaf"
				value='${modIsleaf }'>
			<input type="hidden" name="modLevel" id="modLevel"
				value='${modLevel }'>
			<input type="hidden" name="modName" id="modName"
				value='${modName }'>
			<input type="hidden" name="menuName" id="menuName"
				value='${menuName }'>	
			<input type="hidden" name="parentId" id="parentId"
				value='${parentId }'>	
			<input type="hidden" name="menuId" id="menuId"
				value='${menuId}'>	
			<input type="hidden" name="status" id="status"
				value='${status }'>	
			<input type="hidden" name="isLeaf" id="isLeaf"
				value='${isLeaf }'>	
			<div id="buttons" align="center">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="searchInfo()" style="width: 90px">查询</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-empty" onclick="clearForm()" style="width: 90px">清空</a>
			</div>
		</div>
		
		
		<div class="easyui-layout" data-options="fit : true,border : false">
			<div data-options="region:'center',border:false"
						style="overflow: hidden;heigth:276px;">
			<table id="dataTable" title="${modName }列表" style="height:276px;">
			</table>
			</div>
			<div id="divDialog"></div>
			</div>
	</div>
	<script type="text/javascript">
		var modId;
		jQuery(function() {
			modId = '${modId}';
			//初始化列表
			$('#dataTable')
					.datagrid(
							{
								iconCls : 'icon-tip',
								singleSelect : true,
								rownumbers : true,
								pagination : true,
								fitColumns : true,
								url : '${rootPath}/module/getSubMenu?modId='
										+ modId,
								method : 'post',
								fit : true,
								idField : 'modId',//此处根据实际情况进行填写
								columns : [ [
										{
											field : 'modId',
											title : '模块ID',
											hidden : true,
											width : 80
										},
										{
											field : 'menuId',
											title : '菜单',
											hidden : true,
											width : 80
										},
										{
											field : 'parentId',
											title : '菜单',
											hidden : true,
											width : 80
										},
										{
											field : 'modIsleaf',
											title : '菜单',
											hidden : true,
											width : 80
										},
										{
											field : 'modSquence',
											title : '排序号',
											width : 40,
											align:'center'
										},
										{
											field : 'modName',
											title : '模块名称',
											width : 80
										},
										{
											field : 'modByname',
											title : '模块别名',
											width : 80
										},
										{
											field : 'modUrl',
											title : '链接名称',
											width : 80
										},
										{
											field : 'modLevel',
											title : '栏目层级',
											width : 80,
											formatter : function(value, row,
													index) {
												/* //alert(row.modName); */
												if (value == '1' && row.modIsleaf=='0') {
													return '一级栏目';
												}else if (value == '2' && row.modIsleaf=='0') {
													return '二级栏目';
												}else if (value == '3' && row.modIsleaf=='0') {
													return '三级栏目';
												}else if (value == '4' && row.modIsleaf=='1') {
													return '叶子栏目';
												}else if(row.modIsleaf=='1'){
													return '叶子栏目';
												}
													
											}
										},
										{
											field : 'modType',
											title : '模块类型',
											width : 80,
											formatter : function(value, row,
													index) {
												/* //alert(row.modName); */
												if (value == '01') {
													return '下级栏目';
												}else if (value == '02') {
													return '栏目介绍';
												}else if (value == '03') {
													return '内容模板';
												}else if (value == '04') {
													return '带图列表模板';
												}else if(value == '05'){
													return 'Logo列表模板';
												}else if(value == '06'){
													return '不带图列表模板';
												}else if(value == '07'){
													return '发展历程模板';
												}else {
													return "";
												}
													
											}
										},
										{
											field : 'createdTime',
											title : '创建时间',
											width : 80
										},
										{
											field : 'operate',
											title : '操作',
											width : 200,
											formatter : function(value, row,
													index) {
												return "<a href='#' onclick=editrow('"
														+ row.modId
														+ "','"
														+ row.modName
														+ "','"
														+ row.modType
														+ "','"
														+ row.modLevel
														+ "') style='margin-left:20px'>[编辑]</a>"
														/* + "<a href='javascript:void(0)' iconCls='icon-add' onclick=addrow('"
														+ row.modId
														+ "','"
														+ row.modIsleaf
														+ "','"
														+ row.modLevel
														+ "','"
														+ row.modName
														+ "') style='margin-left:10px'>[增加子栏目]</a>" 
														+ "<a href='#' onclick=viewrow('"
														+ row.modId
														+ "','"
														+ row.modName
														+ "','"
														+ row.modLevel
														+ "','"
														+ row.menuId
														+ "') style='margin-left:10px'>[查看子栏目]</a>"*/
														+ "<a href='#' onclick=delerow('"
														+ row.menuId
														+ "','"
														+ row.VERSION
														+ "') style='margin-left:10px'>[删除]</a>";
											}
										}
								//注：最后一行后面的逗号要去掉
								] ],
								toolbar : [ {
									id : 'btnadd',
									text : '新增',
									iconCls : 'icon-add',
									handler : function() {
										addrowTop();
									}
								}],/*  '-', {
									id : 'btnedit',
									text : '更新',
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
								} ], */
								onLoadSuccess : function(data) {
									$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
									$(".datagrid-view").css("height","276px")
								}
							});

			//初始化下拉框-示例，请根据需要自定义实现
			/*
			 $('#combo1').combobox({    
			       url:'${rootPath}/getDictlist?dicttypeid=userstatus',    
			       valueField:'dictid',    
			       textField:'dictname',
			    	panelHeight:'auto'
			   }); 
			 */
			 
		});

		//表格查询
		function searchInfo() {
			//查询参数直接添加在queryParams中
			var modId='${modId}';
			var queryParams = $('#dataTable').datagrid('options').queryParams;
			var fields = $('#dataForm').serializeArray(); //自动序列化表单元素为JSON对象

			$.each(fields, function(i, field) {
				queryParams[field.name] = field.value; //设置查询参数

			});
			var url = '${rootPath}/module/list?modId='+modId;;
			$('#dataTable').datagrid('reload', url); //设置好查询参数 reload 一下就可以了
		}

		//清空查询条件
		function clearForm() {
			$('#dataForm').form('clear');

		}
		
		//顶边新增
		function addrowTop() {
			var title = '新增子栏目';
			var modId='${modId}',modIsleaf='${modIsleaf}',modLevel='${modLevel}',modName='${modName}';
			var menuId='${menuId}';var isLeaf='${isLeaf}',parentId='${parentId}',menuName='${menuName}';
			url = '/module/add?modId=' + modId + '&modName='
					+ escape(encodeURIComponent(modName))+'&modLevel='+modLevel+'&modIsleaf='+modIsleaf
					+'&menuId='+menuId
					+'&parentId='+parentId;
				
			/* openWin(url); */
			//window.parent.wrapper.addTab(title, url, '');
				
				window.parent.wrapper.colseExistTitle(title);
			window.parent.wrapper.addTab(title, url, '');
			/*  var tabName = window.parent.wrapper.getCurrentTabTitle();
			window.parent.wrapper.addTabCloseOld(title, url, '', tabName);  */
			
		}
		
			
		//查看
		function viewrow(modId,modName,modLevel,menuId) {
			//var row = $('#dataTable').datagrid('getSelected');
			if(modLevel=='4'){
				$.messager.alert('提示', "已是叶子栏目！", 'info');
				return;
			}
			if (modId) {
				//url = '${rootPath}/module/subList?backUp2=' +modId+'&reqMenuId='+menuId;
				url = '/module/subList?backUp2=' + modId +'&reqMenuId='+menuId;
				//$('#dataTable').datagrid('reload', url);
				window.parent.wrapper.addTab(modName, url, '');
				
			} else {
				$.messager.alert('提示', "请选择你要查看的记录", 'info');
				return;
			}
		}
		
		//修改
		function editrow(modId,modName,modType,modLevel) {
			 var menuParentName='${modName }';
			if (modId && modName && modLevel&& modType) {
				url = '/module/edit?modId=' + modId +'&modLevel='+modLevel+'&modType='+modType+'&menuParentName='+escape(encodeURIComponent(menuParentName));
				var title = '栏目编辑-'+modName; 
				window.parent.wrapper.addTab(title,url);
			} else {
				$.messager.alert('提示', "请选择你要更新的记录", 'info');
				return;
			}
		}

		//删除
		function delerow(modId) {
			var row = $('#dataTable').datagrid('getSelected');
			if (row) {
				$.messager.confirm('提示', '确定要删除行记录吗？', function(r) {
					if (r) {
						$.post('${rootPath}/module/del', {
							modId : row.modId,modLevel:row.modLevel,menuId:row.menuId,parentId:row.parentId
						}, function(data) {
							if (data.result == 'true' || data.result == true) {
								$('#dataTable').datagrid('reload'); // reload the user data
								$.messager.alert('提示', data.msg, 'show');
							} else {
								$.messager.alert('提示', data.msg, 'error');
							}
						});
					}
				});
			}
		}

		//点击增加弹出增加窗口
		function openWin(url) {
			$('#iframeDialogSelect').attr("src", url);
			$('#divDialog').window('open');

		}

		//关闭弹出窗口，返回父页面,根据标记决定是否执行查询操作
		function returnParent(flag) {
			if (flag == 1) {
				searchInfo();
			}
			$("#divDialog").window('close');
		}
	</script>
</body>
</html>