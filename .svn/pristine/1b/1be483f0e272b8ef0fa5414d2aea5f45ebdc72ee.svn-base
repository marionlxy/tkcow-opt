<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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
<div class="easyui-layout" data-options="fit : true,border : true">
        <div data-options="region:'center',border:false"  style="overflow: hidden;">
             <table id="dataTypeTable" >
             <input type="hidden" id="dictTypeId"/>
             </table> 
        </div>
         <div id="divTypeDialog">
    </div>
    </div>

<script type="text/javascript">	
	jQuery(function(){	  
	   	//初始化列表
	   	$('#dataTypeTable').datagrid({
	   		iconCls:'icon-tip',
	   		singleSelect : true,
	   		rownumbers:true,
	   		pagination:true,
	   		fit:true,
	   		url:'${rootPath}/sys/dictType/getDictTypeList',
	   		method : 'post',		
			idField : 'dictTypeId',//此处根据实际情况进行填写
			columns:[[
			                {field:'ck',checkbox:true},
							{field:'dictTypeId',title:'字典类型',width:100},
							{field:'dictTypeName',title:'字典名称',width:120},
							{field:'status',title:'状态',width:80,
								formatter: function(value,row,index){
		                            if(value=='1'){
		                            	return '有效';
		                            }else if(value=='0'){
		                            	return '无效';
		                            }
						}		
							},
							{field:'sortNo',title:'排序',width:80},
							{field:'parentId',title:'父类型',width:80},
							{field:'seqNo',title:'级联关系描述',width:80},
						//注：最后一行后面的逗号要去掉
		]],
			toolbar : [{
				id : 'btnadd',
				text : '刷新',
				iconCls : 'icon-arrow_refresh',
				handler : function() {
					 var url="${rootPath}/sys/dictType/getDictTypeList";
					$('#dataTypeTable').datagrid('load',url);
					
				}
			   },'-',{
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
				iconCls : 'icon-cross',
				handler : function() {
					delerow();
				}
			}, '-', {
				id : 'btndel',
				text : '后退',
				iconCls : 'icon-rewind_green',
				handler : function() {
					 goBack(0);
				}
			}],
			onLoadSuccess : function(data) {
				$('#dataTypeTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			}
		});
	   	
	   });
 
	//新增
	function addrow() {
		url = '${rootPath}/sys/dictType/showAddTypeEditPage';
		$("#divTypeDialog").dialog({
			title : "新增类别",
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
		var row = $('#dataTypeTable').datagrid('getSelected');

		if (row) {
			url = '${rootPath}/sys/dictType/showDictTypeEditPage?dictTypeId='
					+ row.dictTypeId;
			$("#divTypeDialog").dialog({
				title : "编辑类别",
				width : 450,
				height : 300,
				href : url,
				cache : false,
				closed : false,
				modal : true
			});
		} else {
			$.messager.alert("warning", "请选择你要更新的记录!");
			return;
		}
	}

	//删除
	function delerow() {
		var row = $('#dataTypeTable').datagrid('getSelected');
		if (row) {
			com.message('confirm', '确定要删除该类别吗？请谨慎操作!', '提示', function(r) {
				if (r) {
					$.post('${rootPath}/sys/dictType/deleteDictType', {
						dictType : row.dictTypeId
					}, function(data) {
						if (data.result == 'true' || data.result == true) {
							$.messager.alert("success", data.msg);
							goTypeBack(1);
						} else if (data.result == 'false') {
							$.messager.alert("error", data.msg);
						}
					});
				}
			});
		}
	}

	function goTypeBack(flag) {
		if (flag == 1) {
			url = '${rootPath}/sys/dictType/getDictTypeList', $(
					'#dataTypeTable').datagrid('reload');
		}
		$("#divTypeDialog").window('close');
	}
</script>
</body>
</html>