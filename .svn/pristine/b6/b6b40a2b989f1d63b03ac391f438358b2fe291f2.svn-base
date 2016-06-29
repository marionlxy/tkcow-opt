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
				 <td><input name="banImgName" class="easyui-textbox" ></td>
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
   		url : '${rootPath}/banner/list',
   		method : 'post',		
		idField : 'rowId',//此处根据实际情况进行填写
		columns:[[
							{field:'BAN_IMG_NUM',title:'排序号',width:80},
							{field:'BAN_IMG_NAME',title:'标题',width:80},
							{field:'BAN_IMG_URL',title:'图片',width:80},
							{field:'BAN_TYPE',title:'类别',width:80},
							{field:'BAN_IMG_ID',title:'BAN_IMG_ID',width:80,hidden:true},
							{field:'CREATED_TIME',title:'创建时间',width:80},
							{  
							
								field : 'operate',
								title : '操作',
								width : 100,
								formatter : function(value, row,index) {
									return "<a href='#' onclick=editrow('"+row.BAN_IMG_ID+"') style='margin-left:30px'>[修改]</a>"
									+"<a href='#' onclick=delerow('"+row.BAN_IMG_ID+"') style='margin-left:40px'>[删除]</a>";
								}
							}
						//注：最后一行后面的逗号要去掉
		]],
		toolbar : [{
			id : 'btnadd',
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				addrow();
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
		var url = '${rootPath}/banner/list';
		$('#dataTable').datagrid('reload',url); //设置好查询参数 reload 一下就可以了
	}
	
	//清空查询条件
	function clearForm() {
		$('#dataForm').form('clear');
	
	}

   //新增
   function addrow(){
   		var url = '/banner/edit';
   		window.parent.wrapper.addTab('banner编辑',url,'');
   }
   
   //修改
   function editrow(banImgId){    	
       var row = $('#dataTable').datagrid('getSelected');
	
       if (banImgId){
       	url = '${rootPath}/banner/edit?banImgId='+ banImgId;
		openWin(url);
       }
       else
       {
       	$.messager.alert('提示', "请选择你要更新的记录", 'info');
		return;
       }
   }
   
   //删除
   function delerow(banImgId){
       var row = $('#dataTable').datagrid('getSelected');
       if (banImgId){
           $.messager.confirm('提示','确定要删除行记录吗？',function(r){
               if (r){
                   $.post('${rootPath}/banner/del',{banImgId:banImgId},function(data){
                   	
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