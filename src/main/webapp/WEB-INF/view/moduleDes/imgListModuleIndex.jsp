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
	<div class="easyui-panel" title="查询条件" data-options="region:'north',height:124,border : false">
		<form id="dataForm" method="post">
			<table style="width:100%; height:1%; overflow: hidden;" border="10" 
				cellpadding="0" cellspacing="0"  class="kTable" >
			 <tr>
				 <td class="kTableLabel lbl">标题:</td>
				 <td><input name="modName" class="easyui-textbox" ></td>
			 </tr>
			  <tr>
				 <td class="kTableLabel lbl" style="height:29px;line-height: 29px;">创建时间:</td>
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
				<input type="hidden" name="modParentId" id="modParentId"
				value='${modParentId }'>		
      <div data-options="region:'center',border:false"   style="overflow: hidden;">
         <table id="dataTable" style="height:350px">
         </table> 
     	</div>

	</div>
<script type="text/javascript">
		
	jQuery(function(){	  
   	//初始化列表
   	$('#dataTable').datagrid({
   		iconCls:'icon-tip',
   		singleSelect : true,
   		rownumbers:true,
   		pagination:true,
   		fitColumns:true,
   		url : '${rootPath}/moduleDes/getImgListModuleList?modId=${modId}',
   		method : 'post',		
		idField : 'rowId',//此处根据实际情况进行填写
		columns:[[
							{field:'MOD_SQUENCE',title:'排序号',width:40,align:'center'},
							{field:'MOD_NAME',title:'标题',width:80},
							{field:'MOD_IMG',title:'图片',width:180,
								formatter : function(value, row,
										index) {
									var imgurl = '<%=basePath%>' + '/' + row.MOD_IMG;
									return "<img style='height: 30px;width: 30px;' src='" + imgurl + "'>";
								}
							},
							{field:'CREATED_TIME',title:'创建时间',width:80},
							{
								field : 'operate',
								title : '操作',
								width : 100,
								formatter : function(value, row,index) {
									return "<a href='#' onclick=editrow('"+row.MOD_ID+"') style='margin-left:30px'>[编辑]</a>"
									+"<a href='#' onclick=delerow('"+row.MOD_ID+"') style='margin-left:40px'>[删除]</a>";
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
   
   //表格查询
	function searchInfo() {
		//查询参数直接添加在queryParams中
		var queryParams = $('#dataTable').datagrid('options').queryParams;
		var fields = $('#dataForm').serializeArray(); //自动序列化表单元素为JSON对象
	
		$.each(fields, function(i, field) {
			queryParams[field.name] = field.value; //设置查询参数
	
		});
		var url = '${rootPath}/moduleDes/getImgListModuleList?modId=${modId}';
		$('#dataTable').datagrid('reload',url); //设置好查询参数 reload 一下就可以了
	}
	
	//清空查询条件
	function clearForm() {
		$('#dataForm').form('clear');
	
	}

   //新增
   function addrow(){
	   var modName=escape(encodeURIComponent('${modName}'));
   		var url = '/moduleDes/imgListElementAdd?modId=${modId}&reqMenuId=${reqMenuId}&modLevel=${modLevel}&modName='+modName;
   		window.parent.wrapper.addTab('${modName}·添加图片元素',url,'');
   }
   
   //修改
   function editrow(modIdAndContentId){
	   var modName=escape(encodeURIComponent('${modName}'));
       if (modIdAndContentId){
       	url = '/moduleDes/imgListElementEdit?modId='+modIdAndContentId+'&reqMenuId=${reqMenuId}&modName='+modName;
   		window.parent.wrapper.addTab('${modName}·编辑图片元素',url,'');
       }
       else
       {
       	$.messager.alert('提示', "请选择你要更新的记录", 'info');
		return;
       }
   }
   
   //删除
   function delerow(modId){
	   //alert(modId)
       var row = $('#dataTable').datagrid('getSelected');
       if (modId){
           $.messager.confirm('提示','确定要删除行记录吗？',function(r){
               if (r){
                   $.post('${rootPath}/moduleDes/imgListElementDelete',{modId:modId},function(data){
                   	
                   	if(data.code == '0')
   					{
                   		$('#dataTable').datagrid('reload');    // reload the user data
   					}
   					else
   					{
   						$.messager.alert('提示',"删除失败!",'error');
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
		if(flag==1)
		{
			searchInfo();
		}
		$("#divDialog").window('close');
	}
	
		
</script>
</body>
</html>