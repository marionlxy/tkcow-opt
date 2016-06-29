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
<input type="hidden" name="menuId">
    <table id="buttonTable">
    </table> 
<script type="text/javascript">
var menuId;

	jQuery(function(){	  
	menuId = '${menuId}';
   	//初始化列表
   	var lastIndex;
   	var url='${rootPath}/menu/getBtnList?menuId=' + menuId;
   	$('#buttonTable').datagrid({
   		singleSelect : true,
   		pagination:true,
   		rownumbers:true,
   		url:url,
   		fit:true,
   		method : 'post',
		idField : 'Menu_Id',//此处根据实际情况进行填写
		columns:[[
            {field:'ck',checkbox:true},
			{field:'Menu_Id',title:'编码',width:100,editor:'text'},
			{field:'Menu_Name',title:'名称',width:160,editor:'text'},
			{field:'Icon',title:'图标',width:180 ,editor:'text'},
			{field:'Menu_Order',title:'排序',width:100,editor:'text'},
			//{field:'description',title:'描述',width:80,editor:'text'},
       		//注：最后一行后面的逗号要去掉
		]],
		toolbar : [{
			id : 'btnadd',
			text : '新增行',
			iconCls : 'icon-add',
			handler : function() {
				$('#buttonTable').datagrid('appendRow',{
					ck:null,
					Menu_Id:'',
					Menu_Name: '',
					Icon: 'icon-',
					Menu_Order:''
					//,description:''
				});
				endEdit();
                $('input:checkbox').each(function() {
                    $(this).attr('checked', false);
                });
			    lastIndex=$('#buttonTable').datagrid('getRows').length-1;
			    $('#buttonTable').datagrid('selectRow',lastIndex);
			    $('#buttonTable').datagrid('beginEdit',lastIndex);
			}
	   }, '-', {
           id : 'btnedit',
           text : '取消行',
           iconCls : 'icon-edit',
           handler : function() {
        	   var row = $('#buttonTable').datagrid('getSelected');
        	   if (row) {
        		   var rowIndex = $('#buttonTable').datagrid('getRowIndex', row);
				   $('#buttonTable').datagrid('deleteRow', rowIndex);  
        	   } else {
        		   $.messager.alert("warning", "请选择你要取消的行!");
        	   }
           }
       }, '-', {
			id : 'btndel',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
			    var row = $('#buttonTable').datagrid('getSelected');
				if (row){
				 	   com.message('confirm','删除后不可恢复,确定要删除该按钮吗？','提示',function(r){
				        if (r){
				            $.post('${rootPath}/menu/deleteMenu?menuId=' + row.Menu_Id,function(data){
				         	   if(data.result == 'true' || data.result == true) {
				         		  $.messager.alert("success", "按钮删除成功！");
				    				reLoad();
				    			} else {
				    			  $.messager.alert("error", "按钮删除成功！");
				    			}       	
				            });
				        }
				    });
				} else {
					$.messager.alert("warning", "请选择你要操作的记录!");
				}
			}
			}, '-', {
			id : 'btnsure',
			text : '确定',
			iconCls : 'icon-ok',
			handler : function() {
				commit();
			}
		    }, '-', {
			id : 'btndel',
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				goBack(0);
			}
		}],
		onDblClickRow : function(rowIndex) {
		   if(lastIndex!=rowIndex){
			   $('#buttonTable').datagrid('beginEdit',rowIndex);
			   $('#buttonTable').datagrid('endEdit',lastIndex);
		   }
		   lastIndex=rowIndex;
   		},
	});
   });
  

	function commit() {
		endEdit();
		var inserted = $('#buttonTable').datagrid('getChanges', 'inserted');
		var updated = $('#buttonTable').datagrid('getChanges', 'updated');
		var deleted = $('#buttonTable').datagrid('getChanges', 'deleted');
		if (inserted.length || updated.length || deleted.length) {
			com.ajax({
				type : "POST",
				url : "${rootPath}/menu/saveButtons?parentId=" + menuId,
				contentType : "application/json",
				data : JSON.stringify({
					"inserted" : inserted,
					"updated" : updated,
					"deleted" : deleted
				}),
				dataType : "json",
				success : function(data) {
					if (data.result == 'true' || data.result == true) {
						$('#buttonTable').datagrid('acceptChanges');
						$.messager.alert("success", "按钮设置成功！");
						reLoad();
					} else {
						$.messager.alert("error", '按钮设置失败 ！');
					}
				}
			});
		}
	}

	function endEdit() {
		var rows = $('#buttonTable').datagrid('getRows');
		for (var i = 0; i < rows.length; i++) {
			$('#buttonTable').datagrid('endEdit', i);
		}
	}
	function reLoad() {
		url = '${rootPath}/button/getBtnList', $('#buttonTable')
				.datagrid('reload');
	}
</script>
</body>
</html>