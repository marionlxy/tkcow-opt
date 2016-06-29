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
<input  name="modId" id="modId" type="hidden" value="${modId}">
		<form id="dataForm" >
			<table style="width:100%; height:1%; overflow: hidden;" border="10" 
				cellpadding="0" cellspacing="0"  class="kTable" >
			 <tr>
				 <td class="kTableLabel lbl">logo名称:</td>
				 <td><input name=cooperLogoName class="easyui-textbox" ></td>
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
      <div data-options="region:'center',border:false"   style="overflow: hidden;">
         <table id="dataTable" style="height:435px">
         </table> 
     </div>
    <div id="divDialog" class="easyui-window"
		data-options="closed:true,closable:true,collapsible:false,minimizable:false,maximizable:false,modal:true,title:'logo详细信息维护'"
		style="width: 650px; height: 260px;">
		<iframe id="iframeDialogSelect" frameborder="0" scrolling="yes"	width="100%" height="98%"></iframe>
	</div>
</div>
<script type="text/javascript">
	var modId=$("#modId").val();
	jQuery(function(){	  
   	//初始化列表
   	$('#dataTable').datagrid({
   		singleSelect : true,
   		rownumbers:true,
   		pagination:true,
   		fitColumns:true,
   		url : '${rootPath}/logoModule/list?modId='+modId,
   		method : 'post',		
		idField : 'cooperLogoId',//此处根据实际情况进行填写
		columns:[[
							{field:'LOGO_SQUENCE',title:'序号',width:30,align:'center'},
							{field:'COOPER_LOGO_NAME',title:'logo名称',width:80},
							{field:'COOPER_LOGO_URL',title:'图片',width:80,
								formatter : function(value, row,
										index) {
									return "<img style=\"height: 30px;width: 30px;\" src=\"" +"<%=basePath%>"+'/'+ row.COOPER_LOGO_URL + "\"/>";
								}	
							},
							{field:'MOD_BYNAME',title:'模块别名',width:80},
							{field:'COOPER_LOGO_ID',title:'COOPER_LOGO_ID',width:80,hidden:true},
							{field:'CREATE_TIME',title:'创建时间',width:80},
							{  
								field : 'operate',
								title : '操作',
								width : 100,
								formatter : function(value, row,index) {
									return "<a href='#' onclick=viewrow('"+row.COOPER_LOGO_ID+"') style='margin-left:40px'>[编辑]</a>"
									+"<a href='#' onclick=delerow('"+row.COOPER_LOGO_ID+"') style='margin-left:45px'>[删除]</a>";
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
		var url = '${rootPath}/logoModule/list?modId='+modId;
		$('#dataTable').datagrid('reload',url); //设置好查询参数 reload 一下就可以了
	}
	
	//清空查询条件
	function clearForm() {
		$('#dataForm').form('clear');
	
	}
   
   //删除
   function delerow(cooperLogoId){
       var row = $('#dataTable').datagrid('getSelected');
       if (row){
           $.messager.confirm('提示','确定要删除行记录吗？',function(r){
               if (r){
                   $.post('${rootPath}/logoModule/deleteModule',{cooperLogoId:cooperLogoId},function(data){
                   	
                   	if(data.result == 'true' || data.result == true)
   					{
                   		$('#dataTable').datagrid('reload');    // reload the user data
   					}
   					else
   					{
   						$.messager.alert('提示',data.msg,'error');
   					}                    	
                   });
               }
           });
       }
   }
   
    //添加logo模板内容
	function addrow() {
    	var modId = $("#modId").val();
		var urls = '${rootPath}/logoModule/addLogoImge?modId='+modId;
		$('#iframeDialogSelect').attr("src", urls);
		//$('#divDialog').window('open');
		$('#divDialog').dialog({
			title : "添加Logo元素",
			width : 400,
			height : 300,
			href : urls,
			cache : false,
			closed : false,
			modal : true
		});
	}
	
	 //修改logo模板内容
	 function viewrow(cooperLogoId){
		 var modId = $("#modId").val();
		 var urls = '${rootPath}/logoModule/editLogoImge?cooperLogoId='+cooperLogoId+'&modId='+modId;
			$('#iframeDialogSelect').attr("src", urls);
			//$('#divDialog').window('open');
			$('#divDialog').dialog({
				title : "编辑Logo元素",
				width : 400,
				height : 300,
				href : urls,
				cache : false,
				closed : false,
				modal : true
			});
	   }
    //关闭弹出窗口
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