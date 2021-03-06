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

<div class="easyui-layout" fit="true" data-options="border:false">

      <div data-options="region:'center',border:false"   style="overflow: hidden;">
         <table id="dataTable" style="height:435px">
         </table> 
     </div>
    <div id="divDialog" class="easyui-window"
		data-options="closed:true,closable:true,collapsible:false,minimizable:false,maximizable:false,modal:true,title:'明细信息维护'"
		style="width: 650px; height: 260px;">
		<iframe id="iframeDialogSelect" frameborder="0" scrolling="yes"	width="100%" height="98%"></iframe>
	</div>
</div>
<script type="text/javascript">
var type = '${dicType}';
var tabName;
	jQuery(function(){
		tabName = window.parent.wrapper.getCurrentTabTitle();
   	//初始化列表
   	$('#dataTable').datagrid({
   		singleSelect : true,
   		rownumbers:true,
   		pagination:true,
   		fitColumns:true,
   		url : '${rootPath}/sys/dictEntry/getDictEntryList?dictTypeId=' + type,
   		method : 'post',		
		idField : 'dictTypeId',//此处根据实际情况进行填写
		columns:[[
							//{field:'sortNo',title:'排序号',width:150},
							{field:'dictName',title:'标题',width:140},
							{field:'dictTypeId',title:'类别',width:200},
							{field:'backup1',title:'创建时间',width:110},
							{  
								field : 'operate',
								title : '操作',
								width : 100,
								formatter : function(value, row,index) {
									return "<a href='#' onclick=viewrow('"+row.dictId+"," + row.dictTypeId + "') style='margin-left:20px'>[编辑]</a>"
									+"<a href='#' onclick=delerow('"+row.dictId+"') style='margin-left:20px'>[删除]</a>";
								}
							}
						//注：最后一行后面的逗号要去掉
		]],
   		toolbar : [{
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function() {
				addrow();
			}
		}],
		onLoadSuccess : function(data) {
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
   });
	
	//清空查询条件
	function clearForm() {
		$('#dataForm').form('clear');
	
	}
   
   //删除
   function delerow(cooperLogoId){
	   var row = $('#dataTable').datagrid('getSelected');
       if (row){
        	   com.message('confirm','确定删除本链接吗？请谨慎操作！','提示',function(r){
               if (r){
                   $.post('${rootPath}/sys/dictEntry/deleteDictEntry',{dictTypeId:row.dictTypeId,dictId:row.dictId},function(data){
                	   if(data.result == 'true' || data.result == true) {
                		   
                		   $('#dataTable').datagrid('reload'); // reload the user data
							$.messager.alert('提示', data.msg, 'show');
                		   goback(1);
						} else {
							$.messager.alert('提示', data.msg, 'error');
						}
                   });
               }
           });
       }
   }
   
    //添加
	function addrow() {
		var url = '/dictype/addNew?type=' + type;
   		window.parent.wrapper.addTab('新增' + tabName ,url,'');

	}
	
	 //修改
	 function viewrow(dictId, dictTypeId){
		 var row = $('#dataTable').datagrid('getSelected');
		 var url = '/dictype/edit?dictTypeId='+row.dictTypeId+'&dictId='+row.dictId;
		 window.parent.wrapper.addTab('编辑' + tabName,url,'');
	   }

</script>
</body>
</html>