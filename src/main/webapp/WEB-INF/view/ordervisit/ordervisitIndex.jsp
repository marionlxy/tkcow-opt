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
		<form id="dataForm" method="post"  method="post" action="${rootPath}/orderVisit/downloadOrderVisit">
			<table style="width:100%; height:1%; overflow: hidden;" border="10" 
				cellpadding="0" cellspacing="0"  class="kTable" >
			 <tr>
				 <td class="kTableLabel lbl">联系人:</td>
				 <td><input name="visitName" class="easyui-textbox" ></td>
				 <td class="kTableLabel lbl">联系电话:</td>
				 <td><input name="visitTel" class="easyui-textbox" ></td> 
			 </tr>
			  <tr>
				 <td class="kTableLabel lbl" style="height:29px;line-height: 29px;">提交日期:</td>
				 <td><input name="startTime" class="easyui-datetimebox" style="width:100px" >-<input name="endTime" class="easyui-datetimebox" style="width:100px"></td>
				 <td class="kTableLabel lbl">来源:</td>
				 <td><input name="visitFrom" class="easyui-combobox"
								panelHeight="auto"
								panelHeight="auto" 
								url="${rootPath}/getDictCombox?dictType=customfrom" valueField="dictId" textField="dictName" editable="false" ></td> 
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
         <table id="dataTable" style="height:350px">
         </table> 
     </div>

</div>
<script type="text/javascript">
jQuery(function(){	  
	jQuery.ajaxSetup({
		cache : false
	}); 
    var dictLt = getDictList('customfrom');
   	//初始化列表
   	$('#dataTable').datagrid({
   		iconCls:'icon-tip',
   		singleSelect : true,
   		rownumbers:true,
   		pagination:true,
   		fitColumns:true,
   		url : '${rootPath}/orderVisit/list',
   		method : 'post',		
		idField : 'VISIT_ID',//此处根据实际情况进行填写
		columns:[[
							{field:'VISIT_NAME',title:'联系人',width:80},
							{field:'VISIT_TEL',title:'联系电话',width:80},
							{field:'VISIT_FROM',title:'来源',width:80,formatter:function(value, row, index){return getDictName(dictLt,"customfrom",value)}},
							{field:'VISIT_ID',title:'VISIT_ID',width:80,hidden:true},
							{field:'CREATED_TIME',title:'提交日期',width:80},
							{  
								field : 'operate',
								title : '操作',
								width : 100,
								formatter : function(value, row,index) {
									return "<a href='#' onclick=viewrow('"+row.VISIT_ID+"') style='margin-left:30px'>[查看]</a>"
									+"<a href='#' onclick=delerow('"+row.VISIT_ID+"') style='margin-left:40px'>[删除]</a>";
								}
							}
						//注：最后一行后面的逗号要去掉
		]],
   		toolbar : [{
			id : 'btnadd',
			text : '导出',
			iconCls : 'icon-add',
			handler : function() {
				download();
			}
		}],
		onLoadSuccess : function(data) {
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
		var queryParams = $('#dataTable').datagrid('options').queryParams;
		var fields = $('#dataForm').serializeArray(); //自动序列化表单元素为JSON对象
	
		$.each(fields, function(i, field) {
			queryParams[field.name] = field.value; //设置查询参数
	
		});
		var url = '${rootPath}/orderVisit/list';
		$('#dataTable').datagrid('reload',url); //设置好查询参数 reload 一下就可以了
	}
	
	//清空查询条件
	function clearForm() {
		$('#dataForm').form('clear');
	
	}
   
   //删除
   function delerow(visitId){
       var row = $('#dataTable').datagrid('getSelected');
       if (visitId){
           $.messager.confirm('提示','确定要删除行记录吗？',function(r){
               if (r){
                   $.post('${rootPath}/orderVisit/del',{visitId:visitId},function(data){                 	
                   	if(data.result == 'true' || data.result == true){
                   		$('#dataTable').datagrid('reload');    // reload the user data
   					}	else	{
   						$.messager.alert('提示',data.msg,'error');
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
	 function viewrow(visitId){
	   	    var row = $('#dataTable').datagrid('getSelected');
	        if (visitId){
	        	url = '/orderVisit/edit?visitId='+ visitId;
	    		window.parent.wrapper.addTab('预约参观详细',url,'');
	        }
	        else
	        {
	        	$.messager.alert('提示', "请选择你要更新的记录", 'info');
	 		return;
	        }
	   }
	  //导出excel
	   function download(){
		   $("form:first").submit();
	   }
</script>
</body>
</html>